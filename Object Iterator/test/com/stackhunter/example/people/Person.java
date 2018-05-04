package com.stackhunter.example.people;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {

    private String name;
    private List<Person> friends;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public Person setFriends(List<Person> friends) {
        this.friends = friends;
        return this;
    }

    public Person addFriends(Person ... friends) {
        if (friends == null) {
            return this;
        }
        
        if (this.friends == null) {
            this.friends = new ArrayList<>(friends.length);
        }
        
        this.friends.addAll(Arrays.asList(friends));
        return this;
    }

}
