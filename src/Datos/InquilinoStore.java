package Datos;

import Logica.Inquilino;
import java.util.ArrayList;

public class InquilinoStore {

    private static final ArrayList<Inquilino> listaInquilinos
            = new ArrayList<>();

    /**
     * Retorna todos los inquilinos registrados.
     */
    public static ArrayList<Inquilino> getListaInquilinos() {
        return listaInquilinos;
    }

    /**
     * Inserta un inquilino si su cédula no está registrada.
     */
    public static boolean insertar(Inquilino inquilino) {

        if (inquilino == null) {
            return false;
        }

        if (cedulaExiste(inquilino.getCedInqui(), -1)) {
            return false;
        }

        listaInquilinos.add(inquilino);
        return true;
    }

    /**
     * Busca un inquilino por su número de cédula.
     */
    public static Inquilino buscarPorCedula(int cedula) {

        for (Inquilino inquilino : listaInquilinos) {

            if (inquilino.getCedInqui() == cedula) {
                return inquilino;
            }
        }

        return null;
    }

    /**
     * Retorna la posición del inquilino dentro del ArrayList.
     */
    public static int buscarIndice(int cedula) {

        for (int i = 0; i < listaInquilinos.size(); i++) {

            if (listaInquilinos.get(i).getCedInqui() == cedula) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Modifica un inquilino existente.
     */
    public static boolean modificar(
            int indice,
            Inquilino inquilinoModificado) {

        if (inquilinoModificado == null) {
            return false;
        }

        if (indice < 0 || indice >= listaInquilinos.size()) {
            return false;
        }

        if (cedulaExiste(
                inquilinoModificado.getCedInqui(),
                indice)) {

            return false;
        }

        listaInquilinos.set(indice, inquilinoModificado);
        return true;
    }

    /**
     * Elimina un inquilino utilizando su índice.
     */
    public static boolean eliminar(int indice) {

        if (indice < 0 || indice >= listaInquilinos.size()) {
            return false;
        }

        listaInquilinos.remove(indice);
        return true;
    }

    /**
     * Elimina un inquilino utilizando su cédula.
     */
    public static boolean eliminarPorCedula(int cedula) {

        int indice = buscarIndice(cedula);

        if (indice == -1) {
            return false;
        }

        listaInquilinos.remove(indice);
        return true;
    }

    /**
     * Verifica si una cédula ya se encuentra registrada.
     */
    public static boolean cedulaExiste(
            int cedula,
            int indiceIgnorado) {

        for (int i = 0; i < listaInquilinos.size(); i++) {

            if (i != indiceIgnorado
                    && listaInquilinos.get(i).getCedInqui()
                    == cedula) {

                return true;
            }
        }

        return false;
    }

    /**
     * Retorna la cantidad de inquilinos registrados.
     */
    public static int cantidad() {
        return listaInquilinos.size();
    }

    /**
     * Verifica si no hay inquilinos registrados.
     */
    public static boolean estaVacio() {
        return listaInquilinos.isEmpty();
    }
}
