package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.Tuple;

import com.imanzano.sc.common.parser.html.ElementConverter;
import com.imanzano.sc.common.parser.html.ListElementParser;

import java.io.IOException;
import java.util.List;

import static com.imanzano.stamp.parser.colnect.Constants.BASE_URL;

/**
 * Parser country page and return the available Countries with the URLs
 */
public class CountryListParser extends ListElementParser<Tuple<String, String>> {

    protected String getUrl() {
        return "http://colnect.com/en/stamps/countries";
    }

    protected ElementConverter<Tuple<String, String>> getConverter() {
        return e -> {
            final String href = e.attr("href");
            final String country = e.getElementsByClass("flag32").attr("title");
            return Tuple.tuple(country, BASE_URL + href);
        };
    }

    protected String getSelectExpression() {
        return "#pl_300 a";
    }

    public static void main(String[] args) throws IOException {
        CountryListParser c = new CountryListParser();
        final List<Tuple<String, String>> process = c.parse();
        process.toString();
    }
}
