package view.tm;

import javafx.scene.control.Button;

public class LibrarianTM {
    private String LID;
    private String Name;
    private String Address;
    private int Contact;
    private Button Btn;

    public LibrarianTM() {
    }

    public LibrarianTM(String LID, String name, String address, int contact, Button btn) {
        this.setLID(LID);
        setName(name);
        setAddress(address);
        setContact(contact);
        setBtn(btn);
    }

    public String getLID() {
        return LID;
    }

    public void setLID(String LID) {
        this.LID = LID;
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
        return "LibrarianTM{" +
                "LID='" + LID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact=" + Contact +
                ", Btn=" + Btn +
                '}';
    }
}
