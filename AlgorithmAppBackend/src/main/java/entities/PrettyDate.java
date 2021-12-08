package entities;

import java.util.Date;

public class PrettyDate {
    int month;
    int day;
    int year;
    int hours;
    int minutes;
    int seconds;

    public PrettyDate(Date date) {
        this.month = date.getMonth();
        this.day = date.getDay();
        this.year = date.getYear() - 100;
        this.hours = date.getHours();
        this.minutes = date.getMinutes();
        this.seconds = date.getSeconds();
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
