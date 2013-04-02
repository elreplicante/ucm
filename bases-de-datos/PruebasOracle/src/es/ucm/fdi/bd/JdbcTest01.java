package es.ucm.fdi.bd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;
import es.ucm.fdi.bd.utils.JdbcUtils;

/**
 * Prueba de Conexión a una BD Oracle.
 * 
 * @author Ivan Martinez Ortiz
 */
public class JdbcTest01 {
    private static final String URL = "jdbc:oracle:thin:@tania.fdi.ucm.es:1521/dbBDgris";
    
    private static final String USER = "ADMINUSER";
    
    private static final String PASSWORD = "ADMINUSER";
    
    public static void main(String args[]) throws SQLException {
        JdbcTest01 test = new JdbcTest01();
        test.test();
    }

    /**
     * Prueba la conexión al SGBD.
     */
    private void test() {
        Connection conn = null;

        try {
            // conn = getConnectionFromDriver();
            conn = getConnectionFromDataSource();

            /*
             * Obtenemos los metadatos de la conexión
             */
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("La versión del driver es: " + meta.getDriverVersion());

        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        } finally {
            /*
             * Cerramos la conexión
             */
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JdbcUtils.printSQLException(e);
            }
        }

    }

    /**
     * Crea una {@link Connection} con el SGBD.
     * 
     * @return la {@link Connection} al SGBD.
     * 
     * @throws SQLException
     *             si falla la conexión con el SGBD.
     */
    private Connection getConnectionFromDataSource() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(URL);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
        Connection conn = ds.getConnection();

        return conn;
    }

    /**
     * Crea una {@link Connection} con el SGBD. utiliza el método clásico para
     * obtener una {@link Connection} utilizando el {@link DriverManager}.
     * 
     * @return la {@link Connection} al SGBD.
     * 
     * @throws SQLException
     *             si falla la conexión con el SGBD.
     */
    private Connection getConnectionFromDriver() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

}