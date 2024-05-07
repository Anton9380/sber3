import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<City> cities = readCitiesFromFile("cities.txt");
        City[] citiesArray = cities.toArray(new City[0]);

        long maxPopulation = -1;
        int indexOfMaxPopulation = -1;

        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getPopulation() > maxPopulation) {
                maxPopulation = citiesArray[i].getPopulation();
                indexOfMaxPopulation = i;
            }
        }

        if (indexOfMaxPopulation != -1) {
            System.out.println("[" + indexOfMaxPopulation + "] = " + maxPopulation);
        } else {
            System.out.println("Список городов пуст.");
        }
    }

    private static List<City> readCitiesFromFile(String filename) {
        List<City> cities = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                // Проверяем, что количество частей достаточно для создания объекта City
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String region = parts[1].trim();
                    String district = parts[2].trim();
                    long population = Long.parseLong(parts[3].trim());
                    String foundation = parts[4].trim();

                    City city = new City(name, region, district, population, foundation);
                    cities.add(city);
                } else {
                    System.out.println("Некорректный формат строки: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
            e.printStackTrace();
        }
        return cities;
    }
}
