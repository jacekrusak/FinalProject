package exportEX;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class ExcelGenerator {


    public void generateHeaderRowOfAttendanceSheet(Workbook workbook, Sheet sheet1, List<String> finalDatesList) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.GREEN.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);

        Row headerRow = sheet1.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Name");
        int cellNum = 1;
        for (String finalDate : finalDatesList) {
            Cell headerCell2 = headerRow.createCell(cellNum++);
            headerCell2.setCellValue(finalDate);
            headerCell2.setCellStyle(headerCellStyle);
        }
        headerCell.setCellStyle(headerCellStyle);

    }

    public void generateNamesInFirstCellsOfAttendanceSheet(Workbook workbook, List<ParticipantData> participantData, Sheet sheet1) {
        int rowNum = 1;
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        for (ParticipantData participantName : participantData) {
            Row row = sheet1.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(participantName.getName());
            cell.setCellStyle(cellStyle);
        }
    }

    public void generateFirstTableOfCourseInformation(Sheet sheet2, String nameOfTheCourse, double courseLengthInHours, List<String> finalDatesList) {
        Row row1 = sheet2.createRow(0);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("Course");
        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(nameOfTheCourse);

        Row row2 = sheet2.createRow(1);
        Cell cell3 = row2.createCell(0);
        cell3.setCellValue("Hours Planned");
        Cell cell4 = row2.createCell(1);
        cell4.setCellValue(courseLengthInHours);

        Row row3 = sheet2.createRow(2);
        Cell cell5 = row3.createCell(0);
        cell5.setCellValue("Lessons Planned");
        Cell cell6 = row3.createCell(1);
        cell6.setCellValue(finalDatesList.size());


    }

    public void generateSecondTableOfCourseInformation(Sheet sheet2, Map<String, List<String>> classDates ) {
        int rowNum = 5;
        for (Map.Entry<String, List<String>> entry : classDates.entrySet() ) {
            Row row = sheet2.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(entry.getKey());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(String.valueOf(entry.getValue()));
        }


    }
}

