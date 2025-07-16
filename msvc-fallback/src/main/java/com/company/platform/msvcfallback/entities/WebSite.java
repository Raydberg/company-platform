package com.company.platform.msvcfallback.entities;

import lombok.Data;

@Data
public class WebSite {
    private Long id;
    private String name;
    private Category category;
    private String description;

}
