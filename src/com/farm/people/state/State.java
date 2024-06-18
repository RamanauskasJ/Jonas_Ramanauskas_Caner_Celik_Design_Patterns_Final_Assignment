package com.farm.people.state;

import com.farm.people.worker.Worker;

public interface State {
    void executeState(Worker worker);
    void nextState(Worker worker);
}

