package com.mateuszmedon;

import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        System.out.println("Hello world");

        Star star = new Star(false,"name", "catName", 4, LocalTime.now(), 2.3, 3, "kot", true, 0.6);
        Star star2 = new Star(true,"JOE1234", "catName", 66, LocalTime.now(), 4.0, 153, "kot", false, 0.6);

        System.out.println(star);
        System.out.println(star2);
    }
}


class Star {

    Scanner scanner = new Scanner(System.in);

    private String name;
    private String catalogName;
    private int declination;
    private LocalTime renascence;
    private double observableSize;
    private double absoluteSize;
    private String constellation;

    // true for North
    private boolean hemisphere;
    private double mass;

    public Star(boolean isInitValue, String name, String catalogName, int declination, LocalTime renascence,
                double observableSize, double absoluteSize, String constellation,
                boolean hemisphere, double mass) {

        // value for initialize

        if (isInitValue) {
            this.name = name;
            this.catalogName = catalogName;
            this.declination = declination;
            this.renascence = renascence;
            this.observableSize = observableSize;
            this.absoluteSize = absoluteSize;
            this.constellation = constellation;
            this.hemisphere = hemisphere;
            this.mass = mass;
        } else {
            this.name = name();
            this.catalogName = catalogName;
            this.declination = declination;
            this.renascence = renascence;
            this.observableSize = observableSize;
            this.absoluteSize = absoluteSize;
            this.constellation = constellation;
            this.hemisphere = hemisphere;
            this.mass = mass;
        }

    }

    private String name() {

        while (true) {
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

            System.out.println("Numbers " + countNumbers + " \n letters: " + countDigit);

            if (countNumbers == 4 && countDigit == 3) {
                return returnValue;
            } else {
                System.out.println("Star need to have 3 capital letter and 4 numbers");
            }
        }
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