package view.tm;

import javafx.scene.control.Button;

public class CategoryTM {
    private String CID;
    private String Name;
    private Button Btn;

    public CategoryTM(String CID, String name, Button btn) {
        this.setCID(CID);
        setName(name);
        setBtn(btn);
    }

    public CategoryTM() {
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Button getBtn() {
        return Btn;
    }

    public void setBtn(Button btn) {
        Btn = btn;
    }

    @Override
    public String toString() {
        return "CategoryTM{" +
                "CID='" + CID + '\'' +
                ", Name='" + Name + '\'' +
                ", Btn=" + Btn +
                '}';
    }
}
