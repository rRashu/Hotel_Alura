package com.hotel.alura.hotelalurafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MenuPrincipal {

    public AnchorPane otras;
    public Label Ingreso;
    public Label reporte;
    public Label Ingreso2;
    public ImageView github;
    public ImageView l;
    public Label mover;

    public void initialize() {

        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];
        mover.setOnMousePressed(event -> {
            yOffset[0] = event.getSceneY();
            xOffset[0] = event.getSceneX();

        });
        mover.setOnMouseDragged(event -> {
            mover.getScene().getWindow().setX(event.getScreenX() - xOffset[0]);
            mover.getScene().getWindow().setY(event.getScreenY() - yOffset[0]);

        });
        Application application = new Application() {
            @Override
            public void start(Stage stage) {
            }
        };
        Ingreso2.setOnMouseClicked(event ->{
            Stage stage1 = (Stage) mover.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root1;
            try {
                root1 = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Menu Principal");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        });
        github.setOnMouseClicked(event -> application.getHostServices().showDocument("https://github.com/rRashu"));
        l.setOnMouseClicked(event -> application.getHostServices().showDocument("https://www.linkedin.com/in/robin-rezabala/"));
        ContextMenu menu = new ContextMenu();
        MenuItem item1 = new MenuItem("Ingresar Reserva");
        MenuItem item2 = new MenuItem("Ingresar Huesped");
        menu.getItems().addAll(item1, item2);

        ContextMenu menuReporte = new ContextMenu();
        MenuItem itemreporte1 = new MenuItem("Reporte Reservas");
        MenuItem itemreporte2 = new MenuItem("Reporte Usuario");
        menuReporte.getItems().addAll(itemreporte1, itemreporte2);

        Ingreso.setContextMenu(menu);
        reporte.setContextMenu(menuReporte);

        Ingreso.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                menu.show(Ingreso, Side.RIGHT, 0, 0);
            }
        });

        reporte.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                menuReporte.show(reporte, Side.RIGHT, 0, 0);
            }
        });

        item1.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ingresoReserva.fxml"));
                Node formularioSecundario = loader.load();
                otras.getChildren().clear();
                otras.getChildren().add(formularioSecundario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        item2.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ingresoHuesped.fxml"));
                Node formularioSecundario = loader.load();
                otras.getChildren().clear();
                otras.getChildren().add(formularioSecundario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        itemreporte1.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ver_reserva.fxml"));
                Node formularioSecundario = loader.load();
                otras.getChildren().clear();
                otras.getChildren().add(formularioSecundario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        itemreporte2.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ver_huespedes.fxml"));
                Node formularioSecundario = loader.load();
                otras.getChildren().clear();
                VerHuespedes a = new VerHuespedes();
                a.cerrar(false);
                otras.getChildren().add(formularioSecundario);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}