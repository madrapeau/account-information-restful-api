package me.madrapeau.accountinformationrestfulapi;

import javax.persistence.*;
import java.util.List;

@Entity
// Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=100, allocationSize=100)
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;
    @Column
    private String AccountType;
    @Column
    private String AccountSubType;
    @Column
    private String Currency;
    @Column
    private String Description;
    @Column
    private String Nickname;
    @OneToMany(mappedBy = "account")
    private List<Balance> balances;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

    public String getAccountSubType() {
        return AccountSubType;
    }

    public void setAccountSubType(String AccountSubType) {
        this.AccountSubType = AccountSubType;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }
}