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
package org.fusionsoft.database.snapshot;

/**
 * The interface Writable representing a thing,
 *  can be written in {@link SnapshotFolder}.
 * @since 0.1
 * @todo #15:15min Relax the contract of `writeTo`.
 */
public interface Writable {

    /**
     * Write to.
     * @param folder The folder.
     */
    void writeTo(SnapshotFolder folder);

}
