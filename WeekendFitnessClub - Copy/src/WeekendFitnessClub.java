import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WeekendFitnessClub {
    private List<Lesson> lessons;
    private Map<String, List<Lesson>> lessonsByType;
    private Map<String, List<Lesson>> lessonsByDay;

    public WeekendFitnessClub() {
        lessons = new ArrayList<>();
        lessonsByType = new HashMap<>();
        lessonsByDay = new HashMap<>();
    }
    public List<Lesson> getLessons() {
    return lessons;
}

public Map<String, List<Lesson>> getLessonsByDay() {
    return lessonsByDay;
}

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        if (!lessonsByType.containsKey(lesson.getType())) {
            lessonsByType.put(lesson.getType(), new ArrayList<>());
        }
        lessonsByType.get(lesson.getType()).add(lesson);
        for (int i = 1; i <= 2; i++) {
            String day = "Saturday";
            if (i == 2) {
                day = "Sunday";
            }
            if (!lessonsByDay.containsKey(day)) {
                lessonsByDay.put(day, new ArrayList<>());
            }
            lessonsByDay.get(day).add(lesson);
        }
    }

    public void printTimetableByDay(String day) {
        if (!lessonsByDay.containsKey(day)) {
            System.out.println("No lessons available on " + day);
            return;
        }
        System.out.println("Lessons on " + day + ":");
        for (Lesson lesson : lessonsByDay.get(day)) {
            System.out.println(lesson.getType() + " at " + lesson.getPrice() + " per lesson, " + lesson.getBooked() + "/" + lesson.getCapacity() + " booked");
        }
    }

    public void printTimetableByType(String type) {
        if (!lessonsByType.containsKey(type)) {
            System.out.println("No " + type + " lessons available");
            return;
        }
        System.out.println(type + " lessons:");
        for (Lesson lesson : lessonsByType.get(type)) {
            System.out.println("On " + lesson.getType() + " at " + lesson.getPrice() + " per lesson, " + lesson.getBooked() + "/" + lesson.getCapacity() + " booked");
        }
    }

    public boolean bookLesson(String day, String type) {
        if (!lessonsByDay.containsKey(day)) {
            return false;
        }
        Lesson chosenLesson = null;
        for (Lesson lesson : lessonsByDay.get(day)) {
            if (lesson.getType().equals(type)) {
                chosenLesson = lesson;
                break;
            }
        }
        if (chosenLesson == null || chosenLesson.isFull()) {
            return false;
        }
        chosenLesson.book();
        return true;
    }

    public boolean cancelLesson(String day, String type) {
        if (!lessonsByDay.containsKey(day)) {
            return false;
        }
        Lesson chosenLesson = null;
        for (Lesson lesson : lessonsByDay.get(day)) {
            if (lesson.getType().equals(type)) {
                chosenLesson = lesson;
                break;
            }
        }
        if (chosenLesson == null || chosenLesson.getBooked() == 0) {
            return false;
        }
        chosenLesson.cancel();
        return true;
    }
    

    public void addRating(String day, String type, int rating) {
        if (!lessonsByDay.containsKey(day)) {
            return;
        }
        Lesson chosenLesson = null;
        for (Lesson lesson : lessonsByDay.get(day)) {
            if (lesson.getType().equals(type)) {
                chosenLesson = lesson;
                break;
            }
        }
        if (chosenLesson == null) {
            return;
        }
        chosenLesson.addRating(rating);
    }

    public void printReport() {
        // Report 1: number of customers per lesson on each day, along with the average rating of each lesson
        System.out.println("Report 1: Number of customers per lesson on each day, along with the average rating of each lesson");
        for (String day : lessonsByDay.keySet()) {
            System.out.println("Lessons on " + day + ":");
            for (Lesson lesson : lessonsByDay.get(day)) {
                System.out.println("- " + lesson.getType() + ": " + lesson.getBooked() + " customers, average rating " + lesson.getAverageRating());
            }
        }
        // Report 2: type of fitness lessons which has generated the highest income, counting all the same type of lessons together
        System.out.println("Report 2: Type of fitness lessons which has generated the highest income");
        Map<String, Double> incomeByType = new HashMap<>();
        for (Lesson lesson : lessons) {
            if (!incomeByType.containsKey(lesson.getType())) {
                incomeByType.put(lesson.getType(), 0.0);
            }
            incomeByType.put(lesson.getType(), incomeByType.get(lesson.getType()) + lesson.getBooked() * lesson.getPrice());
        }
        String highestIncomeType = "";
        double highestIncome = 0;
        for (String type : incomeByType.keySet()) {
            double income = incomeByType.get(type);
            System.out.println("- " + type + ": $" + income);
            if (income > highestIncome) {
                highestIncome = income;
                highestIncomeType = type;
            }
        }
        System.out.println("The type of lessons with the highest income is " + highestIncomeType);
    }

    public static void main(String[] args) {
        WeekendFitnessClub club = new WeekendFitnessClub();
        // add lessons to the club's timetable
        club.addLesson(new Lesson("SPIN", 20, 5));
        club.addLesson(new Lesson("YOGA", 15, 5));
        club.addLesson(new Lesson("BODYSCULPT", 25, 5));
        club.addLesson(new Lesson("ZUMBA", 10, 5));
        club.addLesson(new Lesson("SPIN", 20, 5));
        club.addLesson(new Lesson("YOGA", 15, 5));
        club.addLesson(new Lesson("BODYSCULPT", 25, 5));
        club.addLesson(new Lesson("ZUMBA", 10, 5));
        club.addLesson(new Lesson("SPIN", 20, 5));
        club.addLesson(new Lesson("YOGA", 15, 5));
        club.addLesson(new Lesson("BODYSCULPT", 25, 5));
        club.addLesson(new Lesson("ZUMBA", 10, 5));
        club.addLesson(new Lesson("SPIN", 20, 5));
        club.addLesson(new Lesson("YOGA", 15, 5));
        club.addLesson(new Lesson("BODYSCULPT", 25, 5));
        club.addLesson(new Lesson("ZUMBA", 10, 5));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. View timetable by day");
            System.out.println("2.View timetable by fitness type");
            System.out.println("3. Book a lesson");
            System.out.println("4. Cancel a booking");
            System.out.println("5. Add a rating");
            System.out.println("6. Print report");
            System.out.println("7. Quit");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline character
            switch (option) {
                case 1:
                    System.out.println("Enter day (Saturday or Sunday):");
                    String day = scanner.nextLine();
                    club.printTimetableByDay(day);
                    break;
                case 2:
                    System.out.println("Enter fitness type:");
                    String type = scanner.nextLine();
                    club.printTimetableByType(type);
                    break;
                case 3:
                    System.out.println("Enter day (Saturday or Sunday):");
                    day = scanner.nextLine();
                    System.out.println("Enter fitness type:");
                    type = scanner.nextLine();
                    if (club.bookLesson(day, type)) {
                        System.out.println("Booking successful");
                    } else {
                        System.out.println("Booking failed");
                    }
                    break;
                case 4:
                    System.out.println("Enter day (Saturday or Sunday):");
                    day = scanner.nextLine();
                    System.out.println("Enterfitness type:");
                    type = scanner.nextLine();
                    if (club.cancelLesson(day, type)) {
                        System.out.println("Cancellation successful");
                    } else {
                        System.out.println("Cancellation failed");
                    }
                    break;
                case 5:
                    System.out.println("Enter day (Saturday or Sunday):");
                    day = scanner.nextLine();
                    System.out.println("Enter fitness type:");
                    type = scanner.nextLine();
                    System.out.println("Enter rating (1-5):");
                    int rating = scanner.nextInt();
                    club.addRating(day, type, rating);
                    break;
                case 6:
                    club.printReport();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}