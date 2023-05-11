package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
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
        tabla_cliente.getColumns().clear();
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

        tabla_cliente.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {

                Reporte.idSeleccionado(tabla_cliente.getSelectionModel().getSelectedItem().getId());
            }

            if (mouseEvent.getClickCount() == 2) {
                resultadolista = tabla_cliente.getSelectionModel().getSelectedItem();
                Stage stage = (Stage) tabla_cliente.getScene().getWindow();
                if (!Cerrar) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reporte.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage1 = new Stage();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    stage1.setTitle("Reportes");
                    stage1.setResizable(false);
                    stage1.setScene(new Scene(root1));
                    stage1.setOnHidden(event -> initialize());
                    stage1.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                }else{
                    stage.close();
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