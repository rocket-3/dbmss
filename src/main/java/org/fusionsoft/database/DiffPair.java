package org.fusionsoft.database;

public interface DiffPair<T> {
    T previousValue();
    T currentValue();
}
