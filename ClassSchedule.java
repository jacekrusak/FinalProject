package exportEX;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;
import java.util.HashMap;

public class ClassSchedule {

    //   String startDay;
    Calendar startDate;
    double CourseLengthInHours;
    double CourseLengthinMinutes;
    String dayNow;
    String nameOfTheCourse;

    List<String> startStop = new ArrayList<>();
    Map<String, List<String>> classDates = new TreeMap<>();

    public List<ClassDay> classDays;

    public ClassSchedule(String nameOfTheCourse, double CourseLengthInHours, Calendar startDate, List<ClassDay> classDays) {
        this.CourseLengthInHours = CourseLengthInHours;
        this.startDate = startDate;
        this.classDays = classDays;
        this.CourseLengthinMinutes = CourseLengthInHours * 60;
        this.nameOfTheCourse = nameOfTheCourse;
        GetDayFromDate();

    }

    public List<ClassDay> getClassDays() {
        return classDays;
    }

    public Map<String, List<String>> getClassDates() {
        return classDates;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setClassDates(String classDate,String hourStart, String hourStop) {
        this.classDates.put(classDate,Arrays.asList(hourStart,hourStop));
    }

    public void GetDayFromDate() {

        Map<Integer, String> weekDaysNum = new HashMap<>();
        weekDaysNum.put(1, "SUNDAY");
        weekDaysNum.put(2, "MONDAY");
        weekDaysNum.put(3, "TUESDAY");
        weekDaysNum.put(4, "WEDNESDAY");
        weekDaysNum.put(5, "THURSDAY");
        weekDaysNum.put(6, "FRIDAY");
        weekDaysNum.put(7, "SATURDAY");
        Calendar cal = (startDate);
        while (CourseLengthinMinutes > 0) {
            dayNow = weekDaysNum.get(cal.get(Calendar.DAY_OF_WEEK));
            for (ClassDay dayNameObject : classDays) {
                if (dayNow.equals(dayNameObject.day.toUpperCase())) {
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy MM dd");
                    String classDateString = format1.format(cal.getTime());
                    startStop.add(dayNameObject.getHourStart());
                    setClassDates(classDateString,dayNameObject.getHourStart(), dayNameObject.getHourStop()  );
                    CourseLengthinMinutes = CourseLengthinMinutes - dayNameObject.getClasslengthMinutes();
                }
            }
            cal.add(Calendar.DATE, 1);
        }
    }

}