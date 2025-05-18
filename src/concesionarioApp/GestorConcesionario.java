package concesionarioApp;

import concesionarioApp.dominio.Persona;
import concesionarioApp.dominio.Vehiculo;

import java.util.List;

public interface GestorConcesionario {
    /**
     * Inserta un nuevo propietario en la base de datos
     *
     * @param propietario el propietario a insertar
     * @return 0 si la operación se realizó con éxito, -1 en caso contrario
     */
    Integer insertarPropietario(Persona propietario);

    /**
     * Inserta un nuevo vehículo en la base de datos
     *
     * @param vehiculo el vehículo a insertar
     * @return 0 si la operación se realizó con éxito, -1 en caso contrario
     */
    Integer insertarVehiculo(Vehiculo vehiculo);

    /**
     * Actualiza el propietario de un vehículo en la base de datos
     *
     * @param dniPropietario el dni del nuevo propietario
     * @param matricula      la matricula del vehiculo a actualizar
     * @return 0 si la operación se realizó con éxito o -1 en caso contrario
     */
    Integer actualizarPropietarioVehiculo(String dniPropietario, String matricula);

    /**
     * Obtiene todos los vehículos de un propietario de la base de datos
     * y los devuelve en forma de una lista de cadenas.
     *
     * @param dniPropietario dni del propietario
     * @return Lista de vehículos encontrados en formato String
     */
    List<String> obtenerVehiculosPropietario(String dniPropietario);

    /**
     * Obtiene todos los vehículos correspondientes a una marca de la base de datos
     * y los devuelve en forma de una lista de cadenas.
     *
     * @param marca la marca del vehiculo
     * @return Lista de vehículos encontrados en formato String
     */
    List<String> obtenerVehiculosMarca(String marca);

    /**
     * Obtiene todos los vehículos de la base de datos
     * y los devuelve en forma de una lista de cadenas.
     *
     * @return Lista de vehículos en formato String
     */
    List<String> obtenerVehiculos();

    /**
     * Elimina un propietario de la base de datos
     *
     * @param dniPropietario el dni del propietario a eliminar
     * @return número de registros actualizados de la base de datos
     */
    Integer eliminarPropietario(String dniPropietario);

    /**
     * Elimina un vehiculo de la base de datos
     *
     * @param matricula la matricula del vehiculo a eliminar
     * @return número de registros actualizados de la base de datos.
     */
    Integer eliminarVehiculo(String matricula);

    /**
     * Cierra la conexión activa de base de datos de todos los DAOs
     */
    void cerrarConexion();

}
