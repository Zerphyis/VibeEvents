package dev.Zerpyhis.VibeEvents.entitys.events;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.records.DataEvents;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "eventos")
public class EventsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;
    @NotBlank
    private String description;

    @NotNull
    private LocalDate date;
    @NotNull
    private LocalDate dateEnd;

    @NotNull
    private Integer quantiyTicket;

    @NotNull
    private Double priceTicket;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoryEntity category;

    public EventsEntity() {

    }

    public EventsEntity(DataEvents data) {
        this.name = data.name();
        this.description=data.description();
        this.location = data.location();
        this.quantiyTicket= data.quantityTicket();
        this.priceTicket= data.priceTicket();
        this.date = data.date();
        this.dateEnd=data.dateEnd();
        this.category = data.category();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantiyTicket() {
        return quantiyTicket;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public void setQuantiyTicket(Integer quantiyTicket) {
        this.quantiyTicket = quantiyTicket;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
