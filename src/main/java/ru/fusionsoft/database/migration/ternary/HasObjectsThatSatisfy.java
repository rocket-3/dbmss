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
package ru.fusionsoft.database.migration.ternary;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.iterable.Filtered;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.snapshot.DbObject;

public class HasObjectsThatSatisfy implements Scalar<Boolean> {

    private final Iterable<DbObject<YamlNode>> objects;

    private final Func<DbObject<YamlNode>, Boolean> predicate;

    public HasObjectsThatSatisfy(
        final Iterable<DbObject<YamlNode>> objects,
        final Func<DbObject<YamlNode>, Boolean> predicate
    ) {
        this.objects = objects;
        this.predicate = predicate;
    }

    @Override
    public final Boolean value() {
        return new SetOf<>(
            new Filtered<>(
                predicate,
                objects
            )
        ).size() > 0;
    }

}
