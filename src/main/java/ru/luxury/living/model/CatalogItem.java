//package ru.luxury.living.model;
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.validation.constraints.NotBlank;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//@Entity
//public class CatalogItem extends BaseEntity {
//
//    @NotBlank
//    private String title;
//
//    private String description;
//
//    @OneToMany
//    private List<Image> images;
//
//    private Integer price;
//
//    @ManyToMany
//    private List<Component> component;
//
//    private boolean active = true;
//}
