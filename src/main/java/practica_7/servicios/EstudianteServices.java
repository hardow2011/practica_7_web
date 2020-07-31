package practica_7.servicios;

import practica_7.encapsulaciones.*;
import practica_7.util.NoExisteEstudianteException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Ejemplo de servicio patron Singleton
 */
public class EstudianteServices {

    private static EstudianteServices instancia;
    private List<Estudiante> listaEstudiante = new ArrayList<>();

    /**
     * Constructor privado.
     */
    private EstudianteServices(){

    }

    public static EstudianteServices getInstancia(){
        if(instancia==null){
            instancia = new EstudianteServices();
        }
        return instancia;
    }

    public List<Estudiante> listarEstudiante(){
        return listaEstudiante;
    }

    public Estudiante getEstudiantePorMatricula(int matricula){
        return listaEstudiante.stream().filter(e -> e.getMatricula() == matricula).findFirst().orElse(null);
    }

    public Estudiante crearEstudiante(Estudiante estudiante){
        if(getEstudiantePorMatricula(estudiante.getMatricula())!=null){
            System.out.println("Estudiante registrado...");
            return null; //generar una excepcion...
        }
        listaEstudiante.add(estudiante);
        return estudiante;
    }

    public Estudiante actualizarEstudiante(Estudiante estudiante){
        Estudiante tmp = getEstudiantePorMatricula(estudiante.getMatricula());
        if(tmp == null){//no existe, no puede se actualizado
            throw new NoExisteEstudianteException("No Existe el estudiante: "+estudiante.getMatricula());
        }
        tmp.actualizar(estudiante);
        return tmp;
    }

    public boolean eliminandoEstudiante(int matricula){
        return listaEstudiante.remove(getEstudiantePorMatricula(matricula));
    }

}
