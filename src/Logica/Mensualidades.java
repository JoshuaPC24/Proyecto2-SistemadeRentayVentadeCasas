package Logica;

import java.time.LocalDate;

public class Mensualidades {

    private int consecutivo;
    private Alquileres alquiler;
    private LocalDate fechCreacion;
    private String nomInquilino;
    private int mesCobro;
    private int anioActual;
    private double descuento;
    private double montoMes;
    private String estado;

    // Constructor vacío
    public Mensualidades() {
    }

    // Constructor con todos los atributos
    public Mensualidades(int consecutivo, Alquileres alquiler,
                         LocalDate fechCreacion, String nomInquilino,
                         int mesCobro, int anioActual, double descuento,
                         double montoMes, String estado) {

        this.consecutivo = consecutivo;
        this.alquiler = alquiler;
        this.fechCreacion = fechCreacion;
        this.nomInquilino = nomInquilino;
        this.mesCobro = mesCobro;
        this.anioActual = anioActual;
        this.descuento = descuento;
        this.montoMes = montoMes;
        this.estado = estado;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Alquileres getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquileres alquiler) {
        this.alquiler = alquiler;
    }

    public LocalDate getFechCreacion() {
        return fechCreacion;
    }

    public void setFechCreacion(LocalDate fechCreacion) {
        this.fechCreacion = fechCreacion;
    }

    public String getNomInquilino() {
        return nomInquilino;
    }

    public void setNomInquilino(String nomInquilino) {
        this.nomInquilino = nomInquilino;
    }

    public int getMesCobro() {
        return mesCobro;
    }

    public void setMesCobro(int mesCobro) {
        this.mesCobro = mesCobro;
    }

    public int getAnioActual() {
        return anioActual;
    }

    public void setAnioActual(int anioActual) {
        this.anioActual = anioActual;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getMontoMes() {
        return montoMes;
    }

    public void setMontoMes(double montoMes) {
        this.montoMes = montoMes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mensualidad #" + consecutivo
                + " - " + nomInquilino
                + " - " + mesCobro + "/" + anioActual;
    }
}