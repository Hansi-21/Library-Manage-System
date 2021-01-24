package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public AnchorPane layer2;
    public JFXTextField txtUser;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane layer1;
    public JFXButton btnLog;
    public Hyperlink hyprText;
    public ImageView imgLock;
    public ImageView imgUser;
    public ImageView imgKey;
    public ImageView imgpic;
    public ImageView imgLabel;
    public AnchorPane mainLayer;
    public ImageView imgSignIn;

    static boolean b;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        logInCode();
    }

    public void initialize(){
        layer2.setVisible(false);
    }

    public void SignInOnAction(MouseEvent mouseEvent) {
        TranslateTransition transition=new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(layer1);
        transition.setToX(-400);
        transition.play();
        layer2.setVisible(true);
        layer2.setTranslateX(400);

        transition.setOnFinished(event -> {

        });
        imgSignIn.setVisible(false);
        txtUser.requestFocus();
    }

     public void EnterOnAction(ActionEvent actionEvent) throws IOException {
        logInCode();
    }

    public void logInCode() throws IOException {
        String username = txtUser.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.length()>0 && password.length()>0){

            if (username.equalsIgnoreCase("librarian") && password.equalsIgnoreCase("1121")){

                b=false;
                setVisible();

                URL resource = this.getClass().getResource("/view/LibrarianView/LibrarianDashBoardForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene= new Scene(load);
                Stage stage= new Stage();
                stage.setTitle("Library Management System");
                stage.setScene(scene);
//                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();

                Stage close=(Stage) btnLogin.getScene().getWindow();
                close.close();

            }else if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1121")) {



                URL resource = this.getClass().getResource("/view/AdminView/AdminDashBoardForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene= new Scene(load);
                Stage stage= new Stage();
                stage.setTitle("Library Management System");
                stage.setScene(scene);
//                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();

                Stage close=(Stage) btnLogin.getScene().getWindow();
                close.close();

            }else{
                new Alert(Alert.AlertType.WARNING,"Incorrect UserName or Password !",
                        ButtonType.OK,ButtonType.NO).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                    ButtonType.OK,ButtonType.NO).show();
        }
    }

    public static boolean setVisible(){
        return b;
    }


}
