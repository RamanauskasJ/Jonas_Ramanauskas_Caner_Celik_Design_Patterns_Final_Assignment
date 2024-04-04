package com.farm.people.worker;

import com.farm.farm.Farm;
import com.farm.people.Observer;
import com.farm.people.state.State;

public abstract class Worker implements Observer {
    private int timesWorked;
    private int hungerMeter;
    private int sanityMeter;
    private State state;

    public Worker() {
        this.timesWorked = 0;
        this.hungerMeter = 100;
        this.sanityMeter = 100;
    }

    public abstract String toString();

    public abstract void work(Farm mine);

    public int getTimesWorked() {
        return timesWorked;
    }

    public int getHungerMeter() {
        return hungerMeter;
    }

    public int getSanityMeter() {
        return sanityMeter;
    }

    public void increaseTimesWorked() {
        this.timesWorked += 1;
    }

    public void resetTimesWorked() {
        this.timesWorked = 0;
    }

    public void decreaseHungerMeter(int hungerMeter) {
        this.hungerMeter -= hungerMeter;
    }

    public void increaseHungerMeter(int hungerMeter) {
        this.hungerMeter += hungerMeter;
        if (this.hungerMeter > 100) this.hungerMeter = 100;
    }

    public void decreaseSanityMeter(int sanityMeter) {
        this.sanityMeter -= sanityMeter;
    }

    public void increaseSanityMeter(int sanityMeter) {
        this.sanityMeter += sanityMeter;
        if (this.sanityMeter > 100) this.sanityMeter = 100;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isWorkerOverWorked() {
        return this.getHungerMeter() <= 0 || this.sanityMeter <= 0 || this.timesWorked >= 5;
    }
}
