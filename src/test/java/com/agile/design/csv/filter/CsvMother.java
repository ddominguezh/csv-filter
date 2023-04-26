package com.agile.design.csv.filter;

import java.util.Arrays;
import java.util.List;

public class CsvMother {
    
    private static final String headerLine = "Num_factura,Fecha,Bruto,Neto,IVA,IGIC,Concepto,CIF_cliente,NIF_cliente";

    public static List<String> valid_csv(){
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_iva("19")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> empty(){
        return Arrays.asList(
            headerLine
        );
    }
    
    public static List<String> with_both_tax_filled(){
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_iva("19")
                .with_igic("8")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> with_both_tax_empty(){
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> when_ivi_is_not_a_number(){
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_iva("42d3")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> when_igic_is_not_a_number(){
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_igic("42d3")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> with_both_identification_fields_filled() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_iva("19")
                .with_cif("P1234567A")
                .with_nif("74928379C")
                .build()
        );
    }

    public static List<String> with_both_identification_fields_are_empty() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_iva("19")
                .build()
        );
    }

    public static List<String> when_cif_has_bad_fornat() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_iva("19")
                .with_cif("B76365789Rv")
                .build()
        );
    }

    public static List<String> when_nif_has_bad_fornat() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("810")
                .with_iva("19")
                .with_nif("76365789Rv")
                .build()
        );
    }

    private static List<String> addLineToHeader(String line){
        return Arrays.asList(
            headerLine, 
            line
        );
    }

    public static List<String> when_net_is_empty() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_iva("19")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> when_net_is_not_a_number() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("489s")
                .with_iva("19")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> when_the_calculation_of_the_net_field_is_not_valid() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("1000")
                .with_net("811")
                .with_iva("19")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> when_gross_is_empty() {
        return addLineToHeader(
            new LineBuilder()
                .with_net("810")
                .with_iva("19")
                .with_cif("P1234567A")
                .build()
        );
    }

    public static List<String> when_gross_is_not_a_number() {
        return addLineToHeader(
            new LineBuilder()
                .with_gross("382sj")
                .with_net("810")
                .with_iva("19")
                .with_cif("P1234567A")
                .build()
        );
    }
}
