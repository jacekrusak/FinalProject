package exportEX;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException{
        List<ClassDay> classDays = new ArrayList<>();

        double courseLengthInHours;
        Calendar startDate = Calendar.getInstance();
        List<String> finalStartStopValues = new ArrayList<>();
        List<String> finalDatesList  =  new ArrayList<>();
        String participanName;
        String participantEmail;
        String participantPhonenum;
        String hourStart;
        String hourStop;
        List<ParticipantData> participantList = new ArrayList<>();
        List<String> Dates = new ArrayList<>();
        ExcelGenerator excelGenerator = new ExcelGenerator();

        // to be acquired by scanner

        String nameOfTheCourse ="java";
        courseLengthInHours = 9;
        int startYear = 2020;
        // -1 as Calendar uses numbers from 0-11 to identify the months
        int startMonth = 3-1;
        int startDateMonth = 15;

        //simulated first scanner entry for class days
        startDate.set(startYear, startMonth, startDateMonth);
        String classDayOne = "Monday";
        hourStart = "15:30";
        hourStop = "17:00";

        classDays.add(new  ClassDay(classDayOne, hourStart, hourStop));

        //second entry in scanner for class days

        String classDayTwo = "Friday";
        hourStart = "16:30";
        hourStop = "18:00";

        classDays.add(new  ClassDay(classDayTwo, hourStart, hourStop));

        //participants loop in scanner

        participanName = "Juzef Amstrad";
        participantEmail = "juzef@amstrad.com";
        participantPhonenum = "453455";
        participantList.add(new ParticipantData(participanName, participantEmail, participantPhonenum));

        participanName = "Stefan Atari";
        participantEmail = "stefan@atari.com";
        participantPhonenum = "345345";
        participantList.add(new ParticipantData(participanName, participantEmail, participantPhonenum));

        //classSchedule initialization
        ClassSchedule course = new  ClassSchedule( nameOfTheCourse, courseLengthInHours, startDate, classDays);
        for (Map.Entry<String, List<String>> entry :  course.classDates.entrySet()) {
            finalDatesList.add(entry.getKey());
            for (String singleValue : entry.getValue()) {
                finalStartStopValues.add(singleValue);
            }
        }
        System.out.println(finalStartStopValues);
        System.out.println(finalDatesList);


        Workbook workbook = new XSSFWorkbook();
        CreationHelper creationHelper = workbook.getCreationHelper();

        Sheet sheet1 = workbook.createSheet("Attendance");
        Sheet sheet2 = workbook.createSheet("Course Information");

        excelGenerator.generateHeaderRowOfAttendanceSheet(workbook, sheet1, finalDatesList );
        excelGenerator.generateNamesInFirstCellsOfAttendanceSheet( workbook, participantList, sheet1);
        excelGenerator.generateFirstTableOfCourseInformation(sheet2, nameOfTheCourse, courseLengthInHours, finalDatesList);
        excelGenerator.generateSecondTableOfCourseInformation(sheet2, course.classDates);

        for(int i = 0; i <= finalDatesList.size()+1; i++) {
            sheet1.autoSizeColumn(i);
        }
        for(int i = 0; i <= 3; i++) {
            sheet2.autoSizeColumn(i);
        }

        try (OutputStream fileOut = new FileOutputStream("project.xlsx")) {
            workbook.write(fileOut);
        }

        //        participant participant = new participant()
        //Participants

    }


}

