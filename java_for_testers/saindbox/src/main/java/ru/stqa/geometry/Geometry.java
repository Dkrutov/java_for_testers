package ru.stqa.geometry;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
//        System.out.printf("Периметр треугольника = %.2f" , Tri1.perimeter());
//        System.out.printf( "\nПлощадь треугольника = %.2f" , Tri1.area());
//        Triangle.printTrianglePerimeter(new Triangle(2,3,4));
//        var triangls = List.of(new Triangle(2,3,4),new Triangle(3,4,5),new Triangle(5,6,7));
        Supplier<Triangle> randomTriangl = () -> new Triangle(new Random().nextDouble(5,10.0), new Random().nextDouble(10,20.0), new Random().nextDouble(20,30.0));
        var triangls = Stream.generate(randomTriangl).limit(5);
//        for (Triangle triangl : triangls) {
//            Triangle.printTriangleArea(triangl);
//        }
//        triangls.forEach(Triangle::printTriangleArea);
        Consumer<Triangle> print = triangl -> {
            Triangle.printTrianglePerimeter(triangl);
            Triangle.printTriangleArea(triangl);
        };
        triangls.forEach(print);

    }
}
