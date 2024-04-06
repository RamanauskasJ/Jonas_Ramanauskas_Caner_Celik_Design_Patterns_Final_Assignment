package com.farm;

import com.farm.farm.*;
import com.farm.people.decorator.LoadTractor;
import com.farm.people.decorator.UnloadTractor;
import com.farm.people.worker.Farmer;
import com.farm.people.worker.TractorDriver;
import com.farm.people.worker.Worker;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class App {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final ArrayList<Farm> farms;

    public App(ArrayList<Farm> farms) {
        this.farms = farms;
    }

    public void home() {
        System.out.println(
                """
                           Greetings Farm Manager. What would you like to do today?
                            1. Create a new farm.
                            2. Manage an existing farm.
                            3. Observe crop storage.
                        """
        );
    }

    public void createFarm(Farm farm) {
        this.farms.add(farm);
        home();
    }

    public void manageFarm(Farm farm) throws Exception {
        if (!farms.contains(farm)) {
            throw new Exception("Farm doesn't exist");
        }

        System.out.println("Farm Status:");
        System.out.println("Number of workers: " + farm.getWorkers().size());
        System.out.println("Unloaded crops: " + farm.getUnloadedCrops().size());
        System.out.println("Crops in tractor: " + farm.getTractor().getCrops().size());
        System.out.println("Crops in storage: " + CropStorage.getCrops().size());
        System.out.println("Is tractor at crop storage: " + farm.getTractor().isAtCropStorage());

        System.out.println(
                """
                        What action would you like to perform?
                        1. 'Hire' new workers
                        2. 'Motivate' workers
                        3. Move workers to next state
                        4. Manage specific worker
                            
                        Press `q` to cancel
                        """
        );

        try {
            String choice = reader.readLine();

            switch (choice) {
                case "1" -> purchaseWorker(farm);
                case "2" -> motivateWorkers(farm);
                case "3" -> moveShifts(farm);
                case "4" -> manageWorkers(farm);
                case "q" -> home();
                default -> manageFarm(farm);
            }
        } catch (IOException e) {
            manageFarm(farm);
        }

        manageFarm(farm);
    }

    public void manageWorker(Worker worker, Farm farm) {
        System.out.println("Worker Status: " + worker.getState().toString());
        System.out.println("Worker type: " + worker.getClass());
        System.out.println("Worker Hunger: " + worker.getHungerMeter());
        System.out.println("Worker Sanity: " + worker.getSanityMeter());
        System.out.println("Worker Times Worked: " + worker.getTimesWorked());

        System.out.println(
                """
                        What action would you like to perform?
                        1. Add extra task
                        2. 'Fire' worker
                            
                        Press `q` to cancel
                        """
        );

        try {
            String choice = reader.readLine();

            switch (choice) {
                case "1" -> addWorkerTask(worker, farm);
                case "2" -> fireWorker(worker, farm);
                case "q" -> home();
                default -> manageWorker(worker, farm);
            }
        } catch (IOException e) {
            manageWorker(worker, farm);
        }
    }

    public void createFarms() {
        System.out.println(
                """
                            What kind of farm would you like to create?
                            1. Corn Field
                            2. Oat Field
                            3. Wheat Field
                            
                            Press `q` to cancel
                        """
        );

        try {
            String choice = reader.readLine();

            switch (choice) {
                case "1" -> createFarm(new CornField());
                case "2" -> createFarm(new OatField());
                case "3" -> createFarm(new WheatField());
                case "q" -> home();
                default -> createFarms();
            }
        } catch (IOException e) {
            createFarms();
        }
    }

    public void manageFarms() {
        if (farms.size() < 1) {
            home();
        }

        System.out.println("Which farm would you like to manage?");
        for (int i = 0; i < farms.size(); i++) {
            System.out.println((i + 1) + ". Farm number " + (i + 1));
        }

        try {
            int choice = Integer.parseInt(reader.readLine());

            manageFarm(farms.get(choice - 1));
        } catch (Exception e) {
            manageFarms();
        }
    }

    public void manageWorkers(Farm farm) {
        if (farm.getWorkers().size() < 1) {
            try {
                manageFarm(farm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Which worker you like to manage?");
        for (int i = 0; i < farm.getWorkers().size(); i++) {
            System.out.println((i + 1) + ". " + farm.getWorkers().get(i).toString() + " number " + (i + 1));
        }

        try {
            int choice = Integer.parseInt(reader.readLine());

            manageWorker(farm.getWorkers().get(choice - 1), farm);
        } catch (Exception e) {
            try {
                manageFarm(farm);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * Create a method for purchasing workers for a specific farm
     * @param farm
     */
    public void purchaseWorker(Farm farm){
    }

    /**
     * Create a method for Motivate workers for a specific farm
     * @param farm
     */
    public void motivateWorkers(Farm farm){

    }

    /**
     * Create a method for moving workers to next state
     * @param farm
     */
    public void moveShifts(Farm farm){

    }

    public void addWorkerTask(Worker worker, Farm farm) {
        final var worker1 = worker;
        farm.getWorkers().forEach(farmWorker -> {
            if (farmWorker.equals(worker1)) {
                farm.getWorkers().remove(farmWorker);

                if (farmWorker instanceof Farmer || farmWorker instanceof LoadTractor) {
                    farm.addWorker(new LoadTractor(worker1));
                } else if (farmWorker instanceof TractorDriver) {
                    farm.addWorker(new UnloadTractor(worker1));
                }
            }
        });
    }

    public void fireWorker(Worker worker, Farm farm) {
        farm.fireWorker(worker);
    }
}
