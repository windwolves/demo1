package com.leading.demo1.commons;
/**
 * Static Class that returns System Environment. Introduced to allow mocking for tests.
 */
public class Environment {
    public static String getEnvironmentServices(String service) {
        return System.getenv(service);
    }

}
