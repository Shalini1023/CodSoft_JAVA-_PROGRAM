package Codsoft;  

import java.util.ArrayList;
import java.util.Scanner;

public class Task5 { 
    static class Course {
        String courseCode;
        String title;
        String description;
        int capacity;
        int enrolled;
        ArrayList<Student> studentsEnrolled; 

        public Course(String courseCode,String title,String description,int capacity){
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.enrolled = 0;  
            this.studentsEnrolled = new ArrayList<>();
        }
        public boolean isAvailable() {
            return enrolled < capacity;
        }
        public boolean enrollStudent(Student student) {
            if (isAvailable()) {
                enrolled++;
                studentsEnrolled.add(student);
                return true;
            }
            return false;
        }

        public void dropStudent(Student student) {
            if (studentsEnrolled.contains(student)) {
                enrolled--;
                studentsEnrolled.remove(student);
            }
        }

        @Override
        public String toString() {
            return String.format("Course Code:%s,"
            		            + "Title:%s,"
            		            + "Description:%s,Capacity:%d,Enrolled:%d", 
                                 courseCode, title, description, capacity, enrolled);
        }
        
        public void displayEnrolledStudents() {
            if (studentsEnrolled.isEmpty()) {
                System.out.println("No students are enrolled in this course.");
            } else {
                System.out.println("Students enrolled in " + title + ":");
                for (Student student : studentsEnrolled) {
                    System.out.println(student);
                }
            }
        }
    }

     static class Student {
        String studentId;
        String name;
        ArrayList<Course> registeredCourses;

        public Student(String studentId, String name) {
            this.studentId = studentId;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }

        public boolean registerCourse(Course course) {
            if (course.enrollStudent(this)) {
                registeredCourses.add(course);
                return true;
            }
            return false;
        }

        public boolean dropCourse(Course course) {
            if (registeredCourses.contains(course)) {
                course.dropStudent(this);
                registeredCourses.remove(course);
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("Student ID: %s, Name: %s", studentId, name);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("101","Java","Core and Advanced Java",30));
        courses.add(new Course("102","Python","Basic concepts on Python",25));
        courses.add(new Course("104","C++","Basic to Advance",20));

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Riya"));
        students.add(new Student("S002", "Sihara"));

        boolean running = true;

        while (running) {
            System.out.println("<<Student Course Registration System>>");
            System.out.println(" 1. Display Available Courses");
            System.out.println(" 2. Register for a Course");
            System.out.println(" 3. Drop a Course");
            System.out.println(" 4. Display Students List");
            System.out.println(" 5. Add New Student");
            System.out.println(" 6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        System.out.println(course);
                    }
                    break;
                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students available to register.");
                        break;
                    }
                    System.out.println("Available Students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i));
                    }
                    System.out.print("Choose student by number (e.g., 1 for S001): ");
                    int studentChoice = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    if (studentChoice < 1 || studentChoice > students.size()) {
                        System.out.println("Invalid student selection.");
                        break;
                    }

                    Student studentToRegister = students.get(studentChoice - 1);
                    System.out.println("Enter the course code to register for(e.g.,101):");
                    String courseCode = scanner.nextLine();

                    Course courseToRegister = null;
                    for (Course course : courses) {
                        if (course.courseCode.equals(courseCode)) {
                            courseToRegister = course;
                            break;
                        }
                    }

                    if (courseToRegister != null) {
                        if (studentToRegister.registerCourse(courseToRegister)) {
                            System.out.println("Registration successful!");
                        } else {
                            System.out.println("Registration failed.The course might be full.");
                        }
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students available to drop courses.");
                        break;
                    }
                    System.out.println("Available Students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i)); 
                    }
                    System.out.print("Choose student by number (e.g.,1 for S001):");
                    studentChoice = scanner.nextInt();
                    scanner.nextLine();
                   
                    if (studentChoice < 1 || studentChoice > students.size()) {
                        System.out.println("Invalid student selection.");
                        break;
                    }

                    Student studentToDrop = students.get(studentChoice - 1);
                    System.out.print("Enter the course code to drop: ");
                    courseCode = scanner.nextLine();

                    Course courseToDrop = null;
                    for (Course course : courses) {
                        if (course.courseCode.equals(courseCode)) {
                            courseToDrop = course;
                            break;
                        }
                    }

                    if (courseToDrop != null) {
                        if (studentToDrop.dropCourse(courseToDrop)) {
                            System.out.println("Course dropped successfully!");
                        } else {
                            System.out.println("You are not registered for this course.");
                        }
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 4:
                    System.out.println("List of Students:");
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    break;

                case 5:
                    System.out.print("Enter student ID (e.g., S003): ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    Student newStudent = new Student(studentId, studentName);
                    students.add(newStudent);
                    System.out.println("New student added successfully!");
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
}
