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
package org.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlMapping;
import java.text.MessageFormat;
import org.cactoos.text.Repeated;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.SimpleYamlRepresentative;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.ObjectSignature;

/**
 * The naive impl of DbObject.
 * @param <Y> The type of YamlNode parameter.
 * @since 0.1
 */
public class SimpleDbObject<Y extends YamlMapping>
    extends SimpleYamlRepresentative<Y>
    implements DbObject<Y> {

    /**
     * The ObjectSignature encapsulated.
     */
    private final ObjectSignature sig;

    /**
     * Instantiates a new simple db object.
     * @param yaml The YamlNode to be encapsulated.
     * @param signature The ObjectSignature to be encapsulated.
     */
    public SimpleDbObject(final Y yaml, final ObjectSignature signature) {
        super(yaml);
        this.sig = signature;
    }

    @Override
    public final ObjectSignature signature() {
        return this.sig;
    }

    @Override
    public final String toString() {
        final int length = 10;
        return MessageFormat.format(
            "\n{2}\n{0}\n{3}\n{1}",
            this.sig.asString(),
            this.asYaml().toString(),
            new UncheckedText(new Repeated("=  ", length)).asString(),
            new UncheckedText(new Repeated("-  ", length)).asString()
        );
    }

}
