package com.agile.design.csv.filter;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CsvFilterTest {
    
    private final String headerLine = "Num_factura,Fecha,Bruto,Neto,IVA,IGIC,Concepto,CIF_cliente,NIF_cliente";

    @Test
    public void allow_for_correct_lines_only(){
        List<String> lines = CsvMother.valid_csv();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, lines);
    }

    @Test
    public void exclude_lines_with_both_tax_fields_populated_as_they_are_exclusive(){
        List<String> lines = CsvMother.with_both_tax_filled();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_with_both_tax_fields_are_empty(){
        List<String> lines = CsvMother.with_both_tax_empty();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_ivi_is_not_a_number(){
        List<String> lines = CsvMother.when_ivi_is_not_a_number();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_igic_is_not_a_number(){
        List<String> lines = CsvMother.when_igic_is_not_a_number();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_with_both_identification_fields_populated_as_they_are_exclusive(){
        List<String> lines = CsvMother.with_both_identification_fields_filled();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_with_both_identification_fields_are_empty(){
        List<String> lines = CsvMother.with_both_identification_fields_are_empty();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_cif_has_bad_fornat(){
        List<String> lines = CsvMother.when_cif_has_bad_fornat();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_nif_has_bad_fornat(){
        List<String> lines = CsvMother.when_nif_has_bad_fornat();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_net_is_empty(){
        List<String> lines = CsvMother.when_net_is_empty();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_net_is_not_a_number(){
        List<String> lines = CsvMother.when_net_is_not_a_number();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_the_calculation_of_the_net_field_is_not_valid(){
        List<String> lines = CsvMother.when_the_calculation_of_the_net_field_is_not_valid();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_gross_is_empty(){
        List<String> lines = CsvMother.when_gross_is_empty();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }

    @Test
    public void exclude_lines_when_gross_is_not_a_number(){
        List<String> lines = CsvMother.when_gross_is_not_a_number();
        List<String> filterLines = new CsvFilter().filter(lines);
        assertIterableEquals(filterLines, CsvMother.empty());
    }
}