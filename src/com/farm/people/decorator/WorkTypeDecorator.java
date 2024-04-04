package com.farm.people.decorator;

import com.farm.farm.Farm;
import com.farm.people.worker.Worker;

public abstract class WorkTypeDecorator extends Worker {
    protected Worker worker;

    public WorkTypeDecorator(Worker worker) {
        this.worker = worker;
    }

    public abstract void work(Farm mine);
}
