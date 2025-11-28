import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaConcesionariaVehiculos gestion = new SistemaConcesionariaVehiculos();        
        Scanner scanner = new Scanner(System.in);

        String input;
        int opcion = 0;

        do {
            gestion.iniciarSistema();
            input = scanner.nextLine();

            /**
             * Trata de parseas a un integer,
             * si es una letra fallará entonces el error
             * provocará que salga.
             **/
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n¡Gracias por usar el Sistema de Concesionaria!");
                break;
            }

            switch (opcion) {
                case 1:
                    //agregar vehículo
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 2:
                    //Eliminar vehículo
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 3:
                    //Actualizar vehículo
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 4:
                    //Listar vehículos en mantenimiento
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 5:
                    //Listar vehículos en lavadero
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 6:
                    //Mostrar listado de vehículos
                    gestion.mostrarListadoVehiculos();
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 7:
                    //Futura fucnionalidad
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 8:
                    //Futura fucnionalidad
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    System.out.println("\nPresione Enter para continuar...");
                    scanner.nextLine();
            }

        } while (true);

        scanner.close();
    }

}
