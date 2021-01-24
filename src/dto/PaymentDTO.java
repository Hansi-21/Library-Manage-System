package dto;

public class PaymentDTO {
    private String PID;
    private String regID;
    private String Date;
    private String Type;
    private Double Cost;

    public PaymentDTO() {
    }

    public PaymentDTO(String PID, String regID, String date, String type, Double cost) {
        this.setPID(PID);
        this.setRegID(regID);
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
        return regID;
    }

    public void setRegID(String regID) {
        this.regID = regID;
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
        return "PaymentDTO{" +
                "PID='" + PID + '\'' +
                ", regID='" + regID + '\'' +
                ", Date='" + Date + '\'' +
                ", Type='" + Type + '\'' +
                ", Cost=" + Cost +
                '}';
    }
}
