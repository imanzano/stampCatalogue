package com.imanzano.sc.common.parser.html;


import com.imanzano.sc.common.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * Basic Html Parser worker
 * Receive and url to processor and return a Map of items based on the expression list
 */

public abstract class HtmlParser<T> implements Parser<T> {

    private static final Logger log = LoggerFactory.getLogger(HtmlParser.class);

    private static final int TIMEOUT = 19000;
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36";
    private static final int LIMIT_PER_MINUTE = 15;
    private static final long MIN = 60000;
    private static final AtomicInteger count = new AtomicInteger(0);

    private String url = null;
    private String html = null;
    private Element element = null;
    private ElementProcessor<T> processor = null;
    private boolean beNice = false;

    protected void setProcessor(ElementProcessor<T> p)
    {
        processor = p;
    }

    @Override
    public HtmlParser<T> source(String s)
    {
        element = null;
        url = s;
        return this;
    }

    @Override
    public HtmlParser<T> fromString(String h)
    {
        element = null;
        url = null;
        html = h;
        return this;
    }

    @Override
    public HtmlParser<T> from(Element e)
    {
        url = null;
        html = null;
        element = e;
        return this;
    }

    public String getSource()
    {
        return url;
    }

    @Override
    public T parse() throws IOException {
        if (element != null)
            return processor.process(element);

        final Document doc;
        if (html != null)
            doc = Jsoup.parseBodyFragment(html);
        else
            doc = parseExternalSource();

        return processor.process(doc);
    }

    private Document parseExternalSource() throws IOException {
        final Document doc;
        final boolean isFile = url.startsWith("file://");

        if ( isFile)
        {
            doc = Jsoup.parse(new File(url.substring(7)), "UTF-8");
        }
        else
        {
            if (beNice)
            {
                if (count.get() >= LIMIT_PER_MINUTE)
                {
                    try {
                        log.info("Going sleep for 1min to be nice guy.");
                        sleep(MIN);
                        count.set(0);
                    } catch (final InterruptedException e) {
                        log.warn("Fail waiting",e);
                    }
                }
                count.getAndIncrement();
            }
            doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT)
                    .get();
        }
        return doc;
    }

    /** */
    public void beNicer()
    {
        beNice = true;
    }

    public ElementProcessor<T> getProcessor() {
        return processor;
    }
}
