package Datos;

import Logica.Vivienda;
import java.util.ArrayList;

/**
 *
 * @author mauri
 */
public class ViviendaStore {

    private ArrayList<Vivienda> listaViviendas;

    public ViviendaStore() {
        this.listaViviendas = new ArrayList();
    }

    public ArrayList<Vivienda> getListaViviendas() {
        return listaViviendas;
    }

    public void setListaViviendas(ArrayList<Vivienda> listaViviendas) {
        this.listaViviendas = listaViviendas;
    }

    //Métodos del CRUD
    public void insertarVivienda(Vivienda vivienda) {
        if (this.listaViviendas != null) {
            this.listaViviendas.add(vivienda);
        }
    }

    public void editarVivienda(int index, Vivienda nueva) {
        if (index >= 0 && nueva != null && !listaViviendas.isEmpty()) {
            this.listaViviendas.set(index, nueva);
        }
    }

    public boolean eliminarVivienda(Vivienda vivienda) {
        if (this.listaViviendas.contains(vivienda)) {
            this.listaViviendas.remove(vivienda);
            return true;
        }
        return false;  // La vivienda no existe en el ArrayList
    }

    public Vivienda buscarId(int idVivienda) {
        for (Vivienda v : listaViviendas) {
            if (v.getIdVivienda() == idVivienda) {
                return v;
            }
        }
        return null;
    }

    //Solamente las viviendas que se pueden alquilar
    public ArrayList<Vivienda> listaDisponibles() {
        ArrayList<Vivienda> disponibles = new ArrayList();

        for (Vivienda v : listaViviendas) {
            if (v.getEstado().equalsIgnoreCase("Disponible")) {
                disponibles.add(v);
            }
        }
        return disponibles;
    }

    public int cantidad() {
        return listaViviendas.size();
    }
}
