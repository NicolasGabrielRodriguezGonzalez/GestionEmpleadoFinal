package mundo;

import java.util.LinkedList;

public class Empresa {
    private String nombre;
    private LinkedList<Empleado> empleados = new LinkedList<>();
    private LinkedList<Proyecto> proyectos = new LinkedList<>();
    private LinkedList<Departamento> departamentos = new LinkedList<>();
    private LinkedList <Presupuesto> listaPresupuestos = new LinkedList<>();
    //Singlenton
    private static Empresa instancia;
    private Empresa() {
    }
    public static Empresa getInstancia() {
        if (instancia == null) {
            instancia = new Empresa();
        }
        return instancia;
    }
    // Builder
    public static class EmpresaBuilder {
        private String nombre;
        private LinkedList<Empleado> empleados = new LinkedList<>();
        private LinkedList<Proyecto> proyectos = new LinkedList<>();
        private LinkedList<Departamento> departamentos = new LinkedList<>();
        private LinkedList<Presupuesto> listaPresupuestos = new LinkedList<>();

        public EmpresaBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public EmpresaBuilder setEmpleados(LinkedList<Empleado> empleados) {
            this.empleados = empleados;
            return this;
        }

        public EmpresaBuilder setProyectos(LinkedList<Proyecto> proyectos) {
            this.proyectos = proyectos;
            return this;
        }

        public EmpresaBuilder setDepartamentos(LinkedList<Departamento> departamentos) {
            this.departamentos = departamentos;
            return this;
        }

        public EmpresaBuilder setPresupuestos(LinkedList<Presupuesto> listaPresupuestos) {
            this.listaPresupuestos = listaPresupuestos;
            return this;
        }

        public Empresa build() {
            Empresa empresa = Empresa.getInstancia();
            empresa.setNombre(this.nombre);
            empresa.setEmpleados(this.empleados);
            empresa.setProyectos(this.proyectos);
            empresa.setDepartamentos(this.departamentos);
            empresa.setPresupuestos(this.listaPresupuestos);
            return empresa;
        }
    }
    public Empresa(String nombre, LinkedList<Empleado> empleados, LinkedList<Proyecto> proyectos,
                   LinkedList<Departamento> departamentos, LinkedList <Presupuesto> listaPresupuestos) {
        this.nombre = nombre;
        this.empleados = empleados;
        this.proyectos = proyectos;
        this.departamentos = departamentos;
        this.listaPresupuestos = listaPresupuestos;
    }

