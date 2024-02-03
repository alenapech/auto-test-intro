package org.max.demo.pattern.adapter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Интерфейс адаптера
 */
public interface IAdapter {

    public void open();

    public Integer row();

    public String find(String searchString);

    public String author();

    public Integer size();

    public Date createDate();
}
