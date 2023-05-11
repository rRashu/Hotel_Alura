package Accesos_Datos;

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
                statement.setDate(2, reserva.getDia_salida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getForma_pago());
                statement.setInt(5, reserva.getId_huesped());
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                        System.out.printf("Fue insertado la reserva: %s%n", reserva);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_pago, id_huesped FROM hotel_alura.reservas");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Reserva(
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

    public List<Reserva> resultadoBusqueda(String busqueda) {
        List<Reserva> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM hotel_alura.reservas WHERE id LIKE '%" + busqueda + "%' OR fecha_entrada LIKE '%" + busqueda + "%' OR fecha_salida LIKE '%" + busqueda + "%' OR valor LIKE '%" + busqueda + "%'OR forma_pago LIKE '%" + busqueda + "%' OR id_huesped LIKE '%" + busqueda + "%'");
            statement.execute();
            final ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                resultado.add(new Reserva(
                        resultSet.getInt("id"),
                        resultSet.getDate("fecha_entrada"),
                        resultSet.getDate("fecha_salida"),
                        resultSet.getDouble("valor"),
                        resultSet.getString("forma_pago"),
                        resultSet.getInt("id_huesped")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public Reserva busquedaid(int busqueda) {
        Reserva resultado = new Reserva();
        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM hotel_alura.reservas WHERE id ="+busqueda);
            statement.execute();
            final ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                resultado.setId(resultSet.getInt("id"));
                resultado.setDia_entrada(resultSet.getDate("fecha_entrada"));
                resultado.setDia_salida(resultSet.getDate("fecha_salida"));
                resultado.setValor(resultSet.getDouble("valor"));
                resultado.setForma_pago(resultSet.getString("forma_pago"));
                resultado.setId_huesped(resultSet.getInt("id_huesped"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public void modificar(Reserva reserva) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE hotel_alura.reservas SET " + " fecha_entrada = ?, " + " fecha_salida = ?," + " valor = ?," + " forma_pago  = ?," + " id_huesped = ?" + " WHERE id = ?");

            try (statement) {
                statement.setDate(1, reserva.getDia_entrada());
                statement.setDate(2, reserva.getDia_salida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getForma_pago());
                statement.setInt(5, reserva.getId_huesped());
                statement.setInt(6, reserva.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminar(int id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM hotel_alura.reservas WHERE id = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
