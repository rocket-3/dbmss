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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.fusionsoft.database.application.MigrationSqlFileName;

/**
 * The JavaFX application class to verify migration sql using gui.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public final class Verify extends Application {

    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/verify.fxml"));
        final Parent root = loader.load();
        final Path migration = Paths.get(getParameters().getRaw().get(0));
        ((VerifyControls) loader.getController()).load(migration);
        final Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/verify.css").toExternalForm());
        stage.setTitle(new MigrationSqlFileName().asString() + " fixing...");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main.
     * @param args The args.
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
