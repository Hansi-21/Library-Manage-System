package controller.LibrarianController;

import bo.BoFactory;
import bo.custom.BookBo;
import bo.custom.BorrowDetailBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.QuaryDAOImpl;
import db.DBConnection;
import dto.BookDTO;
import dto.BorrowDetailDTO;
import entite.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.BorrowDetailTM;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class IssueBookFormController {
    public JFXTextField txtDate;
    public JFXTextField txtCID;
    public JFXTextField txtISBN;
    public JFXTextField txtName;
    public JFXTextField txtAuthor;
    public JFXTextField txtYear;
    public JFXTextField txtLanguage;
    public JFXTextField txtBook;
    public JFXTextField txtQty;
    public JFXTextField txtBID;
    public JFXComboBox cmbRID;
    public Label lblDate;
    public TableView tblIssuedBook;
    public TableColumn colBID;
    public TableColumn colCID;
    public TableColumn colRID;
    public TableColumn colQty;
    public TableColumn colDate;
    public TableColumn colOperate;
    public TableColumn colBorrowID;
    public JFXTextField txtBorrowID;
    public JFXTextField txtRDate;
    public JFXTextField txtCost;
    public TableColumn colRDate;
    public TableColumn colReturnCost;
    public JFXButton btnDB;

    BookBo bookBo;
    BorrowDetailBo borrowDetailBo;

    public void initialize() throws Exception {

        bookBo= BoFactory.getInstance().getBo(BoFactory.BOType.Book);
        borrowDetailBo=BoFactory.getInstance().getBo(BoFactory.BOType.Book_detail);

        generateDate();

//        generateBorrowID();

        getRID();

        loadAllIssueBooks();

        colBorrowID.setCellValueFactory(new PropertyValueFactory("BorrowID"));
        colBID.setCellValueFactory(new PropertyValueFactory("BID"));
        colRID.setCellValueFactory(new PropertyValueFactory("RegID"));
        colDate.setCellValueFactory(new PropertyValueFactory("IssuedDate"));
        colRDate.setCellValueFactory(new PropertyValueFactory("ReturnDate"));
        colReturnCost.setCellValueFactory(new PropertyValueFactory("Cost"));
        colQty.setCellValueFactory(new PropertyValueFactory("Qty"));
        colOperate.setCellValueFactory(new PropertyValueFactory("Btn"));

    }

    public void generateDate(){
        LocalDate localDate=LocalDate.now();
        lblDate.setText(localDate.toString());
    }

    public void generateBorrowID() throws Exception {
        int x=0;
        String BorrowID=borrowDetailBo.getLatestBorrowID();
        if(BorrowID !=null){
            BorrowID=BorrowID.split("[A-Z]")[1];
            x+=Integer.parseInt(BorrowID)+1;
            BorrowID="b"+x;
            txtBorrowID.setText(BorrowID);
        }else{
            txtBorrowID.setText("b1");
        }
    }

    public void BookIssueOnAction(ActionEvent actionEvent) throws Exception {
        if(Integer.parseInt(txtQty.getText())>=Integer.parseInt(txtBook.getText())){
            boolean isAdded = borrowDetailBo.AddBorrow(
                    new BorrowDetailDTO(
                    txtBorrowID.getText(),
                    txtBID.getText(),
                    cmbRID.getValue().toString(),
                    lblDate.getText(),
                    "",
                    "",
                     txtBook.getText()

                    ));
            System.out.println(isAdded);
            loadAllIssueBooks();
        }else{
            new Alert(Alert.AlertType.WARNING,"Book Qty larger than book store..!", ButtonType.OK).show();
        }
    }

    public void SearchBookOnAction(ActionEvent actionEvent) throws Exception {
        ArrayList<BookDTO> arrayList = bookBo.SearchBook(txtBID.getText());
        for (BookDTO dto:arrayList) {
            txtBID.setText(dto.getBID());
            txtCID.setText(dto.getCID());
            txtISBN.setText(dto.getISBN());
            txtName.setText(dto.getName());
            txtAuthor.setText(dto.getAuthor());
            txtYear.setText(dto.getYear()+"");
            txtLanguage.setText(dto.getLanguage());
            txtQty.setText(dto.getQty());
        }
        System.out.println(arrayList);
    }

    public void getRID() throws Exception {
        ObservableList<String> observableList=FXCollections.observableArrayList();
        ArrayList<Registration> RIDList =new QuaryDAOImpl().getRID();
        for (Registration r:RIDList) {
            observableList.add(r.getRegID());
        }
        cmbRID.setItems(observableList);
    }

    public void loadAllIssueBooks() throws Exception {
        ArrayList<BorrowDetailDTO> allBorrows = borrowDetailBo.getAllIssueBook();
        ObservableList<BorrowDetailTM> tmList= FXCollections.observableArrayList();

        for (BorrowDetailDTO dto:allBorrows) {
            Button btn= new Button("Delete");

            BorrowDetailTM borrowDetailTM=new BorrowDetailTM(
                    dto.getBorrowID(),
                    dto.getBID(),
                    dto.getRegID(),
                    dto.getIssuedDate(),
                    dto.getReturnDate(),
                    dto.getCost(),
                    dto.getQty(),
                    btn
                    );

            btn.setOnAction((e)->{
                try {
                    ButtonType ok= new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no= new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?",ok,no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no)==ok){
                        if (borrowDetailBo.deleteBorrow(borrowDetailTM.getBorrowID())) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                            loadAllIssueBooks();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING, "Try Again",ButtonType.OK).show();
                    }else{

                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            BorrowDetailTM tm=new BorrowDetailTM(
                    dto.getBorrowID(),
                    dto.getBID(),
                    dto.getRegID(),
                    dto.getIssuedDate(),
                    dto.getReturnDate(),
                    dto.getCost(),
                    dto.getQty(),
                    btn
            );
            tmList.add(tm);
        }
        tblIssuedBook.setItems(tmList);
    }

    public void GenerateReportFromDBOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/IssueBook.jasper");
//           JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp= JasperFillManager.fillReport(is,null, DBConnection.getInstance().getConnection());
//           JasperPrintManager.printReport(jp,true);
            JasperViewer.viewReport(jp);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
