package com.github.mauriciobelusso.challengeshoppingapi.service;

import com.github.mauriciobelusso.challengeshoppingapi.ChallengeShoppingApiApplicationTests;
import com.github.mauriciobelusso.challengeshoppingapi.dto.InstallmentsDefinitionDTO;
import com.github.mauriciobelusso.challengeshoppingapi.dto.ShoppingDTO;
import com.github.mauriciobelusso.challengeshoppingapi.model.Payment;
import com.github.mauriciobelusso.challengeshoppingapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class InstallmentsServiceTests extends ChallengeShoppingApiApplicationTests {

    @Autowired
    private InstallmentsService installmentsService;

    @Test
    void mustBeReturnInstallmentsListAsInstallmentsSelected() {
        Product product = new Product();
        product.setId(1);
        product.setName("Potato");
        product.setPrice(100);

        Payment payment = new Payment();
        payment.setInstallments(5);
        payment.setEntry(0);

        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setProduct(product);
        shoppingDTO.setPayment(payment);

        List<InstallmentsDefinitionDTO> installmentsDefinitionDTOS = installmentsService.calculateInstallments(shoppingDTO);

        Assertions.assertEquals(5, installmentsDefinitionDTOS.size());
    }

    @Test
    void mustBeReturn20InstallmentPriceWhen5InstallmentsAndPriceIs100() {
        Product product = new Product();
        product.setId(1);
        product.setName("Potato");
        product.setPrice(100);

        Payment payment = new Payment();
        payment.setInstallments(5);
        payment.setEntry(0);

        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setProduct(product);
        shoppingDTO.setPayment(payment);

        List<InstallmentsDefinitionDTO> installmentsDefinitionDTOS = installmentsService.calculateInstallments(shoppingDTO);

        Assertions.assertEquals(20, installmentsDefinitionDTOS.get(0).getPrice());
    }

    @Test
    void mustBeReturn0TaxWhen5Installments() {
        Product product = new Product();
        product.setId(1);
        product.setName("Potato");
        product.setPrice(100);

        Payment payment = new Payment();
        payment.setInstallments(5);
        payment.setEntry(0);

        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setProduct(product);
        shoppingDTO.setPayment(payment);

        List<InstallmentsDefinitionDTO> installmentsDefinitionDTOS = installmentsService.calculateInstallments(shoppingDTO);

        double tax = installmentsDefinitionDTOS.stream().mapToDouble(InstallmentsDefinitionDTO::getTax).sum();

        Assertions.assertEquals(0, tax);
    }

    @Test
    void mustBeIncludeTaxesAbove5Installments() {
        Product product = new Product();
        product.setId(1);
        product.setName("Potato");
        product.setPrice(100);

        Payment payment = new Payment();
        payment.setInstallments(6);
        payment.setEntry(0);

        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setProduct(product);
        shoppingDTO.setPayment(payment);

        List<InstallmentsDefinitionDTO> installmentsDefinitionDTOS = installmentsService.calculateInstallments(shoppingDTO);
        double installmentValue = installmentsDefinitionDTOS.stream().mapToDouble(InstallmentsDefinitionDTO::getPrice).sum();
        Assertions.assertNotEquals(100D, BigDecimal.valueOf(installmentValue).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
