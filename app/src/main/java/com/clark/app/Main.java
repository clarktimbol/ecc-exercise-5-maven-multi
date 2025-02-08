package com.clark.app;
import com.clark.model.TableArray;
import com.clark.service.TableService;
import com.clark.util.InputUtil;
import java.util.Scanner;

public class Main { 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TableArray tableArray = new TableArray();
        TableService tableService = new TableService(); // Create instance for TableService

        boolean menu = true;

        while (menu) {
            System.out.println("\nMenu:");
            System.out.println("[ 1 ] Generate Table");
            System.out.println("[ 2 ] Display Table");
            System.out.println("[ 3 ] Search Table");
            System.out.println("[ 4 ] Edit Cell");
            System.out.println("[ 5 ] Sort Row");
            System.out.println("[ 6 ] Add Column");
            System.out.println("[ 7 ] Reset Table");
            System.out.println("[ 8 ] Save Table");
            System.out.println("[ 9 ] Load Table");
            System.out.println("[ 0 ] Exit");

            // InputUtil to get the menu choice input (ensuring it's between 0 and 9)
            int choice = InputUtil.getInputInt(scanner, "Choose an option: ");
            
            switch (choice) {
                case 1:
                    tableService.generateTable(tableArray.getTableArray(), scanner); // Use the instance to call methods
                    break;
                case 2:
                    tableService.displayTable(tableArray.getTableArray());
                    break;
                case 3:
                    tableService.searchTable(tableArray.getTableArray(), scanner);
                    break;
                case 4:
                    tableService.editCell(tableArray.getTableArray(), scanner);
                    break;
                case 5:
                    tableService.sortRow(tableArray.getTableArray(), scanner);  
                    break;
                case 6:
                    tableService.addColumn(tableArray.getTableArray(), scanner);
                    break;
                case 7:
                    tableService.resetTable(tableArray.getTableArray());
                    break;
                case 8:
                    InputUtil.saveTable(tableArray.getTableArray()); 
                    break;
                case 9:
                    InputUtil.loadTable(tableArray.getTableArray()); 
                    break;
                case 0:
                    System.out.println("Exiting...");
                    menu = false;  // exit loop
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
