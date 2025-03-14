package mundo;

public class Tecnico extends Empleado implements Contribuyente, IAcciones {
    private String areaEspecifica;
    private TipoContribucionTecnico contribucion;

    public Tecnico(String nombre, String codigo, Departamento departamento, Proyecto proyecto, 
                   String areaEspecifica, TipoContribucionTecnico contribucion) {
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

    public TipoContribucionTecnico getContribucion() {
        return contribucion;
    }

    public void setContribucion(TipoContribucionTecnico contribucion) {
        this.contribucion = contribucion;
    }

    @Override
    public Enum<?> contribuir() {
        return contribucion;
    }

    @Override
    public double calcularSalario() {
        double salarioBase = 3000;
        switch (contribucion) {
            case DESARROLLO_SOFTWARE:
                return salarioBase * 1.4;
            case MANTENIMIENTO:
                return salarioBase * 1.2;
            case SOPORTE_TECNICO:
                return salarioBase * 1.1;
            default:
                return salarioBase;
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Técnico: " + getNombre() + ", Código: " + getCodigo() + 
                           ", Área: " + areaEspecifica + 
                           ", Contribución: " + contribucion);
    }

    public static class TecnicoBuilder implements IBuilder<Tecnico> {
        private String nombre;
        private String codigo;
        private Departamento departamento;
        private Proyecto proyecto;
        private String areaEspecifica;
        private TipoContribucionTecnico contribucion;

        public TecnicoBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public TecnicoBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public TecnicoBuilder setDepartamento(Departamento departamento) {
            this.departamento = departamento;
            return this;
        }

        public TecnicoBuilder setProyecto(Proyecto proyecto) {
            this.proyecto = proyecto;
            return this;
        }

        public TecnicoBuilder setAreaEspecifica(String areaEspecifica) {
            this.areaEspecifica = areaEspecifica;
            return this;
        }

        public TecnicoBuilder setContribucion(TipoContribucionTecnico contribucion) {
            this.contribucion = contribucion;
            return this;
        }

        @Override
        public Tecnico build() {
            return new Tecnico(nombre, codigo, departamento, proyecto, areaEspecifica, contribucion);
        }
    }
}
