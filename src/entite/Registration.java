package entite;

public class Registration implements SuperEntite{
    private String RegID;
    private String MID;
    private String RegDate;
    private Double RegFee;

    public Registration() {
    }

    public Registration(String regID, String MID, String regDate, Double regFee) {
        setRegID(regID);
        this.setMID(MID);
        setRegDate(regDate);
        setRegFee(regFee);
    }


    public String getRegID() {
        return RegID;
    }

    public void setRegID(String regID) {
        RegID = regID;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getRegDate() {
        return RegDate;
    }

    public void setRegDate(String regDate) {
        RegDate = regDate;
    }

    public Double getRegFee() {
        return RegFee;
    }

    public void setRegFee(Double regFee) {
        RegFee = regFee;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "RegID='" + RegID + '\'' +
                ", MID='" + MID + '\'' +
                ", RegDate='" + RegDate + '\'' +
                ", RegFee=" + RegFee +
                '}';
    }
}
