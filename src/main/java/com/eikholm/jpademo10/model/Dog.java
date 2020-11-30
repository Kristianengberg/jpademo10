package com.eikholm.jpademo10.model;

import javax.persistence.*;

@Entity
@Table(name = "dogs") // (name = "owners") er krævet.
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column // (name = "first_name") er IKKE krævet
    private String firstName;  // SKAL være i camel-case (firstName)

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Column
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
