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
    private final Collection<DbObject> persistentDbObjectCollection;
    private final Collection<DbObject> targetDbObjectCollection;
    private final Collection<DiffPair<DbObject>> diffPairs;
    private final RestoreParams restoreParams;
    private final Condition condition;
    private final Iterable<Migration> migrations;

    public DboCollectionMigration(Server server, Collection<DbObject> persistentDbObjectCollection, Collection<DbObject> targetDbObjectCollection, RestoreParams restoreParams) {
        this.server = server;
        this.persistentDbObjectCollection = persistentDbObjectCollection;
        this.targetDbObjectCollection = targetDbObjectCollection;
        this.diffPairs = new DboDiffPairsOf(
            persistentDbObjectCollection,
            targetDbObjectCollection
        );
        this.restoreParams = restoreParams;
        this.condition = new CndDbObjectsHaveSameDBMSSignature(
            server.dbmsSignature(),
            targetDbObjectCollection
        );
        this.migrations = new IterableOf<Migration>(
            new DboCollectionPreMigration(),   
            new DboCollectionNoConstraintsMigration(),
            new DboCollectionPostMigration()
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
