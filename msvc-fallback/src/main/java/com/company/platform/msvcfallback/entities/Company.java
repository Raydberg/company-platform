package com.company.platform.msvcfallback.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {
    private Long id;
    private String name;
    private String founder;
    private String logo;
    //    @Column(name = "foundation_date") -> lo hace automatico en las nuevas versiones de hibernate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;
    private List<WebSite> webSites;
}
