package com.mateuszmedon;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;



/**
 * ...........::::: KOMUNIKAT ::::::.......
 * <p>
 * Proponuję najpierw zacząć od klasy Universe
 * póżniej można myśleć o reszcie  ;)
 * <p>
 * todo --class <<<Universe>>> first best option
 * <p>
 * PS zapomniałem o 2 polach w klasie ;P
 * PS 2 Rozrysowałem to może bardziej czytelnie co gdzie kiedy i jak ;P
 * PS 3 Nazwy, typy, struktura, cokolwiek można zmieniać dowolnie :)
 * <p>
 * <p>
 * ps 4 last but not least >>>> nie zapomnijcie o commitach i "puszach"
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Hello world");

        //test init data
        Constellation constellation = new Constellation("kot");
        Star initStar = new Star("name", "catName", 4, 10, LocalTime.now(), 2.3, 3000, 40, constellation, true, 0.6);
        Star initStar2 = new Star("JOE1234", "catName", 66, 20, LocalTime.now(), 153, 4000,30, constellation, false, 0.6);

//        System.out.println(initStar);
//        System.out.println(initStar2);

        PlayAndGoPlanetUniverse playAndGoPlanetUniverse = new PlayAndGoPlanetUniverse();
        Universe universe = new Universe();

        universe.getConstellations().add(constellation);
        universe.getStarUniverse().add(initStar);
        universe.getStarUniverse().add(initStar2);

        playAndGoPlanetUniverse.bigBang(playAndGoPlanetUniverse, universe);

    }

}

class PlayAndGoPlanetUniverse {

    private final Scanner scanner = new Scanner(System.in);

    public String instruction() {
        return "...:::Universe instruction:::..." +
                "\n1. Add new star" + // Mateusz
                "\n2. Display all" + // Agnieszka
                "\n3. Remove star" + // Agnieszka
                "\n4. Search by constellation name" + // Agnieszka
                "\n5. Find Stars in x parsecs away" +  // Bożena
                "\n6. Select stars with temperature between" + // Bożena
                "\n7. Find from star mass" + // Mateusz
                "\n8. On which hemisphere is visible" + // Mateusz
                "\n9. Search for superStars" + // Mateusz
                "\n'0' save to file" +  // Bożena
                "\n'q' quit";

    }

    public void bigBang(PlayAndGoPlanetUniverse playAndGoPlanetUniverse, Universe universe) {
        boolean play = true;
        while (play) {

            System.out.println(playAndGoPlanetUniverse.instruction());
            char choose = scanner.nextLine().charAt(0);
            switch (choose) {
                case '1':
                    universe.addNewStarToTheUniverse(); // Mateusz
                    break;
                case '2':
                    printUniverse(universe.getStarUniverse()); //TODO - Agnieszka wyszukaj wszystkie gwiazdy

                    break;
                case '3':
                    //TODO - zmiana beta -> alfa (jeśli usunięto alfe) - Agnieszka
                    break;
                case '4':
                    //TODO: szukaj w danym gwiazdozbiorze - Agnieszka
                    break;
                case '5':
                    printUniverse(universe.displayFromDistance()); //Bożena
                    break;
                case '6':
                    printUniverse(universe.displayByTemperature()); //Bożena
                    break;
                case '7':
                    //TODO: wyszukaj gwiazdy o wielkosci gwiazdowej w zadanym przedziale - Mateusz
                    break;
                case '8':
                   printUniverse(universe.displayFromVisible()); //Mateusz

                    break;
                case '9':
                    //TODO: wyszukaj potencjalne supernowe - Mateusz
                    break;
                case '0':
                    //TODO zapis do pliku - - Bożena
                    break;
                case 'q':
                    return;
                default:
                    System.out.println("Wrong choice - try again");
                    System.out.println();
                    break;
            }

            if (choose == 'q') {
                break;
            }
        }
    }

    public void printUniverse(List<Star> starList) {
        int size = starList.size();

        if (size > 0) {
            System.out.println("List of " + size + " stars found:");
            for (Star star : starList) {
                System.out.println(star);
            }
        }
        else {
            System.out.println("No stars find");
        }
    }
}

class Universe {
    private static final double PARSEC_TO_LIGHT_YEARS = 3.26;
    private final Scanner scanner = new Scanner(System.in);

    private List<Star> starOfUniverse = new ArrayList<>();
    private List<Constellation> constellations = new ArrayList<>();

    public List<Star> displayFromVisible() {
        while (true) {
            System.out.println("From which hemisphere is star Visible? N for North and S South");
            String value = scanner.nextLine();
            value = value.toUpperCase(Locale.ROOT);

            if (value.charAt(0) == 'S') {
                return visibleFromNorthOrSouth(false);
            }
            if (value.charAt(0) == 'N') {
                return visibleFromNorthOrSouth(true);
            }
            System.out.println("Enter only 'N' or 'S'");
        }
    }

    private List<Star> visibleFromNorthOrSouth(boolean northOrSouth){

        List<Star> newList = new ArrayList<>(starOfUniverse);

        return newList.stream()
                .filter(p -> p.isHemisphere() == northOrSouth)
                .collect(Collectors.toList());
    }

    public void addNewStarToTheUniverse() {
        starOfUniverse.add(discoverStar());
    }

    public void addNewConstellationToTheUniverse(Constellation constellation) {
        constellations.add(constellation);
    }


    /**
     * @return Star object
     * <p>
     * you are able to create a new star with ALL PARAMS
     */
    public Star discoverStar() {

        String name = createName(); // Mateusz

        //TODO: String catalogName = catalogName() - Agnieszka
        //TODO: int declination = declination() - Agnieszka
        //TODO: Type renascence = renascence() - Agnieszka

        double observableSize =  observableSize(); // Bożena
        double distanceInLightYears = distanceInLightYears();
        double absoluteSize = absoluteSize(observableSize, distanceInLightYears); //Bożena
        Constellation constellation = constellation(); // Bożena

        int starTemperature = temp(); //- Mateusz

        boolean hemisphere = hemisphere();  //- Mateusz
        double mass = mass(); //- Mateusz

        //example  Star star = new Star(name, catalogName, declination, distanceInLightYears, renascence, observableSize, absoluteSize, starTemperature, distanceInLightYears, constellationName, starTemperature, hemisphere, mass);
        Star star = new Star(name, "catName", 4, distanceInLightYears, LocalTime.now(), observableSize, starTemperature, absoluteSize, constellation, hemisphere, mass);

        return star;

    }

    //unit magnitudo
    private double observableSize() {
        double minObservableSize = -26.74;
        double maxObservableSize = 15.00;

        while (true) {
            System.out.println("Enter observable size: (value between " + minObservableSize + " - " + maxObservableSize + " [mag])");
            double size = scanner.nextDouble();
            scanner.nextLine();

            if (size >= minObservableSize && size <= maxObservableSize) {
                return size;
            }

            System.out.println("...:::wrong value:::...");
        }
    }

    // Proxima Centauri 4.2465 light-years
    private double distanceInLightYears() {

        while (true){
            System.out.println("Enter distance in light-years from Earth:");
            double distance = scanner.nextDouble();
            scanner.nextLine();
            if(distance>0){
                return distance;
            }
            System.out.println("Need to be positive value");
        }
    }

    private double absoluteSize(double observableSize, double distanceInLightYears) {
        double distanceInParsec = distanceInLightYears / PARSEC_TO_LIGHT_YEARS;
        double result = observableSize - 5 * Math.log10(distanceInParsec) + 5;

        return Math.round(result * 100.0) / 100.0;
    }

    private Constellation constellation() {
        System.out.println("Existing constellation:");
        for (var constellation: constellations) {
            System.out.println("- " + constellation.getConstellationName());
        }
        System.out.println("Choose an existing constellation or create new by writing name of the constellation.");
        String constellationName = scanner.nextLine();

        for (var constellation: constellations) {
            if (constellation.getConstellationName().equals(constellationName))
                return constellation;
        }

        Constellation constellation = new Constellation(constellationName);
        addNewConstellationToTheUniverse(constellation);
        return constellation;
    }

    private int temp() {
        while (true) {

            System.out.println("Enter Star temperature: (minimum value is 2000)");
            int temp = scanner.nextInt();
            scanner.nextLine();
            if(temp>2000){
                return temp;
            }
            System.out.println("Temperature need to be grater than 2000");
        }
    }

    private double mass() {

        while (true) {

            System.out.println("Enter Star mass between 0.1 - 50");
            double mass = scanner.nextDouble();
            scanner.nextLine();
            if (mass > 0.1 && mass < 50) {
                return mass;
            }
            System.out.println("...:::wrong value:::...");
        }
    }

    private boolean hemisphere() {

        while (true) {
            System.out.println("From which hemisphere is star Visible? N for North and S South");
            String value = scanner.nextLine();
            value = value.toUpperCase(Locale.ROOT);

            if (value.charAt(0) == 'S') {
                return false;
            }
            if (value.charAt(0) == 'N') {
                return true;
            }
            System.out.println("Enter only 'N' or 'S'");
        }
    }

    private String createName() {

        while (true) {

            System.out.println("Enter name for your star (3 letter and 4 numbers): ");
            String returnValue = scanner.nextLine();

            if (returnValue.length() != 7) {
                System.out.println("Invalid value: Star NAME- needs to have 3 capital letter and 4 numbers");
                continue;
            }

            returnValue = returnValue.toUpperCase(Locale.ROOT);

            int countNumbers = (int) returnValue.chars()
                    .filter(Character::isDigit)
                    .count();


            int countDigit = (int) returnValue.chars()
                    .filter(Character::isUpperCase)
                    .count();

            System.out.println("Correct Star name");

            if (countNumbers == 4 && countDigit == 3) {
                return returnValue;
            } else {
                System.out.println("Star need to have 3 capital letter and 4 numbers");
            }
        }
    }

    public List<Star> displayFromDistance() {
        System.out.println("Enter distance: [in parsecs]");
        double distanceInParsec = scanner.nextDouble();
        scanner.nextLine();

        double distanceInLightYears = distanceInParsec * PARSEC_TO_LIGHT_YEARS;

        List<Star> stars = new ArrayList<>();
        //System.out.println("Stars in " + distanceInParsec + " parsecs away from Earth: ");
        for (var star: starOfUniverse) {
            if (star.getDistanceInLightYears() <= distanceInLightYears)
                stars.add(star);
        }

        return stars;
    }

    public List<Star> displayByTemperature() {
        double min;
        while (true) {
            System.out.println("Enter min temperature: (greater or equal 2000)");
            min = scanner.nextDouble();
            scanner.nextLine();
            if (min >= 2000)
                break;
        }

        double max = min - 1;
        while (min > max) {
            System.out.println("Enter max temperature: (greater or equal " + min + ")");
            max = scanner.nextDouble();
            scanner.nextLine();
        }

        return starsBetweenTemperature(min, max);
    }

    private List<Star> starsBetweenTemperature(double minTemp, double maxTemp) {
        List<Star> stars = new ArrayList<>();
        double temp;

        for (var star: starOfUniverse) {
            temp = star.getStarTemperature();
            if (temp >= minTemp && temp <= maxTemp)
                stars.add(star);
        }

        return stars;
    }

    public List<Star> getStarUniverse() {
        return starOfUniverse;
    }

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public void setStarUniverse(List<Star> starUniverse) {
        this.starOfUniverse = starUniverse;
    }

    public void setConstellations(List<Constellation> constellations) {
        this.constellations = constellations;
    }
}


