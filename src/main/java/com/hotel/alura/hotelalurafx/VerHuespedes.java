package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class VerHuespedes {
    Connection con;
    public TableView<Huesped> tabla_cliente = new TableView<Huesped>();

    public void initialize() {
        con = new conexionBD().recuperaConexion();
        huespedesDAO hdao = new huespedesDAO(con);

        TableColumn<Huesped, String> nombreCol = new TableColumn<Huesped,String>("ID");
        nombreCol.setCellValueFactory(new PropertyValueFactory<Huesped,String>("id"));

        TableColumn<Huesped, String> nombreCol1 = new TableColumn<Huesped,String>("Nombre");
        nombreCol1.setCellValueFactory(new PropertyValueFactory<Huesped,String>("Nombre"));

        TableColumn<Huesped, String> nombreCol2 = new TableColumn<Huesped,String>("Apellido");
        nombreCol2.setCellValueFactory(new PropertyValueFactory<Huesped,String>("Apellido"));

        TableColumn<Huesped, String> nombreCol3 = new TableColumn<>("Fecha Nacimiento");
        nombreCol3.setCellValueFactory(cellData -> {
            Huesped hu = cellData.getValue();
            Date fecha = hu.getFecha_nacimiento();
            String fechastr = new SimpleDateFormat("yyyy/MM/dd").format(fecha);
            return new SimpleStringProperty(fechastr);
        });


        TableColumn<Huesped, String> nombreCol4 = new TableColumn<Huesped,String>("Nacionalidad");
        nombreCol4.setCellValueFactory(new PropertyValueFactory<Huesped,String>("Nacionalidad"));

        TableColumn<Huesped, String> nombreCol5 = new TableColumn<Huesped,String>("Telefono");
        nombreCol5.setCellValueFactory(new PropertyValueFactory<Huesped,String>("Telefono"));

        tabla_cliente.getColumns().addAll(nombreCol,nombreCol1, nombreCol2, nombreCol3, nombreCol4, nombreCol5);
        List<Huesped> lista = hdao.listar();
        ObservableList<Huesped> dato = FXCollections.observableArrayList();
        for (Huesped h: lista){
            dato.add(h);
        }
        tabla_cliente.setItems(dato);
}
}
