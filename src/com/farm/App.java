package com.farm;

import com.farm.farm.Farm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
}
