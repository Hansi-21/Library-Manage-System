package dto;

public class CategoryDTO {
    private String CID;
    private String Name;


    public CategoryDTO() {
    }

    public CategoryDTO(String CID, String name) {
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

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "CID='" + CID + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
