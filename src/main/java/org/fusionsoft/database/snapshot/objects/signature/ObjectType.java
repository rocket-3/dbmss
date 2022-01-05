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
package org.fusionsoft.database.snapshot.objects.signature;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;

/**
 * The enum ObjectType.
 * @param <T> The subtype of {@link YamlNode} representation parameter.
 * @since 0.1
 */
public interface ObjectType<T extends YamlNode> extends Text {

    /**
     * Node t.
     * @param mapping The mapping being casted to {@link T}.
     * @return The {@link T} instance.
     */
    T node(YamlNode mapping);

    String asString();

}
