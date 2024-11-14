package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlumnosController implements Initializable {

    // Model
    ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());

    private final SelectedAlumnoController selectedAlumnoController = new SelectedAlumnoController();

    private final ObjectProperty<Alumno> selectedAlumno = new SimpleObjectProperty<>();

    @FXML
    private BorderPane alumnoBorderPane;

    @FXML
    private TableView<Alumno> alumnoTable;

    @FXML
    private TableColumn<Alumno, LocalDate> nacimientoColumn;

    @FXML
    private TableColumn<Alumno, String> ApellidoColumn;

    @FXML
    private TableColumn<Alumno, String> nombreColumn;

    @FXML
    private SplitPane root;

    @FXML
    private VBox vacioVbox;

    public SplitPane getRoot() {
        return root;
    }

    @FXML
    private Button nuevoButton;

    @FXML
    private Button eliminarButton;

    @FXML
    void onEliminarAction(ActionEvent event) {
        if (selectedAlumno.get() != null) {
            EliminarAlumnoAlert();
        } else {
            ErrorBorrarAlert();
        }
    }

    // Alerta de que no se ha seleccionado ningún alumno al borrar
    private void ErrorBorrarAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Selecciona un alumno para eliminar");
        alert.showAndWait();
    }

    // Alerta de confirmación de borrado
    private void EliminarAlumnoAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Alumno");
        alert.setHeaderText("Se va a eliminar al alumno '" + selectedAlumno.get().getNombre() + " " + selectedAlumno.get().getApellidos() +"'");
        alert.setContentText("¿Estás seguro?");

        ButtonType buttonTypeYes = new ButtonType("Aceptar");
        ButtonType buttonTypeNo = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                alumnos.remove(selectedAlumno.get());
            }
        });
    }

    @FXML
    void onNuevoButton(ActionEvent event) {
        Alumno alumno = new Alumno();
        alumno.setNombre("Nuevo Alumno");
        alumno.setApellidos("Nuevo apellido");
        alumno.setFechaNacimiento(LocalDate.now());
        alumnos.add(alumno);
        alumnoTable.getSelectionModel().select(alumno);
    }

    public AlumnosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnosView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // cell value factories
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        ApellidoColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        nacimientoColumn.setCellValueFactory(cellData -> cellData.getValue().fechaNacimientoProperty());

        // Bindings
        alumnoTable.itemsProperty().bind(alumnos);

        selectedAlumno.bind(alumnoTable.getSelectionModel().selectedItemProperty());
        selectedAlumnoController.alumnoProperty().bind(selectedAlumno);
        selectedAlumno.addListener(this::onSelectedAlumno);


        try {
            alumnos.setAll(Grupo.read(new File("2dam.xml")).getAlumnos());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onSelectedAlumno(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {
        if(nv == null) {
            alumnoBorderPane.setCenter(vacioVbox);
        } else {
            alumnoBorderPane.setCenter(selectedAlumnoController.getRoot());
        }
    }
}
