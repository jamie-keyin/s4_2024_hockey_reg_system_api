package com.keyin.rest.division;

import jakarta.persistence.*;

@Entity
public class Division {
    @Id
    @SequenceGenerator(name = "division_sequence", sequenceName = "division_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "division_sequence")
    private long id;

    private String name;

    private String startBirthYear;
    private String endBirthYear;

    public Division() {
    }

    public Division(long id) {
        this.id = id;
    }

    public Division(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartBirthYear() {
        return startBirthYear;
    }

    public void setStartBirthYear(String startBirthYear) {
        this.startBirthYear = startBirthYear;
    }

    public String getEndBirthYear() {
        return endBirthYear;
    }

    public void setEndBirthYear(String endBirthYear) {
        this.endBirthYear = endBirthYear;
    }
}
