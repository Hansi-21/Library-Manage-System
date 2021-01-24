package entite;

public class BorrowDetail implements SuperEntite{
    private String BorrowID;
    private String BID;
    private String RegID;
    private String IssuedDate;
    private String ReturnDate;
    private String Cost;
    private String Qty;

    public BorrowDetail() {
    }

    public BorrowDetail(String borrowID, String BID, String regID, String qty, String issuedDate, String returnDate, String cost) {
        setBorrowID(borrowID);
        this.setBID(BID);
        setRegID(regID);
        setQty(qty);
        setIssuedDate(issuedDate);
        setReturnDate(returnDate);
        setCost(cost);
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

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
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

    @Override
    public String toString() {
        return "BorrowDetail{" +
                "BorrowID='" + BorrowID + '\'' +
                ", BID='" + BID + '\'' +
                ", RegID='" + RegID + '\'' +
                ", Qty='" + Qty + '\'' +
                ", IssuedDate='" + IssuedDate + '\'' +
                ", ReturnDate='" + ReturnDate + '\'' +
                ", Cost='" + Cost + '\'' +
                '}';
    }
}
