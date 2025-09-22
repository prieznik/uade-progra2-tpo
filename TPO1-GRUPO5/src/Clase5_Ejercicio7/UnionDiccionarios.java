package Clase5_Ejercicio7;

import TDAs.List.LinkedList;
import TDAs.Map.Entrada;
import TDAs.Map.Entry;

public class UnionDiccionarios {
    private LinkedList<Entry<Integer,Integer>> listaUnificada;

    public UnionDiccionarios() {
        this.listaUnificada = new LinkedList<>();
    }

    public void unirDos(Materia m1, Materia m2) {
        for (Entry<Integer, Integer> e1 : m1.getNotas().entries()) {
            Integer dni1 = e1.getKey();
            Integer nota1 = e1.getValue();

            // Recorro todas las entradas de M2
            for (Entry<Integer, Integer> e2 : m2.getNotas().entries()) {
                Integer dni2 = e2.getKey();
                Integer nota2 = e2.getValue();

                // Comparo DNIs
                if (dni1.equals(dni2)) {
                    // Si los DNIs coinciden pero las notas son distintas
                    if (!nota1.equals(nota2)) {
                        listaUnificada.addLast(new Entrada<>(dni1, nota1)); // entrada de M1
                        listaUnificada.addLast(new Entrada<>(dni2, nota2)); // entrada de M2
                    }
                }
            }
        }
        }

    public void imprimirListaUnificada() {
        if (listaUnificada.getSize() == 0){
            System.out.println("La lista unificada está vacía.");
            return;
        }

        System.out.println("Lista unificada de entradas con mismo DNI pero diferente nota:");
        listaUnificada.First(); // me posiciono en el primer nodo

        while (!listaUnificada.atEnd()) { // mientras no llegue al final
            Entry<Integer,Integer> e = listaUnificada.getCurrent();
            if (e != null) {
                System.out.println("DNI: " + e.getKey() + " | Nota: " + e.getValue());
            }
            listaUnificada.advance(); // avanzo
        }
    }
}
