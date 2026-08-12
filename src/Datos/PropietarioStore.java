package Datos;

import Logica.Propietario;
import java.util.ArrayList;

public class PropietarioStore {

    private static final ArrayList<Propietario> listaPropietarios
            = new ArrayList<>();

    /**
     * Retorna todos los propietarios registrados.
     */
    public static ArrayList<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    /**
     * Inserta un propietario si la cédula no está registrada.
     */
    public static boolean insertar(Propietario propietario) {

        if (propietario == null) {
            return false;
        }

        if (cedulaExiste(propietario.getCedPropiet(), -1)) {
            return false;
        }

        listaPropietarios.add(propietario);
        return true;
    }

    /**
     * Busca un propietario por número de cédula.
     */
    public static Propietario buscarPorCedula(int cedula) {

        for (Propietario propietario : listaPropietarios) {

            if (propietario.getCedPropiet() == cedula) {
                return propietario;
            }
        }

        return null;
    }

    /**
     * Retorna la posición de un propietario dentro del ArrayList.
     */
    public static int buscarIndice(int cedula) {

        for (int i = 0; i < listaPropietarios.size(); i++) {

            if (listaPropietarios.get(i).getCedPropiet() == cedula) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Modifica un propietario existente.
     */
    public static boolean modificar(
            int indice,
            Propietario propietarioModificado) {

        if (propietarioModificado == null) {
            return false;
        }

        if (indice < 0 || indice >= listaPropietarios.size()) {
            return false;
        }

        if (cedulaExiste(
                propietarioModificado.getCedPropiet(),
                indice)) {

            return false;
        }

        listaPropietarios.set(indice, propietarioModificado);
        return true;
    }

    /**
     * Elimina un propietario según su índice.
     */
    public static boolean eliminar(int indice) {

        if (indice < 0 || indice >= listaPropietarios.size()) {
            return false;
        }

        listaPropietarios.remove(indice);
        return true;
    }

    /**
     * Elimina un propietario utilizando su cédula.
     */
    public static boolean eliminarPorCedula(int cedula) {

        int indice = buscarIndice(cedula);

        if (indice == -1) {
            return false;
        }

        listaPropietarios.remove(indice);
        return true;
    }

    /**
     * Verifica si una cédula ya está registrada.
     *
     * El índice ignorado se utiliza al modificar un registro.
     */
    public static boolean cedulaExiste(
            int cedula,
            int indiceIgnorado) {

        for (int i = 0; i < listaPropietarios.size(); i++) {

            if (i != indiceIgnorado
                    && listaPropietarios.get(i).getCedPropiet()
                    == cedula) {

                return true;
            }
        }

        return false;
    }

    /**
     * Retorna la cantidad de propietarios registrados.
     */
    public static int cantidad() {
        return listaPropietarios.size();
    }

    /**
     * Verifica si el ArrayList está vacío.
     */
    public static boolean estaVacio() {
        return listaPropietarios.isEmpty();
    }
}
