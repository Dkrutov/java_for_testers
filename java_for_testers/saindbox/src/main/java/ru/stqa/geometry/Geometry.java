package ru.stqa.geometry;

public class Geometry {
    public static void main(String[] args) {
        Triangle Tri1 = new Triangle(2.0,3.0,4.0);
        System.out.printf("Периметр треугольника = %.2f" , Tri1.perimeter());
        System.out.printf( "\nПлощадь треугольника = %.2f" , Tri1.area());
    }
}
