package com.turkcellcamp.ecommerce.business.dto.responses.get;

import com.turkcellcamp.ecommerce.entities.Category;
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
public class GetProductResponse {

    private Integer id;
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private String description;
    private Status status;
    private Set<Category> categories;
}
