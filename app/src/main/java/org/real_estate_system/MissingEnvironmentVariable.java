package org.real_estate_system;

public class MissingEnvironmentVariable extends RuntimeException {
    public MissingEnvironmentVariable(String error) {
        super(error);
    }
}
