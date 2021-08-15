package ru.otus.test.framework.cases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.test.framework.SomeService;
import ru.otus.test.framework.annotation.After;
import ru.otus.test.framework.annotation.Before;
import ru.otus.test.framework.annotation.Test;

public class SomeServiceFailAfterTest {

    public static Logger LOG = LoggerFactory.getLogger(SomeServiceTest.class);

    private final SomeService someService = new SomeService();

    @Before
    public void init() {
        LOG.info("call init");
    }

    @Test
    public void sumTest() {
        Integer result =  someService.sum(3, 4);
        if (result != 7) {
            throw new RuntimeException("3 plus 4 not 7");
        }
    }

    @Test
    public void divideTest() {
        Integer result =  someService.divide(3, 4);
    }

    @After
    public void close() {
        throw new RuntimeException("After error");
    }
}
