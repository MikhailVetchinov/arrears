package com.mvetchinov.arrears.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class PDCL {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Deal deal;
    private String currency;
    private Double sum;

    public PDCL() {
    }

    public PDCL(LocalDate date, Customer customer, Deal deal, String currency, Double sum) {
        this.date = date;
        this.customer = customer;
        this.deal = deal;
        this.currency = currency;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "PDCL{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                ", deal=" + deal +
                ", currency='" + currency + '\'' +
                ", sum=" + sum +
                '}';
    }
}
