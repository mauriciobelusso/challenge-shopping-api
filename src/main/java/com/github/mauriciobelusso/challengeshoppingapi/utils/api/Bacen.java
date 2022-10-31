package com.github.mauriciobelusso.challengeshoppingapi.utils.api;

import com.github.mauriciobelusso.challengeshoppingapi.dto.SelicDayDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "bacen", url = URLS.BACEN_SELIC)
public interface Bacen {
    @GetMapping(path = "?formato=json&dataInicial={startDate}&dataFinal={endDate}")
    List<SelicDayDTO> getSelicTaxesInPeriod(
            @PathVariable("startDate")
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            final LocalDate startDate,

            @PathVariable("endDate")
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            final LocalDate endDate
    );

    @GetMapping(path = "?formato=json&dataInicial=01/01/1500&dataFinal=01/01/1500")
    ResponseEntity<String> getStatus();
}
