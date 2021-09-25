package org.xerris.core;

import org.assertj.core.internal.bytebuddy.asm.Advice;

import java.time.LocalDate;

public class Person {
    private String name;
    private int age;
    private LocalDate birthDate;

    public Person(String name, int age) {
        this(name, age, LocalDate.now());
    }

    public Person(String name, int age, LocalDate birthDate) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public LocalDate getBirthDate() { return this.birthDate; }

    public void birthday() { age += 1;}

    public Person happyBirthday() {
        this.age +=1;
        return this;
    }
}
