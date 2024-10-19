public class Hello {
    public static void main(String[] args) {
        try {
            int z = calculate();
            System.out.println(z);
            System.out.println("Hello world");
        } catch (ArithmeticException exception) {
            exception.printStackTrace();
        }


    }

    private static int calculate() {
        int x = 1;
        int y = 0;
        int z = getZ2(x, y);
        return z;
    }

    private static int getZ2(int x, int y) {
        int z = x / y;
        return z;
    }
}
