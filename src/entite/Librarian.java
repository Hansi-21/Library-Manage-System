package entite;

public class Librarian implements SuperEntite{
    private String LID;
    private String Name;
    private String Address;
    private int Contact;

    public Librarian() {
    }

    public Librarian(String LID, String name, String address, int contact) {
        this.setLID(LID);
        setName(name);
        setAddress(address);
        setContact(contact);
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

    @Override
    public String toString() {
        return "Librarian{" +
                "LID='" + LID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
