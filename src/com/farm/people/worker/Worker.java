package com.farm.people.worker;

import com.farm.farm.Farm;
import com.farm.people.Manager;
import com.farm.people.Observer;
import com.farm.people.state.State;
import com.farm.people.state.Working;

public abstract class Worker implements Observer {
    private int timesWorked;
    private int hungerMeter;
    private int sanityMeter;
    private State state;

    public Worker() {
        this.timesWorked = 0;
        this.hungerMeter = 100;
        this.sanityMeter = 100;
        this.state = new Working();
        Manager.GetManager().registerObserver(this);
    }

    public abstract String toString();
    public abstract void work(Farm farm);

    public int getTimesWorked() { return timesWorked; }
    public int getHungerMeter() { return hungerMeter; }
    public int getSanityMeter() { return sanityMeter; }

    public void increaseTimesWorked() { this.timesWorked += 1; }
    public void decreaseHungerMeter(int hungerMeter) { this.hungerMeter -= hungerMeter; }
    public void increaseHungerMeter(int hungerMeter) {
        this.hungerMeter += hungerMeter;
        if (this.hungerMeter > 100) this.hungerMeter = 100;
    }
    public void decreaseSanityMeter(int sanityMeter) { this.sanityMeter -= sanityMeter; }
    public void increaseSanityMeter(int sanityMeter) {
        this.sanityMeter += sanityMeter;
        if (this.sanityMeter > 100) this.sanityMeter = 100;
    }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public final void doTask() { this.state.executeState(this); }
    public final void update() { this.state.nextState(this); }

    public boolean isWorkerOverWorked() { return this.getHungerMeter() <= 0 || this.sanityMeter <= 0 || this.timesWorked >= 5; }

    public void performWork(Farm farm) {
        if (this.state instanceof Working) {
            this.work(farm);
            this.increaseTimesWorked();
            this.decreaseHungerMeter(10);
            this.decreaseSanityMeter(5);
        }
    }
}
