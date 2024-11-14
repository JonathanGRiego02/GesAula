package dad.gesaula;

import dad.gesaula.ui.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GesAulaApp extends Application {

    private final RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GesAula");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }


}
