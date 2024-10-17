package Student;

class StudentLinkedList {

    Node head;

    public void addStudent(Student student) {
        try {
            // Check for invalid student data
            if (student.id < 0 || student.marks < 0 || student.name.isEmpty()) {
                System.out.println("Error: ID and Marks cannot be negative, and Name cannot be empty.");
                return;
            }

            // Check for duplicate ID
            Node current = head;
            while (current != null) {
                if (current.data.id == student.id) {
                    System.out.println("Error: A student with this ID already exists.");
                    return;
                }
                current = current.next;
            }

            // Create a new node and add it to the list
            Node newNode = new Node(student);
            if (head == null) {
                head = newNode;
            } else {
                current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        try {
            if (id < 0) {
                System.out.println("Error: ID cannot be negative.");
                return;
            }
            if (head == null) {
                System.out.println("Error: Student with ID " + id + " not found.");
                return;
            }
            if (head.data.id == id) {
                head = head.next;
                System.out.println("Student with ID " + id + " has been deleted.");
                return;
            }
            Node current = head;
            while (current.next != null && current.next.data.id != id) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
                System.out.println("Student with ID " + id + " has been deleted.");
            } else {
                System.out.println("Error: Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String name, double marks) {
        try {
            if (id < 0 || marks < 0 || name.isEmpty()) {
                System.out.println("Error: ID and Marks cannot be negative, and Name cannot be empty.");
                return;
            }
            Node current = head;
            boolean found = false;
            while (current != null) {
                if (current.data.id == id) {
                    current.data.name = name;
                    current.data.marks = marks;
                    found = true;
                    break;
                }
                current = current.next;
            }
            if (found) {
                System.out.println("Student with ID " + id + " has been updated.");
            } else {
                System.out.println("Error: Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public Student searchStudent(int id) {
        try {
            Node current = head;
            while (current != null) {
                if (current.data.id == id) {
                    return current.data;
                }
                current = current.next;
            }
        } catch (Exception e) {
            System.out.println("Error searching for student: " + e.getMessage());
        }
        return null;
    }

    // Bubble Sort method
    public void sortStudentsByMarksBubbleSort() {
        try {
            if (head == null || head.next == null) {
                System.out.println("No sorting needed.");
                return;
            }

            boolean swapped;
            do {
                swapped = false;
                Node current = head;
                while (current != null && current.next != null) {
                    if (current.data.marks > current.next.data.marks) {
                        // Swap data
                        Student temp = current.data;
                        current.data = current.next.data;
                        current.next.data = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);

            System.out.println("Students sorted by marks (Bubble Sort):");
            displayStudents();
        } catch (Exception e) {
            System.out.println("Error sorting students: " + e.getMessage());
        }
    }

    // Selection Sort method
    public void sortStudentsByMarksSelectionSort() {
        try {
            if (head == null || head.next == null) {
                System.out.println("No sorting needed.");
                return;
            }

            Node current = head;
            while (current != null) {
                Node minNode = current;
                Node nextNode = current.next;

                while (nextNode != null) {
                    if (nextNode.data.marks < minNode.data.marks) {
                        minNode = nextNode;
                    }
                    nextNode = nextNode.next;
                }

                // Swap data if necessary
                if (minNode != current) {
                    Student temp = current.data;
                    current.data = minNode.data;
                    minNode.data = temp;
                }

                current = current.next;
            }

            System.out.println("Students sorted by marks (Selection Sort):");
            displayStudents();
        } catch (Exception e) {
            System.out.println("Error sorting students: " + e.getMessage());
        }
    }

    // Binary Search method (requires sorted array by ID)
    public Student binarySearchStudent(int id) {
        try {
            // Convert linked list to array
            int size = getSize();
            Student[] students = new Student[size];
            Node current = head;
            int index = 0;
            while (current != null) {
                students[index++] = current.data;
                current = current.next;
            }

            // Sort the array by ID using Selection Sort
            selectionSortById(students);

            // Perform binary search
            int left = 0;
            int right = students.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (students[mid].id == id) {
                    return students[mid];
                }

                if (students[mid].id < id) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return null; // Not found
        } catch (Exception e) {
            System.out.println("Error searching for student: " + e.getMessage());
        }
        return null;
    }

    // Helper method to get the size of the list
    private int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // Selection Sort method to sort array by ID
    private void selectionSortById(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students[j].id < students[minIndex].id) {
                    minIndex = j;
                }
            }
            // Swap students[minIndex] with students[i]
            Student temp = students[i];
            students[i] = students[minIndex];
            students[minIndex] = temp;
        }
    }

    public void displayStudents() {
        try {
            if (head == null) {
                System.out.println("No students in the list.");
                return;
            }
            Node current = head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        } catch (Exception e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }
}
