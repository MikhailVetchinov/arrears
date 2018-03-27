package com.mvetchinov.arrears.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Deal {
    @Id
    @GeneratedValue
    private Long id;
    private String description;

    public Deal() {
    }

    public Deal(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
