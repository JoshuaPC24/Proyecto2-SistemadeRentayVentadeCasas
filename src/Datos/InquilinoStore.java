package Datos;

import Logica.Inquilino;
import java.util.ArrayList;

/**
 *
 * @author mauri
 */
public class InquilinoStore {

    private ArrayList<Inquilino> listaInquilinos;

    public InquilinoStore() {
        this.listaInquilinos = new ArrayList();
    }

    public ArrayList<Inquilino> getListaInquilinos() {
        return listaInquilinos;
    }

    public void setListaInquilinos(ArrayList<Inquilino> listaInquilinos) {
        this.listaInquilinos = listaInquilinos;
    }

    //Métodos del CRUD
    public void insertarInquilino(Inquilino inquilino) {
        if (this.listaInquilinos != null) {
            this.listaInquilinos.add(inquilino);
        }
    }

    public void editarInquilino(int index, Inquilino nuevo) {
        if (index >= 0 && nuevo != null && !listaInquilinos.isEmpty()) {
            this.listaInquilinos.set(index, nuevo);
        }
    }

    public boolean eliminarInquilino(Inquilino inquilino) {
        if (this.listaInquilinos.contains(inquilino)) {
            this.listaInquilinos.remove(inquilino);
            return true;
        }
        return false;  // El inquilino no existe en el ArrayList
    }

    public Inquilino buscarCedula(int cedula) {
        for (Inquilino i : listaInquilinos) {
            if (i.getCedInqui() == cedula) {
                return i;
            }
        }
        return null;
    }

    public int cantidad() {
        return listaInquilinos.size();
    }
}
