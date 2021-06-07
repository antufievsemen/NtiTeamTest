package ru.ntiteam.antufievsemen.entity;

import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
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
    @Column(unique = true, nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lord)) return false;
        Lord lord = (Lord) o;
        return Objects.equals(id, lord.id) && Objects.equals(name, lord.name) && Objects.equals(years, lord.years);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, years, planets);
    }

    @Override
    public String toString() {
        return "Lord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", years=" + years +
                '}';
    }
}
