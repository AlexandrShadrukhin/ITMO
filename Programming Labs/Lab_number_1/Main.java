import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        int[] h = new int[13];
        int a = 18;
        for (int i = 0; i < h.length; i++) {
            h[i] = a;
            a--;
        }
        double[] x = new double[13];
        double x1 = -5.0, x2 = 7.0, m;
        for (int i = 0; i < h.length; i++) {
            m = x1 + (Math.random() * ((x2 - x1) + 1));
            x[i] = m;
        }
        Matesha(x, h);
    }
    public static void Matesha(double[] x, int[] h){
        double[][] k = new double[13][13];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                double x3 = x[j];
                if (h[i] == 8) {
                    k[i][j] = atan(Math.pow(E, Math.cbrt(Math.pow(-cos(x3), 2))));
                } else if (h[i] == 6 || h[i] == 7 || h[i] == 11 || h[i] == 12 || h[i] == 17 || h[i] == 18) {
                    k[i][j] = Math.pow(Math.pow(E, (x3)) * (sin(x3) + 0.25), 3) - 1 / Math.pow(asin(((x3) + 1) / 12), (Math.pow(((x3) / 3) / 4, (x3)) / (0.5 - atan(((x3) + 1) / 12))));
                } else {
                    k[i][j] = Math.log(Math.pow(2 * (5 + Math.pow(Math.pow(E, (x3)) * (3 + Math.abs(x3)), cos(Math.pow(E, (x3))))), Math.pow(atan(cos(x3)), sin(Math.pow(2 * (x3), (x3))))));
                }
            }
        }
        Res(k);
    }
    public static void Res(double[][] k){
        for(int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.printf("%15.2f", k[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}