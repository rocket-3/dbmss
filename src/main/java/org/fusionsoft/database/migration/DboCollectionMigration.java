package org.fusionsoft.database.migration;

import java.util.Collection;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.And;
import org.fusionsoft.database.Condition;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.condition.EachDboHasDbmsSignature;

public class DboCollectionMigration implements Migration {
    private final Iterable<Condition> conditions;
    private final Iterable<Migration> migrations;

    private DboCollectionMigration(Iterable<Condition> conditions, Iterable<Migration> migrations) {
        this.conditions = conditions;
        this.migrations = migrations;
    }
    
    public DboCollectionMigration(
        Server server, 
        Collection<DbObject> persistentDBOs, 
        Collection<DbObject> targetDBOs, 
        RestoreParams restoreParams
    ) {
        this(
            new IterableOf<Condition>(
                new EachDboHasDbmsSignature(
                    server.dbmsSignature(),
                    targetDBOs
                )
            ),
            new IterableOf<Migration>(
                new DboCollectionPreMigration(persistentDBOs, server),   
                new DboCollectionNoConstraintsMigration(
                    persistentDBOs, 
                    targetDBOs,
                    restoreParams, 
                    server
                ),
                new DboCollectionPostMigration(targetDBOs, server)
            )
        );
    }


    
    @Override
    public boolean validate() throws Exception {
        return new And(
            new And(this.conditions),
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
