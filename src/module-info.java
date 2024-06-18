module Jonas.Ramanauskas.Caner.Celik.Design.Patterns.Final.Assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;

    exports com.farm.gui to javafx.graphics;
    exports com.farm.farm;
    exports com.farm.crop;
    exports com.farm.people.worker;
    exports com.farm.people.decorator;
    exports com.farm.people.state;
}
