package exportEX;
import java.util.*;

public class ClassDay {


    String day;
    String hourStart;
    String hourStop;
    int classlengthMinutes;

    public ClassDay(String day, String hourStart, String hourStop) {
        this.day = day;
        this.hourStart = hourStart;
        this.hourStop = hourStop;
        setClasslengthMinutes(hourStart, hourStop);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHourStart() {
        return hourStart;
    }

    public void setHourStart(String hourStart) {
        this.hourStart = hourStart;
    }

    public String getHourStop() {
        return hourStop;
    }

    public void setClasslengthMinutes(String hourStart,String hourStop) {

        //expected format 09:20
        List<String> convertedStartHourList = Arrays.asList(hourStart.split(":"));
        List<String> convertedStopHourList = Arrays.asList(hourStop.split(":"));

        String startHours = convertedStartHourList.get(0);
        startHours = startHours.replaceFirst ("^0+(?!$)", "");

        String startMinutes = convertedStartHourList.get(1);
        startMinutes = startMinutes.replaceFirst ("^0+(?!$)", "");

        String stopHours = convertedStopHourList.get(0);
        stopHours = stopHours.replaceFirst ("^0+(?!$)", "");

        String stopMinutes = convertedStopHourList.get(1);

        stopMinutes = stopMinutes.replaceFirst ("^0+(?!$)", "");

        int startInMinutes = Integer.parseInt(startHours) * 60 + Integer.parseInt(startMinutes);
        int stopInMinutes = Integer.parseInt(stopHours) * 60 + Integer.parseInt(stopMinutes);

        this.classlengthMinutes = stopInMinutes - startInMinutes;

    }
    public int getClasslengthMinutes() {
        return classlengthMinutes;
    }
}


