package ru.javawebinar.topjava.dao;

import java.util.concurrent.atomic.AtomicInteger;

public class CountHolder {
    public static AtomicInteger count = new AtomicInteger();
    public static Integer getCount(){
        return count.addAndGet(1);
    }
    
}
