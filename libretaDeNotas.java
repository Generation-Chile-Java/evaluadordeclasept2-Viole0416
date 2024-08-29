package EjercicioClase;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class libretaDeNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, HashMap<String, Double>> nombreEstudiante = new HashMap<>();

        System.out.println("Bienvenido, por favor ingrese la cantidad de estudiantes:");
        int cantidadEstudiantes = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.println("Ingrese el nombre y apellido del estudiante:");
            String nombreyApellido = scanner.nextLine();

            HashMap<String, Double> notas = new HashMap<>();
            System.out.println("Ingrese nota de Matematicas:");
            double notaMat = Double.parseDouble(scanner.nextLine());
            notas.put("Matematicas", notaMat);

            System.out.println("Ingrese nota de Quimica:");
            double notaQui = Double.parseDouble(scanner.nextLine());
            notas.put("Quimica", notaQui);

            System.out.println("Ingrese nota de Fisica:");
            double notaFis = Double.parseDouble(scanner.nextLine());
            notas.put("Fisica", notaFis);

            System.out.println("Ingrese nota de Filosofia:");
            double notaFilo = Double.parseDouble(scanner.nextLine());
            notas.put("Filosofía", notaFilo);

            nombreEstudiante.put(nombreyApellido, notas);
        }


        int opcion;
        do {
            System.out.println("\n--- Menú de opciones ---");
            System.out.println("1. Mostrar el promedio de notas por estudiante");
            System.out.println("2. Mostrar ssi aprueba o desaprueba");
            System.out.println("3. Mostrar si la nota está sobre o por debajo del promedio del curso por sstudiante");
            System.out.println("0. Salir del menú");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    mostrarPromedioPorEstudiante(nombreEstudiante);
                    break;
                case 2:
                    verificarAprobacionPorMateria(nombreEstudiante);
                    break;
                case 3:
                    verificarPorSobreDebajoPromedioCurso(nombreEstudiante);
                    break;
                case 0:
                    System.out.println("Saliendo del menú.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void mostrarPromedioPorEstudiante(HashMap<String, HashMap<String, Double>> nombreEstudiante) {
        for (Map.Entry<String, HashMap<String, Double>> entry : nombreEstudiante.entrySet()) {
            String nombre = entry.getKey();
            HashMap<String, Double> notas = entry.getValue();
            double promedio = calcularPromedio(notas);
            System.out.println(nombre + " - Promedio: " + promedio);
        }
    }

    private static void verificarAprobacionPorMateria(HashMap<String, HashMap<String, Double>> nombreEstudiante) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        if (nombreEstudiante.containsKey(nombre)) {
            HashMap<String, Double> notas = nombreEstudiante.get(nombre);
            System.out.print("Ingrese el nombre de la materia: ");
            String materia = scanner.nextLine();

            if (notas.containsKey(materia)) {
                double nota = notas.get(materia);
                if (nota >= 4.0) {
                    System.out.println("Aprobaste " + materia);
                } else {
                    System.out.println("Reprobaste " + materia);
                }
            } else {
                System.out.println("Materia no encontrada.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void verificarPorSobreDebajoPromedioCurso(HashMap<String, HashMap<String, Double>> nombreEstudiante) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        if (nombreEstudiante.containsKey(nombre)) {
            HashMap<String, Double> notas = nombreEstudiante.get(nombre);
            System.out.print("Ingrese el nombre de la materia: ");
            String materia = scanner.nextLine();

            if (notas.containsKey(materia)) {
                double nota = notas.get(materia);
                double promedioCurso = calcularPromedioCurso(nombreEstudiante, materia);
                if (nota >= promedioCurso) {
                    System.out.println(nombre + " - Nota por sobre el promedio del curso en " + materia);
                } else {
                    System.out.println(nombre + " - Nota por debajo del promedio del curso en " + materia);
                }
            } else {
                System.out.println("Materia no encontrada.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static double calcularPromedio(HashMap<String, Double> notas) {
        double suma = 0.0;
        for (double nota : notas.values()) {
            suma += nota;
        }
        return suma / notas.size();
    }

    private static double calcularPromedioCurso(HashMap<String, HashMap<String, Double>> nombreEstudiante, String materia) {
        double suma = 0.0;
        int cantidadNotas = 0;
        for (HashMap<String, Double> notas : nombreEstudiante.values()) {
            if (notas.containsKey(materia)) {
                suma += notas.get(materia);
                cantidadNotas++;
            }
        }
        return suma / cantidadNotas;
    }
}

