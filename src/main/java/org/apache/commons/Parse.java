/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class Parse {

    /**
     * Creates and returns a parser for the given URL, which the caller MUST close.
     *
     * <p>
     * If you do not read all records from the given {@code url}, you should call {@link #close()} on the parser, unless
     * you close the {@code url}.
     * </p>
     *
     * @param url
     *            a URL. Must not be null.
     * @param charset
     *            the charset for the resource. Must not be null.
     * @param format
     *            the CSVFormat used for CSV parsing. Must not be null.
     * @return a new parser
     * @throws IllegalArgumentException
     *             If the parameters of the format are inconsistent or if either url, charset or format are null.
     * @throws IOException
     *             If an I/O error occurs
     */
    @SuppressWarnings("resource")
    public static CSVParser parse(final URL url, final Charset charset, final CSVFormat format) throws IOException {
        Objects.requireNonNull(url, "url");
        Objects.requireNonNull(charset, "charset");
        Objects.requireNonNull(format, "format");
    
        return new CSVParser(new InputStreamReader(url.openStream(), charset), format);
    }

    /**
     * Creates a CSV parser using the given {@link CSVFormat}.
     *
     * <p>
     * If you do not read all records from the given {@code reader}, you should call {@link #close()} on the parser,
     * unless you close the {@code reader}.
     * </p>
     *
     * @param inputStream
     *            an InputStream containing CSV-formatted input. Must not be null.
     * @param charset
     *            The Charset to decode the given file.
     * @param format
     *            the CSVFormat used for CSV parsing. Must not be null.
     * @return a new CSVParser configured with the given reader and format.
     * @throws IllegalArgumentException
     *             If the parameters of the format are inconsistent or if either reader or format are null.
     * @throws IOException
     *             If there is a problem reading the header or skipping the first record
     * @since 1.5
     */
    @SuppressWarnings("resource")
    public static CSVParser parse(final InputStream inputStream, final Charset charset, final CSVFormat format)
            throws IOException {
        Objects.requireNonNull(inputStream, "inputStream");
        Objects.requireNonNull(format, "format");
        return Parse.parse(new InputStreamReader(inputStream, charset), format);
    }

    /**
     * Creates and returns a parser for the given {@link Path}, which the caller MUST close.
     *
     * @param path
     *            a CSV file. Must not be null.
     * @param charset
     *            The Charset to decode the given file.
     * @param format
     *            the CSVFormat used for CSV parsing. Must not be null.
     * @return a new parser
     * @throws IllegalArgumentException
     *             If the parameters of the format are inconsistent or if either file or format are null.
     * @throws IOException
     *             If an I/O error occurs
     * @since 1.5
     */
    @SuppressWarnings("resource")
    public static CSVParser parse(final Path path, final Charset charset, final CSVFormat format) throws IOException {
        Objects.requireNonNull(path, "path");
        Objects.requireNonNull(format, "format");
        return parse(Files.newInputStream(path), charset, format);
    }

    /**
     * Creates a CSV parser using the given {@link CSVFormat}
     *
     * <p>
     * If you do not read all records from the given {@code reader}, you should call {@link #close()} on the parser,
     * unless you close the {@code reader}.
     * </p>
     *
     * @param reader
     *            a Reader containing CSV-formatted input. Must not be null.
     * @param format
     *            the CSVFormat used for CSV parsing. Must not be null.
     * @return a new CSVParser configured with the given reader and format.
     * @throws IllegalArgumentException
     *             If the parameters of the format are inconsistent or if either reader or format are null.
     * @throws IOException
     *             If there is a problem reading the header or skipping the first record
     * @since 1.5
     */
    public static CSVParser parse(final Reader reader, final CSVFormat format) throws IOException {
        return new CSVParser(reader, format);
    }

    /**
     * Creates a parser for the given {@link String}.
     *
     * @param string
     *            a CSV string. Must not be null.
     * @param format
     *            the CSVFormat used for CSV parsing. Must not be null.
     * @return a new parser
     * @throws IllegalArgumentException
     *             If the parameters of the format are inconsistent or if either string or format are null.
     * @throws IOException
     *             If an I/O error occurs
     */
    public static CSVParser parse(final String string, final CSVFormat format) throws IOException {
        Objects.requireNonNull(string, "string");
        Objects.requireNonNull(format, "format");
    
        return new CSVParser(new StringReader(string), format);
    }

    /**
     * Creates a parser for the given {@link File}.
     *
     * @param file
     *            a CSV file. Must not be null.
     * @param charset
     *            The Charset to decode the given file.
     * @param format
     *            the CSVFormat used for CSV parsing. Must not be null.
     * @return a new parser
     * @throws IllegalArgumentException
     *             If the parameters of the format are inconsistent or if either file or format are null.
     * @throws IOException
     *             If an I/O error occurs
     */
    public static CSVParser parse(final File file, final Charset charset, final CSVFormat format) throws IOException {
        Objects.requireNonNull(file, "file");
        return parse(file.toPath(), charset, format);
    }
    
}
