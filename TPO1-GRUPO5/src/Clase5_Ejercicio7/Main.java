package Clase5_Ejercicio7;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear materia 1
        System.out.print("Ingrese el nombre de la primera materia: ");
        String nombreMateria1 = scanner.nextLine();
        Materia materia1 = new Materia(nombreMateria1);

        // Ingresar notas para materia
        System.out.println("\n--- Ingresando notas para " + nombreMateria1 + " ---");
        System.out.print("¿Cuántas notas desea ingresar? ");
        int cantidadNotas1 = scanner.nextInt();

        for (int i = 0; i < cantidadNotas1; i++) {
            System.out.print("Ingrese DNI del alumno " + (i+1) + ": ");
            int dni = scanner.nextInt();
            System.out.print("Ingrese nota: ");
            int nota = scanner.nextInt();
            materia1.ingresarNota(dni, nota);
        }

        // Crear materia 2
        scanner.nextLine(); // Limpiar buffer
        System.out.print("\nIngrese el nombre de la segunda materia: ");
        String nombreMateria2 = scanner.nextLine();
        Materia materia2 = new Materia(nombreMateria2);

        // Ingresar notas para Física
        System.out.println("\n--- Ingresando notas para " + nombreMateria2 + " ---");
        System.out.print("¿Cuántas notas desea ingresar? ");
        int cantidadNotas2 = scanner.nextInt();

        for (int i = 0; i < cantidadNotas2; i++) {
            System.out.print("Ingrese DNI del alumno " + (i+1) + ": ");
            int dni = scanner.nextInt();
            System.out.print("Ingrese nota: ");
            int nota = scanner.nextInt();
            materia2.ingresarNota(dni, nota);
        }

        // Mostrar diccionarios
        System.out.println("\n=== DICCIONARIOS ===");
        materia1.imprimirDiccionario();
        materia2.imprimirDiccionario();

        // Unir diccionarios
        UnionDiccionarios union = new UnionDiccionarios();
        union.unirDos(materia1, materia2);

        System.out.println("\n=== LISTA UNIFICADA ===");
        union.imprimirListaUnificada();

        scanner.close();

        // FIN EJ 7 (ver borrador mental en Consigna.txt).
    }
}
