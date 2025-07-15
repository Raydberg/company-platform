package com.company.platform.msvccompanies.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "web_site")
public class WebSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "category")
    private Category category;
    private String description;

}
