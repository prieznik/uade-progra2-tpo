package Clase5_Ejercicio8;
import java.util.Scanner;
import TDAs.List.LinkedList;
import TDAs.Map.ArrayMap;
import TDAs.Map.Entry;

public class Sistema {
    private Scanner scanner;
    private ArrayMap<String, Materia> diccionarioGeneral;
    private LinkedList<Materia> listaMaterias;

    public Sistema() {
        this.scanner = new Scanner(System.in);
        this.diccionarioGeneral = new ArrayMap<>();
        this.listaMaterias = new LinkedList<>();
    }

    public void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("         SISTEMA DE GESTIÓN DE NOTAS    ");
        System.out.println("========================================");
        System.out.println("1. Cargar un mapeo con notas de una materia");
        System.out.println("2. Agregar el último mapeo cargado al Diccionario general");
        System.out.println("3. Agregar una nota para un DNI específico");
        System.out.println("4. Quitar una nota para un DNI específico");
        System.out.println("5. Quitar un alumno (todas sus notas)");
        System.out.println("6. Mostrar las notas de un alumno");
        System.out.println("7. Mostrar todos los alumnos");
        System.out.println("8. Mostrar todos los alumnos y su promedio de notas");
        System.out.println("9. Mostrar el último mapeo cargado");
        System.out.println("10. MOSTRAR TODO EL DICCIONARIO GENERAL (Debug)");
        System.out.println("0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccionar una opción: ");
    }

    // Validar nota entre 0 y 10
    private int leerNotaValida() {
        int nota;
        do {
            System.out.print("Ingresar nota (0 a 10): ");
            nota = scanner.nextInt();
            if (nota < 0 || nota > 10) {
                System.out.println("⚠ La nota debe estar entre 0 y 10. Intenta nuevamente.");
            }
        } while (nota < 0 || nota > 10);
        return nota;
    }

    private int leerDni() {
        System.out.print("Ingresar DNI del alumno: ");
        return scanner.nextInt();
    }

    public void crearMateria() {
        System.out.print("Ingresar el nombre de la materia: ");
        String nombreMateria = scanner.nextLine(); // usa nextLine para que lea espacios
        if (nombreMateria.isEmpty()) nombreMateria = scanner.nextLine(); // fix por salto de línea

        Materia nuevaMateria = new Materia(nombreMateria);

        System.out.print("¿Cuántas notas deseas ingresar? ");
        int cantidadNotas = scanner.nextInt();

        for (int i = 0; i < cantidadNotas; i++) {
            int dni = leerDni();
            int nota = leerNotaValida();
            nuevaMateria.ingresarNota(dni, nota);
        }

        listaMaterias.addLast(nuevaMateria);
        System.out.println("Materia '" + nombreMateria + "' creada y agregada a la lista de materias.");
    }

    public void agregarUltimaMateriaAlDiccionario() {
        if (listaMaterias.getSize() == 0) {
            System.out.println("No hay materias cargadas en la lista.");
            return;
        }

        listaMaterias.First();
        Materia ultimaMateria = null;
        while (!listaMaterias.atEnd()) {
            ultimaMateria = listaMaterias.getCurrent();
            listaMaterias.advance();
        }

        if (ultimaMateria != null) {
            diccionarioGeneral.put(ultimaMateria.getNombre(), ultimaMateria);
            System.out.println("Materia '" + ultimaMateria.getNombre() + "' agregada al diccionario general.");
        }
    }


    private Materia buscarMateriaPorNombre(String nombreBuscado) {
        for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
            if (entry.getKey().equals(nombreBuscado)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private Integer obtenerClaveExacta(ArrayMap<Integer, Integer> notas, Integer dniBuscado) {
        for (Entry<Integer, Integer> entry : notas.entries()) {
            if (entry.getKey().intValue() == dniBuscado.intValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Integer buscarNotaPorDni(ArrayMap<Integer, Integer> notas, Integer dniBuscado) {
        for (Entry<Integer, Integer> entry : notas.entries()) {
            Integer dni = entry.getKey();
            if (dni.intValue() == dniBuscado.intValue()) {
                return entry.getValue();
            }
        }
        return null;
    }

    private boolean eliminarNotaPorDni(ArrayMap<Integer, Integer> notas, Integer dniBuscado) {
        Integer claveExacta = obtenerClaveExacta(notas, dniBuscado);
        if (claveExacta != null) {
            notas.remove(claveExacta);
            return true;
        }
        return false;
    }

    public void agregarNotaMateria() {
        System.out.print("Ingresar el nombre de la materia: ");
        String nombreMateria = scanner.nextLine();
        if (nombreMateria.isEmpty()) nombreMateria = scanner.nextLine(); // fix por salto de línea

        Materia materia = buscarMateriaPorNombre(nombreMateria);
        if (materia == null) {
            System.out.println("La materia '" + nombreMateria + "' no existe en el diccionario general.");
            return;
        }

        int dni = leerDni();
        int nota = leerNotaValida();

        ArrayMap<Integer, Integer> notas = materia.getNotas();
        Integer claveExacta = obtenerClaveExacta(notas, dni);

        if (claveExacta != null) {
            notas.put(claveExacta, nota);
        } else {
            notas.put(dni, nota);
        }

        System.out.println("Nota registrada para DNI " + dni + " en la materia '" + nombreMateria + "'.");
    }

    public void eliminarNotaMateria() {
        System.out.print("Ingresar el nombre de la materia: ");
        String nombreMateria = scanner.nextLine();
        if (nombreMateria.isEmpty()) nombreMateria = scanner.nextLine();

        Materia materia = buscarMateriaPorNombre(nombreMateria);
        if (materia == null) {
            System.out.println("La materia '" + nombreMateria + "' no existe en el diccionario general.");
            return;
        }

        int dni = leerDni();
        if (eliminarNotaPorDni(materia.getNotas(), dni)) {
            System.out.println("Nota eliminada para DNI " + dni + " en la materia '" + nombreMateria + "'.");
        } else {
            System.out.println("No existe una nota para ese DNI en '" + nombreMateria + "'.");
        }
    }

    public void eliminarAlumno() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresar el DNI del alumno a eliminar: ");
        Integer dni = scanner.nextInt();

        boolean encontrado = false;
        for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
            Materia materia = entry.getValue();
            ArrayMap<Integer, Integer> notas = materia.getNotas();

            if (eliminarNotaPorDni(notas, dni)) {
                System.out.println("Nota eliminada para DNI " + dni + " en la materia '" + materia.getNombre() + "'.");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron notas para el DNI " + dni);
        }
    }

    public void obtenerNotasAlumno() {
//        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresar el DNI del alumno: ");
        Integer dni = scanner.nextInt();

        System.out.println("\nNotas para el alumno con DNI " + dni + ":");
        boolean encontrado = false;

        for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
            Materia materia = entry.getValue();
            ArrayMap<Integer, Integer> notas = materia.getNotas();

            Integer nota = buscarNotaPorDni(notas, dni);
            if (nota != null) {
                System.out.println("Materia: " + materia.getNombre() + " | Nota: " + nota);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron notas para el DNI " + dni);
        }
    }

    public void obtenerTodosAlumnos() {
        System.out.println("\n--- Lista de todos los alumnos (DNIs) ---");

        // Pregunta para el profe: Es correcta esta forma sin utilizar HashSet?
        int[] dnisUnicos = new int[1000];
        int cantidadDnis = 0;

        for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
            Materia materia = entry.getValue();
            ArrayMap<Integer, Integer> notas = materia.getNotas();

            for (Entry<Integer, Integer> notaEntry : notas.entries()) {
                Integer dni = notaEntry.getKey();
                int dniValue = dni.intValue();

                boolean yaExiste = false;
                for (int i = 0; i < cantidadDnis; i++) {
                    if (dnisUnicos[i] == dniValue) {
                        yaExiste = true;
                        break;
                    }
                }

                if (!yaExiste && cantidadDnis < dnisUnicos.length) {
                    dnisUnicos[cantidadDnis] = dniValue;
                    cantidadDnis++;
                }
            }
        }

        if (cantidadDnis == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (int i = 0; i < cantidadDnis; i++) {
                System.out.println("DNI: " + dnisUnicos[i]);
            }
        }
    }

    public void obtenerAlumnosConPromedio() {
        System.out.println("\n--- Alumnos con sus promedios ---");

        // Pregunta para el profe: Es correcta esta forma sin utilizar TDAs de java?
        int[] dnis = new int[1000];
        int[] sumas = new int[1000];
        int[] contadores = new int[1000];
        int cantidadAlumnos = 0;

        for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
            Materia materia = entry.getValue();
            ArrayMap<Integer, Integer> notas = materia.getNotas();

            for (Entry<Integer, Integer> notaEntry : notas.entries()) {
                int dniValue = notaEntry.getKey().intValue();
                int notaValue = notaEntry.getValue().intValue();

                int indice = -1;
                for (int i = 0; i < cantidadAlumnos; i++) {
                    if (dnis[i] == dniValue) {
                        indice = i;
                        break;
                    }
                }

                if (indice == -1) {
                    if (cantidadAlumnos < dnis.length) {
                        dnis[cantidadAlumnos] = dniValue;
                        sumas[cantidadAlumnos] = notaValue;
                        contadores[cantidadAlumnos] = 1;
                        cantidadAlumnos++;
                    }
                } else {
                    sumas[indice] += notaValue;
                    contadores[indice]++;
                }
            }
        }

        if (cantidadAlumnos == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (int i = 0; i < cantidadAlumnos; i++) {
                double promedio = (double) sumas[i] / contadores[i];
                System.out.printf("DNI: %d | Promedio: %.2f%n", dnis[i], promedio);
            }
        }
    }

    public void imprimirUltimaMateria() {
        if (listaMaterias.getSize() == 0) {
            System.out.println("No hay materias cargadas en la lista.");
            return;
        }

        listaMaterias.First();
        Materia ultimaMateria = null;

        while (!listaMaterias.atEnd()) {
            ultimaMateria = listaMaterias.getCurrent();
            listaMaterias.advance();
        }

        if (ultimaMateria != null) {
            System.out.println("\n--- Última materia cargada ---");
            ultimaMateria.imprimirDiccionario();
        }
    }

    public void mostrarDiccionarioGeneral() {
        System.out.println("\n========================================");
        System.out.println("    CONTENIDO DEL DICCIONARIO GENERAL   ");
        System.out.println("========================================");

        if (diccionarioGeneral.isEmpty()) {
            System.out.println("El diccionario general está vacío.");
            System.out.println("Usar la opción 2 para agregar materias desde la lista.");
        } else {
            System.out.println("Total de materias en el diccionario: " + diccionarioGeneral.size());
            System.out.println("----------------------------------------");

            for (Entry<String, Materia> entry : diccionarioGeneral.entries()) {
                String nombreMateria = entry.getKey();
                Materia materia = entry.getValue();

                System.out.println("\n MATERIA: " + nombreMateria);
                System.out.println("   Notas registradas:");

                ArrayMap<Integer, Integer> notas = materia.getNotas();
                if (notas.isEmpty()) {
                    System.out.println("   (Sin notas registradas)");
                } else {
                    for (Entry<Integer, Integer> notaEntry : notas.entries()) {
                        System.out.println("   • DNI: " + notaEntry.getKey() +
                                " → Nota: " + notaEntry.getValue());
                    }
                }
            }
            System.out.println("\n========================================");
        }
    }
}
