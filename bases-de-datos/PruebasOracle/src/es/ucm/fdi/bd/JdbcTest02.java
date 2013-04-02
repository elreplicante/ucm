package es.ucm.fdi.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import es.ucm.fdi.bd.utils.JdbcUtils;
import es.ucm.fdi.bd.utils.PropertiesUtils;

/**
 * Ejecución de una consulta SELECT.
 * 
 * @author Ivan Martinez Ortiz
 */
public class JdbcTest02 {

    public static void main(String args[]) throws SQLException {
        JdbcTest02 test = new JdbcTest02();
        test.test();
    }

    /**
     * Prueba la conexión al SGBD.
     */
    private void test() {
        Connection conn = null;

        try {
            conn = getConnectionFromDataSource();

            ejecutaConsulta(conn);

        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        } finally {
            /*
             * Cerramos la conexión con el SGBD.
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
     * Ejemplo de uso de las clases de JDBC para lanzar una consulta SELECT contra el SGBD.
     * 
     * @param conn {@link Connection} con el SGBD que se utiliza para lanzar la consulta.
     */
    private void ejecutaConsulta(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Crea el objeto que representa la consulta SQL
            stmt = conn.createStatement();
            
            // Ejecuta la consulta SQL
            stmt.execute("SELECT 2+2 FROM DUAL");
            
            // Obtiene el objeto que representa el resultado de la consulta SQL
            rs = stmt.getResultSet();
            
            // Recorre y muestra el resultado de la consulta
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        } finally {
            /*
             * Cierra el objeto que represneta el resultado de la consulta
             */
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                JdbcUtils.printSQLException(e);
            }
            
            /*
             * Cierra el objeto que representa la consulta SQL
             */
            try {
                if (stmt != null) {
                    stmt.close();
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
        /*
         * Los datos de la conexión se cargan de un archivo
         */
        Properties properties = PropertiesUtils.cargaProperties("database.properties");

        OracleDataSource ds = new OracleDataSource();
        ds.setURL(properties.getProperty("jdbc.url"));
        ds.setUser(properties.getProperty("jdbc.username"));
        ds.setPassword(properties.getProperty("jdbc.password"));

        Connection conn = ds.getConnection();

        return conn;
    }
}