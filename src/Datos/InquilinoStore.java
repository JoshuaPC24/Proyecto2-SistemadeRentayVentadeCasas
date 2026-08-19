package Datos;

import Logica.Inquilino;
import java.util.ArrayList;

/**
 * Guarda y administra el ArrayList de inquilinos del sistema.
 *
 * @author mauri
 * @author Joshua
 * @author Alejandro
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

    /**
     * Agrega un inquilino nuevo al ArrayList.
     */
    public void insertarInquilino(Inquilino inquilino) {
        if (this.listaInquilinos != null) {
            this.listaInquilinos.add(inquilino);
        }
    }

    /**
     * Reemplaza el inquilino que está en la posición index.
     */
    public void editarInquilino(int index, Inquilino nuevo) {
        if (index >= 0 && nuevo != null && !listaInquilinos.isEmpty()) {
            this.listaInquilinos.set(index, nuevo);
        }
    }

    /**
     * Elimina un inquilino del ArrayList.
     *
     * @return true si se pudo eliminar, false si no existía
     */
    public boolean eliminarInquilino(Inquilino inquilino) {
        if (this.listaInquilinos.contains(inquilino)) {
            this.listaInquilinos.remove(inquilino);
            return true;
        }
        return false;  // El inquilino no existe en el ArrayList
    }

    /**
     * Busca un inquilino por su cédula.
     *
     * @return el inquilino encontrado o null si no existe
     */
    public Inquilino buscarCedula(int cedula) {
        for (Inquilino i : listaInquilinos) {
            if (i.getCedInqui() == cedula) {
                return i;
            }
        }
        return null;
    }

    /**
     * Retorna la cantidad de inquilinos registrados.
     */
    public int cantidad() {
        return listaInquilinos.size();
    }
}
