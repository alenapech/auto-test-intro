package org.max.demo.pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.demo.pattern.proxy.ClassExample;
import org.max.demo.pattern.proxy.IClass;
import org.max.demo.pattern.proxy.ProxyObject;

/**
 * Тест для демонстрации работы паттерна Прокси
 */
public class ProxyTest {

    @Test
    void simpleTest () {
        //given
        IClass classExample = new ClassExample();
        //when
        //then
        Assertions.assertTrue(classExample.sendPostRequest(""));
        Assertions.assertTrue(classExample.sendPutRequest("",10));
        Assertions.assertTrue(classExample.sendDeleteRequest(10));
        Assertions.assertEquals("Response", classExample.sendGetRequest(10));
    }

    @Test
    void proxyTest () {
        //given
        IClass proxyObject = new ProxyObject();
        //when
        //then
        Assertions.assertTrue(proxyObject.sendPostRequest(""));
        Assertions.assertTrue(proxyObject.sendPutRequest("",10));
        Assertions.assertTrue(proxyObject.sendDeleteRequest(10));
        Assertions.assertEquals("Response", proxyObject.sendGetRequest(10));
    }
}
