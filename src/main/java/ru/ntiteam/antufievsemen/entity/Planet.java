package ru.ntiteam.antufievsemen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Lord lord;

    public Planet() {
    }

    public Planet(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Planet(String name, Lord lord) {
        this.name = name;
        this.lord = lord;
    }

    public Planet(Long id, String name, Lord lord) {
        this.id = id;
        this.name = name;
        this.lord = lord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lord getLord() {
        return lord;
    }

    public void setLord(Lord lord) {
        this.lord = lord;
    }
}
