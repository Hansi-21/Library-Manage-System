package controller.LibrarianController;

import bo.BoFactory;
import bo.custom.BookBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.CategoryDAOImpl;
import dto.BookDTO;
import entite.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import view.tm.BookTM;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageBookFormController implements Initializable {
    public JFXComboBox<String> cmbCID;
    public JFXTextField txtBID;
    public JFXTextField txtISBN;
    public JFXTextField txtName;
    public JFXTextField txtAuthor;
    public JFXTextField txtYear;
    public JFXTextField txtLanguage;
    public JFXTextField txtSelect;
    public TableView tblBook;
    public TableColumn colBID;
    public TableColumn colCID;
    public TableColumn colISBN;
    public TableColumn colName;
    public TableColumn colAuthor;
    public TableColumn colYear;
    public TableColumn colLanguage;
    public TableColumn colOperate;
    public TableColumn colQty;
    public JFXTextField txtQty;
    public JFXButton btnCategory;
    public AnchorPane root;

    BookBo bookBo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookBo= BoFactory.getInstance().getBo(BoFactory.BOType.Book);

        getCategoryId();

        try {
            generateBookID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            loadAllBooks();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        colBID.setCellValueFactory(new PropertyValueFactory("BID"));
        colCID.setCellValueFactory(new PropertyValueFactory("CID"));
        colISBN.setCellValueFactory(new PropertyValueFactory("ISBN"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory("Author"));
        colYear.setCellValueFactory(new PropertyValueFactory("Year"));
        colLanguage.setCellValueFactory(new PropertyValueFactory("Language"));
        colQty.setCellValueFactory(new PropertyValueFactory("Qty"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        Validation();
    }

    public void SearchBookOnAction(ActionEvent actionEvent) throws Exception {
        ArrayList<BookDTO> arrayList = bookBo.SearchBook(txtSelect.getText());
        for (BookDTO dto:arrayList) {
            txtBID.setText(dto.getBID());
            cmbCID.setId(dto.getCID());
            txtISBN.setText(dto.getISBN());
            txtName.setText(dto.getName());
            txtAuthor.setText(dto.getAuthor());
            txtYear.setText(dto.getYear()+"");
            txtLanguage.setText(dto.getLanguage());
            txtQty.setText(dto.getQty());
        }
        System.out.println(arrayList);
    }

    public void UpdateBookOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated= bookBo.UpdateBook(new BookDTO(
                txtBID.getText(),
                cmbCID.getValue() + "",
                txtISBN.getText(),
                txtName.getText(),
                txtAuthor.getText(),
                Integer.parseInt(txtYear.getText()),
                txtLanguage.getText(),
                txtQty.getText()
        ));
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
        loadAllBooks();
    }

    public void AddBookOnAction(ActionEvent actionEvent) throws Exception {

        Validation();
        if(txtBID.getLength()>0 && cmbCID.getValue().length()>0 && txtISBN.getLength()>0 && txtName.getLength()>0 &&
        txtAuthor.getLength()>0 && txtYear.getLength()>0 && txtLanguage.getLength()>0 && txtQty.getLength()>0) {
            boolean isSaved = bookBo.AddBook(
                    new BookDTO(
                            txtBID.getText(),
                            cmbCID.getValue() + "",
                            txtISBN.getText(),
                            txtName.getText(),
                            txtAuthor.getText(),
                            Integer.parseInt(txtYear.getText()),
                            txtLanguage.getText(),
                            txtQty.getText()
                    ));
            if (isSaved) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Added Success..!");
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.getButtonTypes();
                alert.show();
            }
            generateBookID();
            loadAllBooks();
            txtISBN.clear();
            txtName.clear();
            txtAuthor.clear();
            txtYear.clear();
            txtLanguage.clear();
            txtQty.clear();
            txtSelect.requestFocus();
        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                    ButtonType.OK,ButtonType.NO).show();
        }


    }

    public void generateBookID() throws Exception {
        int x=0;
        String BID=bookBo.getLatestBookID();
        if(BID !=null){
            BID=BID.split("[A-Z]")[1];
            x+=Integer.parseInt(BID)+1;
            BID="B"+x;
            txtBID.setText(BID);
        }else{
            txtBID.setText("B1");
        }
        if(isDeleted()){
            /*x+=Integer.parseInt(BID)-1;
            BID="B"+x;
            txtBID.setText(BID);*/
        }
    }

    public boolean isDeleted(){
        return false;
    }

    public void loadAllBooks() throws Exception {
        ArrayList<BookDTO> allBooks = bookBo.getAllBooks();
        ObservableList<BookTM> tmList=
                FXCollections.observableArrayList();

        for (BookDTO book:allBooks) {
            Button btn= new Button("Delete");


            BookTM bookTM = new BookTM(
                    book.getBID(),
                    book.getCID(),
                    book.getISBN(),
                    book.getName(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getLanguage(),
                    book.getQty(),
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
                        if (bookBo.deleteBook(bookTM.getBID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllBooks();
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
            BookTM tm= new BookTM(book.getBID(),book.getCID(),book.getISBN(),book.getName(),
                    book.getAuthor(),book.getYear(),book.getLanguage(),book.getQty(),btn);
            tmList.add(tm);
        }
        tblBook.setItems(tmList);
    }

    public void getCategoryId(){
        ObservableList<String> observableList= FXCollections.observableArrayList();
        try {
            ArrayList<Category> CategoryID = new CategoryDAOImpl().getCategoryID();
            for (Category category : CategoryID) {
                observableList.add(category.getCID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbCID.setItems(observableList);
    }

    public void Validation(){
        if(Pattern.compile("^(B)[1-9]{1,}$").matcher(txtBID.getText()).matches()){ txtISBN.requestFocus();
            if(Pattern.compile("^[1-9]{1,}$").matcher(txtISBN.getText()).matches()){ txtName.requestFocus();
                if(Pattern.compile("^[^@ & *][^0-9][A-z]{1,}$").matcher(txtName.getText()).matches()){ txtAuthor.requestFocus();
                    if(Pattern.compile("^[^@ & *][^0-9][A-z]{1,}$").matcher(txtAuthor.getText()).matches()) { txtYear.requestFocus();
                        if (Pattern.compile("^[0-9]{1,}$").matcher(txtYear.getText()).matches()) { txtLanguage.requestFocus();
                            if (Pattern.compile("^[A-z]{1,}$").matcher(txtLanguage.getText()).matches()) { txtQty.requestFocus();
                                if (Pattern.compile("^[0-9]{1,}$").matcher(txtQty.getText()).matches()) {

                                } else {txtQty.setFocusColor(Paint.valueOf("red")); txtQty.requestFocus();}
                            } else { txtLanguage.setFocusColor(Paint.valueOf("red"));txtLanguage.requestFocus();}
                        } else {txtYear.setFocusColor(Paint.valueOf("red"));txtYear.requestFocus();}
                    }else{txtAuthor.setFocusColor(Paint.valueOf("red")); txtAuthor.requestFocus();}
                }else{txtName.setFocusColor(Paint.valueOf("red")); txtName.requestFocus();}
            } else{txtISBN.setFocusColor(Paint.valueOf("red")); txtISBN.requestFocus();}
        }else{txtBID.setFocusColor(Paint.valueOf("red"));txtBID.requestFocus();}
    }

    private void initUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));

    }

    public void ShowCAtegoryOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/BookCategoryForm.fxml");
    }

    public void GenerateReportOnAction(ActionEvent actionEvent) {

    }
}
