package com.farm.people;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    List<Observer> observers = new ArrayList<>();

    default void registerObserver(Observer observer) {
        observers.add(observer);
    }

}
