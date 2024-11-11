package com.yvan.beekeeper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "HOUSE", schema = "beekeeper")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class House {
    @Id
    @Column(name = "ID", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "JOURS")
    private String date;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> persons;

    public House(String name, String owner, String date) {
        this.name = name;
        this.owner = owner;
        this.date = date;
    }

    public House(Long id, String name, String owner, String date) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.date = date;
    }

    public House() {

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
