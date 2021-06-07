package ru.ntiteam.antufievsemen.entity;

import java.util.Objects;
import java.util.Set;
import javax.annotation.PreDestroy;
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
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Lord lord;

    public Planet() {
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

    @PreDestroy
    public void preDestroy() {
        Set<Planet> planetSet = this.lord.getPlanets();
        for (Planet planet : planetSet) {
            if (planet.getId().equals(this.id)) {
                planetSet.remove(planet);
                break;
            }
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return id.equals(planet.id) && Objects.equals(name, planet.name) && Objects.equals(lord, planet.lord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lord);
    }
}
