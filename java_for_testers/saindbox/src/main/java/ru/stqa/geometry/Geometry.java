package ru.stqa.geometry;

public class Geometry {
    public static void main(String[] args) {
        Triangle Tri1 = new Triangle(2.1,3.1,4.1);
        System.out.printf("Периметр треугольника = %.2f" , Tri1.perimeter());
        System.out.printf( "\nПлощадь треугольника = %.2f" , Tri1.area());
    }
}
