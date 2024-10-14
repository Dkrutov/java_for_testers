package ru.stqa.geometry;

public class Triangle {

        private double a;
        private double b;
        private double c;

        public Triangle (double a, double b, double c){
                this.a = a;
                this.b = b;
                this.c = c;
        }

        public double area(){
            return Math.sqrt(perimeter()/2 * (perimeter()/2- this.a) * (perimeter()/2- this.b) * (perimeter()/2- this.c));
        }

        public double perimeter(){
                return this.a+this.b+this.c;
        }

}
