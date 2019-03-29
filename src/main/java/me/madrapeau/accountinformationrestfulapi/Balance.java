package me.madrapeau.accountinformationrestfulapi;

import javax.persistence.*;

@Entity
// Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=100, allocationSize=100)
public class Balance {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;
    @Column
    private String CreditDebitIndicator;
    @Column
    private String Type;
    @Column
    private Float Amount;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public String getCreditDebitIndicator() {
        return CreditDebitIndicator;
    }

    public void setCreditDebitIndicator(String CreditDebitIndicator) {
        this.CreditDebitIndicator = CreditDebitIndicator;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float Amount) {
        this.Amount = Amount;
    }

}