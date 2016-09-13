package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.Tuple;

import com.imanzano.sc.common.parser.html.ListElementParser;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/**
 * Parser country page and return the available Countries with the URLs
 */
public class CountryListParser extends ListElementParser<Tuple<String,String>> {

        protected String getUrl() { return "http://colnect.com/en/stamps/countries";}

        protected Tuple<String,String> build(Element element) {
                final String href = element.attr("href");
                final String country = element.getElementsByClass("flag32").attr("title");
                return Tuple.tuple(country,href);
        }
        protected String getSelectExpression() { return "#pl_300 a";}

        public static void main (String[] args) throws IOException {
                CountryListParser c = new CountryListParser();
                final List<Tuple<String, String>> process = c.process();
                process.toString();
        }
}
