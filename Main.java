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

        // Added some sample data so the dashboard isn't lonely
        courses.add("Math");
        tasks.add("Test");
        dueDates.add("Jan 20");
        points.add(90);
        progress.add(0);

        while (running) {
            System.out.println("\n~ ~ ~ ~ ~ Your Dashboard ~ ~ ~ ~ ~");
            if (courses.size() > 0) {
                System.out.println("Up next: " + courses.get(0) + " - " + tasks.get(0) + " (Due: " + dueDates.get(0) + ") [Pts: " + points.get(0) + "] - Completion: " + progress.get(0) + "%");
            } else {
                System.out.println("Looks like you're all caught up! No tasks right now.");
            }
            System.out.println("- - - - - - - - - - - - - - -");

            System.out.println("What would you like to do?");
            System.out.println("[1] Add Assignment");
            System.out.println("[2] View All Assignments");
            System.out.println("[3] Update Progress");
            System.out.println("[4] Mark Assignment Complete");
            System.out.println("[5] View Assignments by Course");
            System.out.println("[6] View Workload Analytics");
            System.out.println("[7] Manage Courses");
            System.out.println("[8] Save and Exit");
            System.out.println("[0] Exit Without Saving");
            
            System.out.print("Go ahead, pick an option: ");
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
                System.out.println("Saving... (Just kidding, I don't have a database yet!). Goodbye!");
                running = false;
            } else if (choice.equals("0")) {
                System.out.println("Exiting without saving. See you next time!");
                running = false;
            } else {
                // Fallback for options 5, 6, 7 or invalid input
                System.out.println("I'm not sure how to help with that yet. Please pick a number from the list.");
            }
        }
    }

    public static void addAssignment() {
        System.out.println("\n--- Let's add a new assignment ---");
        
        System.out.print("Which course is this for? ");
        String c = scanner.nextLine();
        
        System.out.print("What's the assignment title? ");
        String t = scanner.nextLine();
        
        System.out.print("When is it due? ");
        String d = scanner.nextLine();
        
        System.out.print("How many points is it worth? ");
        try {
            int p = Integer.parseInt(scanner.nextLine());
            
            courses.add(c);
            tasks.add(t);
            dueDates.add(d);
            points.add(p);
            progress.add(0);
            
            System.out.println("Awesome! I've added that to your list.");
            
        } catch (Exception e) {
            System.out.println("Oops, that doesn't look like a valid number for points.");
        }
    }

    public static void viewAll() {
        System.out.println("\n--- Here's everything on your plate ---");
        if (courses.size() == 0) {
            System.out.println("Actually, it looks like you don't have any assignments yet.");
            return;
        }
        for (int i = 0; i < courses.size(); i++) {
            String info = courses.get(i) + " - " + tasks.get(i) + " (Due: " + dueDates.get(i) + ") [Pts: " + points.get(i) + "] - Completion: " + progress.get(i) + "%";
            System.out.println((i + 1) + ". " + info);
        }
    }

    public static void updateProgress() {
        if (courses.size() == 0) {
            System.out.println("No assignments to update right now.");
            return;
        }
        
        viewAll();
        System.out.print("\nWhich assignment number do you want to update? ");
        try {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            if (choice < 0 || choice >= courses.size()) {
                System.out.println("Hmm, that number isn't on the list.");
                return;
            }
            
            System.out.print("New progress percentage (0-100): ");
            int prog = Integer.parseInt(scanner.nextLine());
            if (prog < 0 || prog > 100) {
                System.out.println("Progress needs to be between 0 and 100.");
                return;
            }
            
            progress.set(choice, prog);
            System.out.println("Got it! Updated progress for " + tasks.get(choice) + " to " + prog + "%.");
            
        } catch (Exception e) {
            System.out.println("Oops, invalid input.");
        }
    }

    public static void markAssignmentComplete() {
        if (courses.size() == 0) {
            System.out.println("No assignments to mark complete.");
            return;
        }
        
        viewAll();
        System.out.print("\nWhich assignment number did you finish? ");
        try {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            if (choice < 0 || choice >= courses.size()) {
                System.out.println("That's not a valid selection.");
                return;
            }
            
            String completedTask = tasks.get(choice);
            courses.remove(choice);
            tasks.remove(choice);
            dueDates.remove(choice);
            points.remove(choice);
            progress.remove(choice);
            
            System.out.println("Nice work! '" + completedTask + "' is marked complete and removed from your list.");
            
        } catch (Exception e) {
            System.out.println("Oops, something went wrong with that input.");
        }
    }
}
