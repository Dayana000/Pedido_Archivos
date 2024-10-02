module com.example.productos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.productos to javafx.fxml;
    exports com.example.productos;
}