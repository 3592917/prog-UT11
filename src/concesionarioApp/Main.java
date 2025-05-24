package concesionarioApp;

import concesionarioApp.dominio.Persona;
import concesionarioApp.dominio.Vehiculo;
import concesionarioApp.gestorBBDD.ConexionBBDD;
import concesionarioApp.gestorBBDD.GestorP11;
import concesionarioApp.gestorBBDD.PropietarioDAO;
import concesionarioApp.gestorBBDD.VehiculoDAO;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static GestorConcesionario gestor;

    public static void main(String[] args) {
        initialize();
        System.out.println("Bienvenid@");
        int opcion = 0;
        while (opcion != 9) {
            mostrarMenu();
            opcion = leerEntero("Tu selección: ");
            manejarOpcion(opcion);
        }
    }

    private static void initialize() {
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            VehiculoDAO vehiculoDAO = new VehiculoDAO(conexionBBDD);
            PropietarioDAO propietarioDAO = new PropietarioDAO(conexionBBDD);
            gestor = new GestorP11(vehiculoDAO, propietarioDAO);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("Elige una opción ");
        System.out.println("1. Insertar nuevo propietario ");
        System.out.println("2. Recuperar vehículos de un propietario ");
        System.out.println("3. Eliminar propietario ");
        System.out.println("4. Insertar nuevo vehiculo ");
        System.out.println("5. Actualizar propietario de vehiculo ");
        System.out.println("6. Eliminar vehiculo ");
        System.out.println("7. Buscar vehículos por marca: ");
        System.out.println("8. Listar vehículos ");
        System.out.println("9. Salir ");
    }

    private static void manejarOpcion(Integer opcion) {
        switch (opcion) {
            case 1 -> insertarPropietario();
            case 2 -> recuperarVehiculosPropietario();
            case 3 -> eliminarPropietario();
            case 4 -> insertarVehiculo();
            case 5 -> actualizarPropietario();
            case 6 -> eliminarVehiculo();
            case 7 -> buscarVehiculoMarca();
            case 8 -> listarVehiculos();
            case 9 -> salir();
            default -> System.out.println("Opción inválida. Seleccione un número entre 1 y 9.");
        }
    }

    private static void salir() {
        gestor.cerrarConexion();
        System.out.println("Hasta pronto!");
    }

    private static void insertarPropietario() {
        if (gestor.insertarPropietario(leerDatosPersona("Introduce los datos de la persona propietaria")).equals(0)) {
            System.out.println("Propietario añadido correctamente.");
        } else {
            System.out.println("Propietario no añadido.");
        }
    }

    private static void recuperarVehiculosPropietario() {
        List<String> vehiculos = gestor.obtenerVehiculosPropietario(leerCadena("Introduce el dni del propietario: "));
        if (!vehiculos.isEmpty()) {
            for (String s : vehiculos) {
                System.out.println(s);
            }
        } else {
            System.out.println("No existen vehículos que coincidan con el criterio.");
        }
    }

    private static void eliminarPropietario() {
        if (gestor.eliminarPropietario(leerCadena("Introduce el dni del propietario a eliminar: ")).equals(1)) {
            System.out.println("Propietario eliminado correctamente.");
        } else {
            System.out.println("No se eliminó ningún propietario.");
        }
    }

    private static void insertarVehiculo() {
        if (gestor.insertarVehiculo(leerDatosVehiculo()).equals(0)) {
            System.out.println("Vehiculo añadido correctamente");
        } else {
            System.out.println("Vehiculo no añadido");
        }
    }

    private static void actualizarPropietario() {
        if (gestor.actualizarPropietarioVehiculo(leerCadena("Introduce el DNI del nuevo propietario: "),
                leerCadena("Introduce la matricula del vehiculo a actualizar: ")).equals(0)) {
            System.out.println("Propietario actualizado correctamente");
        } else {
            System.out.println("Propietario de vehiculo no actualizado. ");
        }
    }

    private static void eliminarVehiculo() {
        if (gestor.eliminarVehiculo(leerCadena("Introduce la matricula del vehiculo a eliminar: ")).equals(1)) {
            System.out.println("Vehiculo eliminado correctamente. ");
        } else {
            System.out.println("No se pudo eliminar el vehiculo. ");
        }
    }

    private static void buscarVehiculoMarca() {
        List<String> vehiculos = gestor.obtenerVehiculosMarca(leerCadena("Introduce la marca del vehiculo: "));
        if (!vehiculos.isEmpty()) {
            for (String s : vehiculos) {
                System.out.println(s);
            }
        } else {
            System.out.println("No existen vehículos que coincidan con el criterio.");
        }
    }

    private static void listarVehiculos() {
        List<String> vehiculos = gestor.obtenerVehiculos();
        if (!vehiculos.isEmpty()) {
            for (String s : vehiculos) {
                System.out.println(s);
            }
        } else {
            System.out.println("No existen vehículos en el concesionario. ");
        }
    }


    private static String leerCadena(String mensaje) {
        boolean valido = false;
        String cadena = "";
        while (!valido) {
            System.out.print(mensaje);
            cadena = sc.nextLine();
            if (!cadena.trim().isEmpty()) {
                valido = true;
            } else {
                System.err.println("Error. La cadena no puede ser vacía.");
                System.out.println();
            }
        }
        return cadena;
    }

    private static Integer leerEntero(String mensaje) {
        int num = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                num = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.err.println("Error. Introduce un número válido.");
                System.out.println();
            }
        }
        return num;
    }

    private static Double leerDecimal(String mensaje) {
        double num = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                num = Double.parseDouble(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.err.println("Error. Introduce un número decimal válido. El carácter '.' delimita el decimal.");
                System.out.println();
            }
        }
        return num;
    }

    private static Persona leerDatosPersona(String mensaje) {
        System.out.println(mensaje);
        String nombre = leerCadena("Introduce el nombre: ");
        String apellidos = leerCadena("Introduce los apellidos: ");
        String dni = leerCadena("Introduce el dni: ");
        while (!dni.matches("^\\d{8}[A-Z]$")) {
            System.out.println("El dni debe tener un formato válido. Ej 12345678A");
            dni = leerCadena("Introduce el dni: ");
        }
        return new Persona(dni, nombre, apellidos);
    }

    private static Vehiculo leerDatosVehiculo() {
        return new Vehiculo(leerCadena("Introduce la matricula: "),
                leerCadena("Introduce la marca del vehiculo: "),
                leerEntero("Introduce el año de matriculación: "),
                leerDecimal("Introduce el precio de venta: "),
                leerEntero("Introduce el kilometraje: "));
    }
}
