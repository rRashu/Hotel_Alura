package com.hotel.alura.hotelalurafx;

import Accesos_Datos.huespedesDAO;
import ConexionBD.conexionBD;
import Modelo.Huesped;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Date;

public class
IngresoHuesped {
    public Button guardarHuesped;
    public DatePicker fecha_nacimiento;
    public TextField txtnombre;
    public TextField txtapellido;
    public TextField txttelefono;
    String[] nacionalidades = {"afgano-afgana", "alemán-", "alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"};
Connection con;
    public ComboBox<String> combo_naciolidad;

    public void initialize(){
        //llenar combo-box
        for (String nacionalidade : nacionalidades) {
            combo_naciolidad.getItems().add(nacionalidade);
        }
    }

    public void Acceder() {
        Huesped hues = new Huesped(resultSet.getInt("id"), resultSet.getDate("fecha_entrada"), resultSet.getDate("fecha_salida"), resultSet.getDate("valor"), resultSet.getString("forma_pago"), resultSet.getString("id_huesped"));
        hues.setNombre(txtnombre.getText());
        hues.setApellido(txtapellido.getText());
        hues.setFecha_nacimiento(Date.valueOf(fecha_nacimiento.getValue()));
        hues.setNacionalidad(combo_naciolidad.getValue());
        hues.setTelefono(txttelefono.getText());
        con =  new conexionBD().recuperaConexion();
        huespedesDAO hdao = new huespedesDAO(con);
        hdao.guardar(hues);
    }
}
