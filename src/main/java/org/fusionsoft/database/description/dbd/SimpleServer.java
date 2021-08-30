/*
 * Copyright (C) 2018-2021 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.fusionsoft.database.description.dbd;

/**
 * Unfinished JavaDoc.
 * The type is still in pre-design stage.
 * @since 0.0.1
 * @todo Make right Javadoc after re-design
 * @checkstyle JavadocTagsCheck (4096 lines)
 * @checkstyle JavadocLocationCheck (4096 lines)
 * @checkstyle JavadocMethodCheck (4096 lines)
 * @checkstyle RegexpSinglelineCheck (4096 lines)
 * @checkstyle JavadocVariableCheck (4096 lines)
 * @checkstyle MemberNameCheck (4096 lines)
 * @checkstyle ParameterNameCheck (4096 lines)
 * @checkstyle ParameterNumberCheck (4096 lines)
 * @checkstyle StringLiteralsConcatenationCheck (4096 lines)
 * @checkstyle AbbreviationAsWordInNameCheck (4096 lines)
 * @checkstyle LineLengthCheck (4096 lines)
 * @checkstyle AvoidFieldNameMatchingMethodName (4096 lines)
 */
@SuppressWarnings("PMD")
public class SimpleServer implements Server {

    private final String key;

    private final String dbType;

    private final String url;

    private final String user;

    private final String password;

    private final String description;

    private final String variables;

    public SimpleServer(final String key, final String dbType, final String url, final String user, final String password, final String description, final String variables) {
        this.key = key;
        this.dbType = dbType;
        this.url = url;
        this.user = user;
        this.password = password;
        this.description = description;
        this.variables = variables;
    }

    @Override
    public final String key() {
        return this.key;
    }

    @Override
    public final String dbType() {
        return this.dbType;
    }

    @Override
    public final String url() {
        return this.url;
    }

    @Override
    public final String user() {
        return this.user;
    }

    @Override
    public final String password() {
        return this.password;
    }

    @Override
    public final String description() {
        return this.description;
    }

    @Override
    public final String variables() {
        return this.variables;
    }

}
