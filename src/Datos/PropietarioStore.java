package Datos;

import Logica.Propietario;
import java.util.ArrayList;

/**
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
    public void insertarPropietario(Propietario propietario) {
        if (this.listaPropietarios != null) {
            this.listaPropietarios.add(propietario);
        }
    }

    public void editarPropietario(int index, Propietario nuevo) {
        if (index >= 0 && nuevo != null && !listaPropietarios.isEmpty()) {
            this.listaPropietarios.set(index, nuevo);
        }
    }

    public boolean eliminarPropietario(Propietario propietario) {
        if (this.listaPropietarios.contains(propietario)) {
            this.listaPropietarios.remove(propietario);
            return true;
        }
        return false;  // El propietario no existe en el ArrayList
    }

    public Propietario buscarCedula(int cedula) {
        for (Propietario p : listaPropietarios) {
            if (p.getCedPropiet() == cedula) {
                return p;
            }
        }
        return null;
    }

    public int cantidad() {
        return listaPropietarios.size();
    }
}
