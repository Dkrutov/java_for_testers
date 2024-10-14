package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateArea(){
        Triangle result = new Triangle(2.5,3.5, 4.5);
        double roundResult =  Math.round (result.area() * 100.0) / 100.0;
        Assertions.assertEquals(4.35 , roundResult);
    }

    @Test
    void canCalculatePerimeter(){
        Triangle result = new Triangle(2.3,3.3, 4.4);
        Assertions.assertEquals(10.0,result.perimeter());
    }

}
