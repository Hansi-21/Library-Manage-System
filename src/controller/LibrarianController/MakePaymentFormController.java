package controller.LibrarianController;

import bo.BoFactory;
import bo.custom.PaymentBo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.QuaryDAOImpl;
import db.DBConnection;
import dto.PaymentDTO;
import entite.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MakePaymentFormController implements Initializable {
    public ImageView imgePrevious;
    public JFXComboBox cmbRID;
    public JFXTextField PID;
    public JFXTextField PDate;
    public JFXComboBox PType;
    public JFXTextField txtCost;
    public AnchorPane root;

    PaymentBo paymentBo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentBo= BoFactory.getInstance().getBo(BoFactory.BOType.Payment);
        try {
            getRegistrationId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            generatePaymentId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getDate();

        PaymentType();
    }

    public void PaymentOnAction(ActionEvent actionEvent) throws Exception {
        validation();
        if(PID.getLength()>0 && PDate.getLength()>0 && txtCost.getLength()>0){
            boolean isAdded = paymentBo.AddPayment(new PaymentDTO(
                    PID.getText(),
                    cmbRID.getValue() + "",
                    PDate.getText(),
                    PType.getValue() + "",
                    Double.parseDouble(txtCost.getText())
            ));
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Added Success...!",
                        ButtonType.OK,ButtonType.CANCEL).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Added Fail...!",
                        ButtonType.OK,ButtonType.CANCEL).show();
            }
            generatePaymentId();
            txtCost.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                    ButtonType.OK,ButtonType.NO).show();
    }
    }

    public void PreviousMouseClicked(MouseEvent mouseEvent) throws IOException {
       initUI("LibrarianView/MakeRegistrationForm.fxml");
    }

    private void initUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));

    }

    public void getRegistrationId() throws Exception {
        ObservableList<String> observableList= FXCollections.observableArrayList();
        ArrayList<Registration> ridList = new QuaryDAOImpl().getRID();
        for (Registration r:ridList) {
            observableList.add(r.getRegID());
        }
        cmbRID.setItems(observableList);
    }

    public void generatePaymentId() throws Exception {
        int x=0;
        String PayID = paymentBo.getLatestPaymentID();
        if(PayID!=null){
            PayID=PayID.split("[A-Z]")[1];
            x+=Integer.parseInt(PayID)+1;
            PayID="P"+x;
            PID.setText(PayID);
        }else{
            PID.setText("P1");
        }
    }

    public void getDate(){
        LocalDate localDate=LocalDate.now();
        PDate.setText(localDate.toString());
    }

    public void PaymentType(){
        ObservableList<String> observableList=FXCollections.observableArrayList("Register","Return");
        PType.setItems(observableList);
    }

    public void validation(){
        if(Pattern.compile("^[1-9]{1,}.[1-9]{1,}$").matcher(txtCost.getText()).matches()){

        }else{txtCost.setFocusColor(Paint.valueOf("red"));}
    }

    public void GenerateReportFromDBOnAction(ActionEvent actionEvent) {

    }
}
