package controller.LibrarianController;

import com.jfoenix.controls.JFXButton;
import controller.AdminController.AdminDashBoardController;
import controller.LoginFormController;
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
import java.util.ResourceBundle;


public class LibrarianDashBoardController {
    public ImageView btnHome;
    public AnchorPane root;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnExit;
    public JFXButton btnBack;


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
        initUI("HomeForm.fxml");
    }

    public void ViewReportsOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/ViewReportsForm.fxml");
    }

    public void ReturnBookOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/ReturnBookForm.fxml");
    }

    public void IssueBookOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/IssueBookForm.fxml");
    }

    public void ManageMemberOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/ManageMemberForm.fxml");
    }

    public void ManageBookOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/ManageBookForm.fxml");
    }

    public void MakeRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/MakeRegistrationForm.fxml");
    }

    private void initUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));

    }

    public void ExitOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING,"Are you sure?",ButtonType.YES,ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get()==ButtonType.YES){
           initUI("l");
        }
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/AdminView/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene= new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();

        Stage close=(Stage) btnBack.getScene().getWindow();
        close.close();


    }

    public void initialize(URL location, ResourceBundle resources) {
        boolean b=AdminDashBoardController.setVisible();
        if(b==true){
            btnBack.setVisible(true);
        }else{
            btnBack.setVisible(false);
        }
        boolean b1= LoginFormController.setVisible();
        if(b1==true){
            btnBack.setVisible(true);
        }else{
            btnBack.setVisible(false);
        }
    }
}