class Star {

    private String name;
    private String catalogName;
    private int declination;
    private double distanceInLightYears;

    private LocalTime renascence;
    private double observableSize;
    private int starTemperature;
    private double absoluteSize;
    private String constellation;

    // true for North
    private boolean hemisphere;
    private double mass;

    public Star(String name, String catalogName, int declination, double distanceInLightYears, LocalTime renascence,
                double observableSize, int starTemperature, double absoluteSize,
                Constellation constellation, boolean hemisphere, double mass) {
        this.name = name;
        this.catalogName = catalogName;
        this.declination = declination;
        this.distanceInLightYears = distanceInLightYears;
        this.renascence = renascence;
        this.observableSize = observableSize;
        this.starTemperature = starTemperature;
        this.absoluteSize = absoluteSize;
        this.constellation = constellation.getConstellationName();
        this.hemisphere = hemisphere;
        this.mass = mass;

        constellation.addNewStarToTheConstellation(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public int getDeclination() {
        return declination;
    }

    public void setDeclination(int declination) {
        this.declination = declination;
    }

    public double getDistanceInLightYears() {
        return distanceInLightYears;
    }

    public void setDistanceInLightYears(double distanceInLightYears) {

        this.distanceInLightYears = distanceInLightYears;
    }

    public LocalTime getRenascence() {
        return renascence;
    }

    public void setRenascence(LocalTime renascence) {
        this.renascence = renascence;
    }

    public double getObservableSize() {
        return observableSize;
    }

    public void setObservableSize(double observableSize) {
        this.observableSize = observableSize;
    }

    public int getStarTemperature() {
        return starTemperature;
    }

    public void setStarTemperature(int starTemperature) {
        this.starTemperature = starTemperature;
    }

    public double getAbsoluteSize() {
        return absoluteSize;
    }

    public void setAbsoluteSize(double absoluteSize) {
        this.absoluteSize = absoluteSize;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public boolean isHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(boolean hemisphere) {
        this.hemisphere = hemisphere;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Star{" +
                "name='" + name + '\'' +
                ", catalogName='" + catalogName + '\'' +
                ", declination=" + declination +
                ", distanceInLightYears=" + distanceInLightYears +
                ", renascence=" + renascence +
                ", observableSize=" + observableSize +
                ", starTemperature=" + starTemperature +
                ", absoluteSize=" + absoluteSize +
                ", constellation='" + constellation + '\'' +
                ", hemisphere=" + hemisphere() +
                ", mass=" + mass +
                '}';
    }

    private String hemisphere() {
        return hemisphere ? "North" : "South";
    }
}

class Constellation {
    private String name;
    private List<Star> stars;

    public Constellation(String name) {
        this.name = name;
        stars = new ArrayList<>();
    }

    public String getConstellationName() {
        return  name;
    }

    public void addNewStarToTheConstellation(Star star) {
        stars.add(star);
    }
}