package org.fusionsoft.database;

public interface TreeNode {
    String name();
    String stringValue();
    Iterable<TreeNode> collectionValue();
}
