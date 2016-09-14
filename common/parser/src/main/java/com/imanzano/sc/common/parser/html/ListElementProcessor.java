package com.imanzano.sc.common.parser.html;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Process Elements as list of T
 */
class ListElementProcessor<T> extends ElementProcessor<List<T>> {

    private ElementConverter<T> converter = null;

    public ListElementProcessor(ElementConverter<T> c){
        converter = c;
    }

    protected List<T> process(Element element) throws IOException{
        final List<T> ret = new ArrayList<>();

        element.select(getExpression())
                .forEach(e-> ret.add(converter.convert(e)));

        return ret;
    }
}
