package mundo;

public  class Empleado  implements Cloneable{
    private String nombre, codigo;
    private Departamento departamento;
    private Proyecto proyecto;

    public Empleado(String nombre, String codigo, Departamento departamento, Proyecto proyecto) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.departamento = departamento;
        this.proyecto = proyecto;
   
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
    @Override
    public Empleado clone() {
        try {
            return (Empleado) super.clone(); // Clonación superficial
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar el empleado", e);
        }
    }


    public static class EmpleadoBuilder implements IBuilder <Empleado>{
    	public String nombre, codigo;
        public Departamento departamento;
        public Proyecto proyecto;
		public EmpleadoBuilder() {
			super();
		}
		public EmpleadoBuilder(String nombre, String codigo, Departamento departamento, Proyecto proyecto) {
			super();
			this.nombre = nombre;
			this.codigo = codigo;
			this.departamento = departamento;
			this.proyecto = proyecto;
		}
		public EmpleadoBuilder setNombre(String nombre) {
			this.nombre = nombre;
			return this;
		}
		public EmpleadoBuilder setCodigo(String codigo) {
			this.codigo = codigo;
			return this;
		}
		public EmpleadoBuilder setDepartamento(Departamento departamento) {
			this.departamento = departamento;
			return this;
		}
		public EmpleadoBuilder setProyecto(Proyecto proyecto) {
			this.proyecto = proyecto;
			return this;
		}
		@Override
		public Empleado build() {
			// TODO Auto-generated method stub
			return new Empleado (nombre,codigo,departamento,proyecto);
		}
		
        
    }

}