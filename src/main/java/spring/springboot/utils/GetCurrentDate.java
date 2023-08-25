package spring.springboot.utils;

import java.util.Calendar;

public class GetCurrentDate {
    Calendar calendar = Calendar.getInstance();

    public String getTime() {
       int hour = calendar.get(10);
       int minute = calendar.get(12);
       int second = calendar.get(13);
       String time = hour + ":" + minute + ":" + second;
       return time; 
    }

    public int getDate() {
        int date = calendar.get(5);
        return date;
    }

    public int getMonth() {
        int m = calendar.get(2);
        int month = m + 1;
        return month;
    }

    public int getYear() {
        int year = calendar.get(1);
        return year;
    }

    public String getFullDateTime() {
        int year = this.getYear();
        int month = this.getMonth();
        int date = this.getDate();
        String time = this.getTime();
        String fullDateTime = year + "-" + month + "-" + date + " " + time;
        return fullDateTime;
    }

    public String prefixPathImage() {
        String prefixPath = this.getYear() + "" + this.getMonth() + "" + this.getDate() + "" + calendar.get(10) + "" + calendar.get(12) + "" + calendar.get(13);
        return prefixPath;
    }
}
