package ru.otus.test.framework;

import ru.otus.test.framework.annotation.After;
import ru.otus.test.framework.annotation.Before;
import ru.otus.test.framework.annotation.Test;
import ru.otus.test.framework.model.Result;
import ru.otus.test.framework.model.Status;
import ru.otus.test.framework.util.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class TestExecutor {

    private static final String BEFORE_METHOD_KEY = "BEFORE";
    private static final String AFTER_METHOD_KEY = "AFTER";
    private static final String TEST_METHOD = "TEST_METHOD";

    private static final String DOT = ".";

    public Collection<Result> runTest(Class<?> clazz) {

        Map<String, Collection<Method>> methods = collectAllMethodFromClass(clazz.getMethods());

        return methods.get(TEST_METHOD).stream()
                .map(method -> runTest(method, methods.get(BEFORE_METHOD_KEY),
                                                     methods.get(AFTER_METHOD_KEY), clazz))
                .collect(Collectors.toList());

    }

    /**
     * Получаем мапу виду - тип метода (Before, Test, After) - лист с методомами
     * @param methods - все методы класса
     * @return все методы рассортированные по типам
     */
    private Map<String, Collection<Method>> collectAllMethodFromClass(Method[] methods) {
        Map<String, Collection<Method>> result = new HashMap<>();
        Arrays.stream(methods).forEach(method -> {

            if (method.isAnnotationPresent(Before.class)) {
                Collection<Method> testMethods = result.computeIfAbsent(BEFORE_METHOD_KEY, k -> new ArrayList<>());
                testMethods.add(method);
            }

            if (method.isAnnotationPresent(Test.class)) {
                Collection<Method> testMethods = result.computeIfAbsent(TEST_METHOD, k -> new ArrayList<>());
                testMethods.add(method);
            }

            if (method.isAnnotationPresent(After.class)) {
                Collection<Method> testMethods = result.computeIfAbsent(AFTER_METHOD_KEY, k -> new ArrayList<>());
                testMethods.add(method);
            }
        });

        return result;
    }

    /**
     * Выполняет тестовый метод
     *
     * упали при выполнении Before - вернет Result с ошибкой (в каком методе произошла ошибка и какая ошибка)
     * упали при выполнении After - вернет Result с ошибкой (в каком методе произошла ошибка и какая ошибка)
     * если в Before и After не было ошибки - вернет результат (ошибочный или успешный) выполнения тестового метода
     *
     * @param method тестовый метод
     * @param before список методов с аннотацией Before в этом классе
     * @param after список методов с аннотацией After в этом классе
     * @param clazz тестовый класс
     *
     * @return результат выполнения
     */
    private Result runTest(Method method, Collection<Method> before, Collection<Method> after, Class<?> clazz) {

        Object object = ReflectionHelper.instantiate(clazz);

        Optional<Result> beforeResult = runWhileDontGetError(before, object);

        if (beforeResult.isPresent()) {
            return beforeResult.get();
        }

        Result resultTest = runOne(method, object);

        Optional<Result> afterResult = runWhileDontGetError(after, object);

        return afterResult.orElse(resultTest);

    }

    /**
     * Запускаем по очереди методы. Если какой либо из методов завершается с ошибкой
     * прерываем выполнение и возвращаем результат.
     * Используется для запуска методов Before, After
     *
     * @param methods список с методами
     * @param object объект, методы которого мы вызываем
     *
     * @return результат выполнения. Пустой Optional - все прошло без ошибок.
     *         В обратном случае содержит ошибочный результат
     */
    private Optional<Result> runWhileDontGetError(Collection<Method> methods, Object object) {
        for (Method execMethod : methods) {
            Result result = runOne(execMethod, object);
            if (result.getStatus() == Status.ERROR) {
                return Optional.of(result);
            }
        }

        return Optional.empty();
    }

    /**
     * Выполнение одного метода
     * @param method метод
     * @param object объект метод которого мы вызываем
     * @return результат выполнения
     */
    private Result runOne(Method method, Object object) {

        String methodName = method.getDeclaringClass().getSimpleName() + DOT + method.getName();

        try {
            ReflectionHelper.callMethod(method, object);
            return new Result(methodName);

        } catch (Exception ex) {
            Throwable cause = ex.getCause() == null ? ex : ex.getCause().getCause();
            return new Result(methodName, cause.getMessage());
        }
    }

}
