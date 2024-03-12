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
package ru.fusionsoft.database.application.verify;

import java.nio.file.Path;
import java.util.List;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListEnvelope;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.Split;
import org.cactoos.text.TextOf;

/**
 * The {@link Block}s to construct of path to migration sql.
 * @since 0.1
 */
public final class BlocksOfPath extends ListEnvelope<Block> {

    /**
     * Instantiates a new Blocks of path.
     * @param path The {@link Path} to be encapsulated.
     * @checkstyle DiamondOperatorCheck (100 lines)
     */
    public BlocksOfPath(final Path path) {
        super(
            new Unchecked<>(
                () -> {
                    Block current = new Block();
                    final List<Block> blocks = new ListOf<Block>(current);
                    for (final String line : new Mapped<>(
                        Text::asString,
                        new Split(
                            new TextOf(path),
                            "\n"
                        )
                    )) {
                        if (line.startsWith("--")) {
                            blocks.add(current);
                            current = new Block();
                        }
                        current.add(line);
                    }
                    blocks.add(current);
                    return blocks;
                }
            ).value()
        );
    }

}
