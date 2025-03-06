import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();
        
        System.out.println("Enter student grades (type -1 to finish):");
        while (true) {
            System.out.print("Enter grade: ");
            int grade = scanner.nextInt();
            if (grade == -1) break;
            grades.add(grade);
        }
        
        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            int highest = findHighest(grades);
            int lowest = findLowest(grades);
            double average = calculateAverage(grades);
            
            System.out.println("Highest Grade: " + highest);
            System.out.println("Lowest Grade: " + lowest);
            System.out.println("Average Grade: " + average);
        }
        
        scanner.close();
    }
    
    private static int findHighest(ArrayList<Integer> grades) {
        int max = grades.get(0);
        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }
    
    private static int findLowest(ArrayList<Integer> grades) {
        int min = grades.get(0);
        for (int grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }
    
    private static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }
}

