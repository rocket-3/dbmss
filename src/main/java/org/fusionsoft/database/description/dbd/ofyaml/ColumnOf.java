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
package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.description.dbd.SimpleColumn;
import org.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOf;
import org.fusionsoft.lib.yaml.artefacts.StringSetOf;

/**
 * Unfinished JavaDoc.
 * The type is still in pre-design stage.
 * @since 0.0.1
 * @checkstyle JavadocTagsCheck (4096 lines)
 * @checkstyle JavadocLocationCheck (4096 lines)
 * @checkstyle JavadocMethodCheck (4096 lines)
 * @checkstyle RegexpSinglelineCheck (4096 lines)
 * @checkstyle JavadocVariableCheck (4096 lines)
 * @checkstyle MemberNameCheck (4096 lines)
 * @checkstyle ParameterNameCheck (4096 lines)
 * @checkstyle ParameterNumberCheck (4096 lines)
 * @checkstyle StringLiteralsConcatenationCheck (4096 lines)
 * @checkstyle AbbreviationAsWordInNameCheck (4096 lines)
 * @checkstyle LineLengthCheck (4096 lines)
 * @checkstyle AvoidFieldNameMatchingMethodName (4096 lines)
 */
public class ColumnOf extends SimpleColumn {

    public ColumnOf(final StrictYamlMapping mapping) throws Exception {
        super(
            new MaybeEmptyTextOf(mapping, "iuColumn").asString(),
            new MaybeEmptyTextOf(mapping, "iuJsonColumn").asString(),
            new MaybeEmptyTextOf(mapping, "type").asString(),
            !new MaybeEmptyTextOf(mapping, "dbNullable").asString().equals(
                "false"
            ),
            new MaybeEmptyTextOf(mapping, "description").asString(),
            new StringSetOf(mapping, "iuIncludeProps"),
            new MaybeEmptyTextOf(mapping, "dbName").asString(),
            new MaybeEmptyTextOf(mapping, "dbType").asString(),
            new MaybeEmptyTextOf(mapping, "dbLocalIdMethod").asString()
        );
    }

    public ColumnOf(final YamlMapping mapping) throws Exception {
        this(new StrictYamlMapping(mapping));
    }

}
