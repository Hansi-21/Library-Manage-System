package controller.LibrarianController;

import bo.BoFactory;
import bo.custom.MemberBo;
import com.jfoenix.controls.JFXTextField;
import dto.MemberDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import view.tm.MemberTM;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageMemberFormController implements Initializable {
    public AnchorPane root;
    public JFXTextField txtMID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView tblMember;
    public JFXTextField txtSelect;
    public TableColumn colMID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOperate;

    MemberBo memberBo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memberBo= BoFactory.getInstance().getBo(BoFactory.BOType.Member);

        try {
            loadAllMember();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            generateMemberId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        colMID.setCellValueFactory(new PropertyValueFactory("MID"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory("Contact"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    public void AddMemberOnAction(ActionEvent actionEvent) throws Exception {
        validation();
        if(txtMID.getLength()>0 && txtName.getLength()>0 && txtAddress.getLength()>0 && txtContact.getLength()>0){
            boolean b = memberBo.AddMember(new MemberDTO(
                    txtMID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContact.getText()
                    )));
            if (b) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Added Success..!");
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.getButtonTypes();
                alert.show();
            }
            generateMemberId();
            loadAllMember();
            txtName.clear();
            txtAddress.clear();
            txtContact.clear();
            txtSelect.requestFocus();
        }else{
        new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                ButtonType.OK,ButtonType.NO).show();
        }
    }

    public void UpdateMemberOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = memberBo.updateMember(new MemberDTO(
                txtMID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtContact.getText())
        ));
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
        loadAllMember();
    }

    public void SearchMemberOnAction(ActionEvent actionEvent) throws Exception {
        ArrayList<MemberDTO> memberDTOS = memberBo.SearchMember(txtSelect.getText());
        for (MemberDTO dto:memberDTOS) {
            txtMID.setText(dto.getMID());
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAdddress());
            txtContact.setText(dto.getContact()+"");
        }
    }

    public void loadAllMember() throws Exception {
        ArrayList<MemberDTO> allMember = memberBo.getAllMember();
        ObservableList<MemberTM> tmList= FXCollections.observableArrayList();

        for (MemberDTO member:allMember) {
            Button btn= new Button("Delete");

            MemberTM memberTM = new MemberTM(
                    member.getMID(),
                    member.getName(),
                    member.getAdddress(),
                    member.getContact(),
                    btn
            );

            btn.setOnAction((e)->{
                try {
                    ButtonType ok= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no= new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?",ok,no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no)==ok){
                        if (memberBo.deleteMember(memberTM.getMID())) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                            loadAllMember();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING, "Try Again",ButtonType.OK).show();
                    }else{

                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            MemberTM tm= new MemberTM(member.getMID(),member.getName(),member.getAdddress(),member.getContact(),btn);
            tmList.add(tm);
        }
        tblMember.setItems(tmList);
    }

    public void generateMemberId() throws Exception {
        int x=0;
        String MID=memberBo.getLatestMemberID();
        if(MID !=null){
            MID=MID.split("[A-Z]")[1];
            x+=Integer.parseInt(MID)+1;
            MID="M"+x;
            txtMID.setText(MID);
        }else{
            txtMID.setText("M1");
        }
    }

    public void validation(){
        if(Pattern.compile("^(M)[1-9]{1}$").matcher(txtMID.getText()).matches()){ txtName.requestFocus();
            if(Pattern.compile("^[A-z]{1,}$").matcher(txtName.getText()).matches()){ txtAddress.requestFocus();
                if(Pattern.compile("^[^@ & *][A-z]{1,}$").matcher(txtAddress.getText()).matches()){
                    txtContact.requestFocus();
                    if(Pattern.compile("^[0-9]{1,}$").matcher(txtContact.getText()).matches()){

                    }else{txtContact.setFocusColor(Paint.valueOf("red"));txtContact.requestFocus();}
                }else{txtAddress.setFocusColor(Paint.valueOf("red")); txtAddress.requestFocus();}
            } else{txtName.setFocusColor(Paint.valueOf("red")); txtName.requestFocus();}
        }else{txtMID.setFocusColor(Paint.valueOf("red"));txtMID.requestFocus();}
    }

}
