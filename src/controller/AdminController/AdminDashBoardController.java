package controller.AdminController;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AdminDashBoardController {
    public ImageView btnHome;
    public AnchorPane root;
    public Label lblTime;
    public Label lblDate;
    public JFXButton btnExit;
    static boolean b;

    public void generateDate(){
        LocalDate localDate=LocalDate.now();
        lblDate.setText(localDate.toString());
    }

    public void setTime(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void GoHomeOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        initUI("HomeForm.fxml");
    }

    public void initialize() throws IOException {
        generateDate();
        setTime();
        initUI("AdminView/ManageLibrarianForm.fxml");
    }

    public void LibrarianReportsOnAction(ActionEvent actionEvent) throws IOException {
        initUI("AdminView/LibrarianReportForm.fxml");
    }

    public void DashBoardOnAction(ActionEvent actionEvent) throws IOException {

        b=true;
        setVisible();

        URL resource = this.getClass().getResource("/view/LibrarianView/LibrarianDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene= new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();

        Stage close=(Stage) btnHome.getScene().getWindow();
        close.close();


    }

    public void ManageLibrarianOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/AdminView/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene= new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();

        Stage close=(Stage) btnHome.getScene().getWindow();
        close.close();
    }

    private void initUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));

    }

    public void ExitOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING,"Are you sure?",ButtonType.YES,ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get()==ButtonType.YES){
            System.exit(0);
        }
    }

    public static boolean setVisible(){
        return b;
    }
}
