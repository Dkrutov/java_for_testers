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

}
