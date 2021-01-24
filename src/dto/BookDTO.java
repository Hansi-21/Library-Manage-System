package dto;

public class BookDTO {
   private String BID;
   private String CID;
   private String ISBN;
   private String Name;
   private String Author;
   private int Year;
   private String Language;
    private String Qty;

    public BookDTO() {
    }

    public BookDTO(String BID, String CID, String ISBN, String name, String author, int year, String language, String qty) {
        this.setBID(BID);
        this.setCID(CID);
        this.setISBN(ISBN);
        setName(name);
        setAuthor(author);
        setYear(year);
        setLanguage(language);
        setQty(qty);
    }

    public String getBID() {
        return BID;
    }

    public void setBID(String BID) {
        this.BID = BID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "BID='" + BID + '\'' +
                ", CID='" + CID + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", Name='" + Name + '\'' +
                ", Author='" + Author + '\'' +
                ", Year=" + Year +
                ", Language='" + Language + '\'' +
                ", Qty='" + Qty + '\'' +
                '}';
    }
}
