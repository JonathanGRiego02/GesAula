package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // Controllers
    private GrupoController grupoController = new GrupoController();
    private AlumnosController alumnosController = new AlumnosController();

    // Model
    private final Grupo grupo = new Grupo();
    private StringProperty nombreFichero = new SimpleStringProperty();

    // View

    @FXML
    private Tab alumnosTab;

    @FXML
    private TextField ficheronameTextField;

    @FXML
    private Tab grupoTab;

    @FXML
    private Button guardarButton;

    @FXML
    private Button nuevoButton;

    @FXML
    private BorderPane root;

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onGuardarAction(ActionEvent event) throws Exception {
        grupo.save(new File("grupos/" + nombreFichero.get() + ".xml"));
    }

    @FXML
    void onNuevoAction(ActionEvent event) {
        grupoController.clear();
        alumnosController.clear();

    }

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grupoTab.setContent(grupoController.getRoot());
        alumnosTab.setContent(alumnosController.getRoot());

        // Bindings
        grupo.denominacionProperty().bind(grupoController.getDenominacionTextFieldProperty().textProperty());
        grupo.iniCursoProperty().bind(grupoController.getInicioDatePickerProperty().valueProperty());
        grupo.finCursoProperty().bind(grupoController.getFinDatePickerProperty().valueProperty());
        grupo.pesosProperty().bind(grupoController.pesosProperty());
        grupo.alumnosProperty().bind(alumnosController.alumnosProperty());

        ficheronameTextField.textProperty().bindBidirectional(nombreFichero);
    }
}
