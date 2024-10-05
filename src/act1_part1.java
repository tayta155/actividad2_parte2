import java.util.Scanner;

/**
 * act1_parte1
 */
public class act1_part1 {

    String name_station;
    String line_beloning;
    String station_address;
    String service_hours;
    String n;
    int n2;

    public act1_part1( String name_station, String line_beloning, String station_address, String service_hours){

        this.name_station = name_station;
        this.line_beloning = line_beloning;
        this.station_address = station_address;
        this.service_hours = service_hours;

        System.out.println("\n");
        System.out.println("Nombre de la estación: " + this.name_station);
        System.out.println("Línea perteneciente: " + this.line_beloning);
        System.out.println("Dirección de la estación: " + this.station_address);
        System.out.println("Horario de servicio: " + this.service_hours);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n");
        System.out.println("Nombre de la estcion:");
        String a1 = scanner.nextLine();

        System.out.println("Linea pertenciente: ");
        String a2 = scanner.nextLine();

        System.out.println("Direccion de la estacion: ");
        String a3 = scanner.nextLine();

        System.out.println("Horario de servicio");
        String a4 = scanner.nextLine();

        scanner.close();

    }
}