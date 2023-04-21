package com.agile.design.csv.filter;

import java.util.ArrayList;
import java.util.List;

public class CsvFilter {

    private enum FIELDS_POSITION {
        NUM_FACTURA,
        FECHA,
        BRUTO,
        NETO,
        IVA,
        IGIC,
        CONCEPTO,
        CIF,
        NIF
    }
    public List<String> filter(List<String> lines) {
        List<String> result = new ArrayList<String>();
        result.add(lines.get(0));
        final String invoice = lines.get(1);
        String[] fields = invoice.split(",");
        final String iva = fields[FIELDS_POSITION.IVA.ordinal()];
        final String igic = fields[FIELDS_POSITION.IGIC.ordinal()];
        if(
            (iva.isEmpty() || igic.isEmpty()) && 
            !(iva.isEmpty() && iva.isEmpty())
            ){
            result.add(invoice);
        }
        return result;
    }
    
}
