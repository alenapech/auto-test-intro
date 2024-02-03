package org.max.seminar;

/**
 * Интерфейс поставщика
 */
public interface IProducer {

    public boolean subscription(IConsumer consumer);

    public boolean cancel(IConsumer consumer);
}
