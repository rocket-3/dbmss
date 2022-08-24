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
package ru.fusionsoft.database.text;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.Replaced;
import ru.fusionsoft.database.mapping.fields.DbdProcedureFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOfYamlMapping;

/**
 * Args signature inside braces for Postgres procedure {@link Text} of procedure {@link DbObject}.
 * @since 0.1
 */
public class PgProcedureArgs implements Text {

    /**
     * The Object.
     */
    private final DbObject<? extends YamlNode> object;

    /**
     * Instantiates a new Pg procedure args.
     * @param object The object
     */
    public PgProcedureArgs(final DbObject<? extends YamlNode> object) {
        this.object = object;
    }

    @Override
    public final String asString() throws Exception {
        return new Replaced(
            new MaybeEmptyTextOfYamlMapping(
                this.object.asYaml().asMapping(),
                DbdProcedureFields.ARGUMENTS
            ),
            "(\\w+ \\w+) (DEFAULT [^\\,\\n]+)(\\,|\\b)",
            "$1$3"
        ).asString();
    }

}
