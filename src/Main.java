import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        чтение входных данных из файла
        Scanner scan = new Scanner(new File("input.txt"));
        int persons = scan.nextInt();
        int kgSum = scan.nextInt();
        int[] kgArr = new int[persons];
        for (int i = 0; i < persons && scan.hasNext(); i++) {
            kgArr[i] = scan.nextInt();
        }

//        сортируем массив
//        Arrays.sort(kgArr); - можно воспользоваться готовым методом
        bubbleSort(kgArr);

//        подсчитываем минимальное количество лодок
        int res = countingMin(kgArr, kgSum);

//        записываем результат в файл
        writing(res);
    }


    private static int[] bubbleSort(int[] kgArr) {
        for (int j = kgArr.length - 1; j >= 0; j--) {
            for (int i = 0; i < j; i++) {
                int a = kgArr[i];
                if (a > kgArr[i + 1]) {
                    kgArr[i] = kgArr[i + 1];
                    kgArr[i + 1] = a;
                }
            }
        }
        return kgArr;
    }

    private static int countingMin(int[] kgArr, int kgSum) {
        int kol = 0;
        int j = kgArr.length - 1;

        //        начинаем суммировать с разных концов массива
        for (int i = 0; i <= j - i; i++) {
            if (kgArr[i] + kgArr[j - i] > kgSum) {
                j--;
                i--;
            }
            kol++;
        }
        return kol;
    }

    private static void writing(int res) {
        try (FileOutputStream ff = new FileOutputStream("output.txt")) {
            String result = Integer.toString(res);
            ff.write(result.getBytes());
            System.out.println("output.txt created");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
