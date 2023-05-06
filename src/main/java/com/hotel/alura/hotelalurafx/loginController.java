package com.hotel.alura.hotelalurafx;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;


public class loginController {
    public TextField txtusuario;
    public Label lblusuario;
    public TextField txtpass;
    private final Color colorInicio = Color.rgb(175, 65, 140);
    private final Color colorFin = Color.rgb(255, 255, 255);
    public Line myline;
    public Line lineaguia;
    public Pane login;
    public Label lblpass;
    public Line myline1;
    public Line lineaguia1;
    public ImageView github;
    public ImageView l;
    public Button button;
    public Label mover;
    Duration duracionAnimacion = Duration.millis(300);
    int inicio = -100;
    int fin = 100;
    Stage stage;

    public void initialize() {
        button.setDefaultButton(true);
        Init2(txtpass, myline1, lblpass);
        Init2(txtusuario, myline, lblusuario);
        Application application = new Application() {
            @Override
            public void start(Stage stage) {
            }
        };

        github.setOnMouseClicked(event -> application.getHostServices().showDocument("https://github.com/rRashu"));
        l.setOnMouseClicked(event -> application.getHostServices().showDocument("https://www.linkedin.com/in/robin-rezabala/"));
        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];
        mover.setOnMousePressed(event -> {
            yOffset[0] = event.getSceneY();
            xOffset[0] = event.getSceneX();
            System.out.println(xOffset[0] + " " + yOffset[0]);
        });
        mover.setOnMouseDragged(event -> {

            System.out.println(event.getScreenX() - xOffset[0]);
            System.out.println(event.getScreenX() - yOffset[0]);
            mover.getScene().getWindow().setX(event.getScreenX() - xOffset[0]);
            System.out.println(event.getScreenX() - xOffset[0]);
            mover.getScene().getWindow().setY(event.getScreenY() - yOffset[0]);

        });

    }

    private void Init2(TextField txtpass, Line myline1, Label lblpass) {
        txtpass.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if ("".equals(txtpass.getText())) {
                    myline1.setVisible(true);
                    soloLinea(duracionAnimacion, myline1);
                    mouse(colorInicio, colorFin, 25, lblpass, myline1, inicio, fin);
                }
            } else {
                if (txtpass.getText().equals("")) {
                    mouse(colorFin, colorInicio, -25, lblpass, myline1, fin, inicio);
                    des(duracionAnimacion, myline1);
                }
            }
        });
    }

    public void mouse(Color startColor, Color endColor, int px, Label lbl, Line myline, int inicio, int fin) {
        double startY = lbl.getLayoutY();
        double endY = startY - px;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(lbl.textFillProperty(), startColor)),
                new KeyFrame(duracionAnimacion, new KeyValue(lbl.textFillProperty(), endColor)),
                new KeyFrame(Duration.ZERO, new KeyValue(lbl.layoutYProperty(), startY)),
                new KeyFrame(duracionAnimacion, new KeyValue(lbl.layoutYProperty(), endY)),
                new KeyFrame(Duration.ZERO, new KeyValue(myline.endXProperty(), (double) inicio)),
                new KeyFrame(duracionAnimacion, new KeyValue(myline.endXProperty(), (double) fin))
        );
        timeline.play();
    }

    public void soloLinea(Duration duracionAnimacion, Line myline) {
        FadeTransition fadeTransition = new FadeTransition(duracionAnimacion, myline);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished(e -> myline.setVisible(true));
        fadeTransition.play();
    }

    public void des(Duration duracionAnimacion, Line myline) {
        FadeTransition fadeTransition = new FadeTransition(duracionAnimacion, myline);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.5);
        fadeTransition.setOnFinished(e -> myline.setVisible(false));
        fadeTransition.play();
    }

    public void salir() {
        Platform.exit();
    }

    public void Acceder() throws IOException {

        Notifications noti = Notifications.create();

        if (txtusuario.getText().equals("admin") && txtpass.getText().equals("admin")) {
            Stage stage1 = (Stage) button.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu_Principal.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu Principal");
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
            noti.title("Acceso Correcto!!");
            noti.text("Bienvenido " + txtusuario.getText());
            noti.position(Pos.BOTTOM_RIGHT);
            noti.owner(stage);
            noti.hideAfter(Duration.seconds(3));
            noti.showInformation();


        } else {
            noti.title("Algo Fallo");
            noti.text("Estamos Perdidos");
            noti.position(Pos.BOTTOM_RIGHT);
            noti.owner(stage);
            noti.hideAfter(Duration.seconds(3));
            noti.showError();
        }
    }
}