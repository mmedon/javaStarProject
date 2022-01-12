package com.mateuszmedon;

import java.io.*;
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

    public static void main(String[] args) throws IOException {

        System.out.println("Hello in Stars World!!!");

        String filename = "starsOfUniverse.dat";

        PlayAndGoPlanetUniverse playAndGoPlanetUniverse = new PlayAndGoPlanetUniverse();
        Universe universe = new Universe(filename);
        universe.readStarsFromFile();

        playAndGoPlanetUniverse.bigBang(playAndGoPlanetUniverse, universe);
    }
}

class PlayAndGoPlanetUniverse {

    private final Scanner scanner = new Scanner(System.in);

    public String instruction() {
        return "...:::Universe instruction:::..." +
                "\n1. Add new star" + // Mateusz
                "\n2. Display all stars" + // Agnieszka
                "\n3. Remove star" + // Agnieszka
                "\n4. Find stars in constellation" + // Agnieszka
                "\n5. Find stars in x parsecs away" +  // Bożena
                "\n6. Find stars in the temperature range" + // Bożena
                "\n7. Find stars in the observable size range" + // Mateusz
                "\n8. Find stars in the hemisphere" + // Mateusz
                "\n9. Find super stars" + // Mateusz
                "\n'w' Save to file" +  // Bożena
                "\n'q' Quit" +
                "\nRemember to save changes (w) before quit";

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void bigBang(PlayAndGoPlanetUniverse playAndGoPlanetUniverse, Universe universe) throws IOException {
        boolean play = true;

        while (play) {
            clearScreen();
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
                    printUniverse(universe.findBetweenObservableSize()); //Mateusz
                    break;
                case '8':
                    printUniverse(universe.displayFromVisible()); //Mateusz
                    break;
                case '9':
                    printUniverse(universe.displaySuperStars()); //Mateusz
                    break;
                case 'w':
                    universe.saveStarsToFile(); //Bożena
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

            System.out.println("\n\n...:::Click enter:::...");
            scanner.nextLine();
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
            System.out.println("Stars warn't found.");
        }
    }
}

class Universe {
    private static final double PARSEC_TO_LIGHT_YEARS = 3.26;
    private final Scanner scanner = new Scanner(System.in);

    private String fileName;
    private List<Star> starOfUniverse = new ArrayList<>();
    private List<Constellation> constellations = new ArrayList<>();

    public Universe(String fileName) {
        this.fileName = fileName;
    }

    public List<Star> findBetweenObservableSize() {

        double min, max;
        while (true) {

            List<Star> newList = new ArrayList<>(starOfUniverse);
            System.out.println("Find Star by Observable Size \n Enter min value (grater then -26,74 [mag])");
            min = scanner.nextDouble();
            if (min < -26.74){
                System.out.println("wrong value");
                continue;
            }
            System.out.println("Enter value (cannot be grater than 15,00 [mag])");
            max = scanner.nextDouble();
            if (max > 15 && max > min){
                System.out.println("wrong value");
                continue;
            }

            double finalMin = min;
            double finalMax = max;
            return newList.stream()
                    .filter(p -> p.getObservableSize() >= finalMin && p.getObservableSize() <= finalMax)
                    .collect(Collectors.toList());

        }
    }

    public List<Star> displaySuperStars() {
        List<Star> newList = new ArrayList<>(starOfUniverse);
        return newList.stream()
                .filter(p -> p.getMass() >= 1.44)
                .collect(Collectors.toList());
    }

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
        LocalTime renascence = renascence(); //Agnieszka
        double observableSize =  observableSize(); // Bożena
        double distanceInLightYears = distanceInLightYears();
        double absoluteSize = absoluteSize(observableSize, distanceInLightYears); //Bożena
        Constellation constellation = constellation(); // Bożena
        String catalogName = catalogName(constellation); //Agnieszka
        int starTemperature = temp(); //- Mateusz
        boolean hemisphere = hemisphere();  //- Mateusz
        double declination = declination(hemisphere); //Agnieszka
        double mass = mass(); //- Mateusz

        //example  Star star = new Star(name, catalogName, declination, distanceInLightYears, renascence, observableSize, absoluteSize, starTemperature, distanceInLightYears, constellationName, starTemperature, hemisphere, mass);
        Star star = new Star(name, catalogName, declination, distanceInLightYears, renascence, observableSize, starTemperature, absoluteSize, constellation, hemisphere, mass);

        return star;
    }

    private String catalogName(Constellation constellation){
        String catName = "";
        char sign = 'A';
        int starCounter = 0;

        for (int i = 0; i < starOfUniverse.size(); i++){
            if (starOfUniverse.get(i).getConstellation().equals(constellation.getConstellationName())) {
                starCounter++;
            }
        }

        int n = (byte)sign + starCounter;
        sign = (char)n;

        catName = sign + constellation.getConstellationName();

        return catName;
    }

