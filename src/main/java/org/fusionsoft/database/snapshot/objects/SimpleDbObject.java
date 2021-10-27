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
package org.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlMapping;
import java.text.MessageFormat;
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
     * Instantiates a new Naive db object.
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
    public String toString() {
        return MessageFormat.format(
            "\n=   =   =   =\n{0}\n-   -   -   -\n{1}",
            this.sig.asString(),
            this.asYaml().toString()
        );
    }

}
