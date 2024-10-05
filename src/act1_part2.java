import java.util.Currency;
import java.util.LinkedList;
import java.util.Scanner;

class Nodo{

    String name_station;
    Nodo next;
    Nodo prev;

    Nodo (String name_station){

        this.name_station = name_station;
        this.next = null;
        this.prev = null;
    }

    public String toString(){

        return "Estacion: " + name_station + "\n";
    }
    
}

class conexiones{
    Nodo head;
    Nodo tail;

    public void add(String name_station){
        Nodo newNodo = new Nodo(name_station);

        if (head == null) {
            head = newNodo;
            tail = newNodo;
        } else {
            tail.next = newNodo;
            newNodo.prev = tail;
            tail = newNodo;
        }
    }

    public void printList(){

        Nodo current = head;

        while (current != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ESTACION ACTUAL: ").append(current.name_station);
            System.out.println();

            if (current.prev != null) {
                
                sb.append(" ESTACION ANTERIOR -> ").append(current.prev.name_station);
            }

            if (current.next != null) {
                sb.append(" ESTACION SIGUIENTE -> ").append(current.next.name_station);
            }

            System.out.println(sb.toString());
            current = current.next;
            
        }

    }
}



public class act1_part2 {

    public static void main(String[] args) {
        
        conexiones estaciones = new conexiones();

        Scanner scanner = new Scanner(System.in);
        String continuar;
        System.out.println("Agregar estaciones \n");

        do {

            System.out.println("Nombre de la estacion: ");
            String nombre_estacion = scanner.nextLine();
            estaciones.add(nombre_estacion);

            System.out.println("Se necesitan adicionar mas lineas? (si/no)");
            continuar = scanner.nextLine();
            
        } while (continuar.equalsIgnoreCase("si"));

        estaciones.printList();

        scanner.close();
    }
    
}