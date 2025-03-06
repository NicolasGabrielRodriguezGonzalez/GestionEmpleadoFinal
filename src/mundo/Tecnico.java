package mundo;

public class Tecnico extends Empleado implements Contribuyente {
    private String areaEspecifica;
    private TipoContribucionTecnico contribucion;

    public Tecnico(String nombre, String codigo, Departamento departamento, Proyecto proyecto, String areaEspecifica, TipoContribucionTecnico contribucion) {
        super(nombre, codigo, departamento, proyecto);
        this.areaEspecifica = areaEspecifica;
        this.contribucion = contribucion;
    }

    public String getAreaEspecifica() {
        return areaEspecifica;
    }

    public void setAreaEspecifica(String areaEspecifica) {
        this.areaEspecifica = areaEspecifica;
    }

    @Override
    public Enum<?> contribuir() {//hecho
        return contribucion;
    }

    @Override
	public double calcularSalario() {//falta
    	 double salarioBase = 3000; 
         switch (contribucion) {
             case DESARROLLO_SOFTWARE: return salarioBase * 1.4;
             case MANTENIMIENTO: return salarioBase * 1.2;
             case SOPORTE_TECNICO: return salarioBase * 1.1;
             default: return salarioBase;
         }
	}

	@Override
    public void mostrarInformacion() {
        System.out.println("Técnico: " + getNombre() + ", Código: " + getCodigo());
    }
}