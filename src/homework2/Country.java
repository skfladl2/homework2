package homework2;

import java.io.*;
import java.util.Scanner;

// 인터페이스 정의
interface Displayable {
    void displayInfo();
}

// 부모 클래스
class Country implements Displayable {
    private String name;
    private String capital;
    private long population;

    public Country(String name, String capital, long population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    @Override
    public void displayInfo() {
        System.out.println("나라명: " + name + ", 수도: " + capital + ", 인구 수: " + population);
    }

    @Override
    public String toString() {
        return name + "," + capital + "," + population;
    }
}

// 자식 클래스
class DevelopedCountry extends Country {
    private double gdp;

    public DevelopedCountry(String name, String capital, long population, double gdp) {
        super(name, capital, population);
        this.gdp = gdp;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("GDP: " + gdp + "원");
    }

    @Override
    public String toString() {
        return super.toString() + "," + gdp;
    }
}

// CountryManager 클래스
class CountryManager {
    private static final int MAX_COUNTRIES = 100; // 최대 국가 저장 개수
    private Country[] countries = new Country[MAX_COUNTRIES];
    private int countryCount = 0; // 현재 저장된 국가 수

    // 국가 추가
    public void addCountry(Country country) {
        if (countryCount < MAX_COUNTRIES) {
            countries[countryCount] = country;
            countryCount++;
        } else {
            System.out.println("더 이상 국가를 저장할 수 없습니다!");
        }
    }

    // 국가 검색
    public Country searchCountry(String name) {
        for (int i = 0; i < countryCount; i++) {
            if (countries[i].getName().equalsIgnoreCase(name)) {
                return countries[i];
            }
        }
        return null;
    }

    // 모든 국가 정보 출력
    public void displayAllCountries() {
        if (countryCount == 0) {
            System.out.println("저장된 국가가 없습니다.");
            return;
        }

        for (int i = 0; i < countryCount; i++) {
            countries[i].displayInfo();
        }
    }

    // 파일 저장
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < countryCount; i++) {
                writer.write(countries[i].toString()); // 데이터를 CSV 형식으로 저장
                writer.newLine(); // 줄바꿈
            }
            System.out.println("데이터가 파일에 저장되었습니다: " + filename);
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 파일 불러오기
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    addCountry(new Country(parts[0], parts[1], Long.parseLong(parts[2])));
                } else if (parts.length == 4) {
                    addCountry(new DevelopedCountry(parts[0], parts[1], Long.parseLong(parts[2]), Double.parseDouble(parts[3])));
                }
            }
            System.out.println("파일에서 데이터를 불러왔습니다: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("파일이 존재하지 않습니다. 기본 데이터를 로드합니다.");
            addCountry(new Country("한국", "서울", 51780579));
            addCountry(new DevelopedCountry("미국", "워싱턴DC", 331893745, 21140.0));
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}

// 메인 클래스
class CountryInfoSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CountryManager manager = new CountryManager();
        String filename = "countries.txt"; // 파일 이름 설정

        // 프로그램 시작 시 파일에서 데이터 불러오기
        manager.loadFromFile(filename);

        while (true) {
            System.out.println("\n=== 국가 정보 시스템 ===");
            System.out.println("1. 국가를 추가하기");
            System.out.println("2. 국가 찾기");
            System.out.println("3. 모든 국가 나열하기 ");
            System.out.println("4. 프로그램 종료");
            System.out.print("옵션을 선택하세요: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (option) {
                    case 1:
                        System.out.print("국가 이름을 입력하세요: ");
                        String name = scanner.nextLine();
                        System.out.print("수도를 입력하세요: ");
                        String capital = scanner.nextLine();
                        System.out.print("인구 수를 입력하세요: ");
                        long population = scanner.nextLong();
                        scanner.nextLine(); // 버퍼 비우기

                        manager.addCountry(new Country(name, capital, population));
                        System.out.println("성공적으로 국가가 추가되었습니다");
                        break;

                    case 2:
                        System.out.print("찾을 국가 이름을 입력하세요: ");
                        String searchName = scanner.nextLine();
                        Country found = manager.searchCountry(searchName);
                        if (found != null) {
                            found.displayInfo();
                        } else {
                            System.out.println("국가를 찾을 수 없습니다!");
                        }
                        break;

                    case 3:
                        System.out.println("\n--- 국가들 리스트 ---");
                        manager.displayAllCountries();
                        break;

                    case 4:
                        // 종료 전 데이터 저장
                        manager.saveToFile(filename);
                        System.out.println("프로그램을 종료합니다.");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("유효하지 않은 옵션입니다. 다시 시도하세요.");
                }
            } catch (Exception e) {
                System.out.println("에러: " + e.getMessage());
            }
        }
    }
}

