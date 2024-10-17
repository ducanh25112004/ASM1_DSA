// MainArray.java
package Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainArray {
    public static void main(String[] args) {
        StudentArray studentArray = new StudentArray();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student by ID (Linear Search)");
            System.out.println("5. Search Student by ID (Binary Search)");
            System.out.println("6. Sort Students by Marks (Bubble Sort)");
            System.out.println("7. Sort Students by Marks (Selection Sort)");
            System.out.println("8. Display All Students");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            try {
                // Check if there is input available
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                    continue;
                }
                
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        int id = getValidId(scanner);
                        scanner.nextLine();  // Consume newline
                        String name = getValidName(scanner);
                        double marks = getValidMarks(scanner);
                        studentArray.addStudent(new Student(id, name, marks));
                        break;
                    case 2:
                        id = getValidId(scanner);
                        studentArray.deleteStudent(id);
                        break;
                    case 3:
                        id = getValidId(scanner);
                        scanner.nextLine();  // Consume newline
                        name = getValidName(scanner);
                        marks = getValidMarks(scanner);
                        studentArray.updateStudent(id, name, marks);
                        break;
                    case 4:
                        id = getValidId(scanner);
                        Student studentLinear = studentArray.searchStudentLinear(id);
                        if (studentLinear != null) {
                            System.out.println(studentLinear);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 5:
                        id = getValidId(scanner);
                        Student studentBinary = studentArray.searchStudentBinary(id);
                        if (studentBinary != null) {
                            System.out.println(studentBinary);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 6:
                        studentArray.sortStudentsByMarksBubbleSort();
                        break;
                    case 7:
                        studentArray.sortStudentsByMarksSelectionSort();
                        break;
                    case 8:
                        studentArray.displayStudents();
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static int getValidId(Scanner scanner) {
        int id = -1;
        while (true) {
            try {
                System.out.print("Enter ID: ");
                id = scanner.nextInt();
                if (id < 0) {
                    System.out.println("ID cannot be negative.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid ID.");
                scanner.next(); // Clear the invalid input
            }
        }
        return id;
    }

    private static double getValidMarks(Scanner scanner) {
        double marks = -1;
        while (true) {
            try {
                System.out.print("Enter Marks (0-10): ");
                marks = scanner.nextDouble();
                if (marks < 0 || marks > 10) {
                    System.out.println("Marks must be between 0 and 10.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid marks.");
                scanner.next(); // Clear the invalid input
            }
        }
        return marks;
    }

    private static String getValidName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Enter Name: ");
            name = scanner.nextLine().trim(); // Trim to remove leading/trailing whitespace

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            } else if (name.matches(".*\\d.*")) { // Check if name contains any digits
                System.out.println("Name cannot contain numbers. Please enter a valid name.");
            } else {
                break;
            }
        }
        return name;
    }
}
