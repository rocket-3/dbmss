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
package ru.fusionsoft.database.snapshot.objects.generated;

import org.cactoos.iterable.IterableEnvelope;
import ru.fusionsoft.database.mapping.dbd.DbdDomainConstraintMapping;
import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.mapping.fields.DbdDomainFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectOfDbdDomainConstraintMapping;
import ru.fusionsoft.database.snapshot.objects.signature.name.ObjectNameOfScalar;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;
import ru.fusionsoft.lib.yaml.artefacts.IterableOfYamlSequence;

/**
 * Constraints {@link DbObject}'s extracted of {@link DbdDomainMapping} {@link DbObject}.
 * @since 0.1
 */
public class ConstraintsOfDomain extends IterableEnvelope<DbObject<DbdDomainConstraintMapping>> {

    /**
     * Instantiates a new Constraints of domain.
     * @param domain The {@link DbObject} of {@link DbdDomainMapping} to be encapsulated.
     */
    public ConstraintsOfDomain(final DbObject<DbdDomainMapping> domain) {
        super(
            new IterableOfYamlSequence<>(
                new YamlMappingOfPath(
                    new YamlMappingOfScalar(domain::asYaml),
                    DbdDomainFields.CONSTRAINTS
                ),
                node -> new ObjectOfDbdDomainConstraintMapping(
                    node.asMapping(),
                    new ObjectNameOfScalar(() -> domain.signature().name())
                )
            )
        );
    }

}
