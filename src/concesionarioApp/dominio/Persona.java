package concesionarioApp.dominio;

public class Persona {

    private String dni;
    private String nombre;
    private String apellidos;

    public Persona(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return (dni != null || nombre != null || apellidos != null) ?
                "Propietario: " + "\n" +
                        ((dni != null) ? ( "DNI: " + dni + "\n") : "") +
                        ((nombre != null) ? ( "Nombre: " + nombre + "\n") : "") +
                        ((apellidos != null) ? ( "Apellidos: " + apellidos + "\n") : "")
        : "";
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
