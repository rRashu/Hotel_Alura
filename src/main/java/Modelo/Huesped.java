package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Huesped {


    private int id;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String nacionalidad;
    private String telefono;

    @Override
    public String toString() {
        return "Huesped{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    List<Reserva> reservas = new ArrayList<>();

    public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
        this.id= id;
        this.nombre = nombre;
        this.fecha_nacimiento = fechaNacimiento;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Huesped() {

    }


    public void agregar(Reserva reserva) {
        this.reservas.add(reserva);
    }
    public int getId() {
        return id;
    }
    public Integer getIdinter() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
