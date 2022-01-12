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
package ru.fusionsoft.database.mapping.dbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Fallback;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.ScalarWithFallback;
import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.lib.exception.JoinedRuntimeException;
import ru.fusionsoft.lib.exception.ValueNotFoundException;
import ru.fusionsoft.lib.yaml.YamlMappingHasKeys;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The DBD/schemas/#schema/tables/#table/constraints/#constraint node mapping.
 * @since 0.1
 */
public class DbdConstraintMapping extends YamlMappingOfScalar {

    /**
     * Instantiates a new Dbd constraint mapping.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public DbdConstraintMapping(final YamlMapping mapping) {
        super(
            new ScalarWithFallback<>(
                new ScalarOf<YamlMapping>(
                    m -> {
                        m.keys();
                        return m;
                    },
                    new YamlMappingHasKeys(
                        mapping,
                        DbdConstraintFields.foreign()
                    )
                ),
                new Fallback.From<>(
                    ValueNotFoundException.class,
                    foreign -> new YamlMappingOfScalar(
                        new ScalarWithFallback<>(
                            new ScalarOf<YamlMapping>(
                                m -> {
                                    m.keys();
                                    return m;
                                },
                                new YamlMappingHasKeys(
                                    mapping,
                                    DbdConstraintFields.primary()
                                )
                            ),
                            new Fallback.From<>(
                                ValueNotFoundException.class,
                                primary -> {
                                    throw new JoinedRuntimeException(
                                        foreign,
                                        primary
                                    );
                                }
                            )
                        )
                    )
                )
            )
        );
    }

}
