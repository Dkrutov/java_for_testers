package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateArea(){
        Triangle result = new Triangle(2.7,3.7, 4.7);
        Assertions.assertEquals(4.99 , result.area());
    }

    @Test
    void canCalculatePerimeter(){
        Triangle result = new Triangle(2.333,3.333, 4.555);
        Assertions.assertEquals(10.22,result.perimeter());
    }

    @Test
    void cannotCreateTriangleWithNegativeSideA() {
        try {
            new Triangle(-3.0, 3.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new Triangle(3.0, -3.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new Triangle(3.0, 3.0, -3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithSideIncorrectRatioC() {
        try {
            new Triangle(2.0, 2.0, 25.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithSideIncorrectRatioB() {
        try {
            new Triangle(2.0, 25.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithSideIncorrectRatioA() {
        try {
            new Triangle(25.0, 2.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
