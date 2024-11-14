package dad.gesaula.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GrupoController implements Initializable {
    @FXML
    private Label actitudLabel;

    @FXML
    private Slider actitudSlider;

    @FXML
    private TextField denominacionTextField;

    @FXML
    private Label examenesLabel;

    @FXML
    private Slider examenesSlider;

    @FXML
    private DatePicker finDatePicker;

    @FXML
    private DatePicker inicioDatePicker;

    @FXML
    private Label practicasLabel;

    @FXML
    private Slider practicasSlider;

    @FXML
    private GridPane root;

    public GridPane getRoot() {
        return root;
    }

    public GrupoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bindings

    }
}
