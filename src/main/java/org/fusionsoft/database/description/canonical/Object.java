package org.fusionsoft.database.description.canonical;

import org.fusionsoft.database.TreeNode;

public interface Object {
    String name();
    String summary();
    Iterable<TreeNode> dbmsSpecificNodes();
}
