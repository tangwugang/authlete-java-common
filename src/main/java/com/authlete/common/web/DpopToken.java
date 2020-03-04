/*
 * Copyright (C) 2014 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.web;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Utility class for Bearer Token defined in
 * <a href="http://tools.ietf.org/html/rfc6750">RFC 6750</a>.
 *
 * @author Takahiko Kawasaki
 *
 * @see <a href="http://tools.ietf.org/html/rfc6750"
 *      >RFC 6750 (OAuth 2.0 Bearer Token Usage)</a>
 */
public class DpopToken
{
    /**
     * Regular expression to parse {@code Authorization} header.
     */
    private static final Pattern CHALLENGE_PATTERN
            = Pattern.compile("^DPoP *([^ ]+) *$", Pattern.CASE_INSENSITIVE);


    private DpopToken()
    {
    }


    /**
     * Extract the access token embedded in the input string.
     *
     * <p>
     * This method assumes that the input string comes from the
     * Authorization Request Header Field.
     * 
     * @param input
     *            The input string to be parsed.
     *
     * @return
     *         The extracted access token, or <code>null</code> if not found.
     *
     */
    public static String parse(String input)
    {
        if (input == null)
        {
            return null;
        }

        // First, check whether the input matches the pattern
        // "Bearer {access-token}".
        Matcher matcher = CHALLENGE_PATTERN.matcher(input);

        // If the input matches the pattern.
        if (matcher.matches())
        {
            // Return the value as is. Note that it is not Base64-encoded.
            // See https://www.ietf.org/mail-archive/web/oauth/current/msg08489.html
            return matcher.group(1);
        }
        else
        {
            return null;
        }
    }
}