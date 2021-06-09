package ru.ntiteam.antufievsemen.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Lords")
public class Lord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Long years;

    public Lord() {
    }

    public Lord(String name, Long years) {
        this.name = name;
        this.years = years;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lord)) return false;
        Lord lord = (Lord) o;
        return Objects.equals(id, lord.id) && Objects.equals(name, lord.name) && Objects.equals(years, lord.years);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, years);
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
