package mundo;

public class Gerente extends Empleado implements Contribuyente, IAcciones {
    private String areaEncargada;
    private TipoContribucionGerente contribucion;

    public Gerente(String nombre, String codigo, Departamento departamento, Proyecto proyecto, String areaEncargada, TipoContribucionGerente contribucion) {
        super(nombre, codigo, departamento, proyecto);
        this.areaEncargada = areaEncargada;
        this.contribucion = contribucion;
    }

    public String getAreaEncargada() {
        return areaEncargada;
    }

    public void setAreaEncargada(String areaEncargada) {
        this.areaEncargada = areaEncargada;
    }

    public TipoContribucionGerente getContribucion() {
        return contribucion;
    }

    public void setContribucion(TipoContribucionGerente contribucion) {
        this.contribucion = contribucion;
    }

    public void gestionProyecto() {
        System.out.println(getNombre() + " está gestionando " + getProyecto().getNombre());
        // Lógica de gestión de proyectos
    }

    @Override
    public Enum<?> contribuir() {
        return contribucion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Gerente: " + getNombre() + ", Código: " + getCodigo());
    }

    @Override
    public double calcularSalario() {
        double salarioBase = 5000;
        switch (contribucion) {
            case GESTION_ESTRATEGICA:
                return salarioBase * 1.5;
            case PLANIFICACION:
                return salarioBase * 1.3;
            case SUPERVISION:
                return salarioBase * 1.2;
            default:
                return salarioBase;
        }
    }

    // Builder interno para la clase Gerente
    public static class GerenteBuilder {
        private String nombre;
        private String codigo;
        private Departamento departamento;
        private Proyecto proyecto;
        private String areaEncargada;
        private TipoContribucionGerente contribucion;

        public GerenteBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public GerenteBuilder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public GerenteBuilder setDepartamento(Departamento departamento) {
            this.departamento = departamento;
            return this;
        }

        public GerenteBuilder setProyecto(Proyecto proyecto) {
            this.proyecto = proyecto;
            return this;
        }

        public GerenteBuilder setAreaEncargada(String areaEncargada) {
            this.areaEncargada = areaEncargada;
            return this;
        }

        public GerenteBuilder setContribucion(TipoContribucionGerente contribucion) {
            this.contribucion = contribucion;
            return this;
        }

        // Método para construir el objeto Gerente
        public Gerente build() {
            return new Gerente(nombre, codigo, departamento, proyecto, areaEncargada, contribucion);
        }
    }
}
