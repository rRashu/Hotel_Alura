package Modelo;

import java.sql.Date;

public class Reserva {

    private int id;
    private int id_huesped;
    private Date dia_entrada;
    private Date dia_salida;
    private String forma_pago;
    private double valor_cancelar;

    public Reserva() {
        this.id_huesped = id_huesped;
        this.dia_entrada = dia_entrada;
        this.dia_salida = dia_salida;
        this.forma_pago = forma_pago;
        this.valor_cancelar = valor_cancelar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public Date getDia_entrada() {
        return dia_entrada;
    }

    public void setDia_entrada(Date dia_entrada) {
        this.dia_entrada = dia_entrada;
    }

    public Date getDia_salida() {
        return dia_salida;
    }

    public void setDia_salida(Date dia_salida) {
        this.dia_salida = dia_salida;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public double getValor_cancelar() {
        return valor_cancelar;
    }

    public void setValor_cancelar(double valor_cancelar) {
        this.valor_cancelar = valor_cancelar;
    }
}
