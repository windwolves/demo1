package com.leading.demo1.token;

public class AccessToken {
    private String token;
    private long accessTimeStampUTC;
    public String getToken() {
        return token;
    }

    public AccessToken setToken(String token) {
        this.token = token;
        return this;
    }

    public long getAccessTimeStampUTC() {
        return accessTimeStampUTC;
    }

    public AccessToken setAccessTimeStampUTC(long timeStampUTC) {
        this.accessTimeStampUTC = timeStampUTC;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessToken that = (AccessToken) o;

        return token != null ? token.equals(that.token) : that.token == null;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result;
        return result;
    }
}
