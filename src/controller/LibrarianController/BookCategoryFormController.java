package controller.LibrarianController;

import bo.BoFactory;
import bo.custom.CategoryBo;
import bo.custom.MemberBo;
import com.jfoenix.controls.JFXTextField;
import dto.CategoryDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import view.tm.CategoryTM;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BookCategoryFormController implements Initializable {
    public ImageView imgePrevious;
    public JFXTextField txtID;
    public JFXTextField txtName;
    public TableView tblCategory;
    public TableColumn colID;
    public TableColumn colName;
    public AnchorPane root;
    public TableColumn colOperate;

    CategoryBo categoryBo;
    MemberBo memberBo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryBo= BoFactory.getInstance().getBo(BoFactory.BOType.Category);
        memberBo= BoFactory.getInstance().getBo(BoFactory.BOType.Member);
        try {
            generateCategoryId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadAllCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }

        colID.setCellValueFactory(new PropertyValueFactory("CID"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colOperate.setCellValueFactory(new PropertyValueFactory("Btn"));
    }

    public void AddCategoryOnAction(ActionEvent actionEvent) throws Exception {
        validation();
        if(txtID.getLength()>0 && txtName.getLength()>0 ){
            boolean b = categoryBo.AddCategory(new CategoryDTO(
                    txtID.getText(),
                    txtName.getText()
                    ));
            if (b) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Added Success..!");
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.getButtonTypes();
                alert.show();
            }
            generateCategoryId();
            loadAllCategories();
            txtName.clear();

        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                    ButtonType.OK,ButtonType.NO).show();
        }
    }

    public void PreviousMouseClicked(MouseEvent mouseEvent) throws IOException {
        initUI("LibrarianView/ManageBookForm.fxml");
    }

    private void initUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));

    }

    public void UpdateBookOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = categoryBo.UpdateCategory(new CategoryDTO(
                txtID.getText(),
                txtName.getText()
        ));
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
        loadAllCategories();
    }

    public void validation(){
        if(Pattern.compile("^(C)[0-9]{1,}$").matcher(txtID.getText()).matches()){ txtName.requestFocus();
            if(Pattern.compile("^[A-z]{1,}$").matcher(txtName.getText()).matches()){

            }else{txtName.setFocusColor(Paint.valueOf("red"));txtName.requestFocus();}
        }else{txtID.setFocusColor(Paint.valueOf("red")); txtID.requestFocus();}
    }

    public void generateCategoryId() throws Exception {
        int x=0;
        String CID=categoryBo.getLatestCategoryID();
        if(CID !=null){
            CID=CID.split("[A-Z]")[1];
            x+=Integer.parseInt(CID)+1;
            CID="C"+x;
            txtID.setText(CID);
        }else{
            txtID.setText("C1");
        }
    }

    public void loadAllCategories() throws Exception {
        ArrayList<CategoryDTO> allCategory = categoryBo.getAllCategories();
        ObservableList<CategoryTM> tmList= FXCollections.observableArrayList();

        for (CategoryDTO category:allCategory) {
            Button btn= new Button("Delete");

            CategoryTM categoryTM = new CategoryTM(
                    category.getCID(),
                    category.getName(),
                    btn
            );

            btn.setOnAction((e)->{
                try {
                    ButtonType ok= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no= new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?",ok,no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no)==ok){
                        if (categoryBo.deleteCategory(categoryTM.getCID())) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                            loadAllCategories();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING, "Try Again",ButtonType.OK).show();
                    }else{

                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            CategoryTM tm = new CategoryTM(
                    category.getCID(),
                    category.getName(),
                    btn
            );
            tmList.add(tm);
        }
        tblCategory.setItems(tmList);
    }
}
