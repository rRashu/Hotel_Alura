package Util;

import Modelo.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;
import org.controlsfx.control.Notifications;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Util {

    public static void notificacion(Notifications noti, String title) {
        noti.title(title);
        noti.text("Verifique los Valores y vuelva a Intentar");
        noti.position(Pos.TOP_RIGHT);
        noti.owner(Window.getWindows().stream().filter(Window::isFocused).findFirst().orElse(null));
        noti.hideAfter(Duration.seconds(3));
        noti.darkStyle();
        noti.showWarning();
    }

    public static void notificacionbien(Notifications noti) {
        noti.title("Datos Guardados");
        noti.text("Los Datos fueron guardados Correctamente");
        noti.position(Pos.TOP_RIGHT);
        noti.owner(Window.getWindows().stream().filter(Window::isFocused).findFirst().orElse(null));
        noti.hideAfter(Duration.seconds(3));
        noti.darkStyle();
        noti.showInformation();
    }

    public static void llenarTabla(TableView tablaCliente) {
        TableColumn<Reserva, String> nombreCol = new TableColumn<>("ID");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Reserva, String> nombreCol1 = new TableColumn<>("Fecha De Entrada");
        nombreCol1.setCellValueFactory(cellData -> {
            Reserva re = cellData.getValue();
            Date fecha = re.getDia_entrada();
            String fechastr = new SimpleDateFormat("yyyy/MM/dd").format(fecha);
            return new SimpleStringProperty(fechastr);
        });
        TableColumn<Reserva, String> nombreCol2 = new TableColumn<>("Fecha De Salida");
        nombreCol2.setCellValueFactory(cellData -> {
            Reserva re = cellData.getValue();
            Date fecha = re.getDia_salida();
            String fechastr = new SimpleDateFormat("yyyy/MM/dd").format(fecha);
            return new SimpleStringProperty(fechastr);
        });
        TableColumn<Reserva, Double> nombreCol3 = new TableColumn<>("Valor");
        nombreCol3.setCellValueFactory(new PropertyValueFactory<>("valor"));
        nombreCol3.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableColumn<Reserva, String> nombreCol4 = new TableColumn<>("Forma De pago");
        nombreCol4.setCellValueFactory(new PropertyValueFactory<>("forma_pago"));
        TableColumn<Reserva, String> nombreCol5 = new TableColumn<>("Id Huesped");
        nombreCol5.setCellValueFactory(new PropertyValueFactory<>("id_huesped"));
        tablaCliente.getColumns().addAll(nombreCol, nombreCol1, nombreCol2, nombreCol3, nombreCol4, nombreCol5);
    }


}
