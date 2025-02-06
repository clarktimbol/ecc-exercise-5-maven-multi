package com.clark.service;
import com.clark.util.InputUtil;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class TableService { // walang static dapat

    public void generateTable(ArrayList<ArrayList<String>> tableArray, Scanner scanner) {  
        String dimensions = InputUtil.getValidTableDimensions(scanner, "Enter table dimensions (ex. 3x3): ");
        
        String[] dims = dimensions.split("x");
        int rows = Integer.parseInt(dims[0]);
        int cols = Integer.parseInt(dims[1]);

        tableArray.clear();
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                String key = randomString(random);
                String value = randomString(random);
                row.add(key + "," + value);
            }
            tableArray.add(row);
        }
        System.out.println("Table generated with " + rows + " rows and " + cols + " columns.");
    }

    public void displayTable(ArrayList<ArrayList<String>> tableArray) {
        if (tableArray.isEmpty()) {
            System.out.println("Table is empty. Generate a table first.");
            return;
        }
        
        System.out.println("Displaying Table: ");
        for (ArrayList<String> row : tableArray) {
            for (String cell : row) {
                // spacing
                System.out.print((cell != null ? cell : "null/non-existent") + "     ");
            }
            System.out.println(); 
        }
    }

    public void searchTable(ArrayList<ArrayList<String>> tableArray, Scanner scanner) {
        if (tableArray.isEmpty()) {
            System.out.println("Table is empty. Generate a table first.");
            return;
        }
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine();

        int count = 0;
        for (int i = 0; i < tableArray.size(); i++) {
            for (int j = 0; j < tableArray.get(i).size(); j++) {
                String cell = tableArray.get(i).get(j);
                if (cell != null && cell.contains(searchTerm)) {
                    String[] keyValue = cell.split(",");
                    String key = keyValue[0];
                    String value = keyValue.length > 1 ? keyValue[1] : "";

                    if (key.contains(searchTerm)) {
                        System.out.printf("Found at [%d, %d] in Key: %s%n", i, j, key);
                        count++;
                    }
                    if (value.contains(searchTerm)) {
                        System.out.printf("Found at [%d, %d] in Value: %s%n", i, j, value);
                        count++;
                    }
                }
            }
        }

        if (count > 0) {
            System.out.printf("Total occurrences found: %d%n", count);
        } else {
            System.out.println("No matches found!");
        }
    }

    public void editCell(ArrayList<ArrayList<String>> tableArray, Scanner scanner) {
        if (tableArray.isEmpty()) {
            System.out.println("Table is empty. Generate a table first.");
            return;
        }

        System.out.print("Enter cell index to edit (ex. 0x0): ");
        String[] indices = scanner.nextLine().split("x");
        int row = Integer.parseInt(indices[0]);
        int col = Integer.parseInt(indices[1]);

        if (row < 0 || row >= tableArray.size() || col < 0 || col >= tableArray.get(row).size()) {
            System.out.println("Invalid index. Try again.");
            return;
        }

        String cell = tableArray.get(row).get(col);
        if (cell == null) {
            cell = randomString(new Random()) + "," + randomString(new Random());
            tableArray.get(row).set(col, cell);
        }

        String[] keyValue = cell.split(",");
        String key = keyValue[0];
        String value = keyValue[1];

        System.out.print("Edit key or value? (key/value/both): ");
        String menuChoice = scanner.nextLine();

        if (menuChoice.equalsIgnoreCase("key")) {
            System.out.print("Enter new key: ");
            key = scanner.nextLine();
        } else if (menuChoice.equalsIgnoreCase("value")) {
            System.out.print("Enter new value: ");
            value = scanner.nextLine();
        } else if (menuChoice.equalsIgnoreCase("both")) {
            System.out.print("Enter new key: ");
            key = scanner.nextLine();
            System.out.print("Enter new value: ");
            value = scanner.nextLine();
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        tableArray.get(row).set(col, key + "," + value);
        System.out.println("Cell updated!");
    }

    public void sortRow(ArrayList<ArrayList<String>> tableArray, Scanner scanner) {
        if (tableArray.isEmpty()) {
            System.out.println("Table is empty. Generate a table first.");
            return;
        }

        // Use InputUtil to get a valid row index
        int rowIndex = InputUtil.getRowIndex(scanner, "Enter row index to sort: ", tableArray.size());

        // Use InputUtil to get a valid sort order (asc/desc)
        String order = InputUtil.getSortOrder(scanner);

        ArrayList<String> row = tableArray.get(rowIndex);
        row.sort((a, b) -> {
            String combinedA = (a != null ? a : "");
            String combinedB = (b != null ? b : "");

            if (order.equals("asc")) {
                return combinedA.compareTo(combinedB);  // ascending
            } else {
                return combinedB.compareTo(combinedA);  // descending
            }
        });

        System.out.println("Row sorted.");
    }

    public void addColumn(ArrayList<ArrayList<String>> tableArray, Scanner scanner) {
        System.out.print("Enter row index to add column: ");
        int rowIndex = scanner.nextInt();
        scanner.nextLine();

        if (rowIndex < 0 || rowIndex >= tableArray.size()) {
            System.out.println("Invalid row index. Try again.");
            return;
        }

        System.out.print("Enter key for the new column: ");
        String key = scanner.nextLine();

        System.out.print("Enter value for the new column: ");
        String value = scanner.nextLine();

        for (int i = 0; i < tableArray.size(); i++) {
            if (i == rowIndex) {
                tableArray.get(i).add(key + "," + value);
            } else {
                tableArray.get(i).add(randomString(new Random()) + "," + randomString(new Random()));
            }
        }

        System.out.println("Column added to the specified row with key and value.");
    }

    public void resetTable(ArrayList<ArrayList<String>> tableArray) {
        tableArray.clear();
        System.out.println("Table has been reset. Generate a new table.");
    }

    private String randomString(Random random) {
        String randomString = "";
        for (int i = 0; i < 3; i++) {
            char randomChar = (char) (random.nextInt(94) + 33); //to generate random ASCII between 33 and 126 (printable characters)
            randomString += randomChar;
        }
        return randomString;
    }
}
