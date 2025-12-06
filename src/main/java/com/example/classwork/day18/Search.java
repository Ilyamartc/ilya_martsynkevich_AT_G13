package com.example.classwork.day18;

public class Search {

    private String user;
    private boolean strict;

    public Search() {
    }

    public Search(String user, boolean strict) {
        this.user = user;
        this.strict = strict;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    @Override
    public String toString() {
        return "Search{" +
                "user='" + user + '\'' +
                ", strict=" + strict +
                '}';
    }
}
