package Clase3_Ejercicio7;
import java.util.Scanner; // importo scanner para que el usuario pueda ingresar valores por parámetro

public class Main {
    public static void main(String[] args) {

        Validador validador = new Validador();

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese una cadena: ");
        String cadena = sc.nextLine(); // lee la línea escrita por consola. el programa se frena hasta que recibe una línea por consola

        boolean validezCadena = validador.validarParentesis(cadena);
        if (validezCadena) System.out.println("Es valido"); else System.out.println("Es invalido");

    }
}