package org.xerris.core.testDomain;

import java.time.LocalDate;

public class Person {
    private String name;
    private int age;
    private LocalDate birthDate;
    private Gender gender;

    public Person() { } //required for serialization

    public Person(String name, int age, Gender gender) {
        this(name, age, gender, LocalDate.now());
    }

    public Person(String name, int age, Gender gender, LocalDate birthDate) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public LocalDate getBirthDate() { return this.birthDate; }
    public Gender getGender() { return this.gender; }

    public void birthday() { age += 1;}

    public Person happyBirthday() {
        this.age +=1;
        return this;
    }
}
