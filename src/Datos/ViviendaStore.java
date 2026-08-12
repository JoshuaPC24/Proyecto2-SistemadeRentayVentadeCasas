package Datos;

import Logica.Vivienda;
import java.util.ArrayList;

public class ViviendaStore {

    private static final ArrayList<Vivienda> listaViviendas
            = new ArrayList<>();

    /**
     * Retorna todas las viviendas registradas.
     */
    public static ArrayList<Vivienda> getListaViviendas() {
        return listaViviendas;
    }

    /**
     * Inserta una vivienda si su identificador no existe.
     */
    public static boolean insertar(Vivienda vivienda) {

        if (vivienda == null) {
            return false;
        }

        if (idExiste(vivienda.getIdVivienda(), -1)) {
            return false;
        }

        if (vivienda.getPropietario() == null) {
            return false;
        }

        listaViviendas.add(vivienda);
        return true;
    }

    /**
     * Busca una vivienda por su identificador.
     */
    public static Vivienda buscarPorId(int idVivienda) {

        for (Vivienda vivienda : listaViviendas) {

            if (vivienda.getIdVivienda() == idVivienda) {
                return vivienda;
            }
        }

        return null;
    }

    /**
     * Retorna la posición de una vivienda dentro del ArrayList.
     */
    public static int buscarIndice(int idVivienda) {

        for (int i = 0; i < listaViviendas.size(); i++) {

            if (listaViviendas.get(i).getIdVivienda()
                    == idVivienda) {

                return i;
            }
        }

        return -1;
    }

    /**
     * Modifica una vivienda existente.
     */
    public static boolean modificar(
            int indice,
            Vivienda viviendaModificada) {

        if (viviendaModificada == null) {
            return false;
        }

        if (indice < 0 || indice >= listaViviendas.size()) {
            return false;
        }

        if (viviendaModificada.getPropietario() == null) {
            return false;
        }

        if (idExiste(
                viviendaModificada.getIdVivienda(),
                indice)) {

            return false;
        }

        listaViviendas.set(indice, viviendaModificada);
        return true;
    }

    /**
     * Elimina una vivienda por índice.
     */
    public static boolean eliminar(int indice) {

        if (indice < 0 || indice >= listaViviendas.size()) {
            return false;
        }

        Vivienda vivienda = listaViviendas.get(indice);

        if ("Alquilada".equalsIgnoreCase(vivienda.getEstado())) {
            return false;
        }

        listaViviendas.remove(indice);
        return true;
    }

    /**
     * Elimina una vivienda por su identificador.
     */
    public static boolean eliminarPorId(int idVivienda) {

        int indice = buscarIndice(idVivienda);

        if (indice == -1) {
            return false;
        }

        return eliminar(indice);
    }

    /**
     * Verifica si un identificador ya está registrado.
     */
    public static boolean idExiste(
            int idVivienda,
            int indiceIgnorado) {

        for (int i = 0; i < listaViviendas.size(); i++) {

            if (i != indiceIgnorado
                    && listaViviendas.get(i).getIdVivienda()
                    == idVivienda) {

                return true;
            }
        }

        return false;
    }

    /**
     * Retorna únicamente las viviendas disponibles.
     */
    public static ArrayList<Vivienda> obtenerDisponibles() {

        ArrayList<Vivienda> disponibles = new ArrayList<>();

        for (Vivienda vivienda : listaViviendas) {

            if ("Disponible".equalsIgnoreCase(
                    vivienda.getEstado())) {

                disponibles.add(vivienda);
            }
        }

        return disponibles;
    }

    /**
     * Retorna las viviendas pertenecientes a un propietario.
     */
    public static ArrayList<Vivienda> buscarPorPropietario(
            int cedulaPropietario) {

        ArrayList<Vivienda> resultado = new ArrayList<>();

        for (Vivienda vivienda : listaViviendas) {

            if (vivienda.getPropietario() != null
                    && vivienda.getPropietario().getCedPropiet()
                    == cedulaPropietario) {

                resultado.add(vivienda);
            }
        }

        return resultado;
    }

    /**
     * Busca viviendas por id, descripción o dirección.
     *
     * Sirve para la caja de búsqueda de la ventana de viviendas.
     */
    public static ArrayList<Vivienda> buscarPorTexto(String texto) {

        ArrayList<Vivienda> resultado = new ArrayList<>();

        if (texto == null) {
            return resultado;
        }

        String busqueda = texto.trim().toLowerCase();

        for (int i = 0; i < listaViviendas.size(); i++) {

            Vivienda vivienda = listaViviendas.get(i);

            String id = String.valueOf(vivienda.getIdVivienda());
            String descripcion = vivienda.getDescripcion().toLowerCase();
            String direccion = vivienda.getDireccion().toLowerCase();

            if (id.contains(busqueda)
                    || descripcion.contains(busqueda)
                    || direccion.contains(busqueda)) {

                resultado.add(vivienda);
            }
        }

        return resultado;
    }

    /**
     * Retorna la cantidad de viviendas registradas.
     */
    public static int cantidad() {
        return listaViviendas.size();
    }

    /**
     * Verifica si no existen viviendas registradas.
     */
    public static boolean estaVacio() {
        return listaViviendas.isEmpty();
    }
}