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
package org.fusionsoft.database.dbms.signature;

import org.fusionsoft.database.DbmsName;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.DbmsVersion;

/**
 * The naive dbms DbmsSignature impl.
 * @since 0.1
 */
public class SimpleDbmsSignature implements DbmsSignature {

    /**
     * The DbmsName wrapped.
     */
    private final DbmsName kind;

    /**
     * The DbmsName wrapped wrapped.
     */
    private final DbmsVersion version;

    /**
     * Instantiates a new Simple dbms signature.
     * @param kind The name of dbms to return.
     * @param version The version of dbms to return.
     */
    public SimpleDbmsSignature(final DbmsName kind, final DbmsVersion version) {
        this.kind = kind;
        this.version = version;
    }

    @Override
    public final DbmsName dbmsName() {
        return this.kind;
    }

    @Override
    public final DbmsVersion dbmsVersion() {
        return this.version;
    }

}
