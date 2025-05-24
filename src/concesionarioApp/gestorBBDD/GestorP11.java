package concesionarioApp.gestorBBDD;

import concesionarioApp.GestorConcesionario;
import concesionarioApp.dominio.Persona;
import concesionarioApp.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class GestorP11 implements GestorConcesionario {
    private final VehiculoDAO vehiculoDAO;
    private final PropietarioDAO propietarioDAO;

    public GestorP11(VehiculoDAO vehiculoDAO, PropietarioDAO propietarioDAO) {
        this.vehiculoDAO = vehiculoDAO;
        this.propietarioDAO = propietarioDAO;
    }

    @Override
    public Integer insertarPropietario(Persona propietario) {
        int resultado = -1;
            if (propietario != null) {
                Integer filas = propietarioDAO.insertarPropietario(propietario);
                if(filas != null && filas > 0){
                    resultado = 0;
                }
            }
        return resultado;
    }

    @Override
    public Integer insertarVehiculo(Vehiculo vehiculo) {
        int resultado = -1;
        if (vehiculo != null) {
            Integer filas = vehiculoDAO.insertarVehiculo(vehiculo);
            if(filas != null && filas > 0){
                resultado = 0;
            }
        }
        return resultado;
    }

    @Override
    public Integer actualizarPropietarioVehiculo(String dniPropietario, String matricula) {
        int resultado = -1;
        Vehiculo vehiculo = vehiculoDAO.getVehiculoPorMatricula(matricula);
        Persona propietario = propietarioDAO.getPersonaPorDNI(dniPropietario);
        if (vehiculo != null && propietario != null) {
            Integer filas = vehiculoDAO.updatePropietarioMatriculaDNI(dniPropietario, matricula);
            if(filas != null && filas > 0){
                resultado = 0;
            }
        }
        return resultado;
    }

    @Override
    public List<String> obtenerVehiculosPropietario(String dniPropietario) {
        List<String> vehiculosString = new ArrayList<>();
        List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosPorDni(dniPropietario);
        if (!vehiculos.isEmpty()) {
            vehiculosString.add(vehiculos.toString());
        }
        return vehiculosString;
    }

    @Override
    public List<String> obtenerVehiculosMarca(String marca) {
        List<String> vehiculosString = new ArrayList<>();
        List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosPorMarca(marca);
        if (!vehiculos.isEmpty()) {
            vehiculosString.add(vehiculos.toString());
        }
        return vehiculosString;
    }

    @Override
    public List<String> obtenerVehiculos() {
        List<String> vehiculosString = new ArrayList<>();
        List<Vehiculo> vehiculos = vehiculoDAO.getVehiculos();
        if (!vehiculos.isEmpty()) {
            vehiculosString.add(vehiculos.toString());
        }
        return vehiculosString;
    }

    @Override
    public Integer eliminarPropietario(String dniPropietario) {
        Integer resultado = 0;
        if (!dniPropietario.isEmpty()) {
            resultado = propietarioDAO.eliminarPropietario(dniPropietario);
        }
        return resultado;
    }

    @Override
    public Integer eliminarVehiculo(String matricula) {
        Integer resultado = 0;
        if (!matricula.isEmpty()) {
            resultado = vehiculoDAO.eliminarVehiculo(matricula);
        }
        return resultado;
    }

    @Override
    public void cerrarConexion() {
        vehiculoDAO.cerrarConexion();
        propietarioDAO.cerrarConexion();
    }
}
