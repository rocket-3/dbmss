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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import org.cactoos.Proc;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.Joined;
import ru.fusionsoft.lib.input.ConsolePrinting;
import ru.fusionsoft.lib.runnable.ExitWithError;

/**
 * The main class of application.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class Application {

    /**
     * The entry point of application.
     * @param args The input arguments.
     * @throws Exception When can't.
     * @checkstyle AvoidInlineConditionalsCheck (100 lines)
     */
    public static void main(final String[] args) throws Exception {
        final String command = args.length > 0 ? args[0] : "help";
        final ConsolePrinting help = new ConsolePrinting(
            new Joined(
                "\n",
                new Mapped<>(
                    comm -> MessageFormat.format("{0} {1}\n", comm.command(), comm.description()),
                    Commands.values()
                )
            )
        );
        if (command.equals("help")) {
            help.run();
        } else {
            final Map<String, Proc<String[]>> map = Commands.map();
            if (!map.containsKey(command)) {
                help.run();
                new ExitWithError(MessageFormat.format("No such command: {0}", command)).run();
            } else {
                final String[] sub = Arrays.copyOfRange(args, 1, args.length);
                map.get(command).exec(sub);
            }
        }
    }

}
