package com.agile.design.csv.filter;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CsvFilterTest {
    
    private final String headerLine = "Num_factura,Fecha,Bruto,Neto,IVA,IGIC,Concepto,CIF_cliente,NIF_cliente";

    @Test
    public void allow_for_correct_lines_only(){
        final String invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine, invoiceLine));
    }
}
