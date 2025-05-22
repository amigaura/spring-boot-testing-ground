package com.testing.ground.misc;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateObjectRemover {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person(1, "Alice"),
                new Person(2, "Bob"),
                new Person(3, "Charlie"),
                new Person(1, "Alice"),    // duplicate
                new Person(2, "Bob")       // duplicate
        );

        System.out.println("Original list:");
        people.forEach(System.out::println);

        List<Person> uniquePeople = removeDuplicates(people);

        System.out.println("\nSanitized list (unique objects):");
        uniquePeople.forEach(System.out::println);
    }

    // check mandatory field values in an array of objects
    public static <T> boolean areMandatoryFieldsValid(T[] fields) {
        for (T field : fields) {
            if (!isMandatoryFieldValid(field)) {
                return false;
            }
        }
        return true;
    }

    // Mandatory field value check
    public static <T> boolean isMandatoryFieldValid(T field) {
        if (field == null) return false;
        if (field instanceof String) {
            return !((String) field).trim().isEmpty();
        }
        return true; // For non-String objects, just check for null
    }

    // Determine duplicate objects based on equals() and hashCode()
    public List<Person> determineDuplicates(List<Person> list) {
        Set<Person> uniqueSet = new HashSet<>();
        List<Person> duplicates = new ArrayList<>();

        for (Person person : list) {
            if (!uniqueSet.add(person)) {
                duplicates.add(person);
            }
        }
        return duplicates;
    }

    // Alternative method using HashSet
    public static List<Person> removeDuplicates(List<Person> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // Override equals and hashCode for proper comparison in Set
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "Person{id=" + id + ", name='" + name + "'}";
        }
    }
}

