package practica_7.soap;

import practica_7.encapsulaciones.Estudiante;
import practica_7.servicios.EstudianteServices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para implementar un servicio web basado en SOAP
 */
@WebService
public class EstudianteWebServices {

    private EstudianteServices estudianteServices = EstudianteServices.getInstancia();

    @WebMethod
    public List<Estudiante> getListaEstudiantes(){
        return estudianteServices.listarEstudiante();
    }

    @WebMethod
    public Estudiante getEstudiante(int matricula){
        return estudianteServices.getEstudiantePorMatricula(matricula);
    }

    @WebMethod
    public Estudiante crearEstudiante(Estudiante estudiante){
        return estudianteServices.crearEstudiante(estudiante);
    }

    @WebMethod
    public Estudiante actualizarEstudiante(Estudiante estudiante){
        return estudianteServices.actualizarEstudiante(estudiante);
    }

    @WebMethod
    public boolean borrarEstudiante(Estudiante estudiante){
        return estudianteServices.eliminarEstudiante(estudiante);
    }

}
