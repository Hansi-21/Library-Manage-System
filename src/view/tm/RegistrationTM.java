package view.tm;

import javafx.scene.control.Button;

public class RegistrationTM {
    private String RegID;
    private String MID;
    private String RegDate;
    private Double RegFee;
    private Button Btn;

    public RegistrationTM() {
    }

    public RegistrationTM(String regID, String MID, String regDate, Double regFee, Button btn) {
        setRegID(regID);
        this.setMID(MID);
        setRegDate(regDate);
        setRegFee(regFee);
        setBtn(btn);
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

    public Button getBtn() {
        return Btn;
    }

    public void setBtn(Button btn) {
        Btn = btn;
    }

    @Override
    public String toString() {
        return "RegistrationTM{" +
                "RegID='" + RegID + '\'' +
                ", MID='" + MID + '\'' +
                ", RegDate='" + RegDate + '\'' +
                ", RegFee=" + RegFee +
                ", Btn=" + Btn +
                '}';
    }
}
