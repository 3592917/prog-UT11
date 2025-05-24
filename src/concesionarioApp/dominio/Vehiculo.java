package concesionarioApp.dominio;

public class Vehiculo {

    private String matricula;
    private String marca;
    private Integer anyoMatriculacion;
    private Double precioVenta;
    private Integer kilometraje;
    private Persona propietario;

    public Vehiculo(String matricula, String marca, Integer anyoMatriculacion, Double precioVenta, Integer kilometraje) {
        this.matricula = matricula;
        this.marca = marca;
        this.anyoMatriculacion = anyoMatriculacion;
        this.precioVenta = precioVenta;
        this.kilometraje = kilometraje;
    }

    public Vehiculo(String matricula, String marca, Integer anyoMatriculacion, Double precioVenta, Integer kilometraje, Persona propietario) {
        this.matricula = matricula;
        this.marca = marca;
        this.anyoMatriculacion = anyoMatriculacion;
        this.precioVenta = precioVenta;
        this.kilometraje = kilometraje;
        this.propietario = propietario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnyoMatriculacion() {
        return anyoMatriculacion;
    }

    public void setAnyoMatriculacion(Integer anyoMatriculacion) {
        this.anyoMatriculacion = anyoMatriculacion;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Vehiculo: \n" +
                "Matrícula: " + matricula + "\n" +
                "Marca: " + marca + "\n" +
                "Año de matriculación: " + anyoMatriculacion + "\n" +
                "Precio de venta: " + precioVenta + "\n" +
                "Kilometraje: " + kilometraje + "\n" +
                ((propietario != null) ?
                        (propietario + "\n") : "\n");
    }
}
