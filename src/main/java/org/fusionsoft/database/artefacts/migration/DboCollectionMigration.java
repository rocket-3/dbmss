package org.fusionsoft.database.artefacts.migration;

import java.util.Collection;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.And;
import org.fusionsoft.database.artefacts.Condition;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DiffPair;
import org.fusionsoft.database.artefacts.Migration;
import org.fusionsoft.database.artefacts.RestoreParams;
import org.fusionsoft.database.artefacts.Server;
import org.fusionsoft.database.artefacts.condition.CndDbObjectsHaveSameDBMSSignature;
import org.fusionsoft.database.artefacts.diff.DboDiffPairsOf;

public class DboCollectionMigration implements Migration {
    private final Server server;
    private final RestoreParams restoreParams;
    private final Condition condition;
    private final Iterable<Migration> migrations;

    public DboCollectionMigration(Server server, Collection<DbObject> persistentDBOs, Collection<DbObject> targetDBOs, RestoreParams restoreParams) {
        this.server = server;
        final Collection<DiffPair<DbObject>> diffPairs = new DboDiffPairsOf(
            persistentDBOs,
            targetDBOs
        );
        this.restoreParams = restoreParams;
        this.condition = new CndDbObjectsHaveSameDBMSSignature(
            server.dbmsSignature(),
            targetDBOs
        );
        this.migrations = new IterableOf<Migration>(
            new DboCollectionPreMigration(persistentDBOs, server),   
            new DboCollectionNoConstraintsMigration(
                new DboDiffPairsOf(
                    persistentDBOs, 
                    targetDBOs
                ), 
                restoreParams, 
                server
            ),
            new DboCollectionPostMigration(targetDBOs, server)
        );
    }

    @Override
    public boolean validate() throws Exception {
        return new And(
            this.condition,
            new And(
                Migration::validate,
                migrations
            )
        ).value();
    }

    @Override
    public void perform() throws Exception {
        for (final Migration migration : this.migrations) {
            migration.perform();
        }
    }
}
