package com.hotel.alura.hotelalurafx;import Accesos_Datos.reservasDAO;import ConexionBD.conexionBD;import Modelo.Reserva;import Util.Util;import javafx.collections.FXCollections;import javafx.collections.ObservableList;import javafx.fxml.FXMLLoader;import javafx.scene.Parent;import javafx.scene.Scene;import javafx.scene.control.TableView;import javafx.scene.control.TextField;import javafx.scene.input.MouseEvent;import javafx.stage.Modality;import javafx.stage.Stage;import java.sql.Connection;import java.util.List;public class VerReserva {    public TextField buscar;    Connection con;    public TableView<Reserva> tabla_cliente = new TableView<>();    public void initialize() {        con = new conexionBD().recuperaConexion();        reservasDAO rdao = new reservasDAO(con);        Util.llenarTabla(tabla_cliente);        List<Reserva> lista = rdao.listar();        ObservableList<Reserva> dato = FXCollections.observableArrayList();        dato.addAll(lista);        tabla_cliente.setItems(dato);        tabla_cliente.setOnMouseClicked(this::handle);        buscar.setOnKeyReleased(event -> {            String criterioBusqueda = buscar.getText();            reservasDAO rudao = new reservasDAO(con);            tabla_cliente.setItems(FXCollections.observableArrayList(rudao.resultadoBusqueda(criterioBusqueda)));        });    }    private void handle(MouseEvent event) {        if (event.getClickCount() == 1) {            Reporte a = new Reporte();            a.idSeleccionado(tabla_cliente.getSelectionModel().getSelectedItem().getId_huesped());        }        if (event.getClickCount() == 2) {             try {                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reporte.fxml"));                Parent root1 = fxmlLoader.load();                Stage stage1 = new Stage();                stage1.initModality(Modality.APPLICATION_MODAL);                stage1.setTitle("Ver Huespedes");                stage1.setResizable(false);                stage1.setScene(new Scene(root1));                stage1.showAndWait();            } catch (Exception e) {                e.printStackTrace();            }        }    }}