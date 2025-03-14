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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
public class VentanaPrincipal extends JFrame implements ActionListener {
    private JTextField fieldNombre, fieldID;
    private JComboBox<String> boxDepartamento, boxProyecto, boxTipoEmpleado, boxContribucion;
    private JTextPane mostrarInfo;
    private LinkedList<Departamento> departamentos;
    private LinkedList<Proyecto> proyectos;
    private LinkedList<Empleado> empleados;
    private JPanel panelContribucion;
    private Empresa empresa;
    private JComboBox<EstadoPresupuesto> boxEstadoPresupuesto; // Cambiado a JComboBox<EstadoPresupuesto>
    private int numeroEstado; // ✅ Declaración de numeroEstado como atributo de clase
    private static VentanaPrincipal instance;
    private JButton btnClonarProyecto;



    private VentanaPrincipal() {
        setTitle("Sistema Gestión Empleado");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        

        // 
        empresa = new Empresa("Mi Empresa", new LinkedList<>(), new LinkedList<>(), new LinkedList<>(),  new LinkedList<>());

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

        mostrarInfo = new JTextPane();
        mostrarInfo.setEditable(false);
        mostrarInfo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(mostrarInfo);
        scrollPane.setPreferredSize(new Dimension(350, 250));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información"));
        
        
        
        // Nuevo JComboBox para el estado del presupuesto
        boxEstadoPresupuesto = new JComboBox<>(EstadoPresupuesto.values()); // Usar el enum
        boxEstadoPresupuesto.setEnabled(true); // abilitar la edición manual
        boxEstadoPresupuesto.setFont(new Font("Arial", Font.BOLD, 14));
        boxEstadoPresupuesto.setBackground(Color.WHITE);
        
       
        
        JLabel lblNumeroEstado = new JLabel("Número de estado: ");
        lblNumeroEstado.setFont(new Font("Arial", Font.BOLD, 14));
        lblNumeroEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir el JLabel al panel principal
        panelPrincipal.add(lblNumeroEstado);
        panelPrincipal.add(Box.createVerticalStrut(10));
        
      
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelDatos);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelContribucion);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(boxEstadoPresupuesto); // Añadir el JComboBox al panel principal
        panelPrincipal.add(Box.createVerticalStrut(10));

        // Panel de botones (arriba: Agregar y Modificar)
        JPanel panelBotonesArriba = new JPanel();
        panelBotonesArriba.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelPrincipal.add(panelBotonesArriba);

        // Botón Agregar Empleado
        JButton btnAgregar = new JButton("Agregar Empleado");
        btnAgregar.setPreferredSize(new Dimension(140, 30));
        btnAgregar.addActionListener(this);
        panelBotonesArriba.add(btnAgregar);
        
        btnClonarProyecto = new JButton("Clonar Proyecto");
        btnClonarProyecto.setPreferredSize(new Dimension(150, 30));
        btnClonarProyecto.addActionListener(this);

        // Agregar el botón al panel de botones
        panelBotonesArriba.add(btnClonarProyecto);
        
     // Botón Obtener Número de Presupuesto
        JButton btnObtenerNumeroPresupuesto = new JButton("Obtener Número Presupuesto");
        btnObtenerNumeroPresupuesto.setPreferredSize(new Dimension(200, 30));
        btnObtenerNumeroPresupuesto.addActionListener(this);
        panelBotonesArriba.add(btnObtenerNumeroPresupuesto);

        // Botón Modificar Empleado
        JButton btnModificar = new JButton("Modificar Empleado");
        btnModificar.setPreferredSize(new Dimension(150, 30));
        btnModificar.addActionListener(this);
        panelBotonesArriba.add(btnModificar);
    
        // Botón Clonar Proyecto
        JButton btnClonarProyecto = new JButton("Clonar Proyecto");
        btnClonarProyecto.setPreferredSize(new Dimension(150, 30));
        btnClonarProyecto.addActionListener(this);
        panelBotonesArriba.add(btnClonarProyecto);

        // Panel de botones (abajo: Eliminar y Mostrar)
        JPanel panelBotonesAbajo = new JPanel();
        panelBotonesAbajo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelPrincipal.add(panelBotonesAbajo);

        // Botón Eliminar Empleado
        JButton btnEliminar = new JButton("Eliminar Empleado");
        btnEliminar.setPreferredSize(new Dimension(140, 30));
        btnEliminar.addActionListener(this);
        panelBotonesAbajo.add(btnEliminar);

        // Botón Mostrar Empleado
        JButton btnMostrarEmpleado = new JButton("Mostrar Empleado");
        btnMostrarEmpleado.setPreferredSize(new Dimension(140, 30));
        btnMostrarEmpleado.addActionListener(this);
        panelBotonesAbajo.add(btnMostrarEmpleado);

        Component verticalStrut = Box.createVerticalStrut(10);
        panelPrincipal.add(verticalStrut);
        panelPrincipal.add(scrollPane);

        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.RIGHT);
        try {
            URL url = new URL("https://www.shutterstock.com/image-photo/professional-diverse-international-team-young-600nw-2353374147.jpg");
            Image img = ImageIO.read(url);
            
            if (img != null) {
                // Escalar la imagen de acuerdo al tamaño de la ventana
                lblImagen.setIcon(new ImageIcon(img.getScaledInstance(450, 600, Image.SCALE_SMOOTH)));
                
                // Añadir un listener para redimensionar la imagen al cambiar el tamaño de la ventana
                lblImagen.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        int ancho = lblImagen.getWidth();
                        int alto = lblImagen.getHeight();

                        // Redimensionar la imagen dinámicamente
                        Image imgEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                        lblImagen.setIcon(new ImageIcon(imgEscalada));
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Divisor para dividir el panel principal y la imagen
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelPrincipal, lblImagen);
        splitPane.setDividerLocation(450);
        splitPane.setResizeWeight(0.5);

        add(splitPane, BorderLayout.CENTER);
        setVisible(true);
        getContentPane().add(splitPane);
        departamentos = Empresa.cargarDepartamentos();
        proyectos = Empresa.cargarProyectos();
        cargarCombos();
        actualizarComboContribucion();
        actualizarEstadoPresupuesto(); // Inicializar el estado del presupuesto
    }
    public static VentanaPrincipal IsInstance() {
        if (instance == null) {
            instance = new VentanaPrincipal(); // Se crea solo si no existe
        }
        return instance;
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

    private void limpiarCampos() {
        fieldNombre.setText("");
        fieldID.setText("");
        boxDepartamento.setSelectedIndex(0);
        boxProyecto.setSelectedIndex(0);
        boxTipoEmpleado.setSelectedIndex(0);
        boxContribucion.setSelectedIndex(0);
    }

    // Método para actualizar el estado del presupuesto
    private void actualizarEstadoPresupuesto() {
        int totalEmpleados = empresa.getEmpleados().size();
        if (totalEmpleados < 5) {
            boxEstadoPresupuesto.setSelectedItem(EstadoPresupuesto.DISPONIBLE);
            boxEstadoPresupuesto.setBackground(Color.GREEN);
        } else if (totalEmpleados >= 5 && totalEmpleados < 10) {
            boxEstadoPresupuesto.setSelectedItem(EstadoPresupuesto.RESERVADO);
            boxEstadoPresupuesto.setBackground(Color.ORANGE);
        } else {
            boxEstadoPresupuesto.setSelectedItem(EstadoPresupuesto.NO_DISPONIBLE);
            boxEstadoPresupuesto.setBackground(Color.RED);
        }
    }
    private void obtenerYMostrarNumeroPresupuesto() {
        EstadoPresupuesto estadoSeleccionado = (EstadoPresupuesto) boxEstadoPresupuesto.getSelectedItem();
        int numeroEstado = obtenerNumeroPorEstado(estadoSeleccionado);
        
        String mensaje = "El número de estado para el presupuesto seleccionado es: " + numeroEstado;
        JOptionPane.showMessageDialog(this, mensaje, "Número de Presupuesto", JOptionPane.INFORMATION_MESSAGE);
    }

    private int obtenerNumeroPorEstado(EstadoPresupuesto estado) {
        switch (estado) {
            case DISPONIBLE:
                return 1;
            case NO_DISPONIBLE:
                return 2;
            case RESERVADO:
                return 3;
            default:
                return 0;
        }
    }
    private void clonarProyecto() {
        // Crear un panel para la ventana de diálogo
        JPanel panelClonar = new JPanel(new GridLayout(3, 2, 10, 10));
        panelClonar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Combo para seleccionar el proyecto a clonar
        JComboBox<String> boxProyectoClonar = new JComboBox<>();
        for (Proyecto p : proyectos) {
            boxProyectoClonar.addItem(p.getNombre());
        }

        // Combo para seleccionar el nuevo departamento
        JComboBox<String> boxNuevoDepartamento = new JComboBox<>();
        for (Departamento d : departamentos) {
            boxNuevoDepartamento.addItem(d.getNombre());
        }

        // Campo para ingresar el nuevo código del proyecto
        JTextField fieldNuevoCodigo = new JTextField();

        // Añadir componentes al panel
        panelClonar.add(new JLabel("Proyecto a clonar:"));
        panelClonar.add(boxProyectoClonar);
        panelClonar.add(new JLabel("Nuevo departamento:"));
        panelClonar.add(boxNuevoDepartamento);
        panelClonar.add(new JLabel("Nuevo código del proyecto:"));
        panelClonar.add(fieldNuevoCodigo);

        // Mostrar la ventana de diálogo
        int opcion = JOptionPane.showConfirmDialog(this, panelClonar, "Clonar Proyecto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            // Obtener los valores seleccionados
            String nombreProyecto = (String) boxProyectoClonar.getSelectedItem();
            String nombreNuevoDepartamento = (String) boxNuevoDepartamento.getSelectedItem();
            String nuevoCodigo = fieldNuevoCodigo.getText();

            // Buscar el proyecto y el departamento seleccionados
            Proyecto proyectoSeleccionado = proyectos.stream()
                    .filter(p -> p.getNombre().equals(nombreProyecto))
                    .findFirst()
                    .orElse(null);

            Departamento nuevoDepartamento = departamentos.stream()
                    .filter(d -> d.getNombre().equals(nombreNuevoDepartamento))
                    .findFirst()
                    .orElse(null);

            if (proyectoSeleccionado != null && nuevoDepartamento != null && !nuevoCodigo.isEmpty()) {
                // Clonar el proyecto
                Proyecto proyectoClonado = proyectoSeleccionado.clonarProyecto(nuevoCodigo, nuevoDepartamento);

                // Agregar el proyecto clonado a la lista de proyectos
                proyectos.add(proyectoClonado);

                // Actualizar el combo de proyectos
                boxProyecto.addItem(proyectoClonado.getNombre());

                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Proyecto clonado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Datos inválidos. Verifique los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Agregar Empleado")) {
            // Lógica para agregar empleado
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
                if ("Gerente".equals(tipoEmpleado)) {
                    empleado = new Gerente(nombre, codigo, departamentoSeleccionado, proyectoSeleccionado,
                            "Área de Gestión", TipoContribucionGerente.valueOf(contribucion));
                } else if ("Técnico".equals(tipoEmpleado)) {
                    empleado = new Tecnico(nombre, codigo, departamentoSeleccionado, proyectoSeleccionado,
                            "Área Técnica", TipoContribucionTecnico.valueOf(contribucion));
                }

                if (empleado != null) {
                    if (empleado instanceof Gerente) {
                        empresa.agregarGerente((Gerente) empleado);
                    } else {
                        empresa.agregarTecnico((Tecnico) empleado);
                    }

                    // Mostrar información del empleado en el área de texto
                    String infoEmpleado = "Empleado agregado exitosamente.\n" +
                            "Nombre: " + empleado.getNombre() + "\n" +
                            "ID: " + empleado.getCodigo() + "\n" +
                            "Departamento: " + empleado.getDepartamento().getNombre() + "\n" +
                            "Proyecto: " + proyectoSeleccionado.getNombre() + "\n" +
                            "Tipo Empleado: " + tipoEmpleado + "\n" +
                            "Contribución: " + contribucion + "\n";

                    mostrarInfo.setText(infoEmpleado);

                    // Limpiar los campos después de agregar el empleado
                    limpiarCampos();

                    // Actualizar el estado del presupuesto
                    actualizarEstadoPresupuesto();
                }
            }
        } else if (e.getActionCommand().equals("Modificar Empleado")) {
            mostrarVentanaModificar();
        } else if (e.getActionCommand().equals("Eliminar Empleado")) {
            mostrarVentanaEliminar();
        } else if (e.getActionCommand().equals("Mostrar Empleado")) {
            mostrarInformacionEmpleado();
        }else if (e.getActionCommand().equals("Obtener Número Presupuesto")) {
            obtenerYMostrarNumeroPresupuesto();
            
        } else if (e.getActionCommand().equals("Clonar Proyecto")) {
            clonarProyecto();
        }
    }

    private void mostrarVentanaModificar() {
        String idEmpleado = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado a modificar:");
        if (idEmpleado != null && !idEmpleado.isEmpty()) {
            Empleado empleado = empresa.buscarEmpleadoPorID(idEmpleado);
            if (empleado != null) {
                JPanel panelModificar = new JPanel(new GridLayout(6, 2, 10, 10));
                panelModificar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JTextField fieldNombreModificar = new JTextField(empleado.getNombre());
                JTextField fieldIDModificar = new JTextField(empleado.getCodigo());
                JComboBox<String> boxDepartamentoModificar = new JComboBox<>();
                JComboBox<String> boxProyectoModificar = new JComboBox<>();
                JComboBox<String> boxTipoEmpleadoModificar = new JComboBox<>(new String[]{"Gerente", "Técnico"});
                JComboBox<String> boxContribucionModificar = new JComboBox<>();

                // Cargar combos con los valores actuales del empleado
                for (Departamento d : departamentos) {
                    boxDepartamentoModificar.addItem(d.getNombre());
                }
                for (Proyecto p : proyectos) {
                    boxProyectoModificar.addItem(p.getNombre());
                }

                // Seleccionar los valores actuales del empleado en los combos
                boxDepartamentoModificar.setSelectedItem(empleado.getDepartamento().getNombre());
                boxProyectoModificar.setSelectedItem(empleado.getProyecto().getNombre());
                boxTipoEmpleadoModificar.setSelectedItem(empleado instanceof Gerente ? "Gerente" : "Técnico");

                // Actualizar el combo de contribución según el tipo de empleado
                actualizarComboContribucionModificar(boxTipoEmpleadoModificar, boxContribucionModificar);

                // Añadir un ActionListener al combo de tipo de empleado para actualizar el combo de contribución dinámicamente
                boxTipoEmpleadoModificar.addActionListener(e -> {
                    actualizarComboContribucionModificar(boxTipoEmpleadoModificar, boxContribucionModificar);
                });

                // Seleccionar la contribución actual del empleado
                if (empleado instanceof Gerente) {
                    boxContribucionModificar.setSelectedItem(((Gerente) empleado).getContribucion().name());
                } else {
                    boxContribucionModificar.setSelectedItem(((Tecnico) empleado).getContribucion().name());
                }

                // Añadir campos al panel
                panelModificar.add(new JLabel("Nombre:"));
                panelModificar.add(fieldNombreModificar);
                panelModificar.add(new JLabel("ID:"));
                panelModificar.add(fieldIDModificar);
                panelModificar.add(new JLabel("Departamento:"));
                panelModificar.add(boxDepartamentoModificar);
                panelModificar.add(new JLabel("Proyecto:"));
                panelModificar.add(boxProyectoModificar);
                panelModificar.add(new JLabel("Tipo Empleado:"));
                panelModificar.add(boxTipoEmpleadoModificar);
                panelModificar.add(new JLabel("Contribución:"));
                panelModificar.add(boxContribucionModificar);

                // Mostrar la ventana de diálogo
                int opcion = JOptionPane.showConfirmDialog(this, panelModificar, "Modificar Empleado", JOptionPane.OK_CANCEL_OPTION);
                if (opcion == JOptionPane.OK_OPTION) {
                    // Obtener los nuevos valores
                    String nuevoNombre = fieldNombreModificar.getText();
                    String nuevoID = fieldIDModificar.getText();
                    String nuevoDepartamento = (String) boxDepartamentoModificar.getSelectedItem();
                    String nuevoProyecto = (String) boxProyectoModificar.getSelectedItem();
                    String nuevoTipoEmpleado = (String) boxTipoEmpleadoModificar.getSelectedItem();
                    String nuevaContribucion = (String) boxContribucionModificar.getSelectedItem();

                    // Buscar el departamento y proyecto seleccionados
                    Departamento departamentoSeleccionado = departamentos.stream()
                            .filter(d -> d.getNombre().equals(nuevoDepartamento))
                            .findFirst()
                            .orElse(null);

                    Proyecto proyectoSeleccionado = proyectos.stream()
                            .filter(p -> p.getNombre().equals(nuevoProyecto))
                            .findFirst()
                            .orElse(null);

                    if (departamentoSeleccionado != null && proyectoSeleccionado != null) {
                        // Crear el empleado modificado
                        Empleado empleadoModificado;
                        if ("Gerente".equals(nuevoTipoEmpleado)) {
                            empleadoModificado = new Gerente(nuevoNombre, nuevoID, departamentoSeleccionado, proyectoSeleccionado,
                                    "Área de Gestión", TipoContribucionGerente.valueOf(nuevaContribucion));
                        } else {
                            empleadoModificado = new Tecnico(nuevoNombre, nuevoID, departamentoSeleccionado, proyectoSeleccionado,
                                    "Área Técnica", TipoContribucionTecnico.valueOf(nuevaContribucion));
                        }

                        // Llamar al método de modificación en la clase Empresa
                        empresa.modificarEmpleado(idEmpleado, empleadoModificado);

                        JOptionPane.showMessageDialog(this, "Empleado modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarComboContribucionModificar(JComboBox<String> boxTipoEmpleadoModificar, JComboBox<String> boxContribucionModificar) {
        boxContribucionModificar.removeAllItems();
        if ("Gerente".equals(boxTipoEmpleadoModificar.getSelectedItem())) {
            for (TipoContribucionGerente contribucion : TipoContribucionGerente.values()) {
                boxContribucionModificar.addItem(contribucion.name());
            }
        } else {
            for (TipoContribucionTecnico contribucion : TipoContribucionTecnico.values()) {
                boxContribucionModificar.addItem(contribucion.name());
            }
        }
    }

	private void mostrarVentanaEliminar() {
        String idEmpleado = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado a eliminar:");
        if (idEmpleado != null && !idEmpleado.isEmpty()) {
            Empleado empleado = empresa.buscarEmpleadoPorID(idEmpleado);
            if (empleado != null) {
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar al empleado " + empleado.getNombre() + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    empresa.eliminarEmpleado(empleado);
                    JOptionPane.showMessageDialog(this, "Empleado eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Actualizar el estado del presupuesto
                    actualizarEstadoPresupuesto();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

	private void mostrarInformacionEmpleado() {
	    String idEmpleado = JOptionPane.showInputDialog(this, "Ingrese el ID del empleado a mostrar:");
	    if (idEmpleado != null && !idEmpleado.isEmpty()) {
	        Empleado empleado = empresa.buscarEmpleadoPorID(idEmpleado);
	        if (empleado != null) {
	            EstadoPresupuesto estadoSeleccionado = (EstadoPresupuesto) boxEstadoPresupuesto.getSelectedItem();
	            numeroEstado = obtenerNumeroPorEstado(estadoSeleccionado);

	            String infoEmpleado = "Información del Empleado:\n" +
	                    "Nombre: " + empleado.getNombre() + "\n" +
	                    "ID: " + empleado.getCodigo() + "\n" +
	                    "Departamento: " + empleado.getDepartamento().getNombre() + "\n" +
	                    "Proyecto: " + empleado.getProyecto().getNombre() + "\n" +
	                    "Tipo Empleado: " + (empleado instanceof Gerente ? "Gerente" : "Técnico") + "\n" +
	                    "Contribución: " + (empleado instanceof Gerente ? ((Gerente) empleado).getContribucion() : ((Tecnico) empleado).getContribucion()) + "\n" +
	                    "Número de Estado Presupuesto: " + numeroEstado;

	            JOptionPane.showMessageDialog(this, infoEmpleado, "Información del Empleado", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}