package Clase5_Ejercicio7;

import TDAs.Map.ArrayMap;
import TDAs.Map.Entry;

public class Materia {
    private String nombre;
    private ArrayMap<Integer, Integer> notas;

    public Materia(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayMap<>();
    }


    public void ingresarNota(Integer dni, Integer nota) {
        this.notas.put(dni, nota);
    }

    // Metodo para imprimir el diccionario
    public void imprimirDiccionario() {
        System.out.println("Notas de la materia: " + this.nombre);
        for (Entry<Integer, Integer> e : notas.entries()) {
            System.out.println("DNI: " + e.getKey() + "  |  Nota: " + e.getValue());
        }
    }

    // Getters & setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayMap<Integer, Integer> getNotas() {
        return notas;
    }

    public void setNotas(ArrayMap<Integer, Integer> notas) {
        this.notas = notas;
    }

}

