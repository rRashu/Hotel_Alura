package Accesos_Datos;

import Modelo.Huesped;
import Modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reservasDAO {
private final Connection con;

    public reservasDAO(Connection con) {
        this.con = con;
    }



    public void guardar(Reserva reserva) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO hotel_alura.reservas "
                            + "(fecha_entrada, fecha_salida,valor, forma_pago,id_huesped)"
                            + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setDate(1, reserva.getDia_entrada());
                statement.setDate(2, reserva.getGetDia_salida());
                statement.setDouble(3, reserva.getValor_cancelar());
                statement.setString(4, reserva.getForma_pago());
                statement.setInt(5, reserva.getId_huesped());
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));

                        System.out.printf("Fue insertado el producto: %s%n", reserva);
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
                    .prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_pago, id_huesped FROM hotel_alura.reservas");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getDate("fecha_entrada"),
                                resultSet.getDate("fecha_salida"),
                                resultSet.getDouble("valor"),
                                resultSet.getString("forma_pago"),
                                resultSet.getInt("id_huesped")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
}
