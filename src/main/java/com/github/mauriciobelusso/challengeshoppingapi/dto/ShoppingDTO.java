package com.github.mauriciobelusso.challengeshoppingapi.dto;

import com.github.mauriciobelusso.challengeshoppingapi.model.Payment;
import com.github.mauriciobelusso.challengeshoppingapi.model.Product;
import lombok.Data;

@Data
public class ShoppingDTO {

    private Product product;
    private Payment payment;
}
