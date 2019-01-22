package com.cherentsov.mapback.Model;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@FetchProfiles({
        @FetchProfile(fetchOverrides = { @FetchProfile.FetchOverride(association = "country", entity = City.class, mode = FetchMode.JOIN) }, name = "city-with-country")
})
@Entity
@Table(name="city")
public class City implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="city_seq",sequenceName="o_city_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="city_seq")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="country_fk", nullable = false)
    private Country country;
/*
    @OneToMany(
        mappedBy = "city",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Set<Point> pointSet = new HashSet<Point>();
*/
    public City(){}

    public City(Long id, String name, Country country){
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
/*
    public Set<Point> getPointSet() {
        return pointSet;
    }

    public void setPointSet(Set<Point> pointSet) {
        this.pointSet = pointSet;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country.getName() +
                '}';
    }
}
