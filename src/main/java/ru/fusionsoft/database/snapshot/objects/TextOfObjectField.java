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
package ru.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.database.snapshot.DbObject;

public class TextOfObjectField extends TextEnvelope {

    public TextOfObjectField(final DbObject<? extends YamlNode> object, final Text field) {
        super(
            new TextOfScalar(
                new Sticky<>(
                    new ObjectFieldString(object, field)
                )
            )
        );
    }

}
