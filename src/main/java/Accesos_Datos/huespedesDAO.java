package Accesos_Datos;

import Modelo.Huesped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class huespedesDAO {
private Connection con;

    public huespedesDAO(Connection con) {
        this.con = con;
    }



    public void guardar(Huesped huesped) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO huespedes "
                            + "(nombre, apellido, fecha_nacimiento, nacionalidad,telefono)"
                            + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFecha_nacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));

                        System.out.println(String.format("Fue insertado el producto: %s", huesped.toString()));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento,nacionalidad,telefono FROM huespedes");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
}
