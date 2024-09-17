package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class FelineTest {

    private Feline feline;

    @Parameterized.Parameter
    public String animalKind;

    @Parameterized.Parameter(1)
    public List<String> expectedFood;

    @Before
    public void setUp() {
        feline = new Feline();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Хищник", Arrays.asList("Животные", "Птицы", "Рыба")}, // ожидаемая пища для хищника
                {"Травоядное", Arrays.asList("Трава", "Различные растения")} // ожидаемая пища для травоядного
        });
    }

    @Test
    public void testEatMeat() throws Exception {
        if (animalKind.equals("Хищник")) {
            List<String> actualFood = feline.eatMeat();
            assertEquals("Feline должен получать правильный рацион еды", expectedFood, actualFood);
        } else {
            // Если это травоядное, просто пропускаем, так как метод eatMeat() неподходящий
            // Можно использовать fail(), чтобы пометить, что этот тест не должен вызываться
            fail("Метод eatMeat() не применим к травоядным");
        }
    }

    @Test
    public void testGetFamily() {
        String expectedFamily = "Кошачьи";
        String actualFamily = feline.getFamily();
        assertEquals("Feline должен принадлежать к семейству Кошачьи", expectedFamily, actualFamily);
    }

    @Test
    public void testGetKittens() {
        int expectedKittens = 1;
        int actualKittens = feline.getKittens();
        assertEquals("Feline должен производить 1 котенка по умолчанию", expectedKittens, actualKittens);
    }

    @Test
    public void testGetKittensWithCount() {
        int expectedKittens = 3;
        int actualKittens = feline.getKittens(3);
        assertEquals("Feline должен производить 3 котенка", expectedKittens, actualKittens);
    }

    @Test
    public void testPredatorPolymorphism() throws Exception {
        Predator predator = new Feline();
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        List<String> actualFood = predator.eatMeat();
        assertEquals("Predator должен правильно возвращать рацион", expectedFood, actualFood);
    }
}