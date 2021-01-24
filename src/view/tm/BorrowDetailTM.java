package view.tm;

import javafx.scene.control.Button;

public class BorrowDetailTM {
    private String BorrowID;
    private String BID;
    private String RegID;
    private String IssuedDate;
    private String ReturnDate;
    private String Cost;
    private String Qty;
    private Button Btn;

    public BorrowDetailTM() {
    }

    public BorrowDetailTM(String borrowID, String BID, String regID, String issuedDate, String returnDate, String cost, String qty, Button btn) {
        setBorrowID(borrowID);
        this.setBID(BID);
        setRegID(regID);
        setIssuedDate(issuedDate);
        setReturnDate(returnDate);
        setCost(cost);
        setQty(qty);
        setBtn(btn);
    }

    public String getBorrowID() {
        return BorrowID;
    }

    public void setBorrowID(String borrowID) {
        BorrowID = borrowID;
    }

    public String getBID() {
        return BID;
    }

    public void setBID(String BID) {
        this.BID = BID;
    }

    public String getRegID() {
        return RegID;
    }

    public void setRegID(String regID) {
        RegID = regID;
    }

    public String getIssuedDate() {
        return IssuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        IssuedDate = issuedDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public Button getBtn() {
        return Btn;
    }

    public void setBtn(Button btn) {
        Btn = btn;
    }
}
