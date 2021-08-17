package org.fusionsoft.database;

public interface RestoreParams {
    String someProp();
    RestoreParams Default = new RestoreParams() {
        @Override
        public String someProp() {
            return "someValue";
        }
    };
}
