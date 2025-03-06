package mundo;

import java.util.LinkedList;

public class Proyecto {
    private String nombre, codigo;
    private LinkedList<Empleado> empleadosAsignados;

    public Proyecto(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.empleadosAsignados = new LinkedList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LinkedList<Empleado> getEmpleadosAsignados() { return empleadosAsignados; }

    public void agregarEmpleado(Empleado empleado) {
        empleadosAsignados.add(empleado);
    }

    public static LinkedList<Proyecto> cargarProyectos() {
        LinkedList<Proyecto> proyectos = new LinkedList<>();
        proyectos.add(new Proyecto("Sistema Web", "P001"));
        proyectos.add(new Proyecto("App Móvil", "P002"));
        return proyectos;
    }
}