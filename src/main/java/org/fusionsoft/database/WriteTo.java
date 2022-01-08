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
package org.fusionsoft.database;

import org.cactoos.Input;
import org.cactoos.Output;
import org.cactoos.Text;
import org.cactoos.io.InputStreamOf;
import org.cactoos.io.OutputStreamTo;
import org.cactoos.io.WriterTo;
import org.cactoos.proc.UncheckedProc;
import org.cactoos.text.TextOf;

/**
 * The Runnable to write {@link Input} to {@link Output} and their delegates.
 * @since 0.1
 */
public class WriteTo implements Runnable {

    /**
     * The Input encapsulated.
     */
    private final Input ipt;

    /**
     * The Output encapsulated.
     */
    private final Output opt;

    /**
     * Instantiates a new Write to.
     * @param ipt The Input to be encapsulated.
     * @param opt The Output to be encapsulated.
     */
    public WriteTo(final Input ipt, final Output opt) {
        this.ipt = ipt;
        this.opt = opt;
    }

    /**
     * Instantiates a new Write to.
     * @param ipt The YamlRepresentative to be encapsulated.
     * @param opt The Output to be encapsulated.
     */
    public WriteTo(final YamlRepresentative<?> ipt, final Output opt) {
        this(
            () -> new InputStreamOf(ipt.asYaml().toString()),
            opt
        );
    }

    /**
     * Instantiates a new Write to.
     * @param ipt The YamlRepresentative to be encapsulated.
     * @param opt The Folder to be encapsulated.
     * @param name The Text of file name to be encapsulated.
     */
    public WriteTo(final YamlRepresentative<?> ipt, final Folder opt, final Text name) {
        this(
            ipt,
            () -> new OutputStreamTo(opt.path().resolve(name.asString()))
        );
    }

    @Override
    public final void run() {
        new UncheckedProc<Input>(
            inp -> {
                new WriterTo(this.opt).write(
                    new TextOf(inp).asString()
                );
            }
        ).exec(this.ipt);
    }

}
