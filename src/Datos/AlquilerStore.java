package Datos;

import Logica.Alquileres;
import Logica.Vivienda;
import java.util.ArrayList;

/**
 *
 * @author mauri
 */
public class AlquilerStore {

    private ArrayList<Alquileres> listaAlquileres;

    public AlquilerStore() {
        this.listaAlquileres = new ArrayList();
    }

    public ArrayList<Alquileres> getListaAlquileres() {
        return listaAlquileres;
    }

    public void setListaAlquileres(ArrayList<Alquileres> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
    }

    //Métodos del CRUD
    public void insertarAlquiler(Alquileres alquiler) {
        if (this.listaAlquileres != null) {
            this.listaAlquileres.add(alquiler);

            //Si el alquiler queda vigente, la vivienda pasa a alquilada
            if (alquiler.getEstado().equalsIgnoreCase("Vigente")) {
                alquiler.getVivienda().setEstado("Alquilada");
            }
        }
    }

    public void editarAlquiler(int index, Alquileres nuevo) {
        if (index >= 0 && nuevo != null && !listaAlquileres.isEmpty()) {

            Vivienda viviendaAnterior = listaAlquileres.get(index).getVivienda();

            //Si cambió de vivienda, la anterior vuelve a estar disponible
            if (viviendaAnterior != nuevo.getVivienda()) {
                viviendaAnterior.setEstado("Disponible");
            }

            if (nuevo.getEstado().equalsIgnoreCase("Vigente")) {
                nuevo.getVivienda().setEstado("Alquilada");
            } else {
                nuevo.getVivienda().setEstado("Disponible");
            }

            this.listaAlquileres.set(index, nuevo);
        }
    }

    public boolean eliminarAlquiler(Alquileres alquiler) {
        if (this.listaAlquileres.contains(alquiler)) {

            alquiler.getVivienda().setEstado("Disponible");
            this.listaAlquileres.remove(alquiler);
            return true;
        }
        return false;  // El alquiler no existe en el ArrayList
    }

    public Alquileres buscarNumero(int numAlquiler) {
        for (Alquileres a : listaAlquileres) {
            if (a.getNumAlquiler() == numAlquiler) {
                return a;
            }
        }
        return null;
    }

    //Genera el siguiente número consecutivo de alquiler
    public int generarNumero() {
        int mayor = 0;

        for (Alquileres a : listaAlquileres) {
            if (a.getNumAlquiler() > mayor) {
                mayor = a.getNumAlquiler();
            }
        }
        return mayor + 1;
    }

    public ArrayList<Alquileres> listaVigentes() {
        ArrayList<Alquileres> vigentes = new ArrayList();

        for (Alquileres a : listaAlquileres) {
            if (a.getEstado().equalsIgnoreCase("Vigente")) {
                vigentes.add(a);
            }
        }
        return vigentes;
    }

    //Se usa para no permitir eliminar un inquilino con alquileres
    public boolean inquilinoTieneAlquiler(int cedInquilino) {
        for (Alquileres a : listaAlquileres) {
            if (a.getInquilino().getCedInqui() == cedInquilino) {
                return true;
            }
        }
        return false;
    }

    //Se usa para no permitir eliminar una vivienda con alquileres
    public boolean viviendaTieneAlquiler(int idVivienda) {
        for (Alquileres a : listaAlquileres) {
            if (a.getVivienda().getIdVivienda() == idVivienda) {
                return true;
            }
        }
        return false;
    }

    public int cantidad() {
        return listaAlquileres.size();
    }
}
