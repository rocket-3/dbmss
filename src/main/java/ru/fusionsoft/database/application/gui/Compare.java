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
import org.cactoos.Text;
import org.cactoos.iterable.Sticky;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.dbdreadable.DbdReadableConstructedOfSnapshotObjects;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfDirectory;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdInfoMappingOfDbdReadable;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadableServer;

/**
 * The application command to compare current DBD to DBD taken from specific server
 *  with gui interface.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class Compare extends Application {

    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/compare.fxml"));
        final Parent root = loader.load();
        final Path path = Paths.get(getParameters().getRaw().get(0));
        final Text server = new TextOf(getParameters().getRaw().get(1));
        final DbdReadableOfDirectory dbdreadable = new DbdReadableOfDirectory(path);
        ((CompareControls) loader.getController()).load(
            new DbdReadableConstructedOfSnapshotObjects(
                new DbdServerEntry(
                    server,
                    new DbdServerMappingOfDbdReadable(
                        dbdreadable,
                        server
                    )
                ),
                new DbdInfoMappingOfDbdReadable(dbdreadable),
                new Sticky<>(
                    new ObjectsOfDbdReadableServer(
                        dbdreadable,
                        server
                    )
                )
            ),
            dbdreadable
        );
        final Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/compare.css").toExternalForm());
        stage.setTitle("Comparing database to DBD...");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The entry point of application.
     * @param args The input arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
