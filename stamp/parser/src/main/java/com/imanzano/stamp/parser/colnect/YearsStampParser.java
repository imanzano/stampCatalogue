package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.Tuple;
import com.imanzano.sc.common.parser.html.ElementConverter;
import com.imanzano.sc.common.parser.html.ListElementParser;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

import static com.imanzano.stamp.parser.colnect.Constants.BASE_URL;

/**
 * Parse Year page and return a list of url
 */
public class YearsStampParser extends ListElementParser<Tuple<String,String>> {

    private final String url;
    public YearsStampParser(String url)
    {
        this.url = url;
        source(url);
    }

    protected String getUrl() { return url;}

    protected ElementConverter<Tuple<String,String>> getConverter() {
        return e -> {
            final String href = e.attr("href");
            final String year = e.getElementsByTag("strong").text();
            return Tuple.tuple(year, BASE_URL + href);
        };
    }

    protected String getSelectExpression() { return "#pl_150 a";}

    public static void main (String[] args) throws IOException {
        YearsStampParser c = new YearsStampParser("http://colnect.com/en/stamps/years/country/15824-Abu_Dhabi");
        final List<Tuple<String, String>> process = c.parse();
        process.toString();
    }
}
