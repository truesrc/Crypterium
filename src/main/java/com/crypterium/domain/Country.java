package com.crypterium.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@Entity(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //ISO3 Country https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3
    private String name;
    private long timeStart;
    private Long count;

    public Country() {
    }

    public Country(String name) {
        this();
        this.name = name;
    }

    public Country(String name, int timeStart, Long count) {
        this();
        this.name = name;
        this.timeStart = timeStart;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return id == country.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeStart=" + timeStart +
                ", count=" + count +
                '}';
    }
}
