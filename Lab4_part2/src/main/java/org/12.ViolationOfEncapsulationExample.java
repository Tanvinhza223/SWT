package org;

import java.util.logging.Logger;

class User {

    private static final Logger logger =
            Logger.getLogger(User.class.getName());

    private String name;
    private int age;

    public void display() {
        logger.info("Name: " + name + ", Age: " + age);
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
