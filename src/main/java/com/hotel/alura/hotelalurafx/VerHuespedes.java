package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class VerHuespedes {
    public TextField buscar;
    Connection con;
    public TableView<Huesped> tabla_cliente = new TableView<>();
    private static Huesped resultadolista = null;
    static boolean Cerrar = false;

    public void cerrar(boolean cerrar){
        Cerrar = cerrar;
    }
    public void initialize() {
        con = new conexionBD().recuperaConexion();
        huespedesDAO hdao = new huespedesDAO(con);
        TableColumn<Huesped, String> nombreCol = new TableColumn<>("ID");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Huesped, String> nombreCol1 = new TableColumn<>("Nombre");
        nombreCol1.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        TableColumn<Huesped, String> nombreCol2 = new TableColumn<>("Apellido");
        nombreCol2.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        TableColumn<Huesped, String> nombreCol3 = new TableColumn<>("Fecha Nacimiento");
        nombreCol3.setCellValueFactory(cellData -> {
            Huesped hu = cellData.getValue();
            Date fecha = hu.getFecha_nacimiento();
            String fechastr = new SimpleDateFormat("yyyy/MM/dd").format(fecha);
            return new SimpleStringProperty(fechastr);
        });
        TableColumn<Huesped, String> nombreCol4 = new TableColumn<>("Nacionalidad");
        nombreCol4.setCellValueFactory(new PropertyValueFactory<>("Nacionalidad"));
        TableColumn<Huesped, String> nombreCol5 = new TableColumn<>("Telefono");
        nombreCol5.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        tabla_cliente.getColumns().addAll(nombreCol, nombreCol1, nombreCol2, nombreCol3, nombreCol4, nombreCol5);
        List<Huesped> lista = hdao.listar();
        ObservableList<Huesped> dato = FXCollections.observableArrayList();
        dato.addAll(lista);
        tabla_cliente.setItems(dato);

        tabla_cliente.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                resultadolista = tabla_cliente.getSelectionModel().getSelectedItem();
                Stage stage = (Stage) tabla_cliente.getScene().getWindow();
                if (Cerrar){

                    stage.close();
                }
                else{

                    System.out.println("no cierra");
                }

            }
        });
        buscar.setOnKeyReleased(event -> {
            String criterioBusqueda = buscar.getText();
            huespedesDAO hudao = new huespedesDAO(con);
            tabla_cliente.setItems(FXCollections.observableArrayList(hudao.resultadoBusqueda(criterioBusqueda)));
        });

    }

    public Huesped getHuespedSeleccionado() {

        return resultadolista;

    }
}