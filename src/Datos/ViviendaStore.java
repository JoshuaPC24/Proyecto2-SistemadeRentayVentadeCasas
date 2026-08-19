package Datos;

import Logica.Vivienda;
import java.util.ArrayList;

/**
 * Guarda y administra el ArrayList de viviendas del sistema.
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

    /**
     * Agrega una vivienda nueva al ArrayList.
     */
    public void insertarVivienda(Vivienda vivienda) {
        if (this.listaViviendas != null) {
            this.listaViviendas.add(vivienda);
        }
    }

    /**
     * Reemplaza la vivienda que está en la posición index.
     */
    public void editarVivienda(int index, Vivienda nueva) {
        if (index >= 0 && nueva != null && !listaViviendas.isEmpty()) {
            this.listaViviendas.set(index, nueva);
        }
    }

    /**
     * Elimina una vivienda del ArrayList.
     *
     * @return true si se pudo eliminar, false si no existía
     */
    public boolean eliminarVivienda(Vivienda vivienda) {
        if (this.listaViviendas.contains(vivienda)) {
            this.listaViviendas.remove(vivienda);
            return true;
        }
        return false;  // La vivienda no existe en el ArrayList
    }

    /**
     * Busca una vivienda por su id.
     *
     * @return la vivienda encontrada o null si no existe
     */
    public Vivienda buscarId(int idVivienda) {
        for (Vivienda v : listaViviendas) {
            if (v.getIdVivienda() == idVivienda) {
                return v;
            }
        }
        return null;
    }

    /**
     * Retorna solamente las viviendas que se pueden alquilar
     * (estado Disponible).
     */
    public ArrayList<Vivienda> listaDisponibles() {
        ArrayList<Vivienda> disponibles = new ArrayList();

        for (Vivienda v : listaViviendas) {
            if (v.getEstado().equalsIgnoreCase("Disponible")) {
                disponibles.add(v);
            }
        }
        return disponibles;
    }

    /**
     * Retorna la cantidad de viviendas registradas.
     */
    public int cantidad() {
        return listaViviendas.size();
    }
}
