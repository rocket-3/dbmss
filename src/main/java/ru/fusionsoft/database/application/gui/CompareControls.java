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
package ru.fusionsoft.database.application.gui;

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.proc.UncheckedProc;
import org.cactoos.scalar.Unchecked;
import org.cactoos.set.SetOf;
import org.cactoos.text.Repeated;
import org.cactoos.text.Split;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.migration.diff.SimpleTemporalDiff;
import ru.fusionsoft.database.migration.diff.SmartIterableTemporalDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;

/**
 * The JavaFX controller for {@link Compare} application.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public final class CompareControls {

    /**
     * The TextField encapsulated.
     */
    @FXML
    private TextField right;

    /**
     * The TextField encapsulated.
     */
    @FXML
    private TextField left;

    /**
     * The ScrollPane encapsulated.
     */
    @FXML
    private ScrollPane scroll;

    /**
     * The TextFlow encapsulated.
     */
    @FXML
    private TextFlow flow;

    /**
     * ScrollBarMark's to access later.
     */
    private final List<ScrollBarMark> marks;

    /**
     * Current line rendering.
     */
    private int linecurrent = 0;

    /**
     * Max lines count of DBDs.
     */
    private int linetotal = 0;

    /**
     * Instantiates a new Compare controls.
     */
    public CompareControls() {
        this.marks = new ArrayList<>(0);
    }

    /**
     * Load.
     * @param current The current {@link DbdReadable}.
     * @param next The next {@link DbdReadable}.
     */
    public void load(final DbdReadable current, final DbdReadable next) {
        this.scroll.setFitToHeight(true);
        this.right.setText("Server version");
        this.left.setText("DBD version");
        this.flow.getChildren().add(new javafx.scene.text.Text("\n"));
        linecurrent = 0;
        linetotal = Math.max(
            current.asYaml().toString().split("\n").length,
            next.asYaml().toString().split("\n").length
        );
        renderIntersectedMappings(current.asYaml().asMapping(), next.asYaml().asMapping(), 0);
        Platform.runLater(() -> {
                for (final ScrollBarMark mark : this.marks) {
                    scrollBar(mark::attach);
                }
            }
        );
    }

    /**
     * Apply some modification on the ScrollBar.
     * @param todo What to do with the scrollbar.
     */
    private void scrollBar(final Proc<ScrollBar> todo) {
        final Unchecked<ScrollBar> scalar = new Unchecked<>(() -> {
            for (final javafx.scene.Node node : scroll.lookupAll(".scroll-bar")) {
                if (node instanceof ScrollBar) {
                    final ScrollBar scrollBar = (ScrollBar) node;
                    if (scrollBar.getOrientation() == Orientation.VERTICAL) {
                        return scrollBar;
                    }
                }
            }
            throw new RuntimeException("Failed to access scrollbar");
        });
        if (this.scroll.getSkin() == null) {
            // Skin is not yet attached, wait until skin is attached to access the scroll bars
            final ChangeListener<Skin<?>> skinChangeListener = new ChangeListener<Skin<?>>() {
                @Override
                public void changed(
                    final ObservableValue<? extends Skin<?>> observable,
                    final Skin<?> oldValue,
                    final Skin<?> newValue
                ) {
                    scroll.skinProperty().removeListener(this);
                    new UncheckedProc<>(todo).exec(scalar.value());
                }
            };
            this.scroll.skinProperty().addListener(skinChangeListener);
        } else {
            new UncheckedProc<>(todo).exec(scalar.value());
        }
    }

    /**
     * Render intersected mappings.
     * @param current The current node.
     * @param next The next node.
     * @param level The level of indentation.
     */
    public void renderIntersectedMappings(
        final YamlMapping current,
        final YamlMapping next,
        final int level
    ) {
        final KeysOfMappings keys = new KeysOfMappings(current, next);
        for (final YamlNode key : keys.added()) {
            renderAddedMappingEntry(key.asScalar().value() + ":", next.value(key), level + 1);
        }
        for (final TemporalDiff<YamlNode> diff : keys.intersection()) {
            final YamlNode ikey = diff.current();
            renderIntersectedMappingEntries(
                diff.current().asScalar().value() + ":",
                new SimpleTemporalDiff<>(current.value(ikey), next.value(ikey)),
                level + 1
            );
        }
        for (final YamlNode key : keys.deleted()) {
            renderDeletedMappingValue(key.asScalar().value() + ":", current.value(key), level + 1);
        }
    }

    /**
     * Render intersected sequences.
     * @param key The sequences common key.
     * @param diff Sequences diff.
     * @param level Indentation level.
     */
    public void renderIntersectedSequences(
        final String key,
        final TemporalDiff<YamlSequence> diff,
        final int level
    ) {
        final SmartIterableTemporalDiff<YamlNode> values = new SmartIterableTemporalDiff<>(
            node -> node.toString().hashCode(),
            diff.current().values(),
            diff.next().values()
        );
        renderNewLine();
        renderValue(key, "same", level);
        renderValue(key, "same", level);
        final String nextkey = "-";
        for (final YamlNode added : values.added()) {
            renderAddedMappingEntry(nextkey, added, level + 1);
        }
        for (final YamlNode deleted : values.deleted()) {
            renderDeletedMappingValue(nextkey, deleted, level + 1);
        }
        for (final TemporalDiff<YamlNode> isec : values.intersection()) {
            renderIntersectedMappingEntries(nextkey, isec, level + 1);
        }
    }

    /**
     * Render intersected mapping entries.
     * @param key The mappings common key.
     * @param diff Mappings diff.
     * @param level Indentation level.
     */
    private void renderIntersectedMappingEntries(
        final String key,
        final TemporalDiff<YamlNode> diff,
        final int level
    ) {
        if (!diff.current().type().equals(diff.next().type())) {
            renderDifferentTypeMappingEntry(key, diff, level);
        } else {
            final Node type = diff.current().type();
            if (type.equals(Node.SCALAR)) {
                final String scurrent = MessageFormat.format(
                    "{0} {1}",
                    key,
                    new TextRepresentationOfYamlNode(diff.current())
                );
                final String snext = MessageFormat.format(
                    "{0} {1}",
                    key,
                    new TextRepresentationOfYamlNode(diff.next())
                );
                renderNewLine();
                if (scurrent.equals(snext)) {
                    renderValue(scurrent, "same", level);
                    renderValue(snext, "same", level);
                } else {
                    renderScrollMark();
                    renderValue(scurrent, "old", level);
                    renderValue(snext, "new", level);
                }
            } else {
                if (type.equals(Node.MAPPING)) {
                    renderNewLine();
                    renderValue(key, "same", level);
                    renderValue(key, "same", level);
                    renderIntersectedMappings(
                        diff.current().asMapping(),
                        diff.next().asMapping(),
                        level + 1
                    );
                }
                if (type.equals(Node.SEQUENCE)) {
                    renderIntersectedSequences(
                        key,
                        new SimpleTemporalDiff<>(
                            diff.current().asSequence(),
                            diff.next().asSequence()
                        ),
                        level + 1
                    );
                }
                if (type.equals(Node.STREAM)) {
                    renderNewLine();
                    throw new RuntimeException("The node type is 'Stream', idk, what to do");
                }
            }
        }
    }

    /**
     * Render different type mapping entry.
     * @param key The nodes common key.
     * @param diff Nodes diff.
     * @param level Indentation level.
     */
    private void renderDifferentTypeMappingEntry(
        final String key,
        final TemporalDiff<YamlNode> diff,
        final int level
    ) {
        renderScrollMark();
        final UncheckedFunc<YamlNode, List<String>> linesof = new UncheckedFunc<>(node -> {
            final String text = new TextRepresentationOfYamlNode(node).asString();
            return new ListOf<String>(
                new Mapped<String>(
                    Text::asString,
                    new Split(text, "\n")
                )
            );
        });
        final List<String> currentLines = linesof.apply(diff.current());
        final List<String> nextLines = linesof.apply(diff.next());
        final int max = Math.max(currentLines.size(), nextLines.size());
        renderNewLine();
        renderValue(key, "same", level);
        renderValue(key, "same", level);
        for (int i = 0; i < max; i++) {
            renderNewLine();
            if (currentLines.size() > i) {
                renderValue(currentLines.get(i), "old", level + 1);
            } else {
                renderValue("", "empty", level);
            }
            if (nextLines.size() > i) {
                renderValue(nextLines.get(i), "new", level + 1);
            } else {
                renderValue("", "empty", level + 1);
            }
        }
    }

    /**
     * Render deleted mapping value.
     * @param key The node key.
     * @param deleted Deleted node to render.
     * @param level Indentation level.
     */
    private void renderDeletedMappingValue(
        final String key,
        final YamlNode deleted,
        final int level
    ) {
        renderScrollMark();
        final Mapped<String> lines = new Mapped<>(
            Text::asString,
            new Split(new TextRepresentationOfYamlNode(deleted), "\n")
        );
        if (new SetOf<>(lines).size() == 1) {
            renderNewLine();
            renderValue(
                MessageFormat.format("{0} {1}", key, lines.iterator().next()),
                "old",
                level
            );
            renderValue("", "empty", level);
        } else {
            renderNewLine();
            renderValue(key, "old", level);
            renderValue("", "empty", level);
            for (final String line : lines) {
                renderNewLine();
                renderValue(line, "old", level + 1);
                renderValue("", "empty", level + 1);
            }
        }
    }

    /**
     * Render added mapping entry.
     * @param key The node key.
     * @param added Added node to render.
     * @param level Indentation level.
     */
    private void renderAddedMappingEntry(final String key, final YamlNode added, final int level) {
        renderScrollMark();
        final Mapped<String> lines = new Mapped<>(
            Text::asString,
            new Split(new TextRepresentationOfYamlNode(added), "\n")
        );
        if (new SetOf<>(lines).size() == 1) {
            renderNewLine();
            renderValue("", "empty", level);
            renderValue(
                MessageFormat.format("{0} {1}", key, lines.iterator().next()),
                "new",
                level
            );
        } else {
            renderNewLine();
            renderValue("", "empty", level);
            renderValue(key, "new", level);
            for (final String line : lines) {
                renderNewLine();
                renderValue("", "empty", level + 1);
                renderValue(line, "new", level + 1);
            }
        }
    }

    /**
     * Render value.
     * @param line The text of line to render.
     * @param css The css class to put on.
     * @param level The indentation level.
     */
    private void renderValue(final String line, final String css, final int level) {
        final TextField field = new TextField(new Padded(line, level).asString());
        field.prefWidthProperty().bind(this.scroll.widthProperty().multiply(0.5));
        field.getStyleClass().add(css);
        field.setEditable(false);
        this.flow.getChildren().add(field);
    }

    /**
     * Render new line.
     */
    private void renderNewLine() {
        linecurrent++;
        this.flow.getChildren().add(new javafx.scene.text.Text("\n"));
    }

    /**
     * Render scroll mark.
     */
    private void renderScrollMark() {
        final ScrollBarMark mark = new ScrollBarMark();
        mark.setPosition((0.0 + linecurrent * 1.05) / (0.0 + linetotal) + 0.012);
        mark.setOrange();
        this.marks.add(mark);
    }

    /**
     * The type of that can be constructed of.
     * @since 0.1
     */
    private static class TextRepresentationOfYamlNode implements Text {

        /**
         * The YamlNode encapsulated.
         */
        private final YamlNode node;

        /**
         * Instantiates a new Text representation of yaml node.
         * @param node The {@link YamlNode} to be encapsulated.
         */
        private TextRepresentationOfYamlNode(final YamlNode node) {
            this.node = node;
        }

        @Override
        public String asString() {
            String text = "";
            if (node.type().equals(Node.SCALAR)) {
                text = node.asScalar().value();
            } else {
                text = node.toString();
            }
            return text == null ? "" : text;
        }

        @Override
        public String toString() {
            return this.asString();
        }

    }

    /**
     * The type of that can be constructed of.
     * @since 0.1
     */
    private static class KeysOfMappings extends SmartIterableTemporalDiff<YamlNode> {

        /**
         * Instantiates a new Keys of mappings.
         * @param current The {@link YamlMapping} to be encapsulated.
         * @param next The {@link YamlMapping} to be encapsulated.
         */
        public KeysOfMappings(final YamlMapping current, final YamlMapping next) {
            super(
                node -> node.asScalar().value().trim().replace("\"", ""),
                current.keys(),
                next.keys()
            );
        }

    }

    /**
     * The type of that can be constructed of.
     * @since 0.1
     */
    private static class Padded implements Text {

        /**
         * The UncheckedText encapsulated.
         */
        private final UncheckedText text;

        /**
         * The int encapsulated.
         */
        private final int level;

        /**
         * Instantiates a new Padded.
         * @param text The {@link Text} to be encapsulated.
         * @param level The {@link int} to be encapsulated.
         */
        private Padded(final Text text, final int level) {
            this.text = new UncheckedText(text);
            this.level = level;
        }

        /**
         * Instantiates a new Padded.
         * @param text The {@link String} to be encapsulated.
         * @param level The {@link int} to be encapsulated.
         */
        private Padded(final String text, final int level) {
            this(new TextOf(text), level);
        }

        @Override
        public String asString() {
            return MessageFormat.format(
                "{0}{1}",
                new UncheckedText(new Repeated(" ", this.level)).asString(),
                this.text.asString()
            );
        }

    }

}
