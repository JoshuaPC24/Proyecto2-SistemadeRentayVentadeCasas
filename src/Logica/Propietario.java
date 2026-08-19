package Logica;

/**
 * Guarda la información de los dueños de las viviendas
 * que se dan en alquiler.
 *
 * La cédula (cedPropiet) es única, no se puede repetir.
 */
public class Propietario {

    private int cedPropiet;
    private String nomPropiet;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;

    // Constructor vacío
    public Propietario() {
    }

    /**
     * Crea un propietario con todos sus datos.
     *
     * @param cedPropiet cédula del propietario, es única
     */
    public Propietario(int cedPropiet, String nomPropiet, String genero,
                       String direccion, String telefono, String email) {
        this.cedPropiet = cedPropiet;
        this.nomPropiet = nomPropiet;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters

    public int getCedPropiet() {
        return cedPropiet;
    }

    public void setCedPropiet(int cedPropiet) {
        this.cedPropiet = cedPropiet;
    }

    public String getNomPropiet() {
        return nomPropiet;
    }

    public void setNomPropiet(String nomPropiet) {
        this.nomPropiet = nomPropiet;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Permite mostrar el propietario en JComboBox o JList
    @Override
    public String toString() {
        return cedPropiet + " - " + nomPropiet;
    }
}