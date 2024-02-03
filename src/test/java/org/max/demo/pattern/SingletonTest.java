package org.max.demo.pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.demo.pattern.singleton.*;

/**
 * Тест для демонстрации работы паттерна Одиночка
 */
public class SingletonTest {

    //Простая инициализация
    @Test
    void singletonSimpleTest () {
        //given
        SimpleSingleton singleton = SimpleSingleton.getInstance();
        SimpleSingleton singleton2 = SimpleSingleton.getInstance();
        //when
        singleton2.setUrl("https://newurl");
        //then
        Assertions.assertSame(singleton, singleton2);
        Assertions.assertEquals("https://newurl", singleton.getUrl());
    }

    //Ленивый метод инициализации
    @Test
    void singletonLazyTest () {
        //given
        LazySingleton singleton = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();
        //when
        singleton2.setUrl("https://newurl");
        //then
        Assertions.assertSame(singleton, singleton2);
        Assertions.assertEquals("https://newurl", singleton.getUrl());
    }

    //Метод инициализации статического блока
    @Test
    void staticBlockSingletonTest () {
        //given
        StaticBlockSingleton singleton = StaticBlockSingleton.getInstance();
        StaticBlockSingleton singleton2 = StaticBlockSingleton.getInstance();
        //when
        singleton2.setUrl("https://newurl");
        //then
        Assertions.assertSame(singleton, singleton2);
        Assertions.assertEquals("https://newurl", singleton.getUrl());
    }

    //Метод отложенной загрузки
    @Test
    void lazyLoadSingletonTest () {
        //given
        LazyLoadSingleton singleton = LazyLoadSingleton.getInstance();
        LazyLoadSingleton singleton2 = LazyLoadSingleton.getInstance();
        //when
        singleton2.setUrl("https://newurl");
        //then
        Assertions.assertSame(singleton, singleton2);
        Assertions.assertEquals("https://newurl", singleton.getUrl());
    }

    //Потокобезопасный метод
    @Test
    void threadSafeSingletonTest () {
        //given
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance();
        //when
        singleton2.setUrl("https://newurl");
        //then
        Assertions.assertSame(singleton, singleton2);
        Assertions.assertEquals("https://newurl", singleton.getUrl());
    }

    //Enum
    @Test
    void enumTest() {
        //given
        //when
        //then
        Assertions.assertEquals("http://", Connection.URL.getValue());
    }
}
