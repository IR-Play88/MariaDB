package es.etg.prog;

import java.util.ArrayList;
import java.util.List;

public class InstitutoMosckDAOImp implements InstitutoDAO {

     @Override
    public int insertar(Alumno a) throws Exception {
        return 1;
    }

    @Override
    public int insertar(List<Alumno> alumnos) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int actualizar(Alumno al) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public int borrar(Alumno al) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrar'");
    }

    @Override
    public List<Alumno> listarAlumnos() throws Exception {
        List<Alumno> alumnos = new ArrayList<>();

        alumnos.add(new Alumno("test1", "test1", 1));
        alumnos.add(new Alumno("test2", "test2", 2));

        return alumnos;
    }

    @Override
    public List<Alumno> listarAlumnos(int edad) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAlumnos'");
    }

    @Override
    public void crearTablaAlumno() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

}
