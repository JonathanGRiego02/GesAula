package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Pesos;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    // Model
    ObjectProperty<Pesos> pesos = new SimpleObjectProperty<>(new Pesos());

    public ObjectProperty<Pesos> pesosProperty() {
        return pesos;
    }

    @FXML
    private Label actitudLabel;

    @FXML
    private Label examenesLabel;

    @FXML
    private Label practicasLabel;

    @FXML
    private Slider actitudSlider;

    @FXML
    private Slider examenesSlider;

    @FXML
    private Slider practicasSlider;

    @FXML
    private TextField denominacionTextField;

    @FXML
    private DatePicker finDatePicker;

    @FXML
    private DatePicker inicioDatePicker;

    @FXML
    private GridPane root;

    public GridPane getRoot() {
        return root;
    }

    // Properties
    public TextField getDenominacionTextFieldProperty() {
        return denominacionTextField;
    }

    public DatePicker getFinDatePickerProperty() {
        return finDatePicker;
    }

    public DatePicker getInicioDatePickerProperty() {
        return inicioDatePicker;
    }

    public Slider getActitudSliderProperty() {
        return actitudSlider;
    }

    public Slider getExamenesSliderProperty() {
        return examenesSlider;
    }

    public Slider getPracticasSliderProperty() {
        return practicasSlider;
    }

    public Label getActitudLabelProperty() {
        return actitudLabel;
    }

    public Label getExamenesLabelProperty() {
        return examenesLabel;
    }

    public Label getPracticasLabelProperty() {
        return practicasLabel;
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
        actitudSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                actitudLabel.setText(String.format("%.0f%%", newVal.doubleValue()))
        );
        examenesSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                examenesLabel.setText(String.format("%.0f%%", newVal.doubleValue()))
        );
        practicasSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                practicasLabel.setText(String.format("%.0f%%", newVal.doubleValue()))
        );

        pesos.get().actitudProperty().bind(actitudSlider.valueProperty());
        pesos.get().examenesProperty().bind(examenesSlider.valueProperty());
        pesos.get().practicasProperty().bind(practicasSlider.valueProperty());
    }

    public void clear() {
        denominacionTextField.clear();
        inicioDatePicker.setValue(null);
        finDatePicker.setValue(null);
        actitudSlider.setValue(0);
        examenesSlider.setValue(0);
        practicasSlider.setValue(0);
    }
}
