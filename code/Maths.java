public class Maths {

    // http://en.wikipedia.org/wiki/Fast_inverse_square_root

    public static double invSqrt(double x) {
        final double xhalf = 0.5d*x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6eb50c7b537a9L - (i >> 1);
        x = Double.longBitsToDouble(i);
        x *= (1.5d - xhalf*x*x); // pass 1
        x *= (1.5d - xhalf*x*x); // pass 2
        x *= (1.5d - xhalf*x*x); // pass 3
        x *= (1.5d - xhalf*x*x); // pass 4
        return x;
    }

    public static final double sqrt(final double d) {
        return d*invSqrt(d);
    }
}

