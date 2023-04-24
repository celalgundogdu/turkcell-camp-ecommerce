package com.turkcellcamp.ecommerce.business.dto.responses.create;

import com.turkcellcamp.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductResponse {

    private Integer id;
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private String description;
    private Status status;
}
