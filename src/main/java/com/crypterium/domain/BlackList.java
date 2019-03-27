package com.crypterium.domain;

import javax.persistence.*;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@Entity(name = "blacklist")
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "person_id")
    private Person person;

    public BlackList() {
    }

    public BlackList(Person person) {
        this();
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlackList blackList = (BlackList) o;

        return id == blackList.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
