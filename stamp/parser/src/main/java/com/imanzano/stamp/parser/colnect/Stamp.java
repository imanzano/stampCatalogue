package com.imanzano.stamp.parser.colnect;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Stamp {
    private String name;
    private String series;
    private String imageUrl;
    private String issueDate;
    private List<CatalogReference> catalogReferences = new ArrayList<>();
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public List<CatalogReference> getCatalogReferences() {
        return catalogReferences;
    }

    public void setCatalogReferences(List<CatalogReference> catalogReferences) {
        this.catalogReferences = catalogReferences;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
