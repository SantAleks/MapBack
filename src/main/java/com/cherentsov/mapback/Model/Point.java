package com.cherentsov.mapback.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="point")
public class Point implements Serializable {
    @Id
    @Column(name="id")
    @SequenceGenerator(name="point_seq",sequenceName="o_point_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="point_seq")
    private Long id;

    @Column(name="address", length = 50, nullable = false)
    private String address;

    @Column(name="fx")
    private Float fX;

    @Column(name="fy")
    private Float fY;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="city_fk", nullable = false)
    private City city;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bank_fk", nullable = false)
    private Bank bank;

    public Point() {
    }

    public Point(Long id, String address, Float fX, Float fY, City city, Bank bank) {
        this.id = id;
        this.address = address;
        this.fX = fX;
        this.fY = fY;
        this.city = city;
        this.bank = bank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getfX() {
        return fX;
    }

    public void setfX(Float fX) {
        this.fX = fX;
    }

    public Float getfY() {
        return fY;
    }

    public void setfY(Float fY) {
        this.fY = fY;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(id, point.id) &&
                Objects.equals(address, point.address) &&
                Objects.equals(fX, point.fX) &&
                Objects.equals(fY, point.fY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, fX, fY);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", bank=" + bank.getName() +
                ", city=" + city.getName() +
                ", address='" + address + '\'' +
                ", fX=" + fX +
                ", fY=" + fY +
                '}';
    }
}
