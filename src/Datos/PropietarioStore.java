package Datos;

import Logica.Propietario;
import java.util.ArrayList;

/**
 * Guarda y administra el ArrayList de propietarios del sistema.
 *
 * @author mauri
 */
public class PropietarioStore {

    private ArrayList<Propietario> listaPropietarios;

    public PropietarioStore() {
        this.listaPropietarios = new ArrayList();
    }

    public ArrayList<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public void setListaPropietarios(ArrayList<Propietario> listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }

    //Métodos del CRUD

    /**
     * Agrega un propietario nuevo al ArrayList.
     */
    public void insertarPropietario(Propietario propietario) {
        if (this.listaPropietarios != null) {
            this.listaPropietarios.add(propietario);
        }
    }

    /**
     * Reemplaza el propietario que está en la posición index.
     */
    public void editarPropietario(int index, Propietario nuevo) {
        if (index >= 0 && nuevo != null && !listaPropietarios.isEmpty()) {
            this.listaPropietarios.set(index, nuevo);
        }
    }

    /**
     * Elimina un propietario del ArrayList.
     *
     * @return true si se pudo eliminar, false si no existía
     */
    public boolean eliminarPropietario(Propietario propietario) {
        if (this.listaPropietarios.contains(propietario)) {
            this.listaPropietarios.remove(propietario);
            return true;
        }
        return false;  // El propietario no existe en el ArrayList
    }

    /**
     * Busca un propietario por su cédula.
     *
     * @return el propietario encontrado o null si no existe
     */
    public Propietario buscarCedula(int cedula) {
        for (Propietario p : listaPropietarios) {
            if (p.getCedPropiet() == cedula) {
                return p;
            }
        }
        return null;
    }

    /**
     * Retorna la cantidad de propietarios registrados.
     */
    public int cantidad() {
        return listaPropietarios.size();
    }
}
