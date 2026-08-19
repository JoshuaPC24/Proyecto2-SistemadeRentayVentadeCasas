package Datos;

import Logica.Alquileres;
import Logica.Mensualidades;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Guarda y administra el ArrayList de mensualidades del sistema.
 *
 * Se encarga de generar automáticamente las mensualidades de los
 * alquileres vigentes, aplicando el descuento según la temporada
 * y evitando que se dupliquen.
 *
 * @author mauri
 */
public class MensualidadStore {

    private ArrayList<Mensualidades> listaMensualidades;

    public MensualidadStore() {
        this.listaMensualidades = new ArrayList();
    }

    public ArrayList<Mensualidades> getListaMensualidades() {
        return listaMensualidades;
    }

    public void setListaMensualidades(ArrayList<Mensualidades> listaMensualidades) {
        this.listaMensualidades = listaMensualidades;
    }

    //Métodos del CRUD

    /**
     * Agrega una mensualidad nueva al ArrayList.
     */
    public void insertarMensualidad(Mensualidades mensualidad) {
        if (this.listaMensualidades != null) {
            this.listaMensualidades.add(mensualidad);
        }
    }

    /**
     * Busca una mensualidad por su consecutivo.
     *
     * @return la mensualidad encontrada o null si no existe
     */
    public Mensualidades buscarConsecutivo(int consecutivo) {
        for (Mensualidades m : listaMensualidades) {
            if (m.getConsecutivo() == consecutivo) {
                return m;
            }
        }
        return null;
    }

    /**
     * Genera el siguiente consecutivo disponible.
     */
    public int generarConsecutivo() {
        int mayor = 0;

        for (Mensualidades m : listaMensualidades) {
            if (m.getConsecutivo() > mayor) {
                mayor = m.getConsecutivo();
            }
        }
        return mayor + 1;
    }

    /**
     * Revisa si ya existe una mensualidad para ese alquiler en ese
     * mes y año. Un mismo alquiler solo puede tener 1 mensualidad
     * por mes/año.
     */
    public boolean existeMensualidad(int numAlquiler, int mes, int anio) {
        for (Mensualidades m : listaMensualidades) {
            if (m.getAlquiler().getNumAlquiler() == numAlquiler
                    && m.getMesCobro() == mes
                    && m.getAnioActual() == anio) {

                return true;
            }
        }
        return false;
    }

    /**
     * Revisa si un alquiler tiene alguna mensualidad generada.
     * Se usa para no permitir eliminar un alquiler con mensualidades.
     */
    public boolean alquilerTieneMensualidad(int numAlquiler) {
        for (Mensualidades m : listaMensualidades) {
            if (m.getAlquiler().getNumAlquiler() == numAlquiler) {
                return true;
            }
        }
        return false;
    }

    /**
     * Genera una mensualidad por cada alquiler vigente que esté
     * activo en el mes y año indicados, aplicando el descuento
     * según la temporada. No genera mensualidades repetidas ni
     * para un periodo anterior al mes actual.
     *
     * @param mes mes a generar (1 al 12)
     * @param anio año a generar
     * @param vigentes alquileres con estado Vigente
     * @return la cantidad de mensualidades generadas, o -1 si el
     *         periodo no es válido
     */
    public int generarMensualidades(int mes, int anio, ArrayList<Alquileres> vigentes) {

        if (!periodoValido(mes, anio)) {
            return -1;
        }

        int cantidadGenerada = 0;

        for (Alquileres a : vigentes) {

            if (!alquilerActivoEnPeriodo(a, mes, anio)) {
                continue;
            }

            if (existeMensualidad(a.getNumAlquiler(), mes, anio)) {
                continue;
            }

            double precio = a.getPrecioAlquiler();
            double porcDescuento = obtenerPorcentajeDescuento(mes);
            double descuento = precio * porcDescuento / 100;
            double montoMes = precio - descuento;

            Mensualidades mensualidad = new Mensualidades(
                    generarConsecutivo(), a, LocalDate.now(),
                    a.getInquilino().getNomInqui(), mes, anio,
                    descuento, montoMes, "Pendiente");

            insertarMensualidad(mensualidad);
            cantidadGenerada++;
        }

        return cantidadGenerada;
    }

    /**
     * Revisa que el periodo sea válido para generar mensualidades.
     * No se permiten periodos anteriores al mes actual.
     */
    public boolean periodoValido(int mes, int anio) {

        if (mes < 1 || mes > 12) {
            return false;
        }

        YearMonth periodoSolicitado = YearMonth.of(anio, mes);
        YearMonth periodoActual = YearMonth.now();

        return !periodoSolicitado.isBefore(periodoActual);
    }

    /**
     * Revisa si el contrato del alquiler cubre ese mes y año,
     * según su fecha de contrato y su cantidad de meses.
     */
    public boolean alquilerActivoEnPeriodo(Alquileres alquiler, int mes, int anio) {

        YearMonth periodoSolicitado = YearMonth.of(anio, mes);
        YearMonth inicio = YearMonth.from(alquiler.getFechContrato());
        YearMonth fin = inicio.plusMonths(alquiler.getCantMeses() - 1L);

        return !periodoSolicitado.isBefore(inicio)
                && !periodoSolicitado.isAfter(fin);
    }

    /**
     * Retorna el porcentaje de descuento según la temporada del mes.
     *
     * Baja: agosto, setiembre, octubre = 10%
     * Media: marzo, abril, mayo, junio, julio = 5%
     * Alta: noviembre, diciembre, enero, febrero = 0%
     */
    public double obtenerPorcentajeDescuento(int mes) {

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

            default:
                return 0;
        }
    }

    /**
     * Retorna todas las mensualidades de un mes y año.
     */
    public ArrayList<Mensualidades> buscarPorPeriodo(int mes, int anio) {
        ArrayList<Mensualidades> resultado = new ArrayList();

        for (Mensualidades m : listaMensualidades) {
            if (m.getMesCobro() == mes && m.getAnioActual() == anio) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    /**
     * Filtra las mensualidades por nombre de inquilino, mes y año
     * al mismo tiempo. Si un valor viene vacío o en 0, no filtra
     * por ese criterio.
     */
    public ArrayList<Mensualidades> filtrar(String nombreInquilino, int mes, int anio) {
        ArrayList<Mensualidades> resultado = new ArrayList();
        String busqueda = nombreInquilino == null ? "" : nombreInquilino.trim().toLowerCase();

        for (Mensualidades m : listaMensualidades) {

            boolean cumpleInquilino = busqueda.equals("")
                    || m.getNomInquilino().toLowerCase().contains(busqueda);

            boolean cumpleMes = mes == 0 || m.getMesCobro() == mes;
            boolean cumpleAnio = anio == 0 || m.getAnioActual() == anio;

            if (cumpleInquilino && cumpleMes && cumpleAnio) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    /**
     * Retorna la cantidad de mensualidades registradas.
     */
    public int cantidad() {
        return listaMensualidades.size();
    }
}
