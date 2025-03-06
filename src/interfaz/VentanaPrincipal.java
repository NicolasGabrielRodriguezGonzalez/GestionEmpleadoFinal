package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import mundo.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private JTextField fieldNombre, fieldID;
    private JComboBox<String> boxDepartamento, boxProyecto, boxTipoEmpleado, boxContribucion;
    private JTextPane mostrarInfo;
    private LinkedList<Departamento> departamentos;
    private LinkedList<Proyecto> proyectos;
    private JPanel panelContribucion;

    public VentanaPrincipal() {
        setTitle("Sistema Gestión Empleado");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Sistema Gestión Empleado", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelDatos = new JPanel(new GridLayout(5, 2, 15, 15));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));

        panelDatos.add(new JLabel("Nombre:"));
        fieldNombre = new JTextField();
        panelDatos.add(fieldNombre);

        panelDatos.add(new JLabel("ID:"));
        fieldID = new JTextField();
        panelDatos.add(fieldID);

        panelDatos.add(new JLabel("Departamento:"));
        boxDepartamento = new JComboBox<>();
        panelDatos.add(boxDepartamento);

        panelDatos.add(new JLabel("Proyecto:"));
        boxProyecto = new JComboBox<>();
        panelDatos.add(boxProyecto);

        panelDatos.add(new JLabel("Tipo Empleado:"));
        boxTipoEmpleado = new JComboBox<>(new String[]{"Gerente", "Técnico"});
        boxTipoEmpleado.addActionListener(e -> actualizarComboContribucion());
        panelDatos.add(boxTipoEmpleado);

        panelContribucion = new JPanel(new BorderLayout());
        panelContribucion.setBorder(BorderFactory.createTitledBorder("Contribución"));
        boxContribucion = new JComboBox<>();
        panelContribucion.add(boxContribucion, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar Empleado");
        btnAgregar.setPreferredSize(new Dimension(150, 40));
        btnAgregar.addActionListener(this);
        panelBotones.add(btnAgregar);

        mostrarInfo = new JTextPane();
        mostrarInfo.setEditable(false);
        mostrarInfo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(mostrarInfo);
        scrollPane.setPreferredSize(new Dimension(350, 250));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información"));

        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelDatos);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelContribucion);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(scrollPane);

        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            URL url = new URL("https://www.shutterstock.com/image-photo/professional-diverse-international-team-young-600nw-2353374147.jpg");
            Image img = ImageIO.read(url);
            if (img != null) {
                Image imgEscalada = img.getScaledInstance(450, 600, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imgEscalada));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelPrincipal, lblImagen);
        splitPane.setDividerLocation(450);
        splitPane.setResizeWeight(0.5);

        add(splitPane);
        departamentos = Departamento.cargarDepartamentos();
        proyectos = Proyecto.cargarProyectos();
        cargarCombos();
        actualizarComboContribucion();
    }

    private void cargarCombos() {
        for (Departamento d : departamentos) {
            boxDepartamento.addItem(d.getNombre());
        }
        for (Proyecto p : proyectos) {
            boxProyecto.addItem(p.getNombre());
        }
    }

    private void actualizarComboContribucion() {
        boxContribucion.removeAllItems();
        if ("Gerente".equals(boxTipoEmpleado.getSelectedItem())) {
            for (TipoContribucionGerente contribucion : TipoContribucionGerente.values()) {
                boxContribucion.addItem(contribucion.name());
            }
        } else {
            for (TipoContribucionTecnico contribucion : TipoContribucionTecnico.values()) {
                boxContribucion.addItem(contribucion.name());
            }
        }
        panelContribucion.revalidate();
        panelContribucion.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = fieldNombre.getText();
        String codigo = fieldID.getText();
        String nombreDepto = (String) boxDepartamento.getSelectedItem();
        String nombreProyecto = (String) boxProyecto.getSelectedItem();
        String tipoEmpleado = (String) boxTipoEmpleado.getSelectedItem();
        String contribucion = (String) boxContribucion.getSelectedItem();

        Departamento departamentoSeleccionado = departamentos.stream()
                .filter(d -> d.getNombre().equals(nombreDepto))
                .findFirst()
                .orElse(null);

        Proyecto proyectoSeleccionado = proyectos.stream()
                .filter(p -> p.getNombre().equals(nombreProyecto))
                .findFirst()
                .orElse(null);

        if (departamentoSeleccionado != null && proyectoSeleccionado != null) {
            Empleado empleado = null;
            String mensajeGestion = "";
            double salario = 0.0;

            if ("Gerente".equals(tipoEmpleado)) {
                Gerente gerente = new Gerente(nombre, codigo, departamentoSeleccionado, proyectoSeleccionado, 
                                              "Área de Gestión", TipoContribucionGerente.valueOf(contribucion));
                gerente.gestionProyecto(); // Muestra la gestión del proyecto
               
                salario = gerente.calcularSalario(); // Calcula el salario del gerente
                empleado = gerente;
                mensajeGestion = nombre + " está gestionando el proyecto " + nombreProyecto + " y gestionando equipos.";
            } else if ("Técnico".equals(tipoEmpleado)) {
                Tecnico tecnico = new Tecnico(nombre, codigo, departamentoSeleccionado, proyectoSeleccionado, 
                                              "Área Técnica", TipoContribucionTecnico.valueOf(contribucion));
                salario = tecnico.calcularSalario(); // Calcula el salario del técnico
                empleado = tecnico;
            }

            if (empleado != null) {
                departamentoSeleccionado.agregarEmpleado(empleado);
                proyectoSeleccionado.agregarEmpleado(empleado);

                String info = "Nombre: " + empleado.getNombre() + "\n" +
                              "ID: " + empleado.getCodigo() + "\n" +
                              "Departamento: " + empleado.getDepartamento().getNombre() + "\n" +
                              "Proyecto: " + nombreProyecto + "\n" +
                              "Tipo Empleado: " + tipoEmpleado + "\n" +
                              "Contribución: " + contribucion + "\n";

                // Agregar el salario si es Gerente o Técnico
                if (empleado instanceof Tecnico || empleado instanceof Gerente) {
                    info += "Salario Calculado: $" + salario + "\n";
                }
                if (empleado instanceof Gerente) {
                    info += "\nInformación del empleado agregado:\n" + mensajeGestion;

                }
                
                mostrarInfo.setText(info);
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}