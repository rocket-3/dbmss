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
package ru.fusionsoft.database.diff;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Sticky;
import ru.fusionsoft.database.snapshot.DbObject;

public class ObjectsDiff implements TemporalDiff<Iterable<DbObject<YamlNode>>> {

    private final Iterable<DbObject<YamlNode>> cur;

    private final Iterable<DbObject<YamlNode>> nex;

    public ObjectsDiff(
        final Iterable<DbObject<YamlNode>> cur,
        final Iterable<DbObject<YamlNode>> next
    ) {
        this.cur = new Sticky<DbObject<YamlNode>>(cur);
        this.nex = new Sticky<DbObject<YamlNode>>(next);
    }

    @Override
    public Iterable<DbObject<YamlNode>> current() {
        return this.cur;
    }

    @Override
    public Iterable<DbObject<YamlNode>> next() {
        return this.nex;
    }

}
