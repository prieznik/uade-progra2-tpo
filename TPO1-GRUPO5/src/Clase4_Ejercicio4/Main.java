package Clase4_Ejercicio4;

import TDAs.Stack.*;
import TDAs.queue.*;

public class Main {
    public static void main(String[] args) {

        /*  Implemente un método que reciba dos pilas de colas de enteros P1 y P2,
            y retorne una nueva pila de colas Pout que sea la unión de los elementos de P1 y P2.

            Cabe aclarar que los elementos de P1 y P2 están ordenados de menor a mayor en función de su tamaño,
            y que la pila Pout debe quedar ordenada del mismo modo.

                // P1 -> Pila de colas. Ej: [ [1,2,3], [4,5,6,7] ]
                // P2 -> Pila de colas. Ej: [ [0], [8,9,10,11,12] ]

                // PoutAux -> [  [8,9,10,11,12], [4,5,6,7], [1,2,3], [0] ];
                // Pout -> [ [0], [1,2,3], [4,5,6,7], [8,9,10,11,12] ]

            En el tope de las pilas se encuentra el elemento de mayor tamaño.
            Resuelva el problema planteado exclusivamente en términos de las operaciones de los TDA Pila y TDACola.

            Implementar un metodo que reciba P1 y P2 -> retorne Pout
            Pout es la union de ambas. Ordenada de menor a mayor.
         */

        // Crear P1: [ [1,2,3], [4,5,6,7] ]
        Stack<Queue<Integer>> P1 = new LinkedStack<>();

        Queue<Integer> cola1 = new LinkedQueue<>();
        cola1.enqueue(1);
        cola1.enqueue(2);
        cola1.enqueue(3);

        Queue<Integer> cola2 = new LinkedQueue<>();
        cola2.enqueue(4);
        cola2.enqueue(5);
        cola2.enqueue(6);
        cola2.enqueue(7);

        P1.push(cola1);  // Base: cola de tamaño 3
        P1.push(cola2);  // Tope: cola de tamaño 4

        // Crear P2: [ [0], [8,9,10,11,12] ]
        Stack<Queue<Integer>> P2 = new LinkedStack<>();

        Queue<Integer> cola3 = new LinkedQueue<>();
        cola3.enqueue(0);

        Queue<Integer> cola4 = new LinkedQueue<>();
        cola4.enqueue(8);
        cola4.enqueue(9);
        cola4.enqueue(10);
        cola4.enqueue(11);
        cola4.enqueue(12);

        P2.push(cola3);  // Base: cola de tamaño 1
        P2.push(cola4);  // Tope: cola de tamaño 5

        // Imprimir entradas
        System.out.print("P1 -> ");
        UnionPilasColas.imprimirPilaSimple(P1);

        System.out.print("P2 -> ");
        UnionPilasColas.imprimirPilaSimple(P2);

        // Llamar al método de unión
        Stack<Queue<Integer>> Pout = UnionPilasColas.unirPilasOrdenadas(P1, P2);

        // Imprimir salida
        System.out.print("Pout -> ");
        UnionPilasColas.imprimirPilaSimple(Pout);
    }
}