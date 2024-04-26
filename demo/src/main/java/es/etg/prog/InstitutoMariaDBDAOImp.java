package es.etg.prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InstitutoMariaDBDAOImp implements InstitutoDAO {
    private final String URL = "jdbc:mariadb://localhost:3306/%s?user=%s&password=%s";
    private final String DATABASE_NAME = "instituto";
    private final String DATABASE_USER = "root";
    private final String DATABASE_PASS = "secret";

    private Connection conn;

    public InstitutoMariaDBDAOImp() throws Exception {

        conn = DriverManager.getConnection(String.format(URL, DATABASE_NAME, DATABASE_USER, DATABASE_PASS));
    }

    @Override
    public void crearTablaAlumno() throws Exception {
        final String SQL = "CREATE TABLE alumno( " +
                "nombre VARCHAR(100) PRIMARY KEY" +
                "apellido VARCHAR(255), " +
                "edat INT" + ")";

        Statement st = conn.createStatement();
        st.execute(SQL);
        st.close();
    }

    @Override
    public int insertar(Alumno a) throws Exception {
        int numRegistrosActualizados = 0;
        final String sql = "INSERT INTO alumno VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, a.getNombre());
        ps.setString(2, a.getApellidos());
        ps.setInt(3, a.getEdad());

        numRegistrosActualizados = ps.executeUpdate();
        ps.close();

        return numRegistrosActualizados;
    }

    @Override
    public int insertar(List<Alumno> alumnos) throws Exception {
        final String sql = "INSERT INTO alumno VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (Alumno a : alumnos) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidos());
            ps.setInt(3, a.getEdad());

            // Añade a la lista de ejecución ese insert
            ps.addBatch();
        }

        // Quito el autocommit por si falla algún insert que no haga ninguno.
        conn.setAutoCommit(false);
        ps.executeBatch();
        conn.setAutoCommit(true);

        ps.close();
        return alumnos.size();
    }

    @Override
    public int actualizar(Alumno al) throws Exception {
        int numRegistrosActualizados = 0;
        final String sql = "UPDATE alumno SET edad = ? where nombre = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, al.getEdad());
        ps.setString(2, al.getNombre());

        numRegistrosActualizados = ps.executeUpdate();

        ps.close();
        return numRegistrosActualizados;
    }

    @Override
    public int borrar(Alumno al) throws Exception {
        int numRegistrosActualizados = 0;
        final String sql = "DELETE FROM alumno where nombre = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, al.getNombre());

        numRegistrosActualizados = ps.executeUpdate();

        ps.close();
        return numRegistrosActualizados;
    }

    @Override
    public List<Alumno> listarAlumnos() throws Exception {
        final String query = "SELECT nombre, apellido, edad FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();

        // Prepara una sentencia de ejecución
        PreparedStatement ps = conn.prepareStatement(query);

        // Ejecutamos la sentencia y devolvemos un conjunto de datos de salida.
        ResultSet rs = ps.executeQuery();

        // Para cada tupla que devuelve la ejecución anterior (.next())
        while (rs.next()) {

            // Obtengo los diferentes datos a partir del nombre de la columna
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");

            Alumno a = new Alumno(nombre, apellido, edad);
            alumnos.add(a);
        }

        // Siempre tengo que cerrar el ResultSet y el PreparedStatement y así liberamos
        // los recursos.
        rs.close();
        ps.close();

        return alumnos;
    }

    @Override
    public List<Alumno> listarAlumnos(int edad) throws Exception {
        final String query = "SELECT nombre, apellido, edad FROM alumno where edad = ?";

        List<Alumno> alumnos = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, edad);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int e = rs.getInt("edad");

            Alumno a = new Alumno(nombre, apellido, e);
            alumnos.add(a);
        }
        rs.close();
        ps.close();

        return alumnos;
    }
}