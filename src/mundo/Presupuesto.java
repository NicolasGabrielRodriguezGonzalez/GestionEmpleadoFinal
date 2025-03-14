package mundo;

public class Presupuesto implements IPresupuesto{
	private String idPresupuesto;
	private double valor;
	private EstadoPresupuesto estado;
	private String descripcion;
	private Proyecto proyecto;//PROYECTO ASIGNADO
	public Presupuesto(String idPresupuesto, double valor, EstadoPresupuesto estado, String descripcion,  Proyecto proyecto) {
		super();
		this.idPresupuesto = idPresupuesto;
		this.valor = valor;
		this.estado = estado;
		this.descripcion = descripcion;
		this.proyecto = proyecto;
	}
	
	@Override
	public void calcularPresupuesto() {
		System.out.println("Presupuesto calculado con exito");
		
	}

	public String getIdPresupuesto() {
		return idPresupuesto;
	}
	public void setIdPresupuesto(String idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public EstadoPresupuesto getEstado() {
		return estado;
	}
	public void setEstado(EstadoPresupuesto estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public int obtenerNumeroPorEstado() {
	    switch (this.estado) {
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
	
	public static class PresupuestoBuilder implements IBuilder <Presupuesto>{
		private String idPresupuesto;
		private double valor;
		private EstadoPresupuesto estado;
		private String descripcion;
		private Proyecto proyecto;
		public PresupuestoBuilder() {
		}
		public PresupuestoBuilder(String idPresupuesto, double valor, EstadoPresupuesto estado, String descripcion, Proyecto proyecto) {
			super();
			this.idPresupuesto = idPresupuesto;
			this.valor = valor;
			this.estado = estado;
			this.descripcion = descripcion;
			this.proyecto = proyecto;
		}
		public PresupuestoBuilder setIdPresupuesto(String idPresupuesto) {
			this.idPresupuesto = idPresupuesto;
			return this;
		}
		public PresupuestoBuilder setValor(double valor) {
			this.valor = valor;
			return this;
		}
		public PresupuestoBuilder setEstado(EstadoPresupuesto estado) {
			this.estado = estado;
			return this;
		}
		public PresupuestoBuilder setDescripcion(String descripcion) {
			this.descripcion = descripcion;
			return this;
			
		}
		
		public PresupuestoBuilder setProyecto(Proyecto proyecto) {
			this.proyecto = proyecto;
			return this;
		}
		@Override
		public Presupuesto build() {
			// TODO Auto-generated method stub
			return new Presupuesto (idPresupuesto, valor, estado, descripcion, proyecto);
		}
		
		
		
	}


}
