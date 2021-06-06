package ru.ntiteam.antufievsemen.entity;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Lords")
public class Lord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long years;

    @JsonIgnore
    @OneToMany
    private Set<Planet> planets;

    public Lord() {
    }

    public Lord(Long id, String name, Long years, Set<Planet> planets) {
        this.id = id;
        this.name = name;
        this.years = years;
        this.planets = planets;
    }


    public Lord(String name, Long years, Set<Planet> planets) {
        this.name = name;
        this.years = years;
        this.planets = planets;
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

    public Long getYears() {
        return years;
    }

    public void setYears(Long years) {
        this.years = years;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }
}
