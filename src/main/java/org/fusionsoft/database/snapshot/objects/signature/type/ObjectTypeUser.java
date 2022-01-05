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
package org.fusionsoft.database.snapshot.objects.signature.type;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;

/**
 * The {@link org.fusionsoft.database.snapshot.objects.signature.ObjectType} of 'user' object.
 * @since 0.1
 * @todo #40:30min Implement fetching of users
 */
public class ObjectTypeUser extends SimpleObjectType<YamlMapping> {

    /**
     * Instantiates a new Object type user.
     */
    public ObjectTypeUser() {
        super(YamlNode::asMapping, "user");
    }

}
