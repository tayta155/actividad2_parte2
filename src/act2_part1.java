import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Nodo { 
    String name_station;
    Nodo next;
    Nodo prev;

    Nodo(String name_station) {
        this.name_station = name_station;
        this.next = null;
        this.prev = null;
    }

    public String toString() {
        return "Estacion: " + name_station + "\n";
    }
}

class Linea {
    private String numeroLinea;
    private Nodo head;
    private Nodo tail;

    public Linea(String numeroLinea) {
        this.numeroLinea = numeroLinea;
        this.head = null;
        this.tail = null;
    }

    public String getNumeroLinea() {
        return numeroLinea;
    }

    public void agregarEstacion(String name_station) {
        Nodo nuevaEstacion = new Nodo(name_station);

        if (head == null) {
            // Si la lista está vacía, head y tail apuntan a la nueva estación
            head = nuevaEstacion;
            tail = nuevaEstacion;
        } else {
            // Agregar la estación al final de la lista
            tail.next = nuevaEstacion;
            nuevaEstacion.prev = tail;
            tail = nuevaEstacion;
        }
    }

    public void imprimirEstaciones() {
        if (head == null) {
            System.out.println("No hay estaciones en la línea " + numeroLinea);
            return;
        }

        Nodo actual = head;
        while (actual != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ESTACION ACTUAL: ").append(actual.name_station);

            if (actual.prev != null) {
                sb.append(" | ESTACION ANTERIOR -> ").append(actual.prev.name_station);
            }

            if (actual.next != null) {
                sb.append(" | ESTACION SIGUIENTE -> ").append(actual.next.name_station);
            }

            System.out.println(sb.toString());
            actual = actual.next;
        }
    }
}

class SistemaTrenes {
    private List<Linea> lineas;

    public SistemaTrenes() {
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(String numeroLinea) {
        Linea nuevaLinea = new Linea(numeroLinea);
        lineas.add(nuevaLinea);
        System.out.println("Línea " + numeroLinea + " agregada.");
    }

    public Linea buscarLinea(String numeroLinea) {
        for (Linea linea : lineas) {
            if (linea.getNumeroLinea().equals(numeroLinea)) {
                return linea;
            }
        }
        return null;
    }

    public void imprimirLineas() {
        if (lineas.isEmpty()) {
            System.out.println("No hay líneas creadas.");
        } else {
            for (Linea linea : lineas) {
                System.out.println("Línea: " + linea.getNumeroLinea());
                linea.imprimirEstaciones();
            }
        }
    }
}

public class act2_part1 {
    public static void main(String[] args) {
        SistemaTrenes sistema = new SistemaTrenes();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu de opciones:");
            System.out.println("1. Agregar línea");
            System.out.println("2. Agregar estación a una línea");
            System.out.println("3. Imprimir lista de estaciones por línea");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.print("Numero de la línea: ");
                    String numeroLinea = scanner.nextLine();
                    sistema.agregarLinea(numeroLinea);
                    break;
                case 2:
                    System.out.print("Número de la línea a la que deseas agregar una estación: ");
                    String numeroLineaBusqueda = scanner.nextLine();
                    Linea lineaEncontrada = sistema.buscarLinea(numeroLineaBusqueda);
                    if (lineaEncontrada != null) {
                        System.out.print("Nombre de la estación: ");
                        String nombreEstacion = scanner.nextLine();
                        lineaEncontrada.agregarEstacion(nombreEstacion);
                        System.out.println("Estación " + nombreEstacion + " agregada a la línea " + numeroLineaBusqueda);
                    } else {
                        System.out.println("Línea no encontrada.");
                    }
                    break;
                case 3:
                    sistema.imprimirLineas();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intenta nuevamente.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }
}
