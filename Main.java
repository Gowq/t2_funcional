import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Comparator;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        String input = scanner.nextLine();
//        String[] numbers = input.split(" ");

        int n1 = 1000000, n2 = 10, n3 = 4, n4 = 2;

        try {
//            n1 = Integer.parseInt(numbers[0]);
//            n2 = Integer.parseInt(numbers[1]);
//            n3 = Integer.parseInt(numbers[2]);
//            n4 = Integer.parseInt(numbers[3]);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }

        String[][] data = csvParser("./dados.csv", ",");
        sumActiveIfConfirmedBiggerThanN1(data, n1);
        n2BiggestActiveDeathsOfN3SmallerConfirmeds(data, n2, n3);
    }
    public static String[][] csvParser(String csvFile, String csvSplitBy) {
        String line;
        String[][] data = new String[0][];

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            List<String[]> dataList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] row = line.split(csvSplitBy);
                dataList.add(row);
            }

            data = dataList.toArray(new String[dataList.size()][]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    // A soma de "Active" de todos os países em que "Confirmed" é maior o igual que n1.
    public static void sumActiveIfConfirmedBiggerThanN1(String[][] data, int n1) {
        int sumActive = 0;
        for (int i = 1; i < data.length; i++) {
            if (Integer.parseInt(data[i][1]) > n1) {
                sumActive += Integer.parseInt(data[i][4]);
            }
        }
        System.out.println(sumActive);
    }

    // Dentre os n2 países com maiores valores de "Active", o "Deaths" dos n3 países com menores valores de "Confirmed".
    public static void n2BiggestActiveDeathsOfN3SmallerConfirmeds(String[][] data, int n2, int n3) {
        int[] active = new int[data.length];
        int[] deaths = new int[data.length];
        int[] confirmed = new int[data.length];

        for (int i = 1; i < data.length; i++) {
            active[i] = Integer.parseInt(data[i][4]);
            deaths[i] = Integer.parseInt(data[i][2]);
            confirmed[i] = Integer.parseInt(data[i][1]);
        }

        Arrays.sort(active);
        Arrays.sort(deaths, Comparator.reverseOrder());

        int sumDeaths = 0;
        for (int i = 1; i <= n2; i++) {
            for (int j = 1; j <= n3; j++) {
                if (active[i] == deaths[j]) {
                    sumDeaths += deaths[j];
                }
            }
        }
        System.out.println(sumDeaths);
    }
}