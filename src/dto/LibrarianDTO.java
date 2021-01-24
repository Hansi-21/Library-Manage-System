package dto;

public class LibrarianDTO {
    private String LID;
    private String Name;
    private String Address;
    private int Contact;

    public LibrarianDTO() {
    }

    public LibrarianDTO(String LID, String name, String address, int contact) {
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
        return "LibrarianDTO{" +
                "LID='" + LID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
