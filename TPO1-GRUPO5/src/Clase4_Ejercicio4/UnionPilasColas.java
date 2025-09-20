package Clase4_Ejercicio4;

import TDAs.Stack.*;
import TDAs.queue.*;

public class UnionPilasColas {

    public static void main(String[] args) {
        // Crear P1: [ [1,2,3], [4,5,6,7] ]
        // En la pila: base=[1,2,3] tope=[4,5,6,7]
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

        P1.push(cola1);  // Primero la más pequeña (base)
        P1.push(cola2);  // Luego la más grande (tope)

        // Crear P2: [ [0], [8,9,10,11,12] ]
        // En la pila: base=[0] tope=[8,9,10,11,12]
        Stack<Queue<Integer>> P2 = new LinkedStack<>();

        Queue<Integer> cola3 = new LinkedQueue<>();
        cola3.enqueue(0);

        Queue<Integer> cola4 = new LinkedQueue<>();
        cola4.enqueue(8);
        cola4.enqueue(9);
        cola4.enqueue(10);
        cola4.enqueue(11);
        cola4.enqueue(12);

        P2.push(cola3);  // Primero la más pequeña (base)
        P2.push(cola4);  // Luego la más grande (tope)

        System.out.println("P1 original:");
        imprimirPilaSimple(P1);

        System.out.println("\nP2 original:");
        imprimirPilaSimple(P2);

        // Llamar al método de unión
        Stack<Queue<Integer>> Pout = unirPilasOrdenadas(P1, P2);

        System.out.println("\nPout resultado:");
        imprimirPilaSimple(Pout);

        // Verificar que P1 y P2 siguen intactas
        System.out.println("\nP1 después de la unión:");
        imprimirPilaSimple(P1);

        System.out.println("\nP2 después de la unión:");
        imprimirPilaSimple(P2);
    }

    /**
     * Une dos pilas de colas ordenadas por tamaño
     * @param P1 Primera pila (ordenada: menor en base, mayor en tope)
     * @param P2 Segunda pila (ordenada: menor en base, mayor en tope)
     * @return Nueva pila con la unión ordenada
     */
    public static Stack<Queue<Integer>> unirPilasOrdenadas(Stack<Queue<Integer>> P1, Stack<Queue<Integer>> P2) {
        // Pilas auxiliares para no modificar las originales
        Stack<Queue<Integer>> auxP1 = new LinkedStack<>();
        Stack<Queue<Integer>> auxP2 = new LinkedStack<>();
        Stack<Queue<Integer>> tempP1 = new LinkedStack<>();
        Stack<Queue<Integer>> tempP2 = new LinkedStack<>();

        // Copiar P1 a auxP1 (invirtiendo para tener menor en tope)
        while (!P1.isEmpty()) {
            Queue<Integer> cola = P1.pop();
            auxP1.push(copiarCola(cola));
            tempP1.push(cola);
        }
        // Restaurar P1
        while (!tempP1.isEmpty()) {
            P1.push(tempP1.pop());
        }

        // Copiar P2 a auxP2 (invirtiendo para tener menor en tope)
        while (!P2.isEmpty()) {
            Queue<Integer> cola = P2.pop();
            auxP2.push(copiarCola(cola));
            tempP2.push(cola);
        }
        // Restaurar P2
        while (!tempP2.isEmpty()) {
            P2.push(tempP2.pop());
        }

        // Ahora auxP1 y auxP2 tienen el elemento MENOR en el tope
        // Hacer merge en una pila temporal
        Stack<Queue<Integer>> poutTemp = new LinkedStack<>();

        while (!auxP1.isEmpty() && !auxP2.isEmpty()) {
            Queue<Integer> cola1 = auxP1.top();
            Queue<Integer> cola2 = auxP2.top();

            if (cola1.size() <= cola2.size()) {
                poutTemp.push(auxP1.pop());
            } else {
                poutTemp.push(auxP2.pop());
            }
        }

        // Agregar elementos restantes de auxP1
        while (!auxP1.isEmpty()) {
            poutTemp.push(auxP1.pop());
        }

        // Agregar elementos restantes de auxP2
        while (!auxP2.isEmpty()) {
            poutTemp.push(auxP2.pop());
        }

        // poutTemp ahora tiene el mayor en el tope (orden inverso al deseado)
        // Necesitamos invertir para tener menor en base, mayor en tope
        Stack<Queue<Integer>> Pout = new LinkedStack<>();
        while (!poutTemp.isEmpty()) {
            Pout.push(poutTemp.pop());
        }

        return Pout;
    }

    /**
     * Copia una cola sin modificar la original
     */
    private static Queue<Integer> copiarCola(Queue<Integer> original) {
        Queue<Integer> copia = new LinkedQueue<>();
        Queue<Integer> temp = new LinkedQueue<>();
        int size = original.size();

        // Desencolar todos los elementos
        for (int i = 0; i < size; i++) {
            Integer elem = original.dequeue();
            temp.enqueue(elem);
            copia.enqueue(elem);
        }

        // Restaurar la cola original
        for (int i = 0; i < size; i++) {
            original.enqueue(temp.dequeue());
        }

        return copia;
    }

    /**
     * Imprime el contenido de una pila de colas en formato simple
     */
    public static void imprimirPilaSimple(Stack<Queue<Integer>> pila) {
        Stack<Queue<Integer>> temp = new LinkedStack<>();
        Stack<String> representacion = new LinkedStack<>();

        // Sacar elementos y guardar representación
        while (!pila.isEmpty()) {
            Queue<Integer> cola = pila.pop();
            String colaStr = colaToString(cola);
            representacion.push(colaStr);
            temp.push(cola);
        }

        // Restaurar pila
        while (!temp.isEmpty()) {
            pila.push(temp.pop());
        }

        // Imprimir en formato [ [cola1], [cola2], ... ]
        Stack<String> paraImprimir = new LinkedStack<>();
        while (!representacion.isEmpty()) {
            paraImprimir.push(representacion.pop());
        }

        System.out.print("[ ");
        boolean primero = true;
        while (!paraImprimir.isEmpty()) {
            String elem = paraImprimir.pop();
            if (!primero) System.out.print(", ");
            System.out.print(elem);
            primero = false;
        }
        System.out.println(" ]");
    }

    /**
     * Convierte una cola a String sin modificarla
     */
    private static String colaToString(Queue<Integer> cola) {
        Queue<Integer> temp = new LinkedQueue<>();
        StringBuilder sb = new StringBuilder("[");
        int size = cola.size();

        for (int i = 0; i < size; i++) {
            Integer elem = cola.dequeue();
            if (i > 0) sb.append(",");
            sb.append(elem);
            temp.enqueue(elem);
        }
        sb.append("]");

        // Restaurar cola
        for (int i = 0; i < size; i++) {
            cola.enqueue(temp.dequeue());
        }

        return sb.toString();
    }
}