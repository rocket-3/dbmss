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
 * The interface IS representing database instance state.
 * @param <D> The type of IS description parameter.
 * @since 0.1
 */
public interface IS<D> {

    /**
     * Restore migration.
     * @return The migration.
     * @throws Exception When can't.
     */
    Migration restore() throws Exception;

    /**
     * Describe description type.
     * @return The description type.
     * @throws Exception When can't.
     */
    D describe() throws Exception;

}
