/*import java.io.*;
import java.util.Scanner;

class Country {
    String name;
    String capital;
    long population;

    public Country(String name, String capital, long population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    @Override
    public String toString() {
        return name + "," + capital + "," + population;
    }
}

public class SimpleCountryInfoSystem {
    static Country[] countries = new Country[100]; // 최대 100개 국가 저장 가능
    static int countryCount = 0; // 현재 저장된 국가 수

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== 국가 정보 시스템 ===");
            System.out.println("1. 국가 추가");
            System.out.println("2. 국가 검색");
            System.out.println("3. 모든 국가 출력");
            System.out.println("4. 파일로 저장");
            System.out.println("5. 파일에서 불러오기");
            System.out.println("6. 종료");
            System.out.print("메뉴를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    addCountry(scanner);
                    break;
                case 2:
                    searchCountry(scanner);
                    break;
                case 3:
                    displayAllCountries();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 5:
                    loadFromFile();
                    break;
                case 6:
                    running = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }

        scanner.close();
    }

    public static void addCountry(Scanner scanner) {
        if (countryCount >= countries.length) {
            System.out.println("더 이상 국가를 추가할 수 없습니다!");
            return;
        }

        System.out.print("국가 이름: ");
        String name = scanner.nextLine();
        System.out.print("수도: ");
        String capital = scanner.nextLine();
        System.out.print("인구: ");
        long population = scanner.nextLong();
        scanner.nextLine(); // 버퍼 비우기

        countries[countryCount] = new Country(name, capital, population);
        countryCount++;
        System.out.println("국가가 추가되었습니다.");
    }

    public static void searchCountry(Scanner scanner) {
        System.out.print("검색할 국가 이름: ");
        String name = scanner.nextLine();

        for (int i = 0; i < countryCount; i++) {
            if (countries[i].name.equalsIgnoreCase(name)) {
                System.out.println("국가 정보: " + countries[i].name + ", 수도: " + countries[i].capital + ", 인구: " + countries[i].population);
                return;
            }
        }

        System.out.println("해당 국가를 찾을 수 없습니다.");
    }

    public static void displayAllCountries() {
        if (countryCount == 0) {
            System.out.println("저장된 국가 정보가 없습니다.");
            return;
        }

        System.out.println("\n--- 저장된 국가 목록 ---");
        for (int i = 0; i < countryCount; i++) {
            System.out.println("국가: " + countries[i].name + ", 수도: " + countries[i].capital + ", 인구: " + countries[i].population);
        }
    }

    public static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("countries.txt"))) {
            for (int i = 0; i < countryCount; i++) {
                writer.write(countries[i].toString());
                writer.newLine();
            }
            System.out.println("데이터가 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        }
    }

    public static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("countries.txt"))) {
            countryCount = 0; // 기존 데이터 초기화
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String capital = parts[1];
                long population = Long.parseLong(parts[2]);
                countries[countryCount] = new Country(name, capital, population);
                countryCount++;
            }
            System.out.println("파일에서 데이터를 불러왔습니다.");
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류가 발생했습니다.");
        }
    }
}
*/
