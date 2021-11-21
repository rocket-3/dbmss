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
package org.fusionsoft.database.snapshot.objectsignature;

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.ObjectSignature;
import org.fusionsoft.database.snapshot.ObjectSignatures;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The iterable of {@link ObjectSignature} from {@link Objects}.
 * @since 0.1
 */
public class SignaturesOfObjects
    extends IterableEnvelope<ObjectSignature> implements ObjectSignatures {

    /**
     * Ctor.
     * @param objects The wrapped objects.
     */
    public SignaturesOfObjects(final Objects objects) {
        super(
            new Mapped<ObjectSignature>(
                DbObject::signature,
                objects
            )
        );
    }

}
