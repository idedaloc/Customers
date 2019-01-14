package com.rest.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jt on 9/24/17.
 */
@Data
@AllArgsConstructor
public class CustomerListDTO {
    private List<CustomerDTO> customers;
}
