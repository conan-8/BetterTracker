import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;

public class FinalProject {
    
    static ArrayList<String> courses = new ArrayList<String>();
    static ArrayList<String> tasks = new ArrayList<String>();
    static ArrayList<String> dueDates = new ArrayList<String>();
    static ArrayList<Integer> points = new ArrayList<Integer>();
    static ArrayList<Integer> progress = new ArrayList<Integer>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean running = true;

        File myObj = new File("data.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] parts = data.split(",");
            courses.add(parts[0]);
            tasks.add(parts[1]);
            dueDates.add(parts[2]);
            points.add(Integer.parseInt(parts[3]));
            progress.add(Integer.parseInt(parts[4]));
        }
        myReader.close();

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
            System.out.println("[9] Add by Paste");
            System.out.println("[-] Sort Assignments");
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
            } else if (choice.equals("5")) {
                viewByCourse();
            } else if (choice.equals("6")) {
                viewAnalytics();
            } else if (choice.equals("7")) {
                manageCourses();
            } else if (choice.equals("8")) {
                FileWriter myWriter = new FileWriter("data.txt");
                for (int i = 0; i < courses.size(); i++) {
                    myWriter.write(courses.get(i) + "," + tasks.get(i) + "," + dueDates.get(i) + "," + points.get(i) + "," + progress.get(i) + "\n");
                }
                myWriter.close();
                System.out.println("Data saved.");
                running = false;
            } else if (choice.equals("9")) {
                addByPaste();
            } else if (choice.equals("-")) {
                sortAssignments();
            } else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println("Invalid choice.");
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
            System.out.println((i + 1) + ". " + courses.get(i) + " - " + tasks.get(i) + " (Due: " + dueDates.get(i) + ") [Pts: " + points.get(i) + "] - Completion: " + progress.get(i) + "%");
        }
    }

    public static void updateProgress() {
        if (courses.isEmpty()) {
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
        if (courses.isEmpty()) {
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

    public static void viewByCourse() {
        System.out.print("\nEnter Course Name: ");
        String c = scanner.nextLine();
        boolean found = false;
        System.out.println("\n--- Tasks for " + c + " ---");
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).equalsIgnoreCase(c)) {
                System.out.println("- " + tasks.get(i) + " (Due: " + dueDates.get(i) + ") [Pts: " + points.get(i) + "] - Completion: " + progress.get(i) + "%");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No assignments found for this course.");
        }
    }

    public static void viewAnalytics() {
        if (courses.isEmpty()) {
            System.out.println("No data for analytics.");
            return;
        }
        int totalPoints = 0;
        int totalProgress = 0;
        for (int i = 0; i < courses.size(); i++) {
            totalPoints += points.get(i);
            totalProgress += progress.get(i);
        }
        int avgProgress = totalProgress / courses.size();
        System.out.println("\n--- Workload Analytics ---");
        System.out.println("Total Assignments: " + courses.size());
        System.out.println("Total Points at Stake: " + totalPoints);
        System.out.println("Average Completion: " + avgProgress + "%");
    }

    public static void manageCourses() {
        System.out.println("\n--- Course Management ---");
        Set<String> uniqueCourses = new HashSet<>(courses);
        if (uniqueCourses.isEmpty()) {
            System.out.println("No courses currently active.");
            return;
        }
        System.out.println("Active Courses:");
        for (String c : uniqueCourses) {
            int count = 0;
            for (String course : courses) {
                if (course.equals(c)) {
                    count++;
                }
            }
            System.out.println("- " + c + " (" + count + " assignments)");
        }
    }

    public static void addByPaste() {
        System.out.println("\n--- Add by Paste ---");
        System.out.println("Paste your assignments (type 'DONE' on a new line to finish):");
        
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("DONE")) {
                break;
            }
            if (line.contains("Due ")) {
                int dueIndex = line.indexOf("Due ");
                String taskName = line.substring(0, dueIndex);
                if (taskName.startsWith("Assignment.")) {
                    taskName = taskName.substring("Assignment.".length());
                } else if (taskName.startsWith(".")) {
                    taskName = taskName.substring(1);
                }
                
                String rest = line.substring(dueIndex + 4);
                String dueDate = "";
                String courseName = "";
                
                if (rest.contains(" pm")) {
                    int pmIndex = rest.indexOf(" pm");
                    dueDate = rest.substring(0, pmIndex + 3);
                    courseName = rest.substring(pmIndex + 3);
                } else if (rest.contains(" am")) {
                    int amIndex = rest.indexOf(" am");
                    dueDate = rest.substring(0, amIndex + 3);
                    courseName = rest.substring(amIndex + 3);
                } else if (rest.contains(" at")) {
                    int atIndex = rest.indexOf(" at");
                    dueDate = rest.substring(0, atIndex);
                    courseName = rest.substring(atIndex + 3);
                } else {
                    continue;
                }
                
                courses.add(courseName);
                tasks.add(taskName);
                dueDates.add(dueDate);
                points.add(100);
                progress.add(0);
                System.out.println("Added: " + taskName + " for " + courseName);
            }
        }
        System.out.println("Paste import complete.");
    }

    public static void sortAssignments() {
        System.out.println("\n--- Sort Assignments ---");
        System.out.println("[1] Sort by Course (Alphabetical)");
        System.out.println("[2] Sort by Task (Alphabetical)");
        System.out.println("[3] Sort by Due Date (Alphabetical)");
        System.out.println("[4] Sort by Points (Highest first)");
        System.out.print("Enter choice: ");
        String sortChoice = scanner.nextLine();

        int n = courses.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean swap = false;
                if (sortChoice.equals("1")) {
                    if (courses.get(j).compareToIgnoreCase(courses.get(j + 1)) > 0) swap = true;
                } else if (sortChoice.equals("2")) {
                    if (tasks.get(j).compareToIgnoreCase(tasks.get(j + 1)) > 0) swap = true;
                } else if (sortChoice.equals("3")) {
                    if (dueDates.get(j).compareToIgnoreCase(dueDates.get(j + 1)) > 0) swap = true;
                } else if (sortChoice.equals("4")) {
                    if (points.get(j) < points.get(j + 1)) swap = true;
                }

                if (swap) {
                    String tempCourse = courses.get(j);
                    courses.set(j, courses.get(j + 1));
                    courses.set(j + 1, tempCourse);

                    String tempTask = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, tempTask);

                    String tempDate = dueDates.get(j);
                    dueDates.set(j, dueDates.get(j + 1));
                    dueDates.set(j + 1, tempDate);

                    int tempPoint = points.get(j);
                    points.set(j, points.get(j + 1));
                    points.set(j + 1, tempPoint);

                    int tempProg = progress.get(j);
                    progress.set(j, progress.get(j + 1));
                    progress.set(j + 1, tempProg);
                }
            }
        }
        System.out.println("Assignments Sorted!");
    }
}
