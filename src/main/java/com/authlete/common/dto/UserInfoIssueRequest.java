/*
 * Copyright (C) 2015-2016 Authlete, Inc.
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
package com.authlete.common.dto;


import java.io.Serializable;
import java.util.Map;
import com.authlete.common.util.Utils;


/**
 * Request to Authlete's {@code /auth/userinfo/issue} API.
 *
 * <blockquote>
 * <dl>
 * <dt><b><code>token</code></b> (REQUIRED)</dt>
 * <dd>
 * <p>
 * The access token that has been passed to the service's <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfo"
 * >userinfo endpoint</a> by the client application. In other words,
 * the access token which was contained in the <a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#UserInfoRequest"
 * >userinfo request</a>.
 * </p>
 * </dd>
 *
 * <dt><b><code>claims</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * Claims in JSON format. As for the format, see {@link #setClaims(String)}
 * and <i>"<a href=
 * "http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
 * >OpenID Connect Core 1.0, 5.1. Standard Claims</a>"</i>.
 * </p>
 * </dd>
 *
 * <dt><b><code>sub</code></b> (OPTIONAL)</dt>
 * <dd>
 * <p>
 * The value of the {@code sub} claim. If the value of this request parameter
 * is not empty, it is used as the value of the 'sub' claim. Otherwise, the
 * value of the subject associated with the access token is used.
 * </p>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @author Takahiko Kawasaki
 */
public class UserInfoIssueRequest implements Serializable
{
    private static final long serialVersionUID = 4L;


    /**
     * The access token.
     */
    private String token;


    /**
     * Claims in JSON format.
     */
    private String claims;


    /**
     * The value of the 'sub' claim. If this field is empty, the value of
     * the subject that is associated with the access token is used as the
     * value of the 'sub' claim.
     */
    private String sub;


    /**
     * Get the access token which has come along with the userinfo
     * request from the client application.
     */
    public String getToken()
    {
        return token;
    }


    /**
     * Set the access token which has been issued by Authlete.
     * The access token is the one that has come along with the
     * <a href="http://openid.net/specs/openid-connect-core-1_0.html#UserInfoRequest"
     * >userinfo request</a> from the client application.
     */
    public UserInfoIssueRequest setToken(String token)
    {
        this.token = token;

        return this;
    }


    /**
     * Get the claims of the subject in JSON format.
     *
     * @return
     *         The claims of the subject in JSON format. See the description
     *         of {@link #setClaims(String)} for details about the format.
     *
     * @see #setClaims(String)
     */
    public String getClaims()
    {
        return claims;
    }


    /**
     * Set the claims of the subject in JSON format.
     *
     * <p>
     * The service implementation is required to retrieve claims of the subject
     * (= information about the end-user) from its database and format them in
     * JSON format.
     * </p>
     *
     * <p>
     * For example, if <code>"given_name"</code> claim, <code>"family_name"</code>
     * claim and <code>"email"</code> claim are requested, the service implementation
     * should generate a JSON object like the following:
     * </p>
     *
     * <pre>
     * {
     *   "given_name": "Takahiko",
     *   "family_name": "Kawasaki",
     *   "email": "takahiko.kawasaki@example.com"
     * }
     * </pre>
     *
     * <p>
     * and set its String representation by this method.
     * </p>
     *
     * <p>
     * See <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >OpenID Connect Core 1.0, 5.1. Standard Claims</a> for further details
     * about the format.
     * </p>
     *
     * @param claims
     *         The claims of the subject in JSON format.
     *
     * @return
     *         {@code this} object.
     *
     * @see <a href="http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     *      >OpenID Connect Core 1.0, 5.1. Standard Claims</a>
     */
    public UserInfoIssueRequest setClaims(String claims)
    {
        this.claims = claims;

        return this;
    }


    /**
     * Set the value of {@code "claims"} which is the claims of the subject.
     * The argument is converted into a JSON string and passed to {@link
     * #setClaims(String)} method.
     *
     * @param claims
     *         The claims of the subject. Keys are claim names.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.24
     */
    public UserInfoIssueRequest setClaims(Map<String, Object> claims)
    {
        if (claims == null || claims.size() == 0)
        {
            this.claims = null;
            return this;
        }

        String json = Utils.toJson(claims);

        return setClaims(json);
    }


    /**
     * Get the value of the {@code sub} claim. If this method returns a non-empty value,
     * it is used as the value of the 'sub' claim. Otherwise, the value of the subject
     * associated with the access token is used.
     *
     * @return
     *         The value of the {@code sub} claim.
     *
     * @since 1.35
     */
    public String getSub()
    {
        return sub;
    }


    /**
     * Set the value of the {@code sub} claim. If a non-empty value is given, it is
     * used as the value of the 'sub' claim. Otherwise, the value of the subject
     * associated with the access token is used.
     *
     * @param sub
     *         The value of the {@code sub} claim.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.35
     */
    public UserInfoIssueRequest setSub(String sub)
    {
        this.sub = sub;

        return this;
    }
}
