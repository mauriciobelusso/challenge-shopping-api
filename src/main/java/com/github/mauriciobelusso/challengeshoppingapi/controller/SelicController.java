package com.github.mauriciobelusso.challengeshoppingapi.controller;

import com.github.mauriciobelusso.challengeshoppingapi.service.InstallmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("selic")
public class SelicController {

    @Autowired
    private InstallmentsService installmentsService;

    @GetMapping("accruedRateOfTheLast30Days")
    public ResponseEntity<Double> accruedRateOfTheLast30Days() {
        return installmentsService.accruedRateOfTheLast30Days();
    }
}
