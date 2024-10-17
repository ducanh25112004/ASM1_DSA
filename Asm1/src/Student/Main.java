package Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentLinkedList studentList = new StudentLinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student by ID (Linear Search)");
            System.out.println("5. Search Student by ID (Binary Search)");
            System.out.println("6. Display All Students");
            System.out.println("7. Sort Students by Marks (Selection Sort)");
            System.out.println("8. Sort Students by Marks (Bubble Sort)");
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
                        studentList.addStudent(new Student(id, name, marks));
                        break;

                    case 2:
                        id = getValidId(scanner);
                        studentList.deleteStudent(id);
                        break;

                    case 3:
                        id = getValidId(scanner);
                        scanner.nextLine();  // Consume newline
                        name = getValidName(scanner);
                        marks = getValidMarks(scanner);
                        studentList.updateStudent(id, name, marks);
                        break;

                    case 4:
                        id = getValidId(scanner);
                        Student student = studentList.searchStudent(id);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 5:
                        id = getValidId(scanner);
                        student = studentList.binarySearchStudent(id);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 6:
                        studentList.displayStudents();
                        break;

                    case 7:
                        studentList.sortStudentsByMarksSelectionSort();
                        break;

                    case 8:
                        studentList.sortStudentsByMarksBubbleSort();
                        break;

                    case 9:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please choose a valid menu item.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static int getValidId(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                if (id >= 0) {
                    return id;
                } else {
                    System.out.println("ID cannot be negative. Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static String getValidName(Scanner scanner) {
        while (true) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Name cannot be empty. Please enter again.");
            }
        }
    }

    private static double getValidMarks(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter student marks: ");
                double marks = scanner.nextDouble();
                if (marks >= 0) {
                    return marks;
                } else {
                    System.out.println("Marks cannot be negative. Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
