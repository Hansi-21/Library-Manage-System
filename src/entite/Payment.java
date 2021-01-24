package entite;

public class Payment{
    private String PID;
    private String RegID;
    private String Date;
    private String Type;
    private Double Cost;

    public Payment() {
    }

    public Payment(String PID, String regID, String date, String type, Double cost) {
        this.setPID(PID);
        setRegID(regID);
        setDate(date);
        setType(type);
        setCost(cost);
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getRegID() {
        return RegID;
    }

    public void setRegID(String regID) {
        RegID = regID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        Cost = cost;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "PID='" + PID + '\'' +
                ", RegID='" + RegID + '\'' +
                ", Date='" + Date + '\'' +
                ", Type='" + Type + '\'' +
                ", Cost=" + Cost +
                '}';
    }
}
