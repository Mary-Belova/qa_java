package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CatTest {
    private Predator predatorMock;
    private Cat cat;

    @Before
    public void setUp() {
        predatorMock = Mockito.mock(Predator.class);
        cat = new Cat(predatorMock);
    }

    @Test
    public void testGetSound() {
        String sound = cat.getSound();
        assertEquals("Кошка должна издавать звук 'Мяу'", "Мяу", sound);
    }

    @Test
    public void testGetFood() throws Exception {
        // Подготавливаем данные для теста
        List<String> expectedFood = Arrays.asList("мясо1", "мясо2");
        when(predatorMock.eatMeat()).thenReturn(expectedFood);

        List<String> food = cat.getFood();
        assertNotNull("Список еды не должен быть null", food);
        assertEquals("Кошка должна получать правильную еду от хищника", expectedFood, food);
        verify(predatorMock, times(1)).eatMeat(); // Проверяем, что метод eatMeat был вызван один раз
    }

    @Test(expected = Exception.class)
    public void testGetFoodThrowsException() throws Exception {
        // Настраиваем поведение мок-объекта на выброс исключения
        when(predatorMock.eatMeat()).thenThrow(new Exception("Ошибка при получении еды"));

        cat.getFood(); // Ожидаем, что здесь будет выброшено исключение
    }
}