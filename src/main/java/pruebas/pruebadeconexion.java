package pruebas;

import ConexionBD.conexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pruebadeconexion {
    public static void main(String[] args) throws SQLException {
        conexionBD factory = new conexionBD();
        Connection con = factory.recuperaConexion();
        String consulta = "SELECT * FROM `huespedes`";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("nombre"));
        }
        System.out.println("Cerrando la conexión");
        con.close();
    }
}
