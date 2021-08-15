package ru.otus.test.framework;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.test.framework.model.Result;
import ru.otus.test.framework.model.Status;
import ru.otus.test.framework.util.ReflectionHelper;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String TEST_CLASS_PATH = "ru.otus.test.framework.cases.";

    public static Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * Точка входа для запуска тестов. Предполагается что все тесты будут размещенны в пакете cases
     * @param args имена классов тестов, например: SomeServiceTest SomeServiceFailAfterTest
     */
    public static void main(String[] args) {

        TestExecutor testExecutor = new TestExecutor();

        Collection<Result> testResults = Arrays.stream(args)
                           .map(clazz -> TEST_CLASS_PATH + clazz)
                           .map(ReflectionHelper::getClassByName)
                           .map(testExecutor::runTest)
                           .flatMap(Collection::stream)
                           .collect(Collectors.toList());

        long success = testResults.stream()
                                  .filter(result -> result.getStatus() == Status.OK)
                                  .count();

        long errors = testResults.size() - success;

        LOG.info("Test results: ");
        testResults.forEach(result -> LOG.info(result.toString()));
        LOG.info("Summary: success {}, error {}", success, errors);

    }

}
