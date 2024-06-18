// Idle.java
package com.farm.people.state;

import com.farm.people.worker.Worker;

public class Idle implements State {
    @Override
    public void executeState(Worker worker) {
        System.out.println(worker + " is idle.");
    }

    @Override
    public void nextState(Worker worker) {
        worker.setState(new Working());
    }

    @Override
    public String toString() {
        return "Idle";
    }
}
