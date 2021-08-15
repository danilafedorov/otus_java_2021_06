package ru.otus.test.framework.model;

import java.util.Objects;

/**
 * Результат выполнения конкретного тестового класса (всех его методов)
 */
public class Result {
    private final String methodName;
    private final Status status;
    private final String exceptionMessage;

    /**
     * Успешный тест
     * @param methodName имя тестируемого метода
     */
    public Result(String methodName) {
        this.methodName = methodName;
        this.status = Status.OK;
        this.exceptionMessage = null;
    }

    /**
     * Тест провален
     * @param methodName имя тестируемого метода
     * @param exceptionMessage сообщение из Эксэпшина
     */
    public Result(String methodName, String exceptionMessage) {
        this.methodName = methodName;
        this.status = Status.ERROR;
        this.exceptionMessage = exceptionMessage;
    }

    public String getMethodName() {
        return methodName;
    }

    public Status getStatus() {
        return status;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    @Override
    public String toString() {
        return  "methodName = " + methodName +
                ", status = " + status +
                (exceptionMessage == null ? "" : ", exceptionMessage = " + exceptionMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(methodName, result.methodName) && status == result.status
                && Objects.equals(exceptionMessage, result.exceptionMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodName, status, exceptionMessage);
    }
}
