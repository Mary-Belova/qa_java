package com.example;

import java.util.List;

public class Cat {

    private final Predator predator; // Зависимость от интерфейса Predator

    public Cat(Predator predator) {
        this.predator = predator; // Теперь мы принимаем любой объект, который является Predator
    }

    public String getSound() {
        return "Мяу";
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
}