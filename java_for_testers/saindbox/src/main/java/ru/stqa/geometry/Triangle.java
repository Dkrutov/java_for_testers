package ru.stqa.geometry;

public record Triangle (double a, double b, double c) {

        public Triangle {

                if (a < 0 || c < 0  || b < 0  ) {
                        throw new IllegalArgumentException("Triangle side should be non-negative");
                }

//                if (a + b < c || a + c < b  || c + b < a  ) {
//                        throw new IllegalArgumentException("The ratio of the sides of the triangle is incorrect");
//                }
        }

        public double area(){
                return (Math.round(Math.sqrt(perimeter()/2 * (perimeter()/2- this.a) * (perimeter()/2- this.b) * (perimeter()/2- this.c)) * 100.0)/100.0);
        }

        public double perimeter(){
                return Math.round((this.a+this.b+this.c)* 100.0) / 100.0;
        }

        public static void printTrianglePerimeter(Triangle t){
        System.out.printf("\nПериметр треугольника = %.2f" , t.perimeter());
        }

        public static void printTriangleArea(Triangle t){
                System.out.printf( "\nПлощадь треугольника = %.2f" , t.area());
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Triangle triangle = (Triangle) o;
                return
                        (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                        || (Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.a) == 0)
                        || (Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0)
                        || (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.b) == 0)
                        || (Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.a) == 0)
                        || (Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.c) == 0);
        }

        @Override
        public int hashCode() {
                return 1;
        }
}
