package co.tz.infowise.e_pesamanager.models;

public class TigoTransactions {
    private Long id;
    private String transactionType;
    private String phoneNumber;
    private  String status;
    private String amount;

    public TigoTransactions(Long id, String transactionType, String phoneNumber, String status) {
        this.id = id;
        this.transactionType = transactionType;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
