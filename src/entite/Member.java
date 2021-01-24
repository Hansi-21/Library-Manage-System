package entite;

public class Member implements SuperEntite{
    private String MID;
    private String Name;
    private String Address;
    private int Contact;

    public Member() {
    }

    public Member(String MID, String name, String address, int contact) {
        this.setMID(MID);
        setName(name);
        setAddress(address);
        setContact(contact);
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

    @Override
    public String toString() {
        return "Member{" +
                "MID='" + MID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
