package com.jensjansson.pagedtable.example;

/**
 * @author Ondrej Kvasnovsky
 */
public class UserExample {

    private String name;

    public UserExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserExample{" +
                "name='" + name + '\'' +
                '}';
    }
}
