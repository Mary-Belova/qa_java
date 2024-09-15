package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LionTest {
    private Feline felineMock;
    private Lion lionMale;
    private Lion lionFemale;

    @Before
    public void setUp() throws Exception {
        felineMock = Mockito.mock(Feline.class);
        lionMale = new Lion("Самец", felineMock);
        lionFemale = new Lion("Самка", felineMock);
    }

    @Test
    public void testLionHasMane() {
        assertTrue("Лев самец должен иметь гриву", lionMale.doesHaveMane());
        assertFalse("Леопард самка не должна иметь гривы", lionFemale.doesHaveMane());
    }

    @Test
    public void testGetKittens() throws Exception {
        // Подготавливаем данные для теста
        when(felineMock.getKittens()).thenReturn(3);

        int kittensCount = lionMale.getKittens();
        assertEquals("Лев должен получить правильное количество котят", 3, kittensCount);
        verify(felineMock, times(1)).getKittens(); // Проверяем, что метод getKittens был вызван один раз
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = Arrays.asList("мясо1", "мясо2");
        when(felineMock.getFood("Хищник")).thenReturn(expectedFood);

        List<String> food = lionMale.getFood();
        assertNotNull("Список еды не должен быть null", food);
        assertEquals("Лев должен получать правильную еду от Feline", expectedFood, food);
        verify(felineMock, times(1)).getFood("Хищник"); // Проверяем, что метод getFood был вызван один раз
    }

    @Test(expected = Exception.class)
    public void testInvalidSexThrowsException() throws Exception {
        new Lion("Неправильный пол", felineMock); // Проверяем, что будет выброшено исключение
    }

    @Test(expected = Exception.class)
    public void testGetFoodThrowsException() throws Exception {
        when(felineMock.getFood("Хищник")).thenThrow(new Exception("Ошибка при получении еды"));

        lionMale.getFood(); // Ожидаем, что здесь будет выброшено исключение
    }
}