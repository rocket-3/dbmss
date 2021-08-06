package org.fusionsoft.database.artefacts;

import java.util.Map;

public interface MappingDiff {
    Map<String, String> persistentOnlyProps();
    Map<String, String> targetOnlyProps();
    Map<String, DiffPair<String>> differingProps(String key);
    MappingDiff nestedDiff();
}
