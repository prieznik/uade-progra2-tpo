package Clase5_Ejercicio8;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            sistema.mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1:
                    sistema.crearMateria();
                    break;
                case 2:
                    sistema.agregarUltimaMateriaAlDiccionario();
                    break;
                case 3:
                    sistema.agregarNotaMateria();
                    break;
                case 4:
                    sistema.eliminarNotaMateria();
                    break;
                case 5:
                    sistema.eliminarAlumno();
                    break;
                case 6:
                    sistema.obtenerNotasAlumno();
                    break;
                case 7:
                    sistema.obtenerTodosAlumnos();
                    break;
                case 8:
                    sistema.obtenerAlumnosConPromedio();
                    break;
                case 9:
                    sistema.imprimirUltimaMateria();
                    break;
                case 10:
                    sistema.mostrarDiccionarioGeneral();
                    break;
                case 0:
                    System.out.println("Terminando el programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intentar nuevamente.");
            }

            if (opcion != 0) {
                System.out.println("\nPresionar Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 0);
        scanner.close();
    }

}