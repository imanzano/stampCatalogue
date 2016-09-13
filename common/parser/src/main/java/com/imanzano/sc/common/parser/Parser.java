package com.imanzano.sc.common.parser;

import java.io.IOException;
/**
 * Worker interface
 */
public interface Parser<R> {
    /** Process it*/
    R process() throws IOException;

    /** Source */
    Parser<R> source(String s);

    /** Source */
    Parser<R> fromString(String s);
}
