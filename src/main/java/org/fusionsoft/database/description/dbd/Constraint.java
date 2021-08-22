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
 *
 */
package org.fusionsoft.database.description.dbd;

import java.io.IOException;
import java.util.Set;

public interface Constraint {

    String key() throws IOException;

    Set<String> dbColumn() throws IOException;

    String dbConstraintType() throws IOException;

    Set<String> dbFKColumn() throws IOException;

    String dbRefSchema() throws IOException;

    String dbRefTable() throws IOException;

    Set<String> dbRefColumn() throws IOException;

    String dbRefUpdate() throws IOException;

    String dbRefDelete() throws IOException;

}
