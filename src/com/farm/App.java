package com.farm;

import com.farm.farm.CornField;
import com.farm.farm.Farm;
import com.farm.farm.OatField;
import com.farm.farm.WheatField;
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
}
