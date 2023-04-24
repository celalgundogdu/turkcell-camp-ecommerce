package com.turkcellcamp.ecommerce.business.dto.requests.create;

import com.turkcellcamp.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String name;
    private Integer quantity;
    private Double unitPrice;
    private String description;
    private Status status;
    private Set<Integer> categories;
}
