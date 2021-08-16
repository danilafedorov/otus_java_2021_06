package ru.otus.test.framework.cases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.test.framework.SomeService;
import ru.otus.test.framework.annotation.After;
import ru.otus.test.framework.annotation.Before;
import ru.otus.test.framework.annotation.Test;

public class SomeServiceTest {

    public static Logger LOG = LoggerFactory.getLogger(SomeServiceTest.class);

    private final SomeService someService = new SomeService();

    @Before
    public void init() {
        LOG.info("call init");
    }

    @Before
    public void init2() {
        LOG.info("call init 2");
    }

    @Test
    public void sumTest() {
        Integer c = someService.sum(1, 2);
        if (c != 3) {
            throw new RuntimeException(" 1 plus 2 not 3");
        }
    }

    @Test
    public void divideFailTest() {
        Integer result = someService.divide(2, 0);
    }

    @After
    public void close() {
        LOG.info("call close");
    }

    @After
    public void close2() {
        LOG.info("call close2");
    }


}
