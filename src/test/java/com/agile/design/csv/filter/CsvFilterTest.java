package com.agile.design.csv.filter;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CsvFilterTest {
    
    private final String headerLine = "Num_factura,Fecha,Bruto,Neto,IVA,IGIC,Concepto,CIF_cliente,NIF_cliente";

    @Test
    public void allow_for_correct_lines_only(){
        final String invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,P1234567A,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine, invoiceLine));
    }

    @Test
    public void exclude_lines_with_both_tax_fields_populated_as_they_are_exclusive(){
        final String invoiceLine = "1,02/05/2019,1000,810,19,8,ACER Laptop,B76430134,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_with_both_tax_fields_are_empty(){
        final String invoiceLine = "1,02/05/2019,1000,810,,,ACER Laptop,B76430134,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_when_ivi_is_not_a_number(){
        final String invoiceLine = "1,02/05/2019,1000,810,42d3,,ACER Laptop,B76430134,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_when_igic_is_not_a_number(){
        final String invoiceLine = "1,02/05/2019,1000,810,,42d3,ACER Laptop,B76430134,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_with_both_identification_fields_populated_as_they_are_exclusive(){
        final String invoiceLine = "11,02/05/2019,1000,810,19,,ACER Laptop,B76430134,74928379C";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_with_both_identification_fields_are_empty(){
        final String invoiceLine = "11,02/05/2019,1000,810,19,,ACER Laptop,,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_when_cif_has_bad_fornat(){
        final String invoiceLine = "11,02/05/2019,1000,810,19,,ACER Laptop,B76365789Rv,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_when_nif_has_bad_fornat(){
        final String invoiceLine = "11,02/05/2019,1000,810,19,,ACER Laptop,,76365789Rv";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }

    @Test
    public void exclude_lines_when_net_is_empty(){
        final String invoiceLine = "1,02/05/2019,1000,,19,,ACER Laptop,P1234567A,";
        List<String> filterLines = new CsvFilter().filter(Arrays.asList(headerLine, invoiceLine));
        assertIterableEquals(filterLines, Arrays.asList(headerLine));
    }
}
