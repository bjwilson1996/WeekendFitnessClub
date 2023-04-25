import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeekendFitnessClubTest {
    private WeekendFitnessClub club;

    @BeforeEach
    void setUp() {
        club = new WeekendFitnessClub();
        club.addLesson(new Lesson("SPIN", 20, 5));
        club.addLesson(new Lesson("YOGA", 15, 5));
        club.addLesson(new Lesson("BODYSCULPT", 25, 5));
        club.addLesson(new Lesson("ZUMBA", 10, 5));
    }

    @Test
    void testAddLesson() {
        int initialSize = club.getLessons().size();
        club.addLesson(new Lesson("PILATES", 20, 5));
        assertEquals(initialSize + 1, club.getLessons().size());
    }

    @Test
    void testBookLesson() {
        boolean booked = club.bookLesson("Saturday", "SPIN");
        assertTrue(booked);
        Lesson spinLesson = club.getLessonsByDay().get("Saturday").get(0);
        assertEquals(1, spinLesson.getBooked());
    }

    @Test
    void testBookLessonFull() {
        club.bookLesson("Saturday", "SPIN");
        club.bookLesson("Saturday", "SPIN");
        club.bookLesson("Saturday", "SPIN");
        club.bookLesson("Saturday", "SPIN");
        club.bookLesson("Saturday", "SPIN");
        boolean booked = club.bookLesson("Saturday", "SPIN");
        assertFalse(booked);
    }

    @Test
    void testCancelLesson() {
        club.bookLesson("Saturday", "SPIN");
        boolean cancelled = club.cancelLesson("Saturday", "SPIN");
        assertTrue(cancelled);
        Lesson spinLesson = club.getLessonsByDay().get("Saturday").get(0);
        assertEquals(0, spinLesson.getBooked());
    }

    @Test
    void testCancelLessonNoBooking() {
        boolean cancelled = club.cancelLesson("Saturday", "SPIN");
        assertFalse(cancelled);
    }

    @Test
    void testAddRating() {
        club.addRating("Saturday", "SPIN", 4);
        Lesson spinLesson = club.getLessonsByDay().get("Saturday").get(0);
        assertEquals(4.0, spinLesson.getAverageRating());
    }
}
