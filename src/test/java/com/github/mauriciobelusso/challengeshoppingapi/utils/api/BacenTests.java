package com.github.mauriciobelusso.challengeshoppingapi.utils.api;

import com.github.mauriciobelusso.challengeshoppingapi.ChallengeShoppingApiApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Collections;

class BacenTests extends ChallengeShoppingApiApplicationTests {

    @Autowired
    private Bacen bacen;

    @Test
    void mustBeReturnHttpStatus200() {
        Assertions.assertEquals(HttpStatus.OK, bacen.getStatus().getStatusCode());
    }

    @Test
    void mustBeReturnEmptyListOfTaxesInPeriod() {
        Assertions.assertEquals(Collections.emptyList(),
                bacen.getSelicTaxesInPeriod(
                LocalDate.of(1500, 10, 29),
                LocalDate.of(1500, 10, 29))
        );
    }

    @Test
    void mustBeReturnListWith3Elements() {
        Assertions.assertEquals(3,
                bacen.getSelicTaxesInPeriod(
                        LocalDate.of(2022, 10, 26),
                        LocalDate.of(2022, 10, 29)
                ).size()
        );
    }

    @Test
    void mustBeReturnTaxForDate26_10_2022() {
        Assertions.assertEquals(0.050788,
                bacen.getSelicTaxesInPeriod(
                        LocalDate.of(2022, 10, 26),
                        LocalDate.of(2022, 10, 26)
                ).get(0).getValor()
        );
    }
}
