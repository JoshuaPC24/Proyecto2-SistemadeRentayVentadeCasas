package Logica;

import java.time.LocalDate;

public class Alquileres {

    private int numAlquiler;
    private LocalDate fechContrato;
    private int cantMeses;
    private int numAdultos;
    private int numNinos;
    private double depositoGarantia;
    private double precioAlquiler;
    private double porcIncremAnual;
    private Inquilino inquilino;
    private Vivienda vivienda;
    private String estado;

    // Constructor vacío
    public Alquileres() {
    }

    // Constructor con todos los atributos
    public Alquileres(int numAlquiler, LocalDate fechContrato,
                      int cantMeses, int numAdultos, int numNinos,
                      double depositoGarantia, double precioAlquiler,
                      double porcIncremAnual, Inquilino inquilino,
                      Vivienda vivienda, String estado) {

        this.numAlquiler = numAlquiler;
        this.fechContrato = fechContrato;
        this.cantMeses = cantMeses;
        this.numAdultos = numAdultos;
        this.numNinos = numNinos;
        this.depositoGarantia = depositoGarantia;
        this.precioAlquiler = precioAlquiler;
        this.porcIncremAnual = porcIncremAnual;
        this.inquilino = inquilino;
        this.vivienda = vivienda;
        this.estado = estado;
    }

    public int getNumAlquiler() {
        return numAlquiler;
    }

    public void setNumAlquiler(int numAlquiler) {
        this.numAlquiler = numAlquiler;
    }

    public LocalDate getFechContrato() {
        return fechContrato;
    }

    public void setFechContrato(LocalDate fechContrato) {
        this.fechContrato = fechContrato;
    }

    public int getCantMeses() {
        return cantMeses;
    }

    public void setCantMeses(int cantMeses) {
        this.cantMeses = cantMeses;
    }

    public int getNumAdultos() {
        return numAdultos;
    }

    public void setNumAdultos(int numAdultos) {
        this.numAdultos = numAdultos;
    }

    public int getNumNinos() {
        return numNinos;
    }

    public void setNumNinos(int numNinos) {
        this.numNinos = numNinos;
    }

    public double getDepositoGarantia() {
        return depositoGarantia;
    }

    public void setDepositoGarantia(double depositoGarantia) {
        this.depositoGarantia = depositoGarantia;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public double getPorcIncremAnual() {
        return porcIncremAnual;
    }

    public void setPorcIncremAnual(double porcIncremAnual) {
        this.porcIncremAnual = porcIncremAnual;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   @Override
public String toString() {

    String descripcionVivienda =
            vivienda != null ? vivienda.getDescripcion() : "Sin vivienda";

    String nombreInquilino =
            inquilino != null ? inquilino.getNomInqui() : "Sin inquilino";

    return "Alquiler #" + numAlquiler
            + " - " + descripcionVivienda
            + " - " + nombreInquilino;
}
}