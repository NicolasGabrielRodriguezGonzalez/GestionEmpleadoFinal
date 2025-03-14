package mundo;

import java.util.LinkedList;

public class Proyecto implements Cloneable {
    private String nombre;
    private String codigo;
    private LinkedList<Empleado> empleadosAsignados = new LinkedList<>();
    private LinkedList<Departamento> departamentos = new LinkedList<>();

    // Constructor
    public Proyecto(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.empleadosAsignados = new LinkedList<>();
        this.departamentos = new LinkedList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LinkedList<Empleado> getEmpleadosAsignados() {
        return empleadosAsignados;
    }

    public void setEmpleadosAsignados(LinkedList<Empleado> empleadosAsignados) {
        this.empleadosAsignados = empleadosAsignados;
    }

    public LinkedList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(LinkedList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    // Agregar empleado
    public void agregarEmpleado(Empleado empleado) {
        empleadosAsignados.add(empleado);
    }

    // Método para clonar un proyecto y asignarlo a un nuevo departamento
    public Proyecto clonarProyecto(String nuevoCodigo, Departamento nuevoDepartamento) {
        try {
            Proyecto clon = (Proyecto) super.clone(); // Clonación superficial

            // Clonación profunda de empleados
            clon.empleadosAsignados = new LinkedList<>();
            for (Empleado empleado : this.empleadosAsignados) {
                Empleado empleadoClonado = empleado.clone();
                empleadoClonado.setProyecto(clon); // Asignar el nuevo proyecto clonado
                empleadoClonado.setDepartamento(nuevoDepartamento); // Asignar el nuevo departamento
                clon.empleadosAsignados.add(empleadoClonado);
            }

            // Clonación profunda de departamentos
            clon.departamentos = new LinkedList<>();
            clon.departamentos.add(nuevoDepartamento);

            // Asignar el nuevo código al proyecto clonado
            clon.setCodigo(nuevoCodigo);

            return clon;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar el proyecto", e);
        }
    }

    // Clase Builder interna para Proyecto
    public static class ProyectoBuilder implements IBuilder<Proyecto> {
        private String nombre;
        private String codigo;
        private LinkedList<Empleado> empleadosAsignados = new LinkedList<>();
        private LinkedList<Departamento> departamentos = new LinkedList<>();

        public ProyectoBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ProyectoBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public ProyectoBuilder agregarEmpleado(Empleado empleado) {
            this.empleadosAsignados.add(empleado);
            return this;
        }

        public ProyectoBuilder agregarDepartamento(Departamento departamento) {
            this.departamentos.add(departamento);
            return this;
        }

        @Override
        public Proyecto build() {
            Proyecto proyecto = new Proyecto(nombre, codigo);
            proyecto.empleadosAsignados.addAll(empleadosAsignados);
            proyecto.departamentos.addAll(departamentos);
            return proyecto;
        }
    }
}
