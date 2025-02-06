package com.clark.util;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;


public class InputUtil {

    static final String FILE_NAME = "Part4_tableData.txt";

    public static void saveTable(ArrayList<ArrayList<String>> tableArray) {
        try {
            File file = new File(FILE_NAME);
            ArrayList<String> lines = new ArrayList<>();
            for (ArrayList<String> row : tableArray) {
                lines.add(String.join("    ;   ", row)); 
            }
            FileUtils.writeLines(file, lines);
            System.out.println("Table saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving table: " + e.getMessage());
        }
    }

    public static void loadTable(ArrayList<ArrayList<String>> tableArray) {
        try {
            File file = new File(FILE_NAME);
            ArrayList<String> lines = (ArrayList<String>) FileUtils.readLines(file, "UTF-8");
            tableArray.clear();
            
            for (String line : lines) {
                ArrayList<String> row = new ArrayList<>(Arrays.asList(line.split("    ;   ")));
                tableArray.add(row);
            }
            System.out.println("Table loaded from file.");
        } catch (IOException e) {
            System.out.println("No previous table found.");
        }
    }

    // get a valid string input
    public static String getInputString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // get a valid integer input, only between 0 and 9
    public static int getInputInt(Scanner scanner, String prompt) {
        int choice = -1;

        while (true) {
            try {
                System.out.print(prompt);
                choice = Integer.parseInt(scanner.nextLine());  
                if (choice >= 0 && choice <= 9) {
                    break;  
                } else {
                    System.out.println("Invalid choice! Please choose a number between 0 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer between 0 and 9.");
            }
        }

        return choice;
    }

    // valid row index for table operations
    public static int getRowIndex(Scanner scanner, String prompt, int tableSize) {
        int rowIndex = -1;

        while (true) {
            try {
                System.out.print(prompt);
                rowIndex = Integer.parseInt(scanner.nextLine());  
                if (rowIndex >= 0 && rowIndex < tableSize) {
                    break;  //input valid,break loop
                } else {
                    System.out.println("Invalid row index! Please enter a value between 0 and " + (tableSize - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid row index.");
            }
        }

        return rowIndex;
    }

    // valid column index for table operations
    public static int getColumnIndex(Scanner scanner, String prompt, int numColumns) {
        int columnIndex = -1;

        while (true) {
            try {
                System.out.print(prompt);
                columnIndex = Integer.parseInt(scanner.nextLine());  
                if (columnIndex >= 0 && columnIndex < numColumns) {
                    break;  
                } else {
                    System.out.println("Invalid column index! Please enter a value between 0 and " + (numColumns - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid column index.");
            }
        }

        return columnIndex;
    }

    // get valid table dimensions in MxM format (e.g., 1x1, 2x2, 3x3)
    public static String getValidTableDimensions(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();

            Pattern pattern = Pattern.compile("^(\\d+)x\\1$");  // ensuresboth values are the same
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Invalid input. Please enter the dimensions in the format MxM where M is a positive integer (ex. 1x1, 2x2).");
            }
        }
        return input;
    }

    // get a valid sort order for table row (asc/desc)
    public static String getSortOrder(Scanner scanner) {
        String order = "";

        while (true) {
            System.out.print("Sort in ascending or descending order? (asc/desc): ");
            order = scanner.nextLine().trim().toLowerCase();

            if (order.equals("asc") || order.equals("desc")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter 'asc' or 'desc'.");
            }
        }

        return order;
    }
}

