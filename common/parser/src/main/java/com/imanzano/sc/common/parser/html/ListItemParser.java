package com.imanzano.sc.common.parser.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * List parser
 */
class ListItemParser<T> extends ElementParser<List<T>>{

    private ElementParser<T> contentParser = null;

    /** */
    ListItemParser(ElementParser<T> contentParser)
    {
        this.contentParser  = contentParser;
    }

    @Override
    protected List<T> evaluate(Document doc) throws IOException {
        final List<T> ret = new ArrayList<>();
        final Elements elements = doc.select(getExpression());
        if (elements.isEmpty()) return ret;

        for (final Element e : elements) {
            ret.add(contentParser.evaluate(e));
        }

        return ret;
    }
}
