package controller.AdminController;

import bo.BoFactory;
import bo.custom.LibrarianBo;
import com.jfoenix.controls.JFXTextField;
import dto.LibrarianDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import view.tm.LibrarianTM;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageLibrarianFormController implements Initializable {
    public JFXTextField txtName;
    public JFXTextField txtlID;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtSelect;
    public TableView tblLibrarian;
    public TableColumn colLID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOperate;

    LibrarianBo librarianBo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        librarianBo=BoFactory.getInstance().getBo(BoFactory.BOType.Librarian);
        try {
            loadAllLibrarian();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            generateLibrarianId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        colLID.setCellValueFactory(new PropertyValueFactory("LID"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory("Contact"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    public void AddLibrarianOnAction(ActionEvent actionEvent) throws Exception {
        validation();
        if(txtlID.getLength()>0 && txtName.getLength()>0 && txtAddress.getLength()>0 && txtContact.getLength()>0) {
            boolean b = librarianBo.AddLibrarian(new LibrarianDTO(
                    txtlID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContact.getText())
            ));

            if (b) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Added Success..!");
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.getButtonTypes();
                alert.show();
            }
            generateLibrarianId();
            loadAllLibrarian();
            txtName.clear();
            txtAddress.clear();
            txtContact.clear();
        }else{
                new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                ButtonType.OK,ButtonType.NO).show();
        }
    }

    public void UpdateLibrarianOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = librarianBo.UpdateLibrarian(new LibrarianDTO(
                txtlID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtContact.getText())
        ));
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
        loadAllLibrarian();
    }

    public void loadAllLibrarian() throws Exception {
        ArrayList<LibrarianDTO> allLibrarian = librarianBo.getAllLibrarians();
        ObservableList<LibrarianTM> tmList= FXCollections.observableArrayList();

        for (LibrarianDTO librarian:allLibrarian) {
            Button btn = new Button("Delete");
            LibrarianTM librarianTM = new LibrarianTM(
                    librarian.getLID(),
                    librarian.getName(),
                    librarian.getAddress(),
                    librarian.getContact(),
                    btn
            );

            btn.setOnAction((e)->{
                try {
                    ButtonType ok= new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no= new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?",ok,no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no)==ok){
                        if (librarianBo.deleteLibrarian((librarianTM.getLID()))) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllLibrarian();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again",ButtonType.OK).show();
                    }else{

                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
          LibrarianTM tm= new LibrarianTM(
                  librarian.getLID(),
                  librarian.getName(),
                  librarian.getAddress(),
                  librarian.getContact(),
                  btn
          );
          tmList.add(tm);
        }
        tblLibrarian.setItems(tmList);
    }

    public void generateLibrarianId() throws Exception {
        int x=0;
        String LID=librarianBo.getLatestLibrarianID();
        if(LID !=null){
            LID=LID.split("[A-Z]")[1];
            x+=Integer.parseInt(LID)+1;
            LID="L"+x;
            txtlID.setText(LID);
        }else{
            txtlID.setText("L1");
        }
    }

    public void SearchLibrarianBookOnAction(ActionEvent actionEvent) throws Exception {
        ArrayList<LibrarianDTO> LibrarianDTOS = librarianBo.SearchLibrarian(txtSelect.getText());
        for (LibrarianDTO dto:LibrarianDTOS) {
            txtlID.setText(dto.getLID());
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtContact.setText(dto.getContact()+"");
        }
    }

    public void validation(){
        if(Pattern.compile("^(L)[1-9]{1}$").matcher(txtlID.getText()).matches()){ txtName.requestFocus();
            if(Pattern.compile("^[A-z]{1,}$").matcher(txtName.getText()).matches()){ txtAddress.requestFocus();
                if(Pattern.compile("^[A-z]{1,}$").matcher(txtAddress.getText()).matches()){
                    txtContact.requestFocus();
                    if(Pattern.compile("^[0-9]{1,}$").matcher(txtContact.getText()).matches()){

                    }else{txtContact.setFocusColor(Paint.valueOf("red"));txtContact.requestFocus();}
                }else{txtAddress.setFocusColor(Paint.valueOf("red")); txtAddress.requestFocus();}
            } else{txtName.setFocusColor(Paint.valueOf("red")); txtName.requestFocus();}
        }else{txtlID.setFocusColor(Paint.valueOf("red"));txtlID.requestFocus();}
    }
}
