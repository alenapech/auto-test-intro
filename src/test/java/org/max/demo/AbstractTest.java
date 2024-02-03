package org.max.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/*
    Абстрактный тест, для наследования логики и этапов ЖЦ
 */
public abstract class AbstractTest {

    @BeforeAll
    static void init() {
    }

    @BeforeEach
    void beforeEach() {
    }

    @AfterEach
    void afterEach() {
    }

    @AfterAll
    static void exit() {
    }
}
