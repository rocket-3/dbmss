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

/**
 * The interface representing a signature of DBMS operated object.
 * @since 0.1
 */
public interface DbObjectSignature {

    /**
     * The constant ABSENT means no object signature provided, i.e. null.
     * @checkstyle AnonInnerLengthCheck (22 lines)
     */
    DbObjectSignature ABSENT = new DbObjectSignature() {

        @Override
        public String name() {
            return "";
        }

        @Override
        public String parentName() {
            return "";
        }

        @Override
        public DbObjectType type() {
            return DbObjectType.ABSENT;
        }

        @Override
        public DbmsSignature dbmsSignature() {
            return DbmsSignature.ABSENT;
        }
    };

    /**
     * The name of DBMS object.
     * @return The string.
     */
    String name();

    /**
     * The name hierarchical upper object.
     * @return The string.
     */
    String parentName();

    /**
     * Type of the object.
     * @return The DbObjectType instance.
     */
    DbObjectType type();

    /**
     * Dbms signature of the object.
     * @return The DbmsSignature instance.
     */
    DbmsSignature dbmsSignature();

}
