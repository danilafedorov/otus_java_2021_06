package ru.otus;

import com.google.common.collect.Lists;

import java.util.Collection;

public class HelloOtus {

    public static void main(String[] args) {
        Collection<String> testCollection = Lists.newArrayList("Test", "Test2", "Test3");
        System.out.println("Hello world! " + testCollection);
    }

}
