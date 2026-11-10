package com.devsuperior.dsmeta.dto;

public class SummarySaleDTO {

    private String sellerName;
    private Double total;

    public SummarySaleDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
