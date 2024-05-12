package spring.springboot.utils;

import java.util.Calendar;

public class GetCurrentDate {
    Calendar calendar = Calendar.getInstance();

    public String getTime() {
        String time = calendar.get(10) + ":" + calendar.get(12) + ":" + calendar.get(13);
        return time;
    }

    public int getDate() {
        return calendar.get(5);
    }

    public int getMonth() {
        return calendar.get(2) + 1;
    }

    public int getYear() {
        return calendar.get(1);
    }

    public String getFullDateTime() {
        String fullDateTime = this.getYear() + "-" + this.getMonth() + "-" + this.getDate() + " " + this.getTime();
        return fullDateTime;
    }

    public String prefixPathImage() {
        String prefixPath = this.getYear() + "" + this.getMonth() + "" + this.getDate() + "" + calendar.get(10) + ""
                + calendar.get(12) + "" + calendar.get(13);
        return prefixPath;
    }
}
