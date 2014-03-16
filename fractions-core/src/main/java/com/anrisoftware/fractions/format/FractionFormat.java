/*
 * Copyright 2011-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of fractions-core.
 *
 * fractions-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * fractions-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * fractions-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.fractions.format;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.split;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.stringtemplate.v4.ST;

import com.anrisoftware.fractions.core.ContinuedFraction;
import com.anrisoftware.fractions.core.FractionFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Parses and formats continued fraction.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@SuppressWarnings("serial")
public class FractionFormat extends Format {

    /**
     * @see #create()
     */
    public static FractionFormat createFractionFormat(FractionFactory factory) {
        return create(factory);
    }

    /**
     * Create the continued fraction format.
     * 
     * @param factory
     *            the continued fraction {@link FractionFactory} factory.
     * 
     * @return the {@link FractionFormat}.
     */
    public static FractionFormat create(FractionFactory factory) {
        return FractionFormatModule.getFactory().create(factory);
    }

    private static final Pattern PATTERN = compile("^\\[(-?\\d+);(.*)\\]$");

    private static final String TEMPLATE = "[<first(d)>;<rest(d);separator=\",\">]";

    private static final String DENOMINATORS = "d";

    private final FractionFactory factory;

    @Inject
    private FractionFormatLogger log;

    private NumberFormat numberFormat;

    @Inject
    FractionFormat(@Assisted FractionFactory factory) {
        this.factory = factory;
        this.numberFormat = NumberFormat.getInstance();
    }

    /**
     * Sets the format to parse the fraction denominators.
     * 
     * @param format
     *            the {@link Format}.
     */
    public void setNumberFormat(NumberFormat format) {
        this.numberFormat = format;
    }

    /**
     * Formats the specified continued fraction.
     * 
     * @param obj
     *            the {@link ContinuedFraction}.
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer buff, FieldPosition pos) {
        if (obj instanceof ContinuedFraction) {
            formatFraction(buff, (ContinuedFraction) obj);
        }
        return buff;
    }

    private void formatFraction(StringBuffer buff, ContinuedFraction fraction) {
        int[] denos = fraction.toArray();
        String out = new ST(TEMPLATE).add(DENOMINATORS, denos).render();
        buff.append(out);
    }

    /**
     * Parses the specified string to a continued fraction.
     * <p>
     * <h2>Format</h2>
     * <p>
     * <ul>
     * <li>{@code "[n0;n1,...,ni]"}
     * </ul>
     * 
     * @return the parsed {@link ContinuedFraction}.
     */
    @Override
    public Object parseObject(String source, ParsePosition pos) {
        try {
            return parse(source, pos);
        } catch (ParseException e) {
            pos.setErrorIndex(pos.getIndex() + e.getErrorOffset());
            return null;
        }
    }

    /**
     * @see #parse(String, ParsePosition)
     */
    public ContinuedFraction parse(String source) throws ParseException {
        ParsePosition pos = new ParsePosition(0);
        ContinuedFraction result = parse(source, pos);
        if (pos.getIndex() == 0) {
            throw log.errorParse(source, pos);
        }
        return result;
    }

    /**
     * @see #parseObject(String)
     * 
     * @param pos
     *            the index {@link ParsePosition} position from where to start
     *            parsing.
     * 
     * @throws ParseException
     *             if the string is not in the correct format.
     */
    public ContinuedFraction parse(String source, ParsePosition pos)
            throws ParseException {
        try {
            source = source.substring(pos.getIndex());
            ContinuedFraction f = decodeFraction(source, pos);
            pos.setErrorIndex(-1);
            pos.setIndex(pos.getIndex() + source.length());
            return f;
        } catch (NumberFormatException e) {
            log.errorParseNumber(e, source);
            pos.setIndex(0);
            pos.setErrorIndex(0);
            return null;
        }
    }

    private ContinuedFraction decodeFraction(String source, ParsePosition pos)
            throws ParseException {
        Matcher matcher = PATTERN.matcher(source);
        log.checkMatches(matcher.find(), source, pos);
        TIntList n = new TIntArrayList(10);
        n.add(numberFormat.parse(matcher.group(1)).intValue());
        String nr = matcher.group(2);
        if (!isBlank(nr)) {
            String[] nrs = split(nr, ',');
            for (int i = 0; i < nrs.length; i++) {
                int nn = numberFormat.parse(nrs[i]).intValue();
                n.add(nn);
            }
        }
        return factory.create(1.0, n.toArray());
    }

}
