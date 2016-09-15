package com.imanzano.stamp.parser.colnect;

/**
 * Catalog Reference
 */
public class CatalogReference {
    private String catalog;
    private String location;

    public static CatalogReference create(String reference)
    {
        final String[] ref = reference.split(":");

        final CatalogReference catalogReference = new CatalogReference();
        catalogReference.catalog = ref[0].trim();
        catalogReference.location = ref[1].trim();
        return catalogReference;
    }

    public String getLocation() {
        return location;
    }

    public String getCatalog() {
        return catalog;
    }
}
