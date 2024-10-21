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
            new Triangle(-3.0, 4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new Triangle(3.0, -4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new Triangle(3.0, 4.0, -5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithSideIncorrectRatioC() {
        try {
            new Triangle(3.0, 4.0, 8.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithSideIncorrectRatioB() {
        try {
            new Triangle(3.4, 8.1, 4.6);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void cannotCreateTriangleWithSideIncorrectRatioA() {
        try {
            new Triangle(8.0, 3.4, 4.5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
    @Test
    void testEqualityTrue1() {
        Triangle t1 = new Triangle(3.3,4.4,5.5);
        Triangle t2 = new Triangle(3.3,4.4,5.5);
        Assertions.assertTrue(t1.equals(t2));
    }
    @Test
    void testEqualityTrue2() {
        Triangle t1 = new Triangle(3.3,4.4,5.5);
        Triangle t2 = new Triangle(4.4,5.5,3.3);
        Assertions.assertTrue(t1.equals(t2));
    }

    @Test
    void testEqualityTrue3() {
        Triangle t1 = new Triangle(3.3,4.4,5.5);
        Triangle t2 = new Triangle(3.3,5.5,4.4);
        Assertions.assertTrue(t1.equals(t2));
    }

    @Test
    void testEqualityFalse() {
        Triangle t1 = new Triangle(3.3,4.4,5.5);
        Triangle t2 = new Triangle(3.3,4.4,5.6);
        Assertions.assertFalse(t1.equals(t2));
    }

}
