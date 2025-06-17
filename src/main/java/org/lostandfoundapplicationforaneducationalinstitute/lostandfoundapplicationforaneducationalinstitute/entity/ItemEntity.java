package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.ItemStatus;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @Column(name = "item_id")
    private String itemId;

    private String name;
    private String description;
    private LocalDate createdDate;
    private String location;
    private ItemStatus status;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<RequestEntity> requestEntities;
}
