package com.imanzano.stamp.core.colnect;

import com.imanzano.sc.common.parser.Tuple;
import com.imanzano.sc.common.parser.html.HtmlPaginationParser;
import com.imanzano.stamp.parser.colnect.CountryListParser;
import com.imanzano.stamp.parser.colnect.Stamp;
import com.imanzano.stamp.parser.colnect.StampPageParser;
import com.imanzano.stamp.parser.colnect.YearsStampParser;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 *
 */
public class Catalogue {

    private Catalogue() {}

    void process() throws IOException {

        final CountryListParser countryListParser = new CountryListParser();

        final List<Tuple<String, String>> countries = countryListParser.parse();

        final List<Stamp> stamps = countries.stream()
                        .flatMap(countryTuple -> new YearsStampParser(countryTuple.second()).parse().stream()
                        .flatMap(yearTuple -> processStamps(countryTuple.first(),yearTuple.second()).stream()))
                        .collect(Collectors.toList());
        stamps.size();
    }

    private List<Stamp> processStamps(String countryName, String stampPageUrl) {

        final HtmlPaginationParser<List<Stamp>> pParser = new HtmlPaginationParser<>();

        final StampPageParser stampPageParser = new StampPageParser(countryName,stampPageUrl);
        final List<List<Stamp>> process = pParser.using(stampPageParser)
                .pageCountResolver(e ->
                                e.select(".pager_page:last-child").isEmpty() ?  1 :
                                    parseInt(e.select(".pager_page:last-child").get(0).attr("href").split("page/")[1])
                )
                .pageUrlResolver(integer -> stampPageUrl+"/page/" + integer)
                .process();

        return  process.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {

        final Catalogue catalogue = new Catalogue();
        catalogue.process();

    }
}
