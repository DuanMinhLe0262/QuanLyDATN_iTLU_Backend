package com.example.backend_itlu.utils;

import org.apache.poi.ss.usermodel.*;

public class ExcelHelper {

    public static String getString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> getStringFromFormula(cell);
            default -> "";
        };
    }

    public static String getString(Row row, int index) {
        return getString(row.getCell(index));
    }

    public static Integer getInt(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case NUMERIC -> (int) cell.getNumericCellValue();
            case STRING -> {
                try {
                    yield Integer.parseInt(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    yield null;
                }
            }
            default -> null;
        };
    }

    public static Integer getInt(Row row, int index) {
        return getInt(row.getCell(index));
    }

    public static Boolean getBoolean(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case BOOLEAN -> cell.getBooleanCellValue();
            case STRING -> {
                String val = cell.getStringCellValue().trim().toLowerCase();
                yield val.equals("true") || val.equals("x") || val.equals("1");
            }
            default -> null;
        };
    }

    public static Boolean getBoolean(Row row, int index) {
        return getBoolean(row.getCell(index));
    }

    private static String getStringFromFormula(Cell cell) {
        try {
            FormulaEvaluator evaluator = cell.getSheet().getWorkbook()
                    .getCreationHelper().createFormulaEvaluator();
            CellValue cellValue = evaluator.evaluate(cell);
            return switch (cellValue.getCellType()) {
                case STRING -> cellValue.getStringValue();
                case NUMERIC -> String.valueOf((long) cellValue.getNumberValue());
                case BOOLEAN -> String.valueOf(cellValue.getBooleanValue());
                default -> "";
            };
        } catch (Exception e) {
            return "";
        }
    }
}
