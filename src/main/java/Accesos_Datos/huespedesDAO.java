package Accesos_Datos;

import Modelo.Huesped;
import Modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class huespedesDAO {
private final Connection con;

    public huespedesDAO(Connection con) {
        this.con = con;
    }



    public void guardar(Huesped huesped) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO hotel_alura.huespedes "
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
                        System.out.printf("Fue insertado el producto: %s%n", huesped);
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
                    .prepareStatement("SELECT id, nombre, apellido, fecha_nacimiento,nacionalidad,telefono FROM hotel_alura.huespedes");

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

    public List<Huesped> resultadoBusqueda(String busqueda) {
        List<Huesped> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM hotel_alura.huespedes WHERE id LIKE '%" + busqueda + "%' OR nombre LIKE '%" + busqueda + "%' OR apellido LIKE '%" + busqueda + "%' OR fecha_nacimiento LIKE '%" + busqueda + "%'OR nacionalidad LIKE '%" + busqueda + "%' OR telefono LIKE '%" + busqueda + "%'");
            statement.execute();
            final ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                resultado.add(new Huesped(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getDate("fecha_nacimiento"),
                        resultSet.getString("nacionalidad"),
                        resultSet.getString("telefono")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int modificar(Huesped huesped) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE hotel_alura.huespedes SET " + " nombre = ?, " + " apellido = ?," + " fecha_nacimiento = ?," + " nacionalidad  = ?," + " telefono = ?" + " WHERE id = ?");

            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFecha_nacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(5, huesped.getId());
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listarConReserva(int idbuscar) {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT h.id, h.nombre, h.apellido, h.fecha_nacimiento, h.nacionalidad, h.telefono, r.id, r.fecha_entrada, r.fecha_salida, r.valor, r.forma_pago, r.id_huesped "
                            + " FROM hotel_alura.huespedes h LEFT JOIN  hotel_alura.reservas r"
                            + "  ON h.id = r.id_huesped WHERE h.id= " + idbuscar);

            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        int huespedid = resultSet.getInt("h.id");
                        String huespednombre = resultSet.getString("h.nombre");
                        String huespedapellido = resultSet.getString("h.apellido");
                        Date huespedfechanacimiento = resultSet.getDate("h.fecha_nacimiento");
                        String huespednacionalidad = resultSet.getString("h.nacionalidad");
                        String huespedntelefono = resultSet.getString("h.telefono");

                        Huesped huesped = resultado
                                .stream()
                                .filter(cat -> cat.getIdinter().equals(huespedid))
                                .findAny().orElseGet(() -> {
                                    Huesped cat = new Huesped(
                                            huespedid, huespednombre, huespedapellido, huespedfechanacimiento,
                                            huespednacionalidad, huespedntelefono);
                                    resultado.add(cat);
                                    return cat;
                                });

                        Reserva reserva = new Reserva(
                                resultSet.getInt("r.id"),
                                resultSet.getDate("r.fecha_entrada"),
                                resultSet.getDate("r.fecha_salida"),
                                resultSet.getDouble("r.valor"),
                                resultSet.getString("r.forma_pago"),
                                resultSet.getInt("r.id_huesped"));

                        huesped.agregar(reserva);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
}
