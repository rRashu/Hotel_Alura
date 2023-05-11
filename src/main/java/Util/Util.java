package Util;

import Modelo.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Util {
    static String[] nacionalidades = {"afgano-afgana", "alemán-alemana", "árabe-árabe", "argentino-argentina",
            "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña",
            "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china",
            "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana",
            "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña",
            "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia",
            "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa",
            "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa",
            "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní",
            "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana",
            "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí",
            "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa",
            "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa",
            "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca",
            "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana",
            "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"};

    public static void llenar_combo(ComboBox<String> comboBox) {
        for (String nacionalidade : nacionalidades) {
            comboBox.getItems().add(nacionalidade);
        }

    }

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

    public static void llenarTabla(TableView<Reserva> tablaCliente) {
        if (tablaCliente.getItems().isEmpty()) {
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
            TableColumn<Reserva, String> nombreCol4 = new TableColumn<>("Forma De pago");
            nombreCol4.setCellValueFactory(new PropertyValueFactory<>("forma_pago"));
            TableColumn<Reserva, String> nombreCol5 = new TableColumn<>("Id Huesped");
            nombreCol5.setCellValueFactory(new PropertyValueFactory<>("id_huesped"));
            nombreCol.setStyle("-fx-alignment: CENTER;");
            nombreCol1.setStyle("-fx-alignment: CENTER;");
            nombreCol2.setStyle("-fx-alignment: CENTER;");
            nombreCol3.setStyle("-fx-alignment: CENTER;");
            nombreCol5.setStyle("-fx-alignment: CENTER;");
            tablaCliente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tablaCliente.getColumns().addAll(nombreCol, nombreCol1, nombreCol2, nombreCol3, nombreCol4, nombreCol5);
        }
    }


}
