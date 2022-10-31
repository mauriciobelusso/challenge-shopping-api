package com.github.mauriciobelusso.challengeshoppingapi.controller;

import com.github.mauriciobelusso.challengeshoppingapi.dto.InstallmentsDefinitionDTO;
import com.github.mauriciobelusso.challengeshoppingapi.dto.ShoppingDTO;
import com.github.mauriciobelusso.challengeshoppingapi.service.InstallmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("installments")
public class InstallmentsController {

    @Autowired
    private InstallmentsService installmentsService;

    @PostMapping
    public List<InstallmentsDefinitionDTO> calculateInstallments(@RequestBody ShoppingDTO shoppingDTO) {
        return installmentsService.calculateInstallments(shoppingDTO);
    }
}
