package com.github.mauriciobelusso.challengeshoppingapi.service.impl;

import com.github.mauriciobelusso.challengeshoppingapi.dto.InstallmentsDefinitionDTO;
import com.github.mauriciobelusso.challengeshoppingapi.dto.SelicDayDTO;
import com.github.mauriciobelusso.challengeshoppingapi.dto.ShoppingDTO;
import com.github.mauriciobelusso.challengeshoppingapi.service.InstallmentsService;
import com.github.mauriciobelusso.challengeshoppingapi.utils.api.Bacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InstallmentsServiceImpl implements InstallmentsService {

    @Autowired
    private Bacen bacen;

    @Override
    public List<InstallmentsDefinitionDTO> calculateInstallments(ShoppingDTO shoppingDTO) {

        List<InstallmentsDefinitionDTO> installmentsDefinitions = new ArrayList<>();

        int installments = shoppingDTO.getPayment().getInstallments();

        BigDecimal debit = BigDecimal.valueOf(
                        shoppingDTO.getProduct().getPrice()
                                - shoppingDTO.getPayment().getEntry())
                .setScale(2, RoundingMode.HALF_UP);

        if(debit.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal installmentValue = debit.divide(BigDecimal.valueOf(installments), RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);

            for(int i=1;i<=5;i++) {
                installmentsDefinitions.add(
                        new InstallmentsDefinitionDTO(i, installmentValue.doubleValue(), 0)
                );
            }

            if (installments > 5) {
                double selic = getSelicUpdated();

                for(int i=6;i<=installments;i++) {
                    long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(i));

                    InstallmentsDefinitionDTO installmentsDefinitionDTO = new InstallmentsDefinitionDTO();
                    installmentsDefinitionDTO.setInstallmentsNum(i);
                    installmentsDefinitionDTO.setTax(selic*daysBetween);
                    installmentsDefinitionDTO.setPrice(
                            installmentValue.add(
                                installmentValue.multiply(BigDecimal.valueOf(installmentsDefinitionDTO.getTax() / 100D)))
                                    .setScale(2, RoundingMode.HALF_UP)
                                    .doubleValue()
                    );
                    installmentsDefinitions.add(installmentsDefinitionDTO);
                }
            }
        }

        return Collections.unmodifiableList(installmentsDefinitions);
    }

    public ResponseEntity<Double> accruedRateOfTheLast30Days() {
        return ResponseEntity.ok(bacen.getSelicTaxesInPeriod(
                LocalDate.now().minusDays(30), LocalDate.now())
                .stream()
                .mapToDouble(SelicDayDTO::getValor)
                .sum());
    }

    private double getSelicUpdated() {
        return bacen.getSelicTaxesInPeriod(
                        LocalDate.now(),
                        LocalDate.now())
                .stream()
                .mapToDouble(SelicDayDTO::getValor)
                .sum();
    }
}
