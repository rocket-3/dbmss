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
package ru.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlNode;
import java.sql.ResultSet;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.snapshot.query.Query;

/**
 * The type of {@link com.amihaiemil.eoyaml.BaseYamlSequence}
 *  of {@link com.amihaiemil.eoyaml.Scalar} that can be constructed of
 *  {@link ResultSet} and some key.
 * @since 0.1
 */
public class YamlScalarSequenceOfResultSet extends YamlSequenceOfNodes {

    /**
     * Instantiates a new Yaml scalar sequence of result set.
     * @param key The String to be encapsulated.
     * @param rset The ResultSet to be encapsulated.
     */
    public YamlScalarSequenceOfResultSet(final String key, final ResultSet rset) {
        super(
            new Mapped<YamlNode>(
                x -> Yaml.createYamlScalarBuilder().addLine(x).buildPlainScalar(),
                new IterableOf<>(
                    new Unchecked<>(
                        () -> {
                            String[] value = new String[0];
                            if (rset.getArray(key) != null) {
                                value = (String[]) rset.getArray(key).getArray();
                            }
                            return value;
                        }
                    ).value()
                )
            )
        );
    }

    /**
     * Instantiates a new Yaml scalar sequence of result set.
     * @param label The {@link T} label to take from
     *  {@link ResultSet} and {@link Query} of {@link T}'s to be encapsulated.
     * @param query The {@link Query} of {@link T} to be encapsulated.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param <T> The {@link Text} subtype, the instances of which to expect in {@link Query}
     */
    public <T extends Text> YamlScalarSequenceOfResultSet(
        final T label,
        final Query<T> query,
        final ResultSet rset
    ) {
        this(
            query.outcomeFor(label),
            rset
        );
    }

}
