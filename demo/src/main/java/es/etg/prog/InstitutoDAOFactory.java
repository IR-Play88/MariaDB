package es.etg.prog;

public class InstitutoDAOFactory {

    public static InstitutoDAO obtenerDAO(Modo modo) throws Exception{
        if (modo == Modo.MARIADB) {
            return new InstitutoMariaDBDAOImp();
        }else if(modo == Modo.SQLITE){
            return new InstitutoSQLiteDAOImp();
        }else{
            return new InstitutoMosckDAOImp();
        }
    }
}