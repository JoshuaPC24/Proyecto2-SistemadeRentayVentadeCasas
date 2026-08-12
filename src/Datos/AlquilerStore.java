package Datos;

import Logica.Alquileres;
import Logica.Vivienda;
import java.util.ArrayList;

public class AlquilerStore {

    private static final ArrayList<Alquileres> listaAlquileres
            = new ArrayList<>();

    /**
     * Retorna todos los alquileres registrados.
     */
    public static ArrayList<Alquileres> getListaAlquileres() {
        return listaAlquileres;
    }

    /**
     * Inserta un alquiler si cumple con las validaciones.
     */
    public static boolean insertar(Alquileres alquiler) {

        if (alquiler == null) {
            return false;
        }

        if (numeroExiste(alquiler.getNumAlquiler(), -1)) {
            return false;
        }

        if (alquiler.getInquilino() == null
                || alquiler.getVivienda() == null) {

            return false;
        }

        if (alquiler.getCantMeses() <= 0) {
            return false;
        }

        if (alquiler.getPrecioAlquiler() <= 0) {
            return false;
        }

        if (alquiler.getDepositoGarantia() < 0) {
            return false;
        }

        if (alquiler.getPorcIncremAnual() < 1
                || alquiler.getPorcIncremAnual() > 30) {

            return false;
        }

        Vivienda vivienda = alquiler.getVivienda();

        if (!"Disponible".equalsIgnoreCase(vivienda.getEstado())) {
            return false;
        }

        listaAlquileres.add(alquiler);

        if ("Vigente".equalsIgnoreCase(alquiler.getEstado())) {
            vivienda.setEstado("Alquilada");
        }

        return true;
    }

    /**
     * Busca un alquiler utilizando su número.
     */
    public static Alquileres buscarPorNumero(int numeroAlquiler) {

        for (Alquileres alquiler : listaAlquileres) {

            if (alquiler.getNumAlquiler() == numeroAlquiler) {
                return alquiler;
            }
        }

        return null;
    }

    /**
     * Retorna la posición del alquiler en el ArrayList.
     */
    public static int buscarIndice(int numeroAlquiler) {

        for (int i = 0; i < listaAlquileres.size(); i++) {

            if (listaAlquileres.get(i).getNumAlquiler()
                    == numeroAlquiler) {

                return i;
            }
        }

        return -1;
    }

    /**
     * Modifica un alquiler existente.
     */
    public static boolean modificar(
            int indice,
            Alquileres alquilerModificado) {

        if (alquilerModificado == null) {
            return false;
        }

        if (indice < 0 || indice >= listaAlquileres.size()) {
            return false;
        }

        if (numeroExiste(
                alquilerModificado.getNumAlquiler(),
                indice)) {

            return false;
        }

        if (alquilerModificado.getInquilino() == null
                || alquilerModificado.getVivienda() == null) {

            return false;
        }

        if (alquilerModificado.getCantMeses() <= 0) {
            return false;
        }

        if (alquilerModificado.getPrecioAlquiler() <= 0) {
            return false;
        }

        if (alquilerModificado.getDepositoGarantia() < 0) {
            return false;
        }

        if (alquilerModificado.getPorcIncremAnual() < 1
                || alquilerModificado.getPorcIncremAnual() > 30) {

            return false;
        }

        Alquileres alquilerAnterior =
                listaAlquileres.get(indice);

        Vivienda viviendaAnterior =
                alquilerAnterior.getVivienda();

        Vivienda viviendaNueva =
                alquilerModificado.getVivienda();

        /*
         * Si se cambió la vivienda, la vivienda anterior
         * vuelve a estar disponible.
         */
        if (viviendaAnterior != null
                && viviendaAnterior != viviendaNueva) {

            viviendaAnterior.setEstado("Disponible");
        }

        /*
         * Actualiza el estado de la nueva vivienda según
         * el estado del alquiler.
         */
        if ("Vigente".equalsIgnoreCase(
                alquilerModificado.getEstado())) {

            viviendaNueva.setEstado("Alquilada");

        } else {

            viviendaNueva.setEstado("Disponible");
        }

        listaAlquileres.set(indice, alquilerModificado);
        return true;
    }

    /**
     * Elimina un alquiler y libera la vivienda.
     */
    public static boolean eliminar(int indice) {

        if (indice < 0 || indice >= listaAlquileres.size()) {
            return false;
        }

        Alquileres alquiler = listaAlquileres.get(indice);

        if (alquiler.getVivienda() != null) {
            alquiler.getVivienda().setEstado("Disponible");
        }

        listaAlquileres.remove(indice);
        return true;
    }

    /**
     * Elimina un alquiler utilizando su número.
     */
    public static boolean eliminarPorNumero(int numeroAlquiler) {

        int indice = buscarIndice(numeroAlquiler);

        if (indice == -1) {
            return false;
        }

        return eliminar(indice);
    }

    /**
     * Verifica si el número de alquiler ya existe.
     */
    public static boolean numeroExiste(
            int numeroAlquiler,
            int indiceIgnorado) {

        for (int i = 0; i < listaAlquileres.size(); i++) {

            if (i != indiceIgnorado
                    && listaAlquileres.get(i).getNumAlquiler()
                    == numeroAlquiler) {

                return true;
            }
        }

        return false;
    }

    /**
     * Genera el siguiente número consecutivo de alquiler.
     */
    public static int generarNumeroAlquiler() {

        int mayor = 0;

        for (Alquileres alquiler : listaAlquileres) {

            if (alquiler.getNumAlquiler() > mayor) {
                mayor = alquiler.getNumAlquiler();
            }
        }

        return mayor + 1;
    }

    /**
     * Retorna los alquileres con estado vigente.
     */
    public static ArrayList<Alquileres> obtenerVigentes() {

        ArrayList<Alquileres> vigentes = new ArrayList<>();

        for (Alquileres alquiler : listaAlquileres) {

            if ("Vigente".equalsIgnoreCase(
                    alquiler.getEstado())) {

                vigentes.add(alquiler);
            }
        }

        return vigentes;
    }

    /**
     * Busca los alquileres pertenecientes a un inquilino.
     */
    public static ArrayList<Alquileres> buscarPorInquilino(
            int cedulaInquilino) {

        ArrayList<Alquileres> resultado = new ArrayList<>();

        for (Alquileres alquiler : listaAlquileres) {

            if (alquiler.getInquilino() != null
                    && alquiler.getInquilino().getCedInqui()
                    == cedulaInquilino) {

                resultado.add(alquiler);
            }
        }

        return resultado;
    }

    /**
     * Verifica si una vivienda posee un alquiler vigente.
     */
    public static boolean viviendaTieneAlquilerVigente(
            int idVivienda) {

        for (Alquileres alquiler : listaAlquileres) {

            if (alquiler.getVivienda() != null
                    && alquiler.getVivienda().getIdVivienda()
                    == idVivienda
                    && "Vigente".equalsIgnoreCase(
                            alquiler.getEstado())) {

                return true;
            }
        }

        return false;
    }

    /**
     * Retorna la cantidad de alquileres registrados.
     */
    public static int cantidad() {
        return listaAlquileres.size();
    }

    /**
     * Verifica si no existen alquileres.
     */
    public static boolean estaVacio() {
        return listaAlquileres.isEmpty();
    }
}
