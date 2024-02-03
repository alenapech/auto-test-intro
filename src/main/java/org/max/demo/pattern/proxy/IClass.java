package org.max.demo.pattern.proxy;

/**
 * Интерфейс класса который мы планируем проксировать
 */
public interface IClass {

    public String sendGetRequest(Integer id);

    public Boolean sendPostRequest(String request);

    public Boolean sendPutRequest(String request, Integer id);

    public Boolean sendDeleteRequest(Integer id);


}
