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
package ru.fusionsoft.database.mapping.entries;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import ru.fusionsoft.database.mapping.MappingOfRepresentative;
import ru.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import ru.fusionsoft.lib.yaml.YamlRepresentative;

/**
 * The Map.Entry of Text and YamlNode from {@link YamlRepresentative}.
 * @since 0.1
 */
public class EntriesOfYamlRepresentative
    extends IterableEnvelope<Map.Entry<? extends Text, ? extends YamlNode>> {

    /**
     * Instantiates a new Entries of yaml representative.
     * @param representative The YamlRepresentative to be encapsulated.
     */
    public EntriesOfYamlRepresentative(
        final YamlRepresentative<? extends YamlMapping> representative
    ) {
        super(
            new EntriesOfYamlMapping(
                new MappingOfRepresentative(representative)
            )
        );
    }

}
