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
package ru.fusionsoft.database.application;

import java.nio.file.Path;
import java.text.MessageFormat;
import javafx.application.Application;
import org.cactoos.iterable.Filtered;
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.application.verify.Block;
import ru.fusionsoft.database.application.verify.BlocksOfPath;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.runnable.ExitWithError;

/**
 * The application command.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public final class Verify {

    /**
     * Main.
     * @param args The args.
     * @throws Exception When can't.
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 1;
        new Ternary<Runnable>(
            () -> args.length != arguments,
            () -> new ExitWithError(
                "Wrong arguments, needs mode: {console|gui}"
            ),
            () -> {
                Path migration = new CurrentWorkingDirectory().value().resolve(
                    new MigrationSqlFileName().asString()
                );
                if (args[0].equals("gui")) {
                    Application.launch(
                        ru.fusionsoft.database.application.gui.Verify.class,
                        migration.toString()
                    );
                } else {
                    final BlocksOfPath blocks = new BlocksOfPath(migration);
                    for (final Block block : new Filtered<>(
                        Block::editable,
                        blocks
                    )) {
                        for (int i = 0; i < block.size(); i++) {
                            String label = block.interventions().contains(i) ? "ERR" : "OKA";
                            System.out.println(
                                MessageFormat.format("{0}: {1}", label, block.get(i))
                            );
                        }
                    }
                    if (blocks.stream().anyMatch(Block::editable)) {
                        new ExitWithError("Migration is unfinished.").run();
                    }
                }
            }
        ).value().run();
    }

}
