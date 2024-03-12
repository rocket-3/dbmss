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

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The type of that can be constructed of.
 * @since 0.1
 * @checkstyle MagicNumberCheck (200 lines)
 */
@SuppressWarnings("PMD")
public final class ScrollBarMark {

    /**
     * The Rectangle encapsulated.
     */
    private final Rectangle rect;

    /**
     * The DoubleProperty encapsulated.
     */
    private final DoubleProperty position;

    /**
     * Instantiates a new Scroll bar mark.
     */
    public ScrollBarMark() {
        this.position = new SimpleDoubleProperty();
        this.rect = new Rectangle(5, 5, Color.RED.deriveColor(0, 1, 1, 0.2));
        this.rect.setManaged(false);
    }

    /**
     * Attach.
     * @param scrollBar The {@link ScrollBar}.
     */
    public void attach(final ScrollBar scrollBar) {
        final StackPane sp = (StackPane) scrollBar.lookup(".track");
        rect.widthProperty().bind(sp.widthProperty());
        sp.getChildren().add(rect);
        rect.layoutYProperty().bind(Bindings.createDoubleBinding(
            () -> {
                double height = sp.getLayoutBounds().getHeight();
                final double visibleAmout = scrollBar.getVisibleAmount();
                final double max = scrollBar.getMax();
                final double min = scrollBar.getMin();
                final double pos = position.get();
                final double delta = max - min;
                height *= 1 - visibleAmout / delta;
                return height * (pos - min) / delta;
            },
            position,
            sp.layoutBoundsProperty(),
            scrollBar.visibleAmountProperty(),
            scrollBar.minProperty(),
            scrollBar.maxProperty()
        ));
    }

    /**
     * Returns position.
     * @return The position.
     */
    public double getPosition() {
        return this.position.get();
    }

    /**
     * Uses position for .
     * @param value The position to set.
     */
    public void setPosition(final double value) {
        this.position.set(value);
    }

    /**
     * Position property double property.
     * @return The double property.
     */
    public DoubleProperty positionProperty() {
        return this.position;
    }

    /**
     * Detach.
     */
    public void detach() {
        final StackPane parent = (StackPane) rect.getParent();
        if (parent != null) {
            parent.getChildren().remove(rect);
            rect.layoutYProperty().unbind();
            rect.widthProperty().unbind();
        }
    }

    /**
     * Use red style.
     */
    public void setRed() {
        this.rect.setFill(
            Color.RED.deriveColor(0, 1, 1, 1)
        );
    }

    /**
     * Use green style.
     */
    public void setGreen() {
        this.rect.setFill(
            Color.GREEN.deriveColor(0, 1, 1, 0.2)
        );
    }

    /**
     * Use orange style.
     */
    public void setOrange() {
        this.rect.setFill(
            Color.ORANGE
        );
        this.rect.setStroke(Color.WHITE);
    }

    /**
     * Uses hyper style.
     */
    public void setHyper() {
        this.rect.setFill(
            Color.WHITE.deriveColor(0, 1, 1, 0.0)
        );
        this.rect.setStroke(Color.WHITE);
        this.rect.setFill(Color.WHITE);
    }

    /**
     * Use hidden style.
     */
    public void setHidden() {
        this.rect.setFill(
            Color.GREEN.deriveColor(0, 1, 1, 0.0)
        );
    }

    /**
     * Uses height for .
     * @param height The height to set.
     */
    public void setHeight(final double height) {
        this.rect.setHeight(height);
    }

}
