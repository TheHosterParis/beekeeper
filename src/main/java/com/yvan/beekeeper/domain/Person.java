package com.yvan.beekeeper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "PERSON", schema = "beekeeper")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {

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

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
