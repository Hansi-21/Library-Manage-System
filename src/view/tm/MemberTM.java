package view.tm;

import javafx.scene.control.Button;

public class MemberTM {
    private String MID;
    private String Name;
    private String Address;
    private int Contact;
    private Button Btn;

    public MemberTM() {
    }

    public MemberTM(String MID, String name, String address, int contact, Button btn) {
        this.setMID(MID);
        setName(name);
        setAddress(address);
        setContact(contact);
        setBtn(btn);
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getContact() {
        return Contact;
    }

    public void setContact(int contact) {
        Contact = contact;
    }

    public Button getBtn() {
        return Btn;
    }

    public void setBtn(Button btn) {
        Btn = btn;
    }

    @Override
    public String toString() {
        return "MemberTM{" +
                "MID='" + MID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact=" + Contact +
                ", Btn=" + Btn +
                '}';
    }
}
