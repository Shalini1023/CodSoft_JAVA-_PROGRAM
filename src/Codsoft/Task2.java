package Codsoft;
import java.util.Scanner;

public class Task2 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        
	        int totalMarks = 0;
	        double averagePercentage;

            System.out.println("  >>>>>Grade Calculator! Get your Student Grades Instantly!<<<<<");
	        System.out.print("\nEnter the number of subjects: ");
	        int numSub = scanner.nextInt();

	        for (int i = 1; i <= numSub; i++) {
	            System.out.print("\n   Enter marks for subject " + i + " (out of 100): ");
	            int marks = scanner.nextInt(); 

	            totalMarks += marks; 
	        }

	        
	        averagePercentage = (totalMarks * 100.0) / (numSub * 100); 

	        
	        String grade = "";

	        if (averagePercentage >= 90) {
	            grade = "A+";
	        } else if (averagePercentage >= 80) {
	            grade = "A";
	        } else if (averagePercentage >= 70) {
	            grade = "B+";
	        } else if (averagePercentage >= 60) {
	            grade = "B";
	        } else if (averagePercentage >= 50) {
	            grade = "C+";
	        } else if (averagePercentage >= 40) {
	            grade = "C";
	        } else {
	            grade = "F";
	        }

	        System.out.println("\n>>Result<<");
	        System.out.println("\n  Total Marks: " + totalMarks + "/" + (numSub * 100));
	        System.out.println("  Average Percentage: " + averagePercentage + "%");
	        System.out.println("  Grade: " + grade);

	        
	        scanner.close();
	    }
	}