package com.hotel.alura.hotelalurafx;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuPrincipal {

    public AnchorPane otras;
    public Label Ingreso;

    public void initialize() {
        ContextMenu menu = new ContextMenu();
        MenuItem item1 = new MenuItem("Ingresar Reserva");
        MenuItem item2 = new MenuItem("Ingresar Huesped");
        menu.getItems().addAll(item1, item2);
        Ingreso.setContextMenu(menu);

        Ingreso.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                menu.show(Ingreso, Side.RIGHT, 0, 0);
            }
        });

        item1.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ingresoReserva.fxml"));
                Node formularioSecundario = loader.load();
                otras.getChildren().add(formularioSecundario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        item2.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ingresoHuesped.fxml"));
                Node formularioSecundario = loader.load();
                otras.getChildren().add(formularioSecundario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}