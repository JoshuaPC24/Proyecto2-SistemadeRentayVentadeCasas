package Datos;

import Logica.Alquileres;
import Logica.Mensualidades;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class MensualidadStore {

    private static final ArrayList<Mensualidades> listaMensualidades
            = new ArrayList<>();

    /**
     * Retorna todas las mensualidades registradas.
     */
    public static ArrayList<Mensualidades> getListaMensualidades() {
        return listaMensualidades;
    }

    /**
     * Inserta una mensualidad si no está repetida.
     */
    public static boolean insertar(Mensualidades mensualidad) {

        if (mensualidad == null) {
            return false;
        }

        if (mensualidad.getAlquiler() == null) {
            return false;
        }

        if (consecutivoExiste(
                mensualidad.getConsecutivo())) {

            return false;
        }

        int numeroAlquiler =
                mensualidad.getAlquiler().getNumAlquiler();

        if (mensualidadExiste(
                numeroAlquiler,
                mensualidad.getMesCobro(),
                mensualidad.getAnioActual())) {

            return false;
        }

        listaMensualidades.add(mensualidad);
        return true;
    }

    /**
     * Busca una mensualidad por consecutivo.
     */
    public static Mensualidades buscarPorConsecutivo(
            int consecutivo) {

        for (Mensualidades mensualidad : listaMensualidades) {

            if (mensualidad.getConsecutivo() == consecutivo) {
                return mensualidad;
            }
        }

        return null;
    }

    /**
     * Verifica si un consecutivo ya está registrado.
     */
    public static boolean consecutivoExiste(int consecutivo) {

        return buscarPorConsecutivo(consecutivo) != null;
    }

    /**
     * Verifica si ya existe una mensualidad para el mismo
     * alquiler, mes y año.
     */
    public static boolean mensualidadExiste(
            int numeroAlquiler,
            int mes,
            int anio) {

        for (Mensualidades mensualidad : listaMensualidades) {

            if (mensualidad.getAlquiler() != null
                    && mensualidad.getAlquiler().getNumAlquiler()
                    == numeroAlquiler
                    && mensualidad.getMesCobro() == mes
                    && mensualidad.getAnioActual() == anio) {

                return true;
            }
        }

        return false;
    }

    /**
     * Genera el siguiente consecutivo disponible.
     */
    public static int generarConsecutivo() {

        int mayor = 0;

        for (Mensualidades mensualidad : listaMensualidades) {

            if (mensualidad.getConsecutivo() > mayor) {
                mayor = mensualidad.getConsecutivo();
            }
        }

        return mayor + 1;
    }

    /**
     * Genera las mensualidades de todos los alquileres
     * vigentes para un mes y año.
     *
     * Retorna la cantidad de mensualidades creadas.
     */
    public static int generarMensualidades(
            int mes,
            int anio) {

        if (!periodoValido(mes, anio)) {
            return -1;
        }

        int cantidadGenerada = 0;

        for (Alquileres alquiler
                : AlquilerStore.getListaAlquileres()) {

            if (!"Vigente".equalsIgnoreCase(
                    alquiler.getEstado())) {

                continue;
            }

            if (!alquilerActivoEnPeriodo(
                    alquiler,
                    mes,
                    anio)) {

                continue;
            }

            if (mensualidadExiste(
                    alquiler.getNumAlquiler(),
                    mes,
                    anio)) {

                continue;
            }

            double precioAlquiler =
                    alquiler.getPrecioAlquiler();

            double porcentajeDescuento =
                    obtenerPorcentajeDescuento(mes);

            double montoDescuento =
                    precioAlquiler
                    * porcentajeDescuento / 100;

            double montoPagar =
                    precioAlquiler - montoDescuento;

            Mensualidades mensualidad =
                    new Mensualidades(
                            generarConsecutivo(),
                            alquiler,
                            LocalDate.now(),
                            alquiler.getInquilino().getNomInqui(),
                            mes,
                            anio,
                            montoDescuento,
                            montoPagar,
                            "Pendiente"
                    );

            listaMensualidades.add(mensualidad);
            cantidadGenerada++;
        }

        return cantidadGenerada;
    }

    /**
     * Determina si el mes y año son válidos.
     *
     * No se permiten períodos anteriores al mes actual.
     */
    public static boolean periodoValido(
            int mes,
            int anio) {

        if (mes < 1 || mes > 12) {
            return false;
        }

        if (anio < 1000 || anio > 9999) {
            return false;
        }

        YearMonth periodoSolicitado =
                YearMonth.of(anio, mes);

        YearMonth periodoActual =
                YearMonth.now();

        return !periodoSolicitado.isBefore(periodoActual);
    }

    /**
     * Verifica si el contrato se encuentra activo
     * durante el mes solicitado.
     */
    public static boolean alquilerActivoEnPeriodo(
            Alquileres alquiler,
            int mes,
            int anio) {

        if (alquiler == null
                || alquiler.getFechContrato() == null
                || alquiler.getCantMeses() <= 0) {

            return false;
        }

        YearMonth periodoSolicitado =
                YearMonth.of(anio, mes);

        YearMonth inicioContrato =
                YearMonth.from(alquiler.getFechContrato());

        YearMonth finalContrato =
                inicioContrato.plusMonths(
                        alquiler.getCantMeses() - 1L);

        return !periodoSolicitado.isBefore(inicioContrato)
                && !periodoSolicitado.isAfter(finalContrato);
    }

    /**
     * Retorna el porcentaje de descuento según la temporada.
     *
     * Baja: agosto, setiembre y octubre = 10%
     * Media: marzo, abril, mayo, junio y julio = 5%
     * Alta: noviembre, diciembre, enero y febrero = 0%
     */
    public static double obtenerPorcentajeDescuento(int mes) {

        switch (mes) {

            case 8:
            case 9:
            case 10:
                return 10;

            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 5;

            case 1:
            case 2:
            case 11:
            case 12:
                return 0;

            default:
                return 0;
        }
    }

    /**
     * Retorna todas las mensualidades de un mes y año.
     */
    public static ArrayList<Mensualidades> buscarPorPeriodo(
            int mes,
            int anio) {

        ArrayList<Mensualidades> resultado =
                new ArrayList<>();

        for (Mensualidades mensualidad : listaMensualidades) {

            if (mensualidad.getMesCobro() == mes
                    && mensualidad.getAnioActual() == anio) {

                resultado.add(mensualidad);
            }
        }

        return resultado;
    }

    /**
     * Busca mensualidades por nombre del inquilino.
     */
    public static ArrayList<Mensualidades> buscarPorInquilino(
            String nombre) {

        ArrayList<Mensualidades> resultado =
                new ArrayList<>();

        if (nombre == null) {
            return resultado;
        }

        String textoBusqueda =
                nombre.trim().toLowerCase();

        for (Mensualidades mensualidad : listaMensualidades) {

            if (mensualidad.getNomInquilino() != null
                    && mensualidad.getNomInquilino()
                            .toLowerCase()
                            .contains(textoBusqueda)) {

                resultado.add(mensualidad);
            }
        }

        return resultado;
    }

    /**
     * Cambia una mensualidad al estado Cancelado.
     */
    public static boolean cancelarMensualidad(
            int consecutivo) {

        Mensualidades mensualidad =
                buscarPorConsecutivo(consecutivo);

        if (mensualidad == null) {
            return false;
        }

        mensualidad.setEstado("Cancelado");
        return true;
    }

    /**
     * Retorna la cantidad de mensualidades registradas.
     */
    public static int cantidad() {
        return listaMensualidades.size();
    }

    /**
     * Verifica si no existen mensualidades.
     */
    public static boolean estaVacio() {
        return listaMensualidades.isEmpty();
    }
}