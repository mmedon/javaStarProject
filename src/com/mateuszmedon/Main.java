package com.mateuszmedon;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


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
//        Star initStar = new Star("name", "catName", 4, LocalTime.now(), 2.3, 3, "kot", true, 0.6);
//        Star initStar2 = new Star("JOE1234", "catName", 66, LocalTime.now(), 4.0, 153, "kot", false, 0.6);
//        System.out.println(initStar);
//        System.out.println(initStar2);

        PlayAndGoPlanetUniverse playAndGoPlanetUniverse = new PlayAndGoPlanetUniverse();
        Universe universe = new Universe();


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
                "\n5. Find Stars in x light years away" +  // Bożena
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
                    printUniverse(universe); //TODO - Agnieszka wyszukaj wszystkie gwiazdy
                    break;
                case '3':
                    //TODO - zmiana beta -> alfa (jeśli usunięto alfe) - Agnieszka
                    break;
                case '4':
                    //TODO: szukaj w danym gwiazdozbiorze - Agnieszka
                    break;
                case '5':
                    //TODO: wyszukaj gwiazdy znajdujace sie w odległosci x parseków od Ziemii - Bożena
                    break;
                case '6':
                    //TODO: wyszukaj gwiazdy o temperaturze w zadanym przedziale - Bożena
                    break;
                case '7':
                    //TODO: wyszukaj gwiazdy o wielkosci gwiazdowej w zadanym przedziale - Mateusz
                    break;
                case '8':
                    //TODO: wyszukaj gwiazdy z półkuli północnej / południowej - Mateusz
                    break;
                case '9':
                    //TODO: wyszukaj potencjalne supernowe - Mateusz
                    break;
                case '0':
                    //TODO zapis do pliku - - Bożena
                    break;
                default:
                    return;

            }

            if (choose == 'q') {
                break;
            }
        }
    }

    public void printUniverse(Universe universe) {
        // print universe
        for (Star star : universe.getStarUniverse()) {
            System.out.println(star);
        }
    }
}

class Universe {

    private final Scanner scanner = new Scanner(System.in);

    private List<Star> starOfUniverse = new ArrayList<>();


    public void addNewStarToTheUniverse() {
        starOfUniverse.add(discoverStar());
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

        //TODO: double observableSize =  observableSize() - Bożena
        //TODO: double absoluteSize = absoluteSize() - Bożena
        //TODO: long distanceInLightYears = distanceInLightYears() odległość w latach świetlnych.      ????
        //TODO: String constellationName = constellation() - Bożena gwiazdozbiór, w którym można zobaczyć daną gwiazdę.

        //TODO: int starTemperature = temp() -                          ???
        boolean hemisphere = hemisphere();  //- Mateusz
        double mass = mass(); //- Mateusz

        //example  Star star = new Star(name, catalogName, declination, distanceInLightYears, renascence, observableSize, absoluteSize, starTemperature, distanceInLightYears, constellationName, starTemperature, hemisphere, mass);
        Star star = new Star(name, "catName", 4, 2000, LocalTime.now(), 2.3, 5000, 3, "kot", hemisphere, mass);
        return star;

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

    public List<Star> getStarUniverse() {
        return starOfUniverse;
    }

    public void setStarUniverse(List<Star> starUniverse) {
        this.starOfUniverse = starUniverse;
    }
}


class Star {

    private String name;
    private String catalogName;
    private int declination;
    private long distanceInLightYears;
    private LocalTime renascence;
    private double observableSize;
    private int starTemperature;
    private double absoluteSize;
    private String constellation;

    // true for North
    private boolean hemisphere;
    private double mass;

    public Star(String name, String catalogName, int declination, long distanceInLightYears, LocalTime renascence,
                double observableSize, int starTemperature, double absoluteSize,
                String constellation, boolean hemisphere, double mass) {
        this.name = name;
        this.catalogName = catalogName;
        this.declination = declination;
        this.distanceInLightYears = distanceInLightYears;
        this.renascence = renascence;
        this.observableSize = observableSize;
        this.starTemperature = starTemperature;
        this.absoluteSize = absoluteSize;
        this.constellation = constellation;
        this.hemisphere = hemisphere;
        this.mass = mass;
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

    public long getDistanceInLightYears() {
        return distanceInLightYears;
    }

    public void setDistanceInLightYears(long distanceInLightYears) {
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