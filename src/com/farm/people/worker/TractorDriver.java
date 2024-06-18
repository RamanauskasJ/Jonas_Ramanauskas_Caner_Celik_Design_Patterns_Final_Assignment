package com.farm.people.worker;

import com.farm.farm.Farm;

public class TractorDriver extends Worker {

    public TractorDriver() {
        super();
    }

    @Override
    public String toString() {
        return "TractorDriver";
    }

    @Override
    public void work(Farm farm) {
        // Implementation for work
    }

    @Override
    public void Update() {
        this.doTask();
    }
}
