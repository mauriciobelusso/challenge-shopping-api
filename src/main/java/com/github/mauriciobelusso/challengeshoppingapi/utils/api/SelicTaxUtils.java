package com.github.mauriciobelusso.challengeshoppingapi.utils.api;

import com.github.mauriciobelusso.challengeshoppingapi.dto.SelicDayDTO;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@UtilityClass
public class SelicTaxUtils {

    public double getTax(final double value, final List<SelicDayDTO> selicDays) {

        double finalValue = value;
        for(SelicDayDTO sd : selicDays){
            finalValue += (sd.getValor() * finalValue/100D);
        }

        return BigDecimal.valueOf(((finalValue/value)-1)*100).setScale(6, RoundingMode.HALF_UP).doubleValue();
    }
}
