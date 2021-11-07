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
package org.fusionsoft.database.mapping.dbd.ofobjects;

import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import org.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The {@link YamlMappingOfEntries} of DBD/schemas/#schema/domains document node built
 *  of objects context.
 * @since 0.1
 */
public class DbdDomainsMappingOfObjects extends YamlMappingOfEntries {

    /**
     * Instantiates a new Dbd domains mapping of objects.
     * @param objects The whole Objects context to be encapsulated.
     * @param schema The parent DbObject to be encapsulated.
     */
    public DbdDomainsMappingOfObjects(
        final Objects objects,
        final DbObject<?> schema
    ) {
        super(
            new UnwrapEntriesOfObjects<DbdDomainMapping>(
                objects,
                new ObjectsWithParentAndType(
                    schema,
                    ObjectType.UDT_DOMAIN,
                    objects
                ),
                (objs, domain) -> new DbdDomainMapping(
                    new MappingOfRepresentative(domain)
                )
            )
        );
    }

}
