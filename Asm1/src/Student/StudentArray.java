package Student;

public class StudentArray {
    private NodeArray[] students;
    private int count;

    public StudentArray() {
        students = new NodeArray[10];
        count = 0;
    }

    private void resizeArray() {
        try {
            NodeArray[] newArray = new NodeArray[students.length * 2];
            System.arraycopy(students, 0, newArray, 0, students.length);
            students = newArray;
        } catch (Exception e) {
            System.out.println("Error resizing the array: " + e.getMessage());
        }
    }

    public void addStudent(Student student) {
        try {
            if (count == students.length) {
                resizeArray();
            }
            // Check for duplicate ID
            for (int i = 0; i < count; i++) {
                if (students[i].getData().id == student.id) {
                    System.out.println("Error: A student with this ID already exists.");
                    return;
                }
            }
            students[count] = new NodeArray(student);
            count++;
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try {
            int indexToDelete = -1;
            for (int i = 0; i < count; i++) {
                if (students[i].getData().id == id) {
                    indexToDelete = i;
                    break;
                }
            }
            if (indexToDelete == -1) {
                System.out.println("Error: Student with ID " + id + " not found.");
                return;
            }
            // Shift elements to remove the student
            for (int i = indexToDelete; i < count - 1; i++) {
                students[i] = students[i + 1];
            }
            students[count - 1] = null; // Clear the last element
            count--;
            System.out.println("Student with ID " + id + " has been deleted.");
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String name, double marks) {
        try {
            for (int i = 0; i < count; i++) {
                if (students[i].getData().id == id) {
                    students[i].getData().name = name;
                    students[i].getData().marks = marks;
                    System.out.println("Student with ID " + id + " has been updated.");
                    return;
                }
            }
            System.out.println("Error: Student with ID " + id + " not found.");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public Student searchStudentLinear(int id) {
        try {
            for (int i = 0; i < count; i++) {
                if (students[i].getData().id == id) {
                    return students[i].getData();
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error searching for student: " + e.getMessage());
            return null;
        }
    }

    public Student searchStudentBinary(int id) {
        try {
            sortStudentsById(); // Ensure the array is sorted by ID
            int left = 0;
            int right = count - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (students[mid].getData().id == id) {
                    return students[mid].getData();
                }
                if (students[mid].getData().id < id) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error searching for student: " + e.getMessage());
            return null;
        }
    }

    public void sortStudentsByMarksBubbleSort() {
        try {
            for (int i = 0; i < count - 1; i++) {
                for (int j = 0; j < count - i - 1; j++) {
                    if (students[j].getData().marks > students[j + 1].getData().marks) {
                        NodeArray temp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = temp;
                    }
                }
            }
            System.out.println("Students sorted by marks using Bubble Sort:");
            displayStudents();
        } catch (Exception e) {
            System.out.println("Error sorting students by marks using Bubble Sort: " + e.getMessage());
        }
    }

    public void sortStudentsByMarksSelectionSort() {
        try {
            for (int i = 0; i < count - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < count; j++) {
                    if (students[j].getData().marks < students[minIndex].getData().marks) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    NodeArray temp = students[i];
                    students[i] = students[minIndex];
                    students[minIndex] = temp;
                }
            }
            System.out.println("Students sorted by marks using Selection Sort:");
            displayStudents();
        } catch (Exception e) {
            System.out.println("Error sorting students by marks using Selection Sort: " + e.getMessage());
        }
    }

    private void sortStudentsById() {
        try {
            for (int i = 0; i < count - 1; i++) {
                for (int j = 0; j < count - i - 1; j++) {
                    if (students[j].getData().id > students[j + 1].getData().id) {
                        NodeArray temp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = temp;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error sorting students by ID: " + e.getMessage());
        }
    }

    public void displayStudents() {
        try {
            if (count == 0) {
                System.out.println("No students in the list.");
                return;
            }
            for (int i = 0; i < count; i++) {
                System.out.println(students[i].getData());
            }
        } catch (Exception e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }
}
