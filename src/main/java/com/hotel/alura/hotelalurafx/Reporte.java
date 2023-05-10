package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import Modelo.Reserva;
import Util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.util.List;

public class Reporte {
    public TextField nombre;
    public TextField apellido;
    public TextField telefono;
    public ComboBox<String> nacionalidad;
    public DatePicker fecha_nacimiento;
    public TableView tabla_cliente;
    Connection con;

    public void initialize() {
        con = new conexionBD().recuperaConexion();
        huespedesDAO rdao = new huespedesDAO(con);
        for (Huesped huesped : rdao.listarConReserva(113)) {
            nombre.setText(huesped.getNombre());
            apellido.setText(huesped.getApellido());
            telefono.setText(huesped.getTelefono());
            nacionalidad.setValue(huesped.getNacionalidad());
            fecha_nacimiento.setValue(huesped.getFecha_nacimiento().toLocalDate());
            Util.llenarTabla(tabla_cliente);
            List<Reserva> lista = huesped.getReservas();
            ObservableList<Reserva> dato = FXCollections.observableArrayList();
            dato.addAll(lista);
            tabla_cliente.setItems(dato);

        }
    }




}