package ru.stqa.geometry;

public class Triangle {

        private double a;
        private double b;
        private double c;

        public Triangle (double a, double b, double c){
                this.a = a;
                this.b = b;
                this.c = c;
                if (a < 0 || c < 0  || b < 0  ) {
                        throw new IllegalArgumentException("Triangle side should be non-negative");
                }

                if (a + b < c || a + c < b  || c + b < a  ) {
                        throw new IllegalArgumentException("The ratio of the sides of the triangle is incorrect");
                }
        }

        public double area(){
                return (Math.round(Math.sqrt(perimeter()/2 * (perimeter()/2- this.a) * (perimeter()/2- this.b) * (perimeter()/2- this.c)) * 100.0)/100.0);
        }

        public double perimeter(){
                return Math.round((this.a+this.b+this.c)* 100.0) / 100.0;
        }

}
