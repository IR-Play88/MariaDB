import org.junit.jupiter.api.Test;
import es.etg.prog.InstitutoDAO;

public class InstitutoMariaDBDAOImpTest {

    private static InstitutoDAO dao;

    @Test
    public void crearTablaAlumnosTest() throws Exception {
        dao.crearTablaAlumno();
    }

}
