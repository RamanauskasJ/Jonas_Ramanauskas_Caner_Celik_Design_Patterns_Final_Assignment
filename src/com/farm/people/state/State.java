package com.farm.people.state;

import com.farm.people.worker.Worker;

public abstract class State {
    public abstract void nextState(Worker worker);

    public abstract void executeState(Worker worker);

    public abstract String toString();
}
