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

import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.cactoos.Proc;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.io.InputOf;
import org.cactoos.io.OutputTo;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.proc.UncheckedProc;
import org.cactoos.scalar.SumOf;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.Joined;
import ru.fusionsoft.database.application.verify.Block;
import ru.fusionsoft.database.application.verify.BlocksOfPath;
import ru.fusionsoft.lib.runnable.WriteTo;

/**
 * The type of that can be constructed of.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class VerifyControls {

    /**
     * The Path encapsulated.
     */
    private Path migration;

    /**
     * The {@link BlocksOfPath} encapsulated.
     */
    private BlocksOfPath blocks;

    /**
     * The List of TextFields encapsulated.
     */
    private List<TextField> fields;

    /**
     * The list of ScrollBarMarks encapsulated.
     */
    private List<ScrollBarMark> marks;

    /**
     * The int encapsulated.
     */
    private int unresolved;

    /**
     * Load.
     * @param migration The path to migration sql to render.
     */
    public void load(final Path migration) {
        this.migration = migration;
        this.blocks = new BlocksOfPath(migration);
        this.fields = new ListOf<>();
        this.marks = new ListOf<>();
        this.unresolved = unresolvedNow();
        update();
    }

    /**
     * The TextFlow encapsulated.
     */
    @FXML
    private TextFlow flow;

    /**
     * The ScrollPane encapsulated.
     */
    @FXML
    private ScrollPane scroll;

    /**
     * The Label encapsulated.
     */
    @FXML
    private Label status;

    /**
     * The Button encapsulated.
     */
    @FXML
    private Button save;

    /**
     * The Button encapsulated.
     */
    @FXML
    private Button exit;

    /**
     * Exit.
     * @param event The {@link MouseEvent}.
     */
    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
    }

    /**
     * Save.
     * @param event The {@link MouseEvent}.
     */
    @FXML
    void save(MouseEvent event) {
        new WriteTo(
            new InputOf(
                new Joined(
                    "\n",
                    new Mapped<CharSequence>(
                        TextInputControl::getText,
                        this.fields
                    )
                )
            ),
            new OutputTo(this.migration)
        ).run();
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
    }

    /**
     * Update.
     */
    private void update() {
        updateStatus();
        int row = 1;
        final int total = this.blocks.stream().map(CollectionEnvelope::size).reduce(
            0,
            Integer::sum
        );
        this.scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        for (final Block block : this.blocks) {
            final boolean hyperblock = block.stream().anyMatch(x -> x.matches("\\s*---.*"));
            final boolean datasensetive = block.stream().anyMatch(x -> x.toUpperCase().matches(
                "DROP TABLE.*|INSERT INTO.*"
            ));
            final ScrollBarMark blockmark = new ScrollBarMark();
            blockmark.setPosition((0.0 + row) / (0.0 + total) + 0.012);
            if (block.editable()) {
                blockmark.setGreen();
                blockmark.setHeight((0.0 + block.size()) / (0.0 + total) * 100 * block.size());
            } else if (hyperblock) {
                blockmark.setHyper();
            } else {
                blockmark.setHidden();
            }
            marks.add(blockmark);
            for (int i = 0; i < block.size(); i++) {
                final String line = block.get(i);
                final TextField field = new TextField(line);
                final ScrollBarMark mark = new ScrollBarMark();
                field.setPrefWidth(1280);
                mark.setPosition((0.0 + row) / (0.0 + total) + 0.012);
                mark.setHidden();
                this.marks.add(mark);
                if (line.matches("\\s*---.*")) {
                    field.getStyleClass().add("hyper");
                } else if (line.matches("\\s*--.*")) {
                    field.getStyleClass().add("info");
                } else {
                    field.setStyle(field.getStyle() + "-fx-font-family: \"consolas\"");
                    if (datasensetive) {
                        if (!line.isEmpty()) {
                            field.getStyleClass().add("data");
                        }
                    }
                }
                if (block.editable()) {
                    field.setEditable(true);
                    if (block.interventions().contains(i)) {
                        field.getStyleClass().add("bad");
                        field.getStyleClass().removeAll("data");
                        mark.setRed();
                    } else {
                        field.getStyleClass().add("good");
                        field.getStyleClass().removeAll("data");
                    }
                    final int index = i;
                    field.textProperty().addListener((observable, oldValue, newValue) -> {
                        final boolean wasfixed = block.interventions().contains(index);
                        block.remove(index);
                        block.add(index, newValue);
                        if (!block.interventions().contains(index)) {
                            mark.setHidden();
                            field.getStyleClass().removeAll("bad");
                            field.getStyleClass().add("good");
                        } else {
                            mark.setRed();
                            field.getStyleClass().add("bad");
                            field.getStyleClass().removeAll("good");
                        }
                        updateStatus();
                    });
                } else {
                    field.setEditable(false);
                }
                final TextFlow rowtext = new TextFlow(new Text(String.valueOf(row++)));
                rowtext.setMinWidth(30);
                rowtext.setTextAlignment(TextAlignment.RIGHT);
                rowtext.setStyle("-fx-font-size: 20;");
                this.flow.getChildren().add(rowtext);
                this.flow.getChildren().add(field);
                this.flow.getChildren().add(new Text("\n"));
                this.fields.add(field);
                this.scroll.setFitToHeight(true);
            }
        }
        this.scroll.setHvalue(-10);
        Platform.runLater(() -> {
                for (final ScrollBarMark mark : this.marks) {
                    scrollBar(mark::attach);
                }
            }
        );
    }

    /**
     * Do some modifications with the ScrollBar.
     * @param todo What to do with the ScrollBar later.
     */
    private void scrollBar(final Proc<ScrollBar> todo) {
        Unchecked<ScrollBar> scalar = new Unchecked<>(() -> {
            for (final Node node : scroll.lookupAll(".scroll-bar")) {
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
     * Unresolved now int.
     * @return The int.
     */
    private int unresolvedNow() {
        return new SumOf(
            new Mapped<Integer>(
                block -> block.interventions().size(),
                this.blocks
            )
        ).intValue();
    }

    /**
     * Update status.
     */
    private void updateStatus() {
        this.status.setText(
            MessageFormat.format(
                "{0} / {1}",
                this.unresolved - unresolvedNow(),
                this.unresolved
            )
        );
    }

}
