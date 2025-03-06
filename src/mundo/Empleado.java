package mundo;

public abstract class Empleado {
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
 


    public abstract void mostrarInformacion();
    //Metodo abstracto.
    public abstract double calcularSalario();
}