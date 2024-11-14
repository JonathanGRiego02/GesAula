package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Sexo;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import dad.gesaula.ui.model.Alumno;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectedAlumnoController implements Initializable {

    // Model
    private final ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();

    public Alumno getAlumno() {
        return alumno.get();
    }

    public ObjectProperty<Alumno> alumnoProperty() {
        return alumno;
    }

    public void setAlumno(dad.gesaula.ui.model.Alumno alumno) {
        this.alumno.set(alumno);
    }

    // View

    @FXML
    private TextField apellidosTextField;

    @FXML
    private DatePicker nacimientoDatePicker;

    @FXML
    private TextField nombreTextField;

    @FXML
    private CheckBox repiteCheckBox;

    @FXML
    private GridPane root;

    @FXML
    private ComboBox<Sexo> sexoCombo;

    public GridPane getRoot() {
        return root;
    }

    public SelectedAlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectedAlumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexoCombo.getItems().setAll(Sexo.values());
        alumno.addListener(this::onAlumnoChanged);
    }

    private void onAlumnoChanged(ObservableValue<? extends Alumno> o, Alumno oldValue, Alumno newValue) {
        if (oldValue != null) {
            nombreTextField.textProperty().unbindBidirectional(oldValue.nombreProperty());
            apellidosTextField.textProperty().unbindBidirectional(oldValue.apellidosProperty());
            nacimientoDatePicker.valueProperty().unbindBidirectional(oldValue.fechaNacimientoProperty());
            repiteCheckBox.selectedProperty().unbindBidirectional(oldValue.repiteProperty());
            sexoCombo.valueProperty().unbindBidirectional(oldValue.sexoProperty());
        }

        if (newValue != null) {
            nombreTextField.textProperty().bindBidirectional(newValue.nombreProperty());
            apellidosTextField.textProperty().bindBidirectional(newValue.apellidosProperty());
            nacimientoDatePicker.valueProperty().bindBidirectional(newValue.fechaNacimientoProperty());
            repiteCheckBox.selectedProperty().bindBidirectional(newValue.repiteProperty());
            sexoCombo.valueProperty().bindBidirectional(newValue.sexoProperty());
        }
    }


}
