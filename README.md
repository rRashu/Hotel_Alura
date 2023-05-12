<p align="center">
  <img src="Imagenes Readme/msedge_kqbQ4Mp6g4.png" width="950" alt="Challenger Alura">
</p>
<hr style="border-top: 2px solid #a30000;">

* Para ver la Aplicacion en ejecución con datos acceda [YouTube](https://www.youtube.com/watch?v=gLJQRXIrsKc)

<hr style="border-top: 2px solid #a30000;">
Este mini sistema fue creado como parte del proceso de aprendizaje en los cursos de aluraLatan
<hr style="border-top: 2px solid #a30000;">
<p align="center">
  <img src="Imagenes Readme/idea64_yOXNC0woRY.gif" width="1304" alt="Challenger Alura">
</p>
<hr style="border-top: 2px solid #a30000;">
El login Fue realizado en javafx junto a las animaciones para poder ese efecto de seleccionado, para que las letras suban y bajen se usó css.
<hr style="border-top: 2px solid #a30000;">
<hr style="border-top: 4px solid #537188;">


## **Funcionamiento**

Para el correcto uso de esta aplicacion tan solo una vez iniciada seleccionamos que deseamos realizar entre las opciones tenemos 

<ul>  
  <li>Agregar Huesped</li>
  <li>ELiminar Huesped</li>
  <li>Modificar Huesped</li>
  <li>Agregar Reserva</li>
  <li>ELiminar Reserva</li>
  <li>Modificar Reserva</li>
</ul>
<table>
  <tr>
    <td><img src="Imagenes Readme/java_RStsmhdZOZ.png" width="994" alt="Challenger Alura"></td>
    <td><img src="Imagenes Readme/java_pfpDlRXvdY.png" width="994" alt="Challenger Alura"></td>
  </tr>
  <tr>
    <td><img src="Imagenes Readme/java_m79wh5B5u6.png" width="994" alt="Challenger Alura"></td>
    <td><img src="Imagenes Readme/java_YeMLqX98op.png" width="994" alt="Challenger Alura"></td>
  </tr>
</table>
<hr style="border-top: 4px solid #a30000;">

<hr>
<hr>

## Descripción

Un sistema basico de Hospedaje en la cual nos permite crear un huesped y registrar una reserva para tal huesped, el
almacenamiento de datos se realiza por medio de base de datos usando JDBC para poder realizar persistencia de datos y
crear un CRUD y recuperar datos una vez cerrado el programa.

<hr>
<hr>

# Proceso de Notificacion, Animacion y Alerta

## Notificacion

Para el proceso de Notificacion se usó la Libreria Notifications de controlsfx perteneciente a javafx la cual nos
permite crear pequeños mensajes no duraderos para mostrar ciertos eventos

``` python
noti.title("Acceso Correcto!!");
noti.text("Bienvenido " + txtusuario.getText());
noti.position(Pos.BOTTOM_RIGHT);
noti.owner(stage);
noti.hideAfter(Duration.seconds(3));
noti.showInformation();
```

<hr>
<p align="center">
  <img src="Imagenes Readme/notifficacion.gif" width="426" alt="Challenger Alura">
</p>

## Animacion

Para el proceso de Animacion de los label que suben u bajan se realizó por medio de Timeline perteneciente a Animacion en javafx 

``` python
  Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(lbl.textFillProperty(), startColor)),
                new KeyFrame(duracionAnimacion, new KeyValue(lbl.textFillProperty(), endColor)),
                new KeyFrame(Duration.ZERO, new KeyValue(lbl.layoutYProperty(), startY)),
                new KeyFrame(duracionAnimacion, new KeyValue(lbl.layoutYProperty(), endY)),
                new KeyFrame(Duration.ZERO, new KeyValue(myline.endXProperty(), (double) inicio)),
                new KeyFrame(duracionAnimacion, new KeyValue(myline.endXProperty(), (double) fin))
 ```
## Transición

La línea que se marca debajo del text al ser seleccionado es una línea que se alarga para dar el efecto de llenado del borde en la parte inferior del Textfield.

``` python

 FadeTransition fadeTransition = new FadeTransition(duracionAnimacion, myline);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished(e -> myline.setVisible(true));
        fadeTransition.play();
```
<hr>
<p align="center">
  <img src="Imagenes Readme/Transicion.gif" width="426" alt="Challenger Alura">
</p>

## Alerta

Al momento de eliminar ya sea un huesped o una reserva son marca una alerta que nos hace esperar 5 segundos para activar el boton si y asi estar seguro de querer eliminar, el temporizador se realizó con TimeLine.

``` python
Alert alert = new Alert(Alert.AlertType.INFORMATION, "¿Desea continuar?", yesButton, noButton);
alert.setTitle("Eliminar Reserva");
DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
Objects.requireNonNull(getClass().getResource("..\\..\\..\\..\\css\\alerta.css")).toExternalForm());
dialogPane.getStyleClass().add("myDialog");
``` 
<hr>
<p align="center">
  <img src="Imagenes Readme/Alerta.gif" width="426" alt="Challenger Alura">
</p>

## `Heramientas Usadas `

- [x] JavaFx
- [x] IntelliJ IDEA
- [x] IconStyle.
- [x] MySql.

<br>
<hr>
<hr>

## Autor

[<img src="https://avatars.githubusercontent.com/u/94420600?v=4" width=115><br><sub>Robinson Rezabala</sub>](https://github.com/rRashu)

<br>

<code><strong>Si tiene en mente alguna mejora del programa o que considere que falta algo no dude a
escribir.</strong></code>

<br>

<a href="https://www.linkedin.com/in/robin-rezabala-b272b8207/">
  <img src="https://content.linkedin.com/content/dam/me/business/en-us/amp/brand-site/v2/bg/LI-Bug.svg.original.svg" width=50 alt="f">
</a>


<a href="mailto:robinsonstalinr@gmail.com">
  <img src="https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_dark_1x.png" width=140 alt="f">
</a>





 
