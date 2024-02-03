package org.max.demo.pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.demo.pattern.adapter.JsonAdapter;
import org.max.demo.pattern.adapter.XMLAdapter;

import java.util.Date;

/**
 * Тест для демонстрации работы паттерна Адаптер
 */
public class AdapterTest {

    @Test
    void jsonTest() {
        //given
        JsonAdapter adapter = new JsonAdapter();
        //when
        //then
        Assertions.assertNotNull(adapter);
    }

    @Test
    void xmlTest() {
        //given
        XMLAdapter adapter = new XMLAdapter();
        //when
        //then
        Assertions.assertNotNull(adapter);
    }
}
