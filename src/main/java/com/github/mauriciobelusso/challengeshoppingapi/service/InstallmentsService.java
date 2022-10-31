package com.github.mauriciobelusso.challengeshoppingapi.service;

import com.github.mauriciobelusso.challengeshoppingapi.dto.InstallmentsDefinitionDTO;
import com.github.mauriciobelusso.challengeshoppingapi.dto.ShoppingDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InstallmentsService {

    List<InstallmentsDefinitionDTO> calculateInstallments(final ShoppingDTO shoppingDTO);

    ResponseEntity<Double> accruedRateOfTheLast30Days();
}
