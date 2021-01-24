package dto;

public class MemberDTO {
    private String MID;
    private String Name;
    private String Adddress;
    private int Contact;

    public MemberDTO() {
    }

    public MemberDTO(String MID, String name, String adddress, int contact) {
        this.setMID(MID);
        setName(name);
        setAdddress(adddress);
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

    public String getAdddress() {
        return Adddress;
    }

    public void setAdddress(String adddress) {
        Adddress = adddress;
    }

    public int getContact() {
        return Contact;
    }

    public void setContact(int contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "MID='" + MID + '\'' +
                ", Name='" + Name + '\'' +
                ", Adddress='" + Adddress + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
