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
package ru.fusionsoft.database.migration.condition;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The predicate {@link HasCurrentChangingObjectsThatSatisfy},
 *  checking given iterable of {@link DbObject}s {@link TemporalDiff} has tables changed.
 * @since 0.1
 */
public class DiffHasTablesChanging extends HasCurrentChangingObjectsThatSatisfy {

    /**
     * Instantiates a new Diff has tables changing.
     * @param diff The {@link TemporalDiff} of Iterable of {@link DbObject}s to be encapsulated.
     */
    public DiffHasTablesChanging(final TemporalDiff<Iterable<DbObject<YamlNode>>> diff) {
        super(
            obj -> obj.signature().type().equalsTo(new ObjectTypeTable()),
            diff
        );
    }

}
