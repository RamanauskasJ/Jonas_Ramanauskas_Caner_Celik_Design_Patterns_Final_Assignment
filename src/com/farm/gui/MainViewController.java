package com.farm.gui;

import com.farm.farm.ConcreteFarm;
import com.farm.farm.CropStorage;
import com.farm.farm.Farm;
import com.farm.people.worker.Farmer;
import com.farm.people.worker.TractorDriver;
import com.farm.people.worker.Worker;
import com.farm.crop.CornCrop;
import com.farm.crop.Crop;
import com.farm.crop.OatCrop;
import com.farm.crop.WheatCrop;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MainViewController {
    private final BorderPane root;
    private final List<Farm> farms;

    public MainViewController(BorderPane root) {
        this.root = root;
        this.farms = new ArrayList<>();
    }

    public void initialize() {
        VBox leftMenu = new VBox();
        Button createFarmButton = new Button("Create Farm");
        Button manageFarmButton = new Button("Manage Farm");
        Button manageWorkersButton = new Button("Manage Workers");
        Button manageTractorButton = new Button("Manage Tractor");
        Button observeStorageButton = new Button("Observe Crop Storage");

        Text statusText = new Text();

        createFarmButton.setOnAction(event -> createFarm());
        manageFarmButton.setOnAction(event -> manageFarm());
        manageWorkersButton.setOnAction(event -> manageWorkers());
        manageTractorButton.setOnAction(event -> manageTractor());
        observeStorageButton.setOnAction(event -> observeCropStorage());

        // Enable worker creation only if a farm exists
        manageWorkersButton.setDisable(farms.isEmpty());

        leftMenu.getChildren().addAll(createFarmButton, manageFarmButton, manageWorkersButton, manageTractorButton, observeStorageButton);

        root.setLeft(leftMenu);
        root.setCenter(statusText);
    }

    private void createFarm() {
        TextField farmNameField = new TextField();
        Button addFarmButton = new Button("Add Farm");
        VBox createFarmBox = new VBox(new Label("Enter Farm Name:"), farmNameField, addFarmButton);
        root.setCenter(createFarmBox);

        addFarmButton.setOnAction(event -> {
            String farmName = farmNameField.getText();
            Farm newFarm = new ConcreteFarm(10); // Use the concrete subclass with max workers
            farms.add(newFarm);
            initialize(); // Reset the view
        });
    }

    private void manageFarm() {
        ListView<Farm> farmListView = new ListView<>();
        farmListView.getItems().addAll(farms);
        farmListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button selectFarmButton = new Button("Select Farm");
        VBox manageFarmBox = new VBox(new Label("Select a Farm to Manage:"), farmListView, selectFarmButton);
        root.setCenter(manageFarmBox);

        selectFarmButton.setOnAction(event -> {
            Farm selectedFarm = farmListView.getSelectionModel().getSelectedItem();
            if (selectedFarm != null) {
                displayFarmDetails(selectedFarm);
            }
        });
    }

    private void displayFarmDetails(Farm farm) {
        Label farmDetails = new Label("Farm Details:\n" +
                "Number of workers: " + farm.getWorkers().size() + "\n" +
                "Unloaded crops: " + farm.getUnloadedCrops().size() + "\n" +
                "Crops in tractor: " + farm.getCropsInTractor().size());
        Button farmWorkButton = new Button("Perform Farm Work");
        Button backButton = new Button("Back");

        VBox farmDetailsBox = new VBox(farmDetails, farmWorkButton, backButton);
        root.setCenter(farmDetailsBox);

        farmWorkButton.setOnAction(event -> {
            farm.getWorkers().forEach(worker -> worker.performWork(farm));
            farmDetails.setText("Farm Details:\n" +
                    "Number of workers: " + farm.getWorkers().size() + "\n" +
                    "Unloaded crops: " + farm.getUnloadedCrops().size() + "\n" +
                    "Crops in tractor: " + farm.getCropsInTractor().size());
        });

        backButton.setOnAction(event -> manageFarm());
    }

    private void manageWorkers() {
        if (farms.isEmpty()) {
            return;
        }

        ListView<Farm> farmListView = new ListView<>();
        farmListView.getItems().addAll(farms);
        farmListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button selectFarmButton = new Button("Select Farm");
        VBox manageWorkersBox = new VBox(new Label("Select a Farm to Manage Workers:"), farmListView, selectFarmButton);
        root.setCenter(manageWorkersBox);

        selectFarmButton.setOnAction(event -> {
            Farm selectedFarm = farmListView.getSelectionModel().getSelectedItem();
            if (selectedFarm != null) {
                displayWorkerManagement(selectedFarm);
            }
        });
    }

    private void displayWorkerManagement(Farm farm) {
        Label farmDetails = new Label("Farm Details:\n" +
                "Number of workers: " + farm.getWorkers().size());
        ListView<Worker> workerListView = new ListView<>();
        workerListView.getItems().addAll(farm.getWorkers());
        workerListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Button selectWorkerButton = new Button("Select Worker");
        Button createWorkerButton = new Button("Create Worker");
        VBox workerDetailsBox = new VBox(farmDetails, workerListView, selectWorkerButton, createWorkerButton);
        root.setCenter(workerDetailsBox);

        createWorkerButton.setOnAction(event -> {
            createWorker(farm);
        });

        selectWorkerButton.setOnAction(event -> {
            Worker selectedWorker = workerListView.getSelectionModel().getSelectedItem();
            if (selectedWorker != null) {
                displayWorkerDetails(selectedWorker);
            }
        });

        farm.getWorkers().forEach(worker -> {
            Label workerDetails = new Label(worker.toString() + " - Times Worked: " + worker.getTimesWorked() +
                    ", Hunger Meter: " + worker.getHungerMeter() +
                    ", Sanity Meter: " + worker.getSanityMeter());
            workerDetailsBox.getChildren().add(workerDetails);
        });

        Button backButton = new Button("Back");
        workerDetailsBox.getChildren().add(backButton);
        backButton.setOnAction(event -> manageWorkers());
    }

    private void displayWorkerDetails(Worker worker) {
        Label workerDetails = new Label("Worker Details:\n" +
                "Type: " + worker.toString() + "\n" +
                "Times Worked: " + worker.getTimesWorked() + "\n" +
                "Hunger Meter: " + worker.getHungerMeter() + "\n" +
                "Sanity Meter: " + worker.getSanityMeter());
        Button backButton = new Button("Back");

        VBox workerDetailsBox = new VBox(workerDetails, backButton);
        root.setCenter(workerDetailsBox);

        backButton.setOnAction(event -> manageWorkers());
    }

    private void createWorker(Farm farm) {
        ComboBox<String> workerTypeComboBox = new ComboBox<>();
        workerTypeComboBox.getItems().addAll("Farmer", "TractorDriver");

        TextField workerNameField = new TextField();
        workerNameField.setPromptText("Enter Worker Name");

        Button addWorkerButton = new Button("Add Worker");
        Button backButton = new Button("Back");

        VBox createWorkerBox = new VBox(new Label("Select Worker Type:"), workerTypeComboBox, workerNameField, addWorkerButton, backButton);
        root.setCenter(createWorkerBox);

        addWorkerButton.setOnAction(event -> {
            String workerType = workerTypeComboBox.getValue();
            String workerName = workerNameField.getText();

            if (workerType != null && !workerName.isEmpty()) {
                Worker newWorker = null;

                if (workerType.equals("Farmer")) {
                    newWorker = new Farmer();
                } else if (workerType.equals("TractorDriver")) {
                    newWorker = new TractorDriver();
                }

                if (newWorker != null) {
                    farm.addWorker(newWorker);
                    displayWorkerManagement(farm); // Refresh the worker management view
                }
            }
        });

        backButton.setOnAction(event -> displayWorkerManagement(farm));
    }

    private void manageTractor() {
        ListView<Farm> farmListView = new ListView<>();
        farmListView.getItems().addAll(farms);
        farmListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button selectFarmButton = new Button("Select Farm");
        VBox manageTractorBox = new VBox(new Label("Select a Farm to Manage Tractor:"), farmListView, selectFarmButton);
        root.setCenter(manageTractorBox);

        selectFarmButton.setOnAction(event -> {
            Farm selectedFarm = farmListView.getSelectionModel().getSelectedItem();
            if (selectedFarm != null) {
                displayTractorManagement(selectedFarm);
            }
        });
    }

    private void displayTractorManagement(Farm farm) {
        Label tractorDetails = new Label("Tractor Details:\n" +
                "Is full: " + farm.getTractor().isFull() + "\n" +
                "Is at crop storage: " + farm.getTractor().isAtCropStorage());
        Button loadTractorButton = new Button("Load Tractor with Corn");
        Button unloadTractorButton = new Button("Unload Tractor");
        Button backButton = new Button("Back");

        VBox tractorManagementBox = new VBox(tractorDetails, loadTractorButton, unloadTractorButton, backButton);
        root.setCenter(tractorManagementBox);

        loadTractorButton.setOnAction(event -> {
            // Load the tractor with a CornCrop
            farm.getTractor().loadTractor(new CornCrop(100.0));
            tractorDetails.setText("Tractor Details:\n" +
                    "Is full: " + farm.getTractor().isFull() + "\n" +
                    "Is at crop storage: " + farm.getTractor().isAtCropStorage());
        });

        unloadTractorButton.setOnAction(event -> {
            Queue<Crop> unloadedCrops = farm.getTractor().unloadTractor();
            displayUnloadedCrops(unloadedCrops);
            tractorDetails.setText("Tractor Details:\n" +
                    "Is full: " + farm.getTractor().isFull() + "\n" +
                    "Is at crop storage: " + farm.getTractor().isAtCropStorage());
            observeCropStorage(); // Update the crop storage view
        });

        backButton.setOnAction(event -> manageTractor());
    }

    private void displayUnloadedCrops(Queue<Crop> unloadedCrops) {
        VBox unloadedCropsBox = new VBox();
        unloadedCropsBox.getChildren().add(new Label("Unloaded Crops:"));
        unloadedCrops.forEach(crop -> unloadedCropsBox.getChildren().add(new Label(crop.getClass().getSimpleName() + " - Weight: " + crop.getWeightInKg() + "kg")));
        Button backButton = new Button("Back");
        unloadedCropsBox.getChildren().add(backButton);
        root.setCenter(unloadedCropsBox);

        backButton.setOnAction(event -> observeCropStorage());
    }

    private void observeCropStorage() {
        Label cropStorageDetails = new Label("Corn Farmed: " + CropStorage.getCrops().stream().filter(crop -> crop instanceof CornCrop).count() + "\n" +
                "Oat Farmed: " + CropStorage.getCrops().stream().filter(crop -> crop instanceof OatCrop).count() + "\n" +
                "Wheat Farmed: " + CropStorage.getCrops().stream().filter(crop -> crop instanceof WheatCrop).count() + "\n" +
                "Value Of Crop: " + Math.round(CropStorage.getCrops().stream().mapToDouble(Crop::getPrice).sum()) + "\n" +
                "Total weight: " + Math.round(CropStorage.getCrops().stream().mapToDouble(Crop::getWeightInKg).sum()));
        Button backButton = new Button("Back");
        VBox cropStorageBox = new VBox(cropStorageDetails, backButton);
        root.setCenter(cropStorageBox);

        backButton.setOnAction(event -> initialize());
    }
}
