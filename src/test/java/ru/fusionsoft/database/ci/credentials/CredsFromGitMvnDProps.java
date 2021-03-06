/*
 * Copyright (C) 2018-2022 FusionSoft
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
package ru.fusionsoft.database.ci.credentials;

import ru.fusionsoft.lib.ci.credentials.Credentials;
import ru.fusionsoft.lib.ci.credentials.CredentialsFromProperties;

/**
 * The type of {@link Credentials}
 *  that can be constructed of -DgitUser='', -DgitPass='' props specified.
 * @since 0.1
 */
public class CredsFromGitMvnDProps extends CredentialsFromProperties {

    /**
     * Instantiates a new Creds from git mvn -D props.
     */
    public CredsFromGitMvnDProps() {
        super("gitUser", "gitPass");
    }

}
