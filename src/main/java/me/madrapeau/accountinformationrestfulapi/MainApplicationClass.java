package me.madrapeau.accountinformationrestfulapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MainApplicationClass implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MainApplicationClass.class);

    public static void main(String args[]) {
        SpringApplication.run(MainApplicationClass.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        //Accounts
        jdbcTemplate.execute("DROP TABLE ACCOUNT_INFORMATION.PUBLIC.account IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE ACCOUNT_INFORMATION.PUBLIC.account(" +
                "id INTEGER, account_type VARCHAR(255), account_sub_type VARCHAR(255), currency VARCHAR(255), description VARCHAR(255), nickname VARCHAR(255))");
        //Balances
        jdbcTemplate.execute("DROP TABLE ACCOUNT_INFORMATION.PUBLIC.balance IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE ACCOUNT_INFORMATION.PUBLIC.balance(" +
                "id INTEGER, account_id INTEGER, credit_debit_indicator VARCHAR(255), type VARCHAR(255), amount decimal(12,2))");
        //Transactions
        jdbcTemplate.execute("DROP TABLE ACCOUNT_INFORMATION.PUBLIC.transaction IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE ACCOUNT_INFORMATION.PUBLIC.transaction(" +
                "id INTEGER, account_id INTEGER, credit_debit_indicator VARCHAR(255), transaction_reference VARCHAR(255), status VARCHAR(255), amount decimal(12,2), value_date_time DATE)");


        // Create list of account record
        DateFormat formatter;
        Date value_date;
        formatter = new SimpleDateFormat("dd-MMM-yy");
        List<Object[]> accountsList = new ArrayList<Object[]>();
        List<Object[]> balancesList = new ArrayList<Object[]>();
        List<Object[]> transactionsList = new ArrayList<Object[]>();

        //Accounts
        Object[] a1 = {12205, "Business", "CurrentAccount", "CAD", "Mon compte", "Mon compte"};
        accountsList.add(a1);
        Object[] a2 = {15000, "Business", "CurrentAccount", "CAD", "Mon compte", "Mon compte"};
        accountsList.add(a2);
        Object[] a3 = {24000, "Business", "CurrentAccount", "CAD", "Mon compte", "Mon compte"};
        accountsList.add(a3);
        Object[] a4 = {25000, "Business", "CurrentAccount", "USD", "Mon compte", "Mon compte"};
        accountsList.add(a4);
        Object[] a5 = {34999, "Business", "CurrentAccount", "USD", "Mon compte", "Mon compte"};
        accountsList.add(a5);
        Object[] a6 = {50000, "Business", "CurrentAccount", "CAD", "Mon compte", "Mon compte"};
        accountsList.add(a6);
        Object[] a7 = {60000, "Business", "CurrentAccount", "USD", "Mon compte", "Mon compte"};
        accountsList.add(a7);
        // Use stream to print out each tuple of the list
        accountsList.forEach(consent -> log.info(String.format("Inserting account id %s", consent[0])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load account data
        jdbcTemplate.batchUpdate("INSERT INTO ACCOUNT_INFORMATION.PUBLIC.account (id, account_type, account_sub_type, currency, description, nickname) VALUES (?,?,?,?,?,?)", accountsList);

        //Balances
        Object[] b1 = {1, 12205,"DEBIT","InterimBooked", 2500};
        balancesList.add(b1);
        Object[] b2 = {2, 15000,"DEBIT","InterimBooked", 1800};
        balancesList.add(b2);
        Object[] b3 = {3, 24000,"DEBIT","InterimBooked", 10000};
        balancesList.add(b3);
        Object[] b4 = {4, 25000,"DEBIT","InterimBooked", 22000000};
        balancesList.add(b4);
        Object[] b5 = {5, 34999,"DEBIT","InterimBooked", 125};
        balancesList.add(b5);
        Object[] b6 = {6, 50000,"DEBIT","InterimBooked", 18000};
        balancesList.add(b6);
        Object[] b7 = {7, 60000,"DEBIT","InterimBooked", 12500};
        balancesList.add(b7);

        // Use stream to print out each tuple of the list
        balancesList.forEach(balance -> log.info(String.format("Inserting balances for account id %s", balance[0])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load balance data
        jdbcTemplate.batchUpdate("INSERT INTO ACCOUNT_INFORMATION.PUBLIC.balance (id, account_id, credit_debit_indicator, type, amount) VALUES (?,?,?,?,?)", balancesList);

        //Transactions
        String str_value_date = "01-February-18";
        value_date = formatter.parse(str_value_date);
        Object[] a = {1,12205, "DEBIT", "6519648974", "Booked", 450, value_date};
        transactionsList.add(a);
        str_value_date = "01-January-19";
        value_date = formatter.parse(str_value_date);
        Object[] b = {2,12205, "DEBIT", "6851965815", "Booked", 25, value_date};
        transactionsList.add(b);
        str_value_date = "03-January-20";
        value_date = formatter.parse(str_value_date);
        Object[] c = {3,15000, "CREDIT", "6516516515", "Booked", 1000, value_date};
        transactionsList.add(c);
        str_value_date = "08-June-19";
        value_date = formatter.parse(str_value_date);
        Object[] d = {4,15000, "CREDIT", "9545196544", "Pending", 75, value_date};
        transactionsList.add(d);
        str_value_date = "01-July-18";
        value_date = formatter.parse(str_value_date);
        Object[] e = {5,24000, "DEBIT", "8768628765", "Booked", 9999, value_date};
        transactionsList.add(e);
        str_value_date = "25-July-19";
        value_date = formatter.parse(str_value_date);
        Object[] f = {6,25000, "DEBIT", "6851965817", "Booked", 25000, value_date};
        transactionsList.add(f);
        str_value_date = "19-January-20";
        value_date = formatter.parse(str_value_date);
        Object[] g = {7,34999, "CREDIT", "0237473529", "Booked", 1001, value_date};
        transactionsList.add(g);
        str_value_date = "11-June-19";
        value_date = formatter.parse(str_value_date);
        Object[] h = {8,34999, "CREDIT", "3947625347", "Pending", 759, value_date};
        transactionsList.add(h);
        str_value_date = "22-February-18";
        value_date = formatter.parse(str_value_date);
        Object[] i = {9,34999, "DEBIT", "1234567890", "Booked", 1450, value_date};
        transactionsList.add(i);
        str_value_date = "01-December-19";
        value_date = formatter.parse(str_value_date);
        Object[] j = {10,34999, "DEBIT", "0987654321", "Booked", 205, value_date};
        transactionsList.add(j);
        str_value_date = "11-November-20";
        value_date = formatter.parse(str_value_date);
        Object[] k = {11,12205, "CREDIT", "1234509876", "Booked", 1777, value_date};
        transactionsList.add(k);
        str_value_date = "4-March-19";
        value_date = formatter.parse(str_value_date);
        Object[] l = {12,15000, "CREDIT", "0853134765", "Pending", 795, value_date};
        transactionsList.add(l);
        str_value_date = "08-April-18";
        value_date = formatter.parse(str_value_date);
        Object[] m = {13,24000, "DEBIT", "0851346789", "Booked", 4250, value_date};
        transactionsList.add(m);
        str_value_date = "09-September-19";
        value_date = formatter.parse(str_value_date);
        Object[] n = {14,24000, "DEBIT", "0192837465", "Booked", 125, value_date};
        transactionsList.add(n);
        str_value_date = "6-April-20";
        value_date = formatter.parse(str_value_date);
        Object[] o = {15,25000, "CREDIT", "0165748392", "Booked", 100000, value_date};
        transactionsList.add(o);
        str_value_date = "09-October-19";
        value_date = formatter.parse(str_value_date);
        Object[] p = {16,25000, "CREDIT", "1111111111", "Pending", 7985, value_date};
        transactionsList.add(p);
        str_value_date = "10-October-18";
        value_date = formatter.parse(str_value_date);
        Object[] q = {17,25000, "DEBIT", "0009876543", "Booked", 22450, value_date};
        transactionsList.add(q);
        str_value_date = "17-March-19";
        value_date = formatter.parse(str_value_date);
        Object[] r = {18,50000, "DEBIT", "7777777777", "Booked", 251, value_date};
        transactionsList.add(r);
        str_value_date = "18-January-20";
        value_date = formatter.parse(str_value_date);
        Object[] s = {19,60000, "CREDIT", "8273012345", "Booked", 98000, value_date};
        transactionsList.add(s);
        str_value_date = "13-June-19";
        value_date = formatter.parse(str_value_date);
        Object[] t = {20,60000, "CREDIT", "3333333333", "Pending", 75000, value_date};
        transactionsList.add(t);
        // Use stream to print out each tuple of the list
        transactionsList.forEach(transaction -> log.info(String.format("Inserting transactions for account id %s", transaction[0])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load transaction data
        jdbcTemplate.batchUpdate("INSERT INTO ACCOUNT_INFORMATION.PUBLIC.transaction (id, account_id, credit_debit_indicator, transaction_reference, status, amount, value_date_time) VALUES (?,?,?,?,?,?,?)", transactionsList);

    }
}