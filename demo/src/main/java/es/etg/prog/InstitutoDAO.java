package es.etg.prog;

import java.util.List;

public interface InstitutoDAO {
    public void crearTablaAlumno()throws Exception;
    public int insertar(Alumno a) throws Exception;
    public int insertar(List<Alumno> alumnos) throws Exception;
    public int actualizar(Alumno al) throws Exception;
    public int borrar(Alumno al) throws Exception;
    public List<Alumno> listarAlumnos() throws Exception;
    public List<Alumno> listarAlumnos(int edad) throws Exception;
}
