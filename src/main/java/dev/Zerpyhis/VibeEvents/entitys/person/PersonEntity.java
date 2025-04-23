package dev.Zerpyhis.VibeEvents.entitys.person;

import dev.Zerpyhis.VibeEvents.records.DataPerson;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String document;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    public PersonEntity(){

    }

    public PersonEntity(DataPerson data) {
        this.name =data.name();
        this.document = data.document();
        this.email = data.email();
        this.phone = data.phone();
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
