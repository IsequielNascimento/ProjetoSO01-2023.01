module gui.resources.projetoso012023_01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens gui.resources to javafx.fxml;
    exports gui.resources;
}