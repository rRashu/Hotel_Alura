package com.hotel.alura.hotelalurafx;

import Accesos_Datos.reservasDAO;
import ConexionBD.conexionBD;
import Modelo.Reserva;
import Util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;


public class VerReserva {
    public TextField buscar;
    Connection con;
    public TableView<Reserva> tabla_cliente = new TableView<>();
    private static Reserva resultadolista = null;

    public void initialize() {
        con = new conexionBD().recuperaConexion();
        reservasDAO rdao = new reservasDAO(con);
        Util.llenarTabla(tabla_cliente);
        List<Reserva> lista = rdao.listar();
        ObservableList<Reserva> dato = FXCollections.observableArrayList();
        dato.addAll(lista);
        tabla_cliente.setItems(dato);

        tabla_cliente.setOnMouseClicked(this::handle);
        buscar.setOnKeyReleased(event -> {
            String criterioBusqueda = buscar.getText();
            reservasDAO rudao = new reservasDAO(con);
            tabla_cliente.setItems(FXCollections.observableArrayList(rudao.resultadoBusqueda(criterioBusqueda)));
        });

    }

    public Reserva getreservaSeleccionado() {
        return resultadolista;
    }

    private void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
            resultadolista = tabla_cliente.getSelectionModel().getSelectedItem();
            Stage stage = (Stage) tabla_cliente.getScene().getWindow();
            //stage.close();
        }
    }
}