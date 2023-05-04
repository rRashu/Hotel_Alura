package Modelo;

import java.sql.Date;

public class Reserva {

    private int id;


    private int id_huesped;
    private Date dia_entrada;
    private Date getDia_salida;
    private String forma_pago;
    private double valor_cancelar;

    public Reserva() {
    }

    public Reserva(int id_huesped, Date dia_entrada, Date getDia_salida, String forma_pago, double valor_cancelar) {
        this.id_huesped = id_huesped;
        this.dia_entrada = dia_entrada;
        this.getDia_salida = getDia_salida;
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

    public Date getGetDia_salida() {
        return getDia_salida;
    }

    public void setGetDia_salida(Date getDia_salida) {
        this.getDia_salida = getDia_salida;
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
