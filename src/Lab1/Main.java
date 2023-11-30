package Lab1;
/*Вычислить длину гипотенузы в прямоугольном треугольнике.
10.1. Дан одномерный массив Xn. Найти количество элементов массива, значение которых
больше его индекса.
10.2. Даны одномерные массивы Хк и Yn. Сформировать массив Zn, состоящий из четных
элементов массива Хк, значение которых меньше индекса и элементов массива Yk с
нечетными индексами.*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);
    private static int n;
    private static int k;
    private static final Random r = new Random();

    public static void main(String[] args) {
        System.out.print("Катет 1 = ");
        double katet1 = scan.nextDouble();
        System.out.print("Катет 2 = ");
        double katet2 = scan.nextDouble();
        System.out.printf("Гипотенуза треугольника с катетами равными " + katet1 + " и " + katet2 + " равна %1.2f", part1(katet1, katet2));
        System.out.println();
        System.out.print("n = ");
        n = scan.nextInt();
        System.out.print("k = ");
        k = scan.nextInt();
        double[] X = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("X[" + i + "] = ");
            X[i] = scan.nextDouble();
        }
        System.out.println("Число элементов массива X[n], которые больше своего индекса - " + lab11(n, X));
        X = Arrays.copyOf(X, k);
        double[] Z = new double[k];
        for (int i = 0; i < k; i++) {
            System.out.print("X[" + i + "] = ");
            X[i] = scan.nextDouble();
        }
        Z = (Z instanceof double[]) ? (double[]) lab12(X, n, k, Z) : null;
        if (Z != null) {
            for (int i = 0; i < k; i++) {
                System.out.printf("Z(%d)=%1.2f\n", i, Z[i]);
            }
        } else System.out.println("Массив Z[m] остался пустым");
    }

    public static double part1(double katet1, double katet2){
        return Math.sqrt(Math.pow(katet1, 2) + Math.pow(katet2, 2));
    }

    public static int lab11(int n, double[] X) {
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (X[i] > i) {
                counter++;
            }
        }
        return counter;
    }

    public static Object lab12(double[] X, int n, int k, double[] Z) {
        double[] y = r.doubles(-100, 100).limit(n).toArray();
        boolean flag = false;
        for (int i = 0; i < k; i++) {
            if (X[i] < i && i % 2 == 0) {
                flag = true;
                Z[i] = X[i];
                Z = Arrays.copyOf(Z, k);
            }
        }
        for (int i = 0; i < n; i++) {
            if (i % 2 == 1) {
                flag = true;
                Z[i] = y[i];
                Z = Arrays.copyOf(Z, k);
            }
        }
        if (flag) {
            return Z;
        } else {
            return "Массив Z пуст";
        }
    }
}