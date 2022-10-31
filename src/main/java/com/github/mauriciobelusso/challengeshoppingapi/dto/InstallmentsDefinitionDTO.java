package com.github.mauriciobelusso.challengeshoppingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentsDefinitionDTO {

    private int installmentsNum;
    private double price;
    private double tax;
}
