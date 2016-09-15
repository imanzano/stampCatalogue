package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.html.ElementConverter;
import com.imanzano.sc.common.parser.html.ListElementParser;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stamp page parser.
 */
public class StampPageParser extends ListElementParser<Stamp> {

    private final String url;
    private final String countryName;

    public StampPageParser(String cn,String url)
    {
        this.url = url;
        countryName = cn;
        source(url);
    }

    protected String getUrl() { return url;}

    protected ElementConverter<Stamp> getConverter() {
        return e -> {
            final String name = e.getElementsByClass("item_header").get(0).text();
            final String img = "http:" + e.select(".item_thumb a img").get(0).attr("src").replaceFirst("/t/","/f/");
            final Stamp stamp = new Stamp();
            stamp.setCountry(countryName);
            stamp.setName(name);
            stamp.setImageUrl(img);

            final String catalogCode = e.select(".i_d dt:contains(Catalog codes:)").first().nextElementSibling().text();
            
            if (catalogCode != null) {
                stamp.setCatalogReferences(Stream.of(catalogCode.split(","))
                                                    .map(CatalogReference::create)
                                                    .collect(Collectors.toList()));
            }

            return stamp;
        };
    }

    protected String getSelectExpression() { return "#plist_items div[itemscope]";}

    public static void main (String[] args) throws IOException {
        StampPageParser c = new StampPageParser("Abu Dhabi","http://colnect.com/en/stamps/list/country/15824-Abu_Dhabi/year/1972");
        final List<Stamp> process = c.parse();
        process.toString();
    }
}
