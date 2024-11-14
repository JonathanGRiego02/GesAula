package dad.gesaula.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // Controllers
    private final GrupoController grupoController = new GrupoController();
    private final AlumnosController alumnosController = new AlumnosController();

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
    void onGuardarAction(ActionEvent event) {

    }

    @FXML
    void onNuevoAction(ActionEvent event) {

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
    }
}
