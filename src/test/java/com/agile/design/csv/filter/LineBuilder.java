package com.agile.design.csv.filter;

import java.util.ArrayList;

public class LineBuilder {
    
    private String invoice;
    private String date;
    private String gross;
    private String net;
    private String iva;
    private String igic;
    private String concept;
    private String cifClient;
    private String nifClient;
    public LineBuilder(){
        this.invoice = "1";
        this.date = "02/05/2019";
        this.gross = "";
        this.net = "";
        this.iva = "";
        this.igic = "";
        this.concept = "ACER Laptop";
        this.cifClient = "";
        this.nifClient = "";
    }

    public LineBuilder with_gross(String gross){
        this.gross = gross;
        return this;
    }

    public LineBuilder with_net(String net){
        this.net = net;
        return this;
    }

    public LineBuilder with_iva(String iva){
        this.iva = iva;
        return this;
    }

    public LineBuilder with_igic(String igic){
        this.igic = igic;
        return this;
    }

    public LineBuilder with_cif(String cifClient){
        this.cifClient = cifClient;
        return this;
    }
    
    public LineBuilder with_nif(String nifClient){
        this.nifClient = nifClient;
        return this;
    }

    public String build(){
        return String.join(",", new ArrayList<String>(){
            {
                add(invoice);
                add(date);
                add(gross);
                add(net);
                add(iva);
                add(igic);
                add(concept);
                add(cifClient);
                add(nifClient);
            }
        });
    }
}
