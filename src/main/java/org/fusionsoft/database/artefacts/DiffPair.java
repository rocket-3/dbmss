package org.fusionsoft.database.artefacts;

public interface DiffPair<T> {
    T previousValue();
    T currentValue();
}
