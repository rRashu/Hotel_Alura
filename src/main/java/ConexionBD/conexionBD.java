package ConexionBD;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class conexionBD {

   private final DataSource dataSource;

    public conexionBD() {
        var comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("1234");
        comboPooledDataSource.setMaxPoolSize(10);
        this.dataSource = comboPooledDataSource;


    }

    public Connection recuperaConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