    private LocalTime renascence(){
        while(true){
            System.out.println("Value of renascence. \nRenascence takes values from 00 to 24 hours.");
            System.out.println("Enter a number of hours between 00 and 24.");
            int hours = scanner.nextInt();
            if (hours < 0 || hours > 24){
                System.out.println("An invalid value was specified.");
                continue;
            }

            System.out.println("Enter a number of minutes between 00 and 60.");
            int minutes = scanner.nextInt();
            if (minutes < 0 || minutes > 60){
                System.out.println("An invalid value was specified.");
                continue;
            }
            else if (minutes > 0 && minutes < 60 && hours == 24){
                System.out.println("An invalid value was specified.");
                continue;
            }

            System.out.println("Enter a number of seconds between 00 and 60.");
            int seconds = scanner.nextInt();
            if (seconds < 0 || seconds > 60){
                System.out.println("An invalid value was specified.");
                continue;
            }
            else if (seconds > 0 && seconds < 60 && hours == 24){
                System.out.println("An invalid value was specified.");
                continue;
            }
            LocalTime renanscence = LocalTime.of(hours, minutes, seconds);

            return renanscence;
        }
    }

    private double declination(boolean hemisphere){
        while(true){
            double declination = 0.0;
            System.out.println("Declination value. \nThe declination is 0 to 90 degrees latitude in the north and negative in the south.");
            System.out.println("Enter a number of degrees between 0 and 90.");
            int degrees = scanner.nextInt();
            if (degrees < 0 || degrees > 90){
                System.out.println("An invalid value was specified.");
                continue;
            }

            System.out.println("Enter a number of minutes between 00 and 60.");
            int minutes = scanner.nextInt();
            if (minutes < 0 || minutes > 60){
                System.out.println("An invalid value was specified.");
                continue;
            }
            else if (minutes > 0 && minutes < 60 && degrees == 90){
                System.out.println("An invalid value was specified.");
                continue;
            }

            System.out.println("Enter a number of seconds between 00 and 60.");
            double seconds = scanner.nextDouble();
            if (seconds < 0 || seconds > 60){
                System.out.println("An invalid value was specified.");
                continue;
            }
            else if (seconds > 0 && seconds < 60 && degrees == 90){
                System.out.println("An invalid value was specified.");
                continue;
            }

            if (hemisphere == true) {
                declination = (degrees + (double) minutes / 60 + seconds / 3600);
            }
            else if (hemisphere == false){
                declination = (degrees + (double) minutes / 60 + seconds / 3600) * (-1);
            }

            return declination;
        }
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

    public void readStarsFromFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.fileName));
            List<Object> objects = (List<Object>) objectInputStream.readObject();

            if(objects.isEmpty()){
                System.out.println("There is no stars in the Universe!!! Add Stars!!!");
            }
            for (Object obj: objects) {
                if (obj instanceof Star) {
                    Star star = (Star) obj;
                    addStarToUniverse(star);
                    addStarToConstellation(star);
                }
            }

//            if (objects != null) {
//                for (var obj : objects) {
//                    if (obj instanceof Star) {
//                        Star star = (Star) obj;
//                        addStarToUniverse(star);
//                        addStarToConstellation(star);
//                    }
//                }
//
//            } else {
//                System.out.println("There is no stars in the Universe!!! Add Stars!!!");
//            }
        } catch (FileNotFoundException e){
            System.out.println("Use write first (w) or no Stars exist in Universe create one (1)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addStarToUniverse(Star star) {
        starOfUniverse.add(star);
    }

    private void addStarToConstellation(Star star) {
        boolean constellationExsists = false;
        for (var constellation : constellations) {
            if (star.getConstellation() == constellation.getConstellationName()) {
                constellation.addNewStarToTheConstellation(star);
                constellationExsists = true;
                break;
            }
        }

        if (!constellationExsists) {
            Constellation constellation = new Constellation(star.getConstellation());
            constellation.addNewStarToTheConstellation(star);
            constellations.add(constellation);
        }
    }

    public void saveStarsToFile() throws IOException {
        ObjectOutputStream objectOutputStream = null;

        if (starOfUniverse.size() > 0) {
            try {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fileName));
                objectOutputStream.writeObject(starOfUniverse);

                System.out.println("Stars ware saved in a file: " + this.fileName);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                objectOutputStream.close();
            }
        } else {
            System.out.println("There is no stars to save!!!");
        }
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


class Star implements Serializable {

    private String name;
    private String catalogName;
    private double declination;
    private double distanceInLightYears;

    private LocalTime renascence;
    private double observableSize;
    private int starTemperature;
    private double absoluteSize;
    private String constellation;

    // true for North
    private boolean hemisphere;
    private double mass;

    public Star(String name, String catalogName, double declination, double distanceInLightYears, LocalTime renascence,
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

    public double getDeclination() {
        return declination;
    }

    public void setDeclination(double declination) {
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