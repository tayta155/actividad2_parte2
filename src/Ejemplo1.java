import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Nodo {
    String name_station;
    String linea;  // Cambiado a String
    String horasServicio;
    String direccion;
    List<Nodo> adyacentes;

    Nodo(String name_station, String linea, String horasServicio, String direccion) {
        this.name_station = name_station;
        this.linea = linea;
        this.horasServicio = horasServicio;
        this.direccion = direccion;
        this.adyacentes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Estación: " + name_station + "\n" +
               "Línea: " + linea + "\n" +  // Ahora línea es String
               "Horas de servicio: " + horasServicio + "\n" +
               "Dirección: " + direccion + "\n";
    }
}

class Conexiones {
    HashMap<String, Nodo> estaciones;

    public Conexiones() {
        estaciones = new HashMap<>();
    }

    public void addEstacion(String name_station, String linea, String horasServicio, String direccion) {
        estaciones.putIfAbsent(name_station, new Nodo(name_station, linea, horasServicio, direccion));
    }

    public void addConexion(String estacion1, String estacion2) {
        Nodo nodo1 = estaciones.get(estacion1);
        Nodo nodo2 = estaciones.get(estacion2);

        if (nodo1 != null && nodo2 != null) {
            nodo1.adyacentes.add(nodo2);
            nodo2.adyacentes.add(nodo1);
        }
    }

    public void eliminarEstacion(String nombreEstacion) {
        Nodo nodoAEliminar = estaciones.get(nombreEstacion);
        
        if (nodoAEliminar != null) {
            List<Nodo> adyacentes = nodoAEliminar.adyacentes;
            
            if (adyacentes.size() >= 2) {
                Nodo estacionA = adyacentes.get(0);
                Nodo estacionC = adyacentes.get(1);
                estacionA.adyacentes.add(estacionC);
                estacionC.adyacentes.add(estacionA);
            }

            for (Nodo nodo : adyacentes) {
                nodo.adyacentes.remove(nodoAEliminar);
            }

            estaciones.remove(nombreEstacion);
        }
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        for (Nodo nodo : estaciones.values()) {
            sb.append("Estación: ").append(nodo.name_station).append("\n")
              .append("Línea: ").append(nodo.linea).append("\n")  // Línea como String
              .append("Horas de servicio: ").append(nodo.horasServicio).append("\n")
              .append("Dirección: ").append(nodo.direccion).append("\n")
              .append("Conectada con: ");
            
            for (Nodo adyacente : nodo.adyacentes) {
                sb.append(adyacente.name_station).append(" ");
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
}

public class Ejemplo1 extends JFrame {
    private Conexiones estaciones;
    private JTextArea textArea;
    private JTextField nameField, lineaField, horasField, direccionField, conexionField, eliminarField;

    public Ejemplo1() {
        estaciones = new Conexiones();
        setTitle("Gestión de Estaciones");
        setSize(700, 500);  // Aumenta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        nameField = new JTextField(20);
        lineaField = new JTextField(5);  // Ahora permite texto para la línea
        horasField = new JTextField(20);
        direccionField = new JTextField(20);
        conexionField = new JTextField(20);
        eliminarField = new JTextField(20);  // Campo para ingresar estación a eliminar

        JButton addButton = new JButton("Agregar Estación");
        JButton connectButton = new JButton("Conectar Estaciones");
        JButton showButton = new JButton("Mostrar Lista");
        JButton deleteButton = new JButton("Eliminar Estación");  // Botón para eliminar estación

        // Acción para añadir una estación
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEstacion = nameField.getText();
                String linea = lineaField.getText();  // Capturamos la línea como String
                String horasServicio = horasField.getText();
                String direccion = direccionField.getText();

                if (!nombreEstacion.trim().isEmpty()) {
                    estaciones.addEstacion(nombreEstacion, linea, horasServicio, direccion);
                    nameField.setText("");
                    lineaField.setText("");
                    horasField.setText("");
                    direccionField.setText("");
                }
            }
        });

        // Acción para conectar dos estaciones
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] conexiones = conexionField.getText().split(",");
                if (conexiones.length == 2) {
                    String estacion1 = conexiones[0].trim();
                    String estacion2 = conexiones[1].trim();
                    estaciones.addConexion(estacion1, estacion2);
                    conexionField.setText("");
                }
            }
        });

        // Acción para mostrar la lista de estaciones y sus conexiones
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(estaciones.printList());
            }
        });

        // Acción para eliminar una estación
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String estacionAEliminar = eliminarField.getText().trim();
                if (!estacionAEliminar.isEmpty()) {
                    estaciones.eliminarEstacion(estacionAEliminar);  // Eliminar la estación
                    textArea.setText(estaciones.printList());  // Actualizar la lista
                    eliminarField.setText("");  // Limpiar el campo
                }
            }
        });

        panel.add(new JLabel("Nombre de la estación:"));
        panel.add(nameField);
        panel.add(new JLabel("Número de línea:"));
        panel.add(lineaField);  // Ahora acepta caracteres
        panel.add(new JLabel("Horas de servicio:"));
        panel.add(horasField);
        panel.add(new JLabel("Dirección:"));
        panel.add(direccionField);
        panel.add(new JLabel("Conectar estaciones (A,B):"));
        panel.add(conexionField);
        panel.add(new JLabel("Eliminar estación:"));
        panel.add(eliminarField);  // Añadimos el campo de eliminación
        panel.add(addButton);
        panel.add(connectButton);
        panel.add(showButton);
        panel.add(deleteButton);  // Añadimos el botón de eliminación

        add(panel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Ejemplo1 ventana = new Ejemplo1();
                ventana.setVisible(true);
            }
        });
    }
}
