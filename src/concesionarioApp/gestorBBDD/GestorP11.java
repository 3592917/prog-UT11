package concesionarioApp.gestorBBDD;

import concesionarioApp.GestorConcesionario;
import concesionarioApp.dominio.Persona;
import concesionarioApp.dominio.Vehiculo;

import java.sql.SQLException;
import java.util.List;

public class GestorP11 implements GestorConcesionario {
    VehiculoDAO vehiculoDAO;
    PropietarioDAO propietarioDAO;
    ConexionBBDD conexionBBDD = new ConexionBBDD();

    private static final Integer KO = -1;

    public GestorP11(VehiculoDAO vehiculoDAO, PropietarioDAO propietarioDAO) {
        this.vehiculoDAO = vehiculoDAO;
        this.propietarioDAO = propietarioDAO;
    }

    @Override
    public Integer insertarPropietario(Persona propietario) {
        Integer resultado = KO;
            if (propietario != null) {
                resultado = propietarioDAO.insertarPropietario(propietario);
            }
        return resultado;
    }

    @Override
    public Integer insertarVehiculo(Vehiculo vehiculo) {
        Integer resultado = KO;
        if (vehiculo != null) {
            resultado = vehiculoDAO.insertarVehiculo(vehiculo);
        }
        return resultado;
    }

    @Override
    public Integer actualizarPropietarioVehiculo(String dniPropietario, String matricula) {
        Integer resultado = KO;
        Vehiculo vehiculo = vehiculoDAO.getVehiculoPorMatricula(matricula);
        Persona propietario = propietarioDAO.getPersonaPorDNI(dniPropietario);
        if (vehiculo != null && propietario != null) {
            resultado = vehiculoDAO.updatePropietarioMatriculaDNI(dniPropietario, matricula);
        }
        return resultado;
    }

    @Override
    public List<String> obtenerVehiculosPropietario(String dniPropietario) {
        return vehiculoDAO.getVehiculosPorDni(dniPropietario);
    }

    @Override
    public List<String> obtenerVehiculosMarca(String marca) {
        return vehiculoDAO.getVehiculosPorMarca(marca);
    }

    @Override
    public List<String> obtenerVehiculos() {
        return vehiculoDAO.getVehiculos();
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
        try {
            conexionBBDD.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Mensaje: " + e.getMessage() + ", con código: " + e.getErrorCode());
        }
    }
}
