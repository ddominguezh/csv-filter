package com.agile.design.csv.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        String[] fields = invoice.split(",", -1);
        final String gross = fields[FIELDS_POSITION.BRUTO.ordinal()];
        final String net = fields[FIELDS_POSITION.NETO.ordinal()];
        final String iva = fields[FIELDS_POSITION.IVA.ordinal()];
        final String igic = fields[FIELDS_POSITION.IGIC.ordinal()];
        final String cif = fields[FIELDS_POSITION.CIF.ordinal()];
        final String nif = fields[FIELDS_POSITION.NIF.ordinal()];
        if(
            isValidTaxFields(iva, igic) &&
            isValidIdentificationFields(cif, nif) &&
            isValidGrossField(gross) &&
            isValidNetField(net, gross, iva, igic)
            ){
            result.add(invoice);
        }
        return result;
    }

    private boolean isValidTaxFields(String iva, String igic){
        return (iva.isEmpty() || igic.isEmpty()) && 
        (isNumber(iva) || isNumber(iva));
    }
    
    private boolean isNumber(String value){
        final Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        return pattern.matcher(value).matches();
    }

    private boolean isValidIdentificationFields(String cif, String nif){
        return (cif.isEmpty() || nif.isEmpty())
            && (isValidFormatCIF(cif) || isValidFormatNIF(nif));
    }

    private boolean isValidFormatCIF(String value){
        final Pattern pattern = Pattern.compile("^([ABCDEFGHJKLMNPQRSUVW])(\\d{7})([0-9A-J])$");
        return pattern.matcher(value).matches();
    }

    private boolean isValidFormatNIF(String value){
        final Pattern pattern = Pattern.compile("(\\d{8})([A-Z])$");
        return pattern.matcher(value).matches();
    }

    public boolean isValidGrossField(String gross){
        return !gross.isEmpty();
    }

    public boolean isValidNetField(String net, String gross, String iva, String igic){
        return !net.isEmpty() && isNumber(net) && isValidCalculationNet(net, gross, iva, igic);
    }

    public boolean isValidCalculationNet(String net, String gross, String iva, String igic){
        double tax = 0;
        if(!iva.isEmpty()){
            tax = Double.valueOf(iva);
        }else if(!igic.isEmpty()){
            tax = Double.valueOf(igic);
        }
        final double grossValue = Double.valueOf(gross);
        return Double.valueOf(net).equals(grossValue - (grossValue * tax / 100.0));
    }
}
