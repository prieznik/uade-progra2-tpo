package Clase5_Ejercicio8;
import java.util.Scanner;
import TDAs.List.LinkedList;
import TDAs.Map.ArrayMap;
import TDAs.Map.Entry;

public class Sistema {
    // diccionario general (diccionario -> comienza vacio)
    private ArrayMap<String, Materia> diccionarioGeneral;

    // lista de materias (lista dinamica)
    private LinkedList<Materia> listaMaterias;

    public Sistema() {
        this.diccionarioGeneral = new ArrayMap<>();
        this.listaMaterias = new LinkedList<>();
    }

    // crear una materia (se guarda en la lista de materias)
    public void crearMateria(String nombre) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la materia: ");
        String nombreMateria = scanner.nextLine();
        Clase5_Ejercicio7.Materia materia1 = new Clase5_Ejercicio7.Materia(nombreMateria);

        Materia nuevaMateria = new Materia(nombre);

        // Ingresar notas para materia
        System.out.println("\n--- Ingresando notas para " + nuevaMateria + " ---");
        System.out.print("¿Cuántas notas desea ingresar? ");
        int cantidadNotas1 = scanner.nextInt();

        for (int i = 0; i < cantidadNotas1; i++) {
            System.out.print("Ingrese DNI del alumno " + (i+1) + ": ");
            int dni = scanner.nextInt();
            System.out.print("Ingrese nota: ");
            int nota = scanner.nextInt();
            materia1.ingresarNota(dni, nota);
        }

        listaMaterias.addLast(nuevaMateria);
        System.out.println("Materia '" + nombre + "' creada y agregada a la lista de materias.");
    }

    // enviar ultima materia cargada al diccionario general
    public void agregarUltimaMateriaAlDiccionario() {
        if (listaMaterias.getSize() == 0) {
            System.out.println("No hay materias cargadas en la lista.");
            return;
        }

        // materia 1
        // materia 2
        // materia 3 <- quiero esta

        listaMaterias.First();

        while (!listaMaterias.atEnd()) {
            if (listaMaterias.getCurrent() != null) {
                listaMaterias.advance();
            }
        }

        Materia ultimaMateria = listaMaterias.getCurrent();
        diccionarioGeneral.put(ultimaMateria.getNombre(), ultimaMateria);
        System.out.println("Materia '" + ultimaMateria.getNombre() + "' agregada al diccionario general.");
    }

    // put de nota materia by dni (agrega/pisa)
    public void agregarNotaMateria(String nombreMateria, Integer dni, Integer nota) {
        Materia materia = diccionarioGeneral.get(nombreMateria);

        if (materia == null) {
            System.out.println("La materia '" + nombreMateria + "' no existe en el diccionario general.");
            return;
        }

        materia.ingresarNota(dni, nota);
        System.out.println("Nota agregada/actualizada para DNI " + dni + " en la materia '" + nombreMateria + "'.");
    }

    // delete nota materia by dni
    public void eliminarNotaMateria(String nombreMateria, Integer dni) {
        Materia materia = diccionarioGeneral.get(nombreMateria);

        if (materia == null) {
            System.out.println("La materia '" + nombreMateria + "' no existe en el diccionario general.");
            return;
        }

        ArrayMap<Integer, Integer> notas = materia.getNotas();
        if (notas.get(dni) != null) {
            notas.remove(dni);
            System.out.println("Nota eliminada para DNI " + dni + " en la materia '" + nombreMateria + "'.");
        } else {
            System.out.println("No existe una nota para DNI " + dni + " en la materia '" + nombreMateria + "'.");
        }
    }


    // delete alumno (todas sus notas)
    public void eliminarAlumno(Integer dni) {
        for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
            Materia materia = entry.getValue();
            ArrayMap<Integer, Integer> notas = materia.getNotas();
            if (notas.get(dni) != null) {
                notas.remove(dni);
                System.out.println("Nota eliminada para DNI " + dni + " en la materia '" + materia.getNombre() + "'.");
            }
        }
    }

    // get notas alumno (todas sus notas)
    public void obtenerNotasAlumno(Integer dni) {
        System.out.println("Notas para el alumno con DNI " + dni + ":");
        for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
            Materia materia = entry.getValue();
            ArrayMap<Integer, Integer> notas = materia.getNotas();
            Integer nota = notas.get(dni);
            if (nota != null) {
                System.out.println("Materia: " + materia.getNombre() + " | Nota: " + nota);
            }
        }
    }

    // get all alumnos (sin repetir)
    public void obtenerTodosAlumnos() {

    }

    //  get all alumnos con promedio de notas

    // imprimir ultima materia cargada (last en la lista de materias)





}
