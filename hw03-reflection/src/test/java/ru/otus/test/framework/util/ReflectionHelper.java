package ru.otus.test.framework.util;

import java.lang.reflect.Method;
import java.util.Arrays;


public class ReflectionHelper {



    private ReflectionHelper() {}

    public static void setFieldValue(Object object, String name, Object value) {
        try {
            var field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object callMethod(Method method, Object object) {
        try {
            method.setAccessible(true);
            return method.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static Class<?> getClassByName(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
