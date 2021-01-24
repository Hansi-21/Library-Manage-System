package entite;

public class Category implements SuperEntite{
    private String CID;
    private String Name;

    public Category() {
    }

    public Category(String CID, String name) {
        this.setCID(CID);
        setName(name);
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
}
