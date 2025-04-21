package dev.Zerpyhis.VibeEvents.entitys.category;

import dev.Zerpyhis.VibeEvents.records.DataCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    public CategoryEntity(DataCategory data) {
        this.name = data.name();
    }
    public  CategoryEntity(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
