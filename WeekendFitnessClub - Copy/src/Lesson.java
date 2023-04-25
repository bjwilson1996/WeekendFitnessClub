public class Lesson {
    private String type;
    private double price;
    private int capacity;
    private int booked;
    private int[] ratings;
    private int numRatings;

    public Lesson(String type, double price, int capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
        this.booked = 0;
        this.ratings = new int[5];
        this.numRatings = 0;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBooked() {
        return booked;
    }

    public boolean isFull() {
        return booked == capacity;
    }

    public boolean book() {
        if (isFull()) {
            return false;
        }
        booked++;
        return true;
    }

    public void cancel() {
        booked--;
    }

    public void addRating(int rating) {
        ratings[rating - 1]++;
        numRatings++;
    }

    public double getAverageRating() {
        if (numRatings == 0) {
            return 0;
        }
        double total = 0;
        for (int i = 0; i < 5; i++) {
            total += ratings[i] * (i + 1);
        }
        return total / numRatings;
    }
}