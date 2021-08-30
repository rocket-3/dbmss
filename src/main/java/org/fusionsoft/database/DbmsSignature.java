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
package org.fusionsoft.database;

import org.fusionsoft.database.dbms.signature.name.Absent;

/**
 * The interface, describes all we need to specify DBMS type specification.
 * @since 0.1
 */
public interface DbmsSignature {

    /**
     * The constant Absent means null DBMS type.
     */
    DbmsSignature ABSENT = new DbmsSignature() {
        @Override
        public DbmsName dbmsName() {
            return new Absent();
        }

        @Override
        public DbmsVersion dbmsVersion() {
            return DbmsVersion.DUMMY;
        }
    };

    /**
     * Dbms name.
     * @return The dbms name.
     */
    DbmsName dbmsName();

    /**
     * Dbms version string.
     * @return The dbms version as string.
     */
    DbmsVersion dbmsVersion();

}