    // Método para buscar un empleado por su ID
    public Empleado buscarEmpleadoPorID(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getCodigo().equals(id)) {
                return empleado;
            }
        }
        return null; // Retorna null si no se encuentra el empleado
    }

    public LinkedList<Presupuesto> getPresupuestos() {
		return listaPresupuestos;
	}

	public void setPresupuestos(LinkedList<Presupuesto> presupuestos) {
		this.listaPresupuestos = presupuestos;
	}

	// Método para modificar un empleado
    public void modificarEmpleado(String id, Empleado empleadoModificado) {
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            if (empleado.getCodigo().equals(id)) {
                empleados.set(i, empleadoModificado); // Reemplaza el empleado antiguo con el modificado
                System.out.println("Empleado modificado exitosamente.");
                return;
            }
        }
        System.out.println("Empleado no encontrado.");
    }

    // Método para eliminar un empleado
    public void eliminarEmpleado(Empleado empleado) {
        if (empleados.removeIf(e -> e.getCodigo().equals(empleado.getCodigo()))) {
            System.out.println("Empleado eliminado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    // Resto de los métodos de la clase Empresa...

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(LinkedList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public LinkedList<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(LinkedList<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public LinkedList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(LinkedList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public void agregarGerente(Gerente gerente) {
        empleados.add(gerente);
        System.out.println("Gerente agregado exitosamente.");
    }

    public void agregarTecnico(Tecnico tecnico) {
        empleados.add(tecnico);
        System.out.println("Técnico agregado exitosamente.");
    }

    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    public void agregarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public void mostrarDepartamento() {
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }

    public void mostrarProyecto() {
        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto);
        }
    }

    public void mostrarGerentes() {
        for (Empleado empleado : empleados) {
            if (empleado instanceof Gerente) {
                System.out.println(empleado);
            }
        }
    }

    public void mostrarTecnicos() {
        for (Empleado empleado : empleados) {
            if (empleado instanceof Tecnico) {
                System.out.println(empleado);
            }
        }
    }

    public void modificarGerente(Gerente gerenteModificado) {
        for (Empleado empleado : empleados) {
            if (empleado instanceof Gerente) {
                Gerente gerente = (Gerente) empleado;
                if (gerente.getCodigo().equals(gerenteModificado.getCodigo())) {
                    gerente.setNombre(gerenteModificado.getNombre());
                    gerente.setAreaEncargada(gerenteModificado.getAreaEncargada());
                    gerente.setContribucion(gerenteModificado.getContribucion());
                    System.out.println("Gerente modificado exitosamente.");
                    return;
                }
            }
        }
        System.out.println("Gerente no encontrado.");
    }

    public void modificarTecnico(Tecnico tecnicoModificado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i) instanceof Tecnico) {
                Tecnico tecnico = (Tecnico) empleados.get(i);
                if (tecnico.getCodigo().equals(tecnicoModificado.getCodigo())) {
                    tecnico.setNombre(tecnicoModificado.getNombre());
                    tecnico.setAreaEspecifica(tecnicoModificado.getAreaEspecifica());
                    tecnico.setContribucion(tecnicoModificado.getContribucion());
                    System.out.println("Técnico modificado exitosamente.");
                    return;
                }
            }
        }
        System.out.println("Técnico no encontrado.");
    }

    public void modificarDepartamento(Departamento departamentoModificado) {
        for (Departamento departamento : departamentos) {
            if (departamento.getCodigo().equals(departamentoModificado.getCodigo())) {
                departamento.setNombre(departamentoModificado.getNombre());
                System.out.println("Departamento modificado exitosamente.");
                return;
            }
        }	
        System.out.println("Departamento no encontrado.");
    }

    public void modificarProyecto(Proyecto proyectoModificado) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getCodigo().equals(proyectoModificado.getCodigo())) {
                proyecto.setNombre(proyectoModificado.getNombre());
                System.out.println("Proyecto modificado exitosamente.");
                return;
            }
        }
        System.out.println("Proyecto no encontrado.");
    }

    public void eliminarGerente(Gerente gerente) {
        if (empleados.removeIf(e -> e instanceof Gerente && e.getCodigo().equals(gerente.getCodigo()))) {
            System.out.println("Gerente eliminado exitosamente.");
        } else {
            System.out.println("Gerente no encontrado.");
        }
    }

    public void eliminarTecnico(Tecnico tecnico) {
        if (empleados.removeIf(e -> e instanceof Tecnico && e.getCodigo().equals(tecnico.getCodigo()))) {
            System.out.println("Técnico eliminado exitosamente.");
        } else {
            System.out.println("Técnico no encontrado.");
        }
    }

    public void eliminarDepartamento(Departamento departamento) {
        if (departamentos.removeIf(d -> d.getCodigo().equals(departamento.getCodigo()))) {
            System.out.println("Departamento eliminado exitosamente.");
        } else {
            System.out.println("Departamento no encontrado.");
        }
    }

    public void eliminarProyecto(Proyecto proyecto) {
        if (proyectos.removeIf(p -> p.getCodigo().equals(proyecto.getCodigo()))) {
            System.out.println("Proyecto eliminado exitosamente.");
        } else {
            System.out.println("Proyecto no encontrado.");
        }
    }

    public static LinkedList<Departamento> cargarDepartamentos() {
        LinkedList<Departamento> departamentos = new LinkedList<>();
        departamentos.add(new Departamento("TI", "001"));
        departamentos.add(new Departamento("Ventas", "002"));
        return departamentos;
    }

    public static LinkedList<Proyecto> cargarProyectos() {
        LinkedList<Proyecto> proyectos = new LinkedList<>();
        proyectos.add(new Proyecto("Sistema Web", "P001"));
        proyectos.add(new Proyecto("App Móvil", "P002"));
        return proyectos;
    }
	 // Método para agregar un presupuesto
	    public void agregarPresupuesto(Presupuesto presupuesto) {
	        if (presupuesto != null && !listaPresupuestos.contains(presupuesto)) {
	            listaPresupuestos.add(presupuesto);
	            System.out.println("Presupuesto agregado correctamente.");
	        } else {
	            System.out.println("El presupuesto ya existe o es inválido.");
	        }
	    }
	
	    // Método para buscar un presupuesto por ID
	    public Presupuesto buscarPresupuesto(String id) {
	        for (Presupuesto presupuesto : listaPresupuestos) {
	            if (presupuesto.getIdPresupuesto().equalsIgnoreCase(id)) {
	                return presupuesto;
	            }
	        }
	        return null;
	    }
	
	    // Método para eliminar un presupuesto por ID
	    public boolean eliminarPresupuesto(String id) {
	        Presupuesto presupuesto = buscarPresupuesto(id);
	        if (presupuesto != null) {
	            listaPresupuestos.remove(presupuesto);
	            System.out.println("Presupuesto eliminado correctamente.");
	            return true;
	        }
	        System.out.println("Presupuesto no encontrado.");
	        return false;
	    }
	
	    // Método para modificar un presupuesto por ID
	    public boolean modificarPresupuesto(String id, double nuevoValor, String nuevaDescripcion) {
	        Presupuesto presupuesto = buscarPresupuesto(id);
	        if (presupuesto != null) {
	            presupuesto.setValor(nuevoValor);
	            presupuesto.setDescripcion(nuevaDescripcion);
	            System.out.println("Presupuesto modificado correctamente.");
	            return true;
	        }
	        System.out.println("Presupuesto no encontrado.");
	        return false;
	    }
	
	    // Método para mostrar todos los presupuestos
	    public void mostrarPresupuestos() {
	        if (listaPresupuestos.isEmpty()) {
	            System.out.println("No hay presupuestos registrados.");
	        } else {
	            System.out.println("Lista de Presupuestos:");
	            for (Presupuesto presupuesto : listaPresupuestos) {
	                System.out.println(presupuesto);
	            }
	        }
	    }
	

    @Override
    public String toString() {
        return "Empresa [nombre=" + nombre + ", empleados=" + empleados + ", proyectos=" + proyectos
                + ", departamentos=" + departamentos + "]";
    }
}