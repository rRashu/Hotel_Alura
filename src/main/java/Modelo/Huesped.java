package Modelo;

import java.sql.Date;

public class Huesped {


    private int id;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private String nacionalidad;
    private String telefono;

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

    public Huesped(int id, Date fechaEntrada, Date fechaSalida, double valor, String formaPago, int idHuesped) {
    }

    public int getId() {
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
