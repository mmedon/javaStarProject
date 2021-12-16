package com.mateuszmedon;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


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
                "\n5. Search in Universe" + // Agnieszka
                "\n6. Find Stars in x light years away" +  // Bożena
                "\n7. Select stars with temperature between" + // Bożena
                "\n8. Find from star mass" + // Mateusz
                "\n9. On which visible from hemisphere" + // Mateusz
                "\n0. Search for superStars" + // Mateusz
                "\n'q' save to file" +  // Bożena
                "\n'q' quit";

    }

    public void bigBang(PlayAndGoPlanetUniverse playAndGoPlanetUniverse, Universe universe) {
        boolean play = true;
        while (play) {

            System.out.println(playAndGoPlanetUniverse.instruction());

            char choose = scanner.nextLine().charAt(0);
            switch (choose){
                case '1':
                    universe.addNewStarToTheUniverse(); // Mateusz
                    break;
                case '2':
                    printUniverse(universe); //TODO - Agnieszka
                    break;
                case '3':
                    //TODO - zmiana beta -> alfa (jeśli usunięto alfe) - Agnieszka
                    break;
                case '4':
                    //TODO: szukaj w gwiazdozbiorze - Agnieszka
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

    public void printUniverse(Universe universe){
        // print universe
        for (Star star : universe.getStarUniverse()) {
            System.out.println(star);
        }
    }
}

class Universe {

    private final Scanner scanner = new Scanner(System.in);

    private List<Star> starOfUniverse = new ArrayList<>();


    public void addNewStarToTheUniverse(){
        starOfUniverse.add(discoverStar());
    }


    /**
     * @return Star object
     *
     * you are able to create a new star with ALL PARAMS
     *
     */
    public Star discoverStar() {

        String name =  createName(); // Mateusz

        //TODO: String catalogName = catalogName() - Agnieszka
        //TODO: int declination = declination() - Agnieszka
        //TODO: Type renascence = renascence() - Agnieszka

        //TODO: double observableSize =  observableSize() - Bożena
        //TODO: double absoluteSize = absoluteSize() - Bożena
        //TODO: long distanceInLightYears = distanceInLightYears() odległość w latach świetlnych.      ????
        //TODO: String constellationName = constellation() - Bożena gwiazdozbiór, w którym można zobaczyć daną gwiazdę.

        //TODO: int starTemperature = temp() -                          ???
        //TODO: boolean hemisphere = hemisphere() - Mateusz
        //TODO: double mass = playAndGoPlanetUniverse.mass() - Mateusz

        //todo example  Star star = new Star(name, catalogName, declination, renascence, observableSize, absoluteSize, distanceInLightYears, constellationName, starTemperature, hemisphere, mass);
        //
        Star star = new Star(name, "catName", 4, LocalTime.now(), 2.3, 3, "kot", true, 0.6);
        return star;

    }

    private String createName() {

        while (true) {

            System.out.println("Enter name for your star (3 letter and 4 numbers): ");
            String returnValue = scanner.next();

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
    //TODO: long distanceInLightYears = distanceInLightYears() odległość w latach świetlnych.
    private LocalTime renascence;
    private double observableSize;
    //TODO: int starTemperature = temp(
    private double absoluteSize;
    private String constellation;

    // true for North
    private boolean hemisphere;
    private double mass;

    public Star(String name, String catalogName, int declination, LocalTime renascence,
                double observableSize, double absoluteSize, String constellation,
                boolean hemisphere, double mass) {

        this.name = name;
        this.catalogName = catalogName;
        this.declination = declination;
        this.renascence = renascence;
        this.observableSize = observableSize;
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
                ", renascence=" + renascence +
                ", observableSize=" + observableSize +
                ", absoluteSize=" + absoluteSize +
                ", constellation='" + constellation + '\'' +
                ", hemisphere=" + hemisphere +
                ", mass=" + mass +
                '}';
    }
}