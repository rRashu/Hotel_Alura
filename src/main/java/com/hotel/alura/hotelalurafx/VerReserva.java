package com.hotel.alura.hotelalurafx;

import Accesos_Datos.reservasDAO;
import ConexionBD.conexionBD;
import Modelo.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class VerReserva {
    public TextField buscar;
    Connection con;
    public TableView<Reserva> tabla_cliente = new TableView<>();
    private static Reserva resultadolista = null;

    public void initialize() {
        con = new conexionBD().recuperaConexion();
        reservasDAO rdao = new reservasDAO(con);
        TableColumn<Reserva, String> nombreCol = new TableColumn<>("ID");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Reserva, String> nombreCol1 = new TableColumn<>("Fecha De Entrada");
        nombreCol1.setCellValueFactory(cellData -> {
            Reserva re = cellData.getValue();
            Date fecha = re.getDia_entrada();
            String fechastr = new SimpleDateFormat("yyyy/MM/dd").format(fecha);
            return new SimpleStringProperty(fechastr);
        });
        TableColumn<Reserva, String> nombreCol2 = new TableColumn<>("Fecha De Salida");
        nombreCol2.setCellValueFactory(cellData -> {
            Reserva re = cellData.getValue();
            Date fecha = re.getDia_salida();
            String fechastr = new SimpleDateFormat("yyyy/MM/dd").format(fecha);
            return new SimpleStringProperty(fechastr);
        });


       TableColumn<Reserva, Double> nombreCol3 = new TableColumn<>("Valor");
        nombreCol3.setCellValueFactory(new PropertyValueFactory<>("valor"));
        nombreCol3.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


        TableColumn<Reserva, String> nombreCol4 = new TableColumn<>("Forma De pago");
        nombreCol4.setCellValueFactory(new PropertyValueFactory<>("forma_pago"));
        TableColumn<Reserva, String> nombreCol5 = new TableColumn<>("Id Huesped");
        nombreCol5.setCellValueFactory(new PropertyValueFactory<>("id_huesped"));
        tabla_cliente.getColumns().addAll(nombreCol, nombreCol1, nombreCol2, nombreCol3, nombreCol4, nombreCol5);
        List<Reserva> lista = rdao.listar();
        ObservableList<Reserva> dato = FXCollections.observableArrayList();
        dato.addAll(lista);
        tabla_cliente.setItems(dato);

        tabla_cliente.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                resultadolista = tabla_cliente.getSelectionModel().getSelectedItem();
                Stage stage = (Stage) tabla_cliente.getScene().getWindow();
                stage.close();
            }
        });
        buscar.setOnKeyReleased(event -> {
            String criterioBusqueda = buscar.getText();
            reservasDAO rudao = new reservasDAO(con);
            tabla_cliente.setItems(FXCollections.observableArrayList(rudao.resultadoBusqueda(criterioBusqueda)));
        });

    }

    public Reserva getHuespedSeleccionado() {

        return resultadolista;

    }
}