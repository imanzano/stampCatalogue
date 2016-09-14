package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.Tuple;
import com.imanzano.sc.common.parser.html.ListElementParser;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

import static com.imanzano.stamp.parser.colnect.Constants.BASE_URL;

/**
 * Stamp page parser.
 */
public class StampPageParser extends ListElementParser<Stamp> {

    private String url;

    public StampPageParser(String url)
    {
        this.url = url;
        source(url);
    }

    protected String getUrl() { return url;}

    protected Stamp build(Element element) {

        final String name = element.getElementsByClass("item_header").get(0).text();
        final String img = "http:" + element.select(".item_thumb a img").get(0).attr("src").replaceFirst("/t/","/f/");
        final Stamp stamp = new Stamp();
        stamp.setName(name);
        stamp.setImageUrl(img);

        return stamp;
    }
    protected String getSelectExpression() { return "#plist_items div[itemscope]";}

    public static void main (String[] args) throws IOException {
        StampPageParser c = new StampPageParser("http://colnect.com/en/stamps/list/country/15824-Abu_Dhabi/year/1972");
        final List<Stamp> process = c.process();
        process.toString();
    }
}
