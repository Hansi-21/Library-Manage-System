package controller.LibrarianController;

import bo.BoFactory;
import bo.custom.BorrowDetailBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.BorrowDetailDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReturnBookFormController implements Initializable {
    public JFXTextField txtDate;
    public AnchorPane root;
    public JFXTextField txtName;
    public JFXTextField txtBID;
    public JFXTextField txtRDate;
    public TextField txtTot;
    public JFXTextField txtBookCount;
    public JFXTextField txtRID;
    public JFXTextField txtBorrowID;
    public JFXButton btnDB;

    BorrowDetailBo borrowDetailBo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borrowDetailBo=BoFactory.getInstance().getBo(BoFactory.BOType.Book_detail);

        generateDate();

    }

    public void generateDate(){
        LocalDate localDate=LocalDate.now();
        txtRDate.setText(localDate.toString());
    }

    public void ReturnBookOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = borrowDetailBo.updateBorrow(txtRDate.getText(), txtTot.getText(), txtBookCount.getText(), txtBorrowID.getText());
        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Success..!", ButtonType.OK).show();

        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Fail..!",ButtonType.OK,ButtonType.CANCEL).show();
        }
    }

    public void SearchBookOnAction(ActionEvent actionEvent) throws Exception {
        ArrayList<BorrowDetailDTO> borrowDetailDTOS = borrowDetailBo.SearchBorrow(txtBorrowID.getText());
        for (BorrowDetailDTO b:borrowDetailDTOS) {
            txtBorrowID.setText(b.getBorrowID());
            txtBID.setText(b.getBID());
            txtRID.setText(b.getRegID());
            txtBookCount.setText(b.getQty());
            txtDate.setText(b.getIssuedDate());

        }

    }

    public void CalPaymentOnAction(ActionEvent actionEvent) throws IOException {
        if(txtBorrowID.getText().length()>0 && txtBID.getText().length()>0 && txtRID.getText().length()>0
                && txtBookCount.getText().length()>0 &&txtDate.getText().length()>0 && txtRDate.getText().length()>0) {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final String firstInput = txtDate.getText();
            final String secondInput = txtRDate.getText();
            final LocalDate firstDate = LocalDate.parse(firstInput, formatter);
            final LocalDate secondDate = LocalDate.parse(secondInput, formatter);
            long days = ChronoUnit.DAYS.between(firstDate, secondDate);
            System.out.println("Days between: " + days);
            int cost = 10;
            days *= cost;
            txtTot.setText(days + "");
        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                    ButtonType.OK,ButtonType.NO).show();
        }
    }

    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        initUI("LibrarianView/MakePaymentForm.fxml");
    }

    private void initUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));

    }

    public void GenerateReportFromDBOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/ReturnBook.jasper");
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
