package controller.LibrarianController;

import bo.BoFactory;
import bo.custom.RegistrationBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.QuaryDAOImpl;
import db.DBConnection;
import dto.RegistrationDTO;
import entite.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.RegistrationTM;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class MakeRegistrationFormController implements Initializable {
    public JFXComboBox cmbMID;
    public JFXTextField txtRID;
    public JFXTextField RegDate;
    public JFXTextField RegFee;
    public AnchorPane root;
    public TableView tblRegistration;
    public TableColumn colRegID;
    public TableColumn colMID;
    public TableColumn colRegDate;
    public TableColumn colRegFee;
    public JFXTextField txtUMID;
    public JFXTextField txtSelect;
    public TableColumn colOperate;

    RegistrationBO registrationBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registrationBO= BoFactory.getInstance().getBo(BoFactory.BOType.Registration);

        getMemberID();

        try {
            generateRegisterId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getDate();

        try {
            loadAllRegistrations();
        } catch (Exception e) {
            e.printStackTrace();
        }

        colRegID.setCellValueFactory(new PropertyValueFactory("RegID"));
        colMID.setCellValueFactory(new PropertyValueFactory("MID"));
        colRegDate.setCellValueFactory(new PropertyValueFactory("RegDate"));
        colRegFee.setCellValueFactory(new PropertyValueFactory("RegFee"));
        colOperate.setCellValueFactory(new PropertyValueFactory("Btn"));
    }

    private void initUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));
    }

    public void RegistrationOnAction(ActionEvent actionEvent) throws Exception {
        validation();
        if(txtRID.getLength()>0 && RegDate.getLength()>0 && RegFee.getLength()>0){
            boolean isAdded = registrationBO.MakeRegistration(new RegistrationDTO(
                    txtRID.getText(),
                    cmbMID.getValue() + "",
                    RegDate.getText(),
                    Double.parseDouble(RegFee.getText())
            ));
           if(isAdded){
               new Alert(Alert.AlertType.CONFIRMATION,"Registration Success...!", ButtonType.OK).show();
           }
           else{
               new Alert(Alert.AlertType.CONFIRMATION,"Registration Failed...!", ButtonType.OK).show();
           }

            generateRegisterId();
            loadAllRegistrations();
            RegFee.clear();
            txtSelect.requestFocus();

        }else{
        new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                ButtonType.OK,ButtonType.NO).show();
        }

    }

    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/MakePaymentForm.fxml");
    }

    public void getMemberID(){
        ObservableList<String> observableList= FXCollections.observableArrayList();
        try {
            ArrayList<Member> MemberId= new QuaryDAOImpl().getMID();
            for (Member member : MemberId) {
                observableList.add(member.getMID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbMID.setItems(observableList);
    }

    public void generateRegisterId() throws Exception {
        int x=0;
        String RID = registrationBO.getLatestRegistrationID();
        if(RID!=null){
            RID=RID.split("[A-Z]")[1];
            x+=Integer.parseInt(RID)+1;
            RID="R"+x;
            txtRID.setText(RID);
        }else{
            txtRID.setText("R1");
        }
    }

    public void getDate(){
        LocalDate localDate=LocalDate.now();
        RegDate.setText(localDate.toString());
    }

    public void SearchRegistrationOnAction(ActionEvent actionEvent) throws Exception {
        ArrayList<RegistrationDTO> registrationDTOS = registrationBO.SearchRegistration(txtSelect.getText());
        for (RegistrationDTO dto:registrationDTOS) {
            txtRID.setText(dto.getRegID());
            txtUMID.setText(dto.getMID());
            RegDate.setText(dto.getRegDate());
            RegFee.setText(dto.getRegFee()+"");
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = registrationBO.UpdateRegistration(new RegistrationDTO(
                txtRID.getText(),
                txtUMID.getText(),
                RegDate.getText(),
                Double.parseDouble(RegFee.getText())
        ));
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
        loadAllRegistrations();
    }
    
    public void loadAllRegistrations() throws Exception {
        ArrayList<RegistrationDTO> registrationDTOS = registrationBO.GetAllRegistration();
        ObservableList<RegistrationTM> tmList =FXCollections.observableArrayList();
        for (RegistrationDTO dto:registrationDTOS) {
            Button btn = new Button("Delete");

            RegistrationTM registrationTM=new RegistrationTM(
                    dto.getRegID(),
                    dto.getMID(),
                    dto.getRegDate(),
                    dto.getRegFee(),
                    btn
            );

            btn.setOnAction((e)->{
                try {
                    ButtonType ok= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no= new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?",ok,no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no)==ok){
                        if (registrationBO.deleteRegistration(registrationTM.getRegID())) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                            loadAllRegistrations();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING, "Try Again",ButtonType.OK).show();
                    }else{

                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            tmList.add(new RegistrationTM(
                    registrationTM.getRegID(),
                    registrationTM.getMID(),
                    registrationTM.getRegDate(),
                    registrationTM.getRegFee(),
                    registrationTM.getBtn()
                    ));
        }
        tblRegistration.setItems(tmList);
    }

    public void validation(){
        if(Pattern.compile("^[1-9]{1,}.[1-9]{1,}$").matcher(RegFee.getText()).matches()){

        }else{RegFee.setFocusColor(Paint.valueOf("red"));RegFee.requestFocus();}
    }

    public void GenerateReportFromDBOnAction(ActionEvent actionEvent) {

    }
}
