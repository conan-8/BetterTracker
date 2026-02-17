import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    static ArrayList<String> courses = new ArrayList<String>();
    static ArrayList<String> tasks = new ArrayList<String>();
    static ArrayList<String> dueDates = new ArrayList<String>();
    static ArrayList<Integer> points = new ArrayList<Integer>();
    static ArrayList<Integer> progress = new ArrayList<Integer>();


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        boolean running = true;

        courses.add("Math");
        tasks.add("Test");
        dueDates.add("Jan 20");
        points.add(90);
        progress.add(0);


        while (running) {
            System.out.println("\n- - - - - Quick Dashboard - - - - -");
            if (courses.size() > 0) {
                System.out.println("Top Task: " + courses.get(0) + " - " + tasks.get(0) + " (Due: " + dueDates.get(0) + ") [Pts: " + points.get(0) + "] - Completion: " + progress.get(0) + "%");
            } else {
                System.out.println("No tasks pending.");
            }
            System.out.println("- - - - - - - - - - - - - - -");

            System.out.println("Actions:");
            System.out.println("[1] Add Assignment");
            System.out.println("[2] View All Assignments");
            System.out.println("[3] Update Progress");
            System.out.println("[4] Mark Assignment Complete");
            System.out.println("[5] View Assignments by Course");
            System.out.println("[6] View Workload Analytics");
            System.out.println("[7] Manage Courses");
            System.out.println("[8] Save and Exit");
            System.out.println("[0] Exit Without Saving");
            
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();


            System.out.println("===========================");

            if (choice.equals("1")) {
                addAssignment();
            } else if (choice.equals("2")) {
                viewAll();
            } else if (choice.equals("3")) {
                updateProgress();
            } else if (choice.equals("4")) {
                markAssignmentComplete();
            } else if (choice.equals("8")) {
                running = false;
            } else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println(" in progress...");
            }
        }
    }

    public static void addAssignment() {
        System.out.println("\n--- New Assignment ---");
        
        System.out.print("Enter Course: ");
        String c = scanner.nextLine();
        
        System.out.print("Enter Title: ");
        String t = scanner.nextLine();
        
        System.out.print("Enter Due Date: ");
        String d = scanner.nextLine();
        
        System.out.print("Enter Points: ");
        try {
            int p = Integer.parseInt(scanner.nextLine());
            
            courses.add(c);
            tasks.add(t);
            dueDates.add(d);
            points.add(p);
            progress.add(0);
            
            System.out.println("Assignment Added!");
            
        } catch (Exception e) {
            System.out.println("Error: Invalid number.");
        }
    }

    public static void viewAll() {
        System.out.println("\n--- All Tasks ---");
        for (int i = 0; i < courses.size(); i++) {
            String info = courses.get(i) + " - " + tasks.get(i) + " (Due: " + dueDates.get(i) + ") [Pts: " + points.get(i) + "] - Completion: " + progress.get(i) + "%";
            System.out.println((i + 1) + ". " + info);
        }
    }

    public static void updateProgress() {
    if (courses.size() == 0) {
        System.out.println("No assignments to update.");
        return;
    }
    
    viewAll();
    System.out.print("\nEnter assignment number to update: ");
    try {
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        if (choice < 0 || choice >= courses.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        System.out.print("Enter progress percentage (0-100): ");
        int prog = Integer.parseInt(scanner.nextLine());
        if (prog < 0 || prog > 100) {
            System.out.println("Progress must be between 0 and 100.");
            return;
        }
        
        progress.set(choice, prog);
        System.out.println("Progress updated for " + tasks.get(choice) + ": " + prog + "%");
        
    } catch (Exception e) {
        System.out.println("Error: Invalid input.");
    }
}

public static void markAssignmentComplete() {
    if (courses.size() == 0) {
        System.out.println("No assignments to complete.");
        return;
    }
    
    viewAll();
    System.out.print("\nEnter assignment number to mark complete: ");
    try {
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        if (choice < 0 || choice >= courses.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        String completedTask = tasks.get(choice);
        courses.remove(choice);
        tasks.remove(choice);
        dueDates.remove(choice);
        points.remove(choice);
        progress.remove(choice);
        
        System.out.println("Assignment '" + completedTask + "' marked complete and removed from list.");
        
    } catch (Exception e) {
        System.out.println("Error: Invalid input.");
    }
}

}