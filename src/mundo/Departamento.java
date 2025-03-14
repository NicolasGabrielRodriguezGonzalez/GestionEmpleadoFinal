package mundo;

import java.util.LinkedList;

public class Departamento implements Cloneable{
    private String nombre, codigo;
    private LinkedList<Empleado> empleados;//Un departamento tiene varios empleados.

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
    @Override
    public Departamento clone() {
        try {
            Departamento clon = (Departamento) super.clone(); // Clonación superficial
            clon.empleados = new LinkedList<>();
            for (Empleado empleado : this.empleados) {
                clon.empleados.add(empleado.clone()); // Clonación profunda
            }
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar el departamento", e);
        }
    }

    public static class DepartamentoBuilder implements IBuilder <Departamento>{
    	 private String nombre, codigo;
    	 private LinkedList<Empleado> empleados;
		public DepartamentoBuilder() {
			super();
		}
		public DepartamentoBuilder(String nombre, String codigo, LinkedList<Empleado> empleados) {
			super();
			this.nombre = nombre;
			this.codigo = codigo;
			this.empleados = empleados;
		}
		public DepartamentoBuilder setNombre(String nombre) {
			this.nombre = nombre;
			return this;
		}
		public DepartamentoBuilder setCodigo(String codigo) {
			this.codigo = codigo;
			return this;
		}
		public DepartamentoBuilder setEmpleados(LinkedList<Empleado> empleados) {
			this.empleados = empleados;
			return this;
		}
		@Override
		public Departamento build() {
			// TODO Auto-generated method stub
			return new Departamento (nombre, codigo);
		}
		
    	
    }
    

        
    
}