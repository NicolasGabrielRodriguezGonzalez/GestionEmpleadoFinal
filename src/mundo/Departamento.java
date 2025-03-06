package mundo;

import java.util.LinkedList;

public class Departamento {
    private String nombre, codigo;
    private LinkedList<Empleado> empleados;

    public Departamento(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.empleados = new LinkedList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LinkedList<Empleado> getEmpleados() { return empleados; }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public static LinkedList<Departamento> cargarDepartamentos() {
        LinkedList<Departamento> departamentos = new LinkedList<>();
        departamentos.add(new Departamento("TI", "001"));
        departamentos.add(new Departamento("Ventas", "002"));
        return departamentos;
    }
}