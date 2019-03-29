package me.madrapeau.accountinformationrestfulapi;

import javax.persistence.*;
import java.util.Date;

@Entity
// Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=100, allocationSize=100)
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;
    @Column
    private String CreditDebitIndicator;
    @Column
    private String TransactionReference;
    @Column
    private String Status;
    @Column
    private Float Amount;
    @Column
    private Date ValueDateTime;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public String getCreditDebitIndicator() {
        return CreditDebitIndicator;
    }

    public void setCreditDebitIndicator(String CreditDebitIndicator) {
        this.CreditDebitIndicator = CreditDebitIndicator;
    }

    public String getTransactionReference() {
        return TransactionReference;
    }

    public void setTransactionReference(String TransactionReference) {
        this.TransactionReference = TransactionReference;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float Amount) {
        this.Amount = Amount;
    }

    public Date getValueDateTime() {
        return ValueDateTime;
    }

    public void setValueDateTime(Date ValueDateTime) {
        this.ValueDateTime = ValueDateTime;
    }
}