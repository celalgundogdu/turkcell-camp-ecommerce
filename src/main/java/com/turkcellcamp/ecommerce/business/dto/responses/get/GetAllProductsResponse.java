package com.turkcellcamp.ecommerce.business.dto.responses.get;

import com.turkcellcamp.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsResponse {

    private Integer id;
    private String name;
    private Double unitPrice;
}
