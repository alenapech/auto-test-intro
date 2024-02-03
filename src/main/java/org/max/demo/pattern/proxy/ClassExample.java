package org.max.demo.pattern.proxy;

/**
 * Реализация прокси класса
 */
public class ClassExample implements IClass{
    @Override
    public String sendGetRequest(Integer id) {
        return "Response";
    }

    @Override
    public Boolean sendPostRequest(String request) {
        return true;
    }

    @Override
    public Boolean sendPutRequest(String request, Integer id) {
        return true;
    }

    @Override
    public Boolean sendDeleteRequest(Integer id) {
        return true;
    }
}
