package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Room(null));
    }

    @Test
    public void constructor_invalidRoom_throwsIllegalArgumentException() {
        String invalidRoom = "$50-23";
        assertThrows(IllegalArgumentException.class, () -> new Room(invalidRoom));
    }

    @Test
    public void isValidRoom() {
        // null room
        assertThrows(NullPointerException.class, () -> Room.isValidRoom(null));

        // invalid room
        assertFalse(Room.isValidRoom("")); // empty string, near boundary values
        assertFalse(Room.isValidRoom(" ")); // spaces only, near boundary values
        assertFalse(Room.isValidRoom("-50-23")); // not allowed to start with a hyphen
        assertFalse(Room.isValidRoom("50 - 23")); // not allowed to have spacings in between
        assertFalse(Room.isValidRoom("50--23")); // not allowed to have more than 1 hyphen in between
        assertFalse(Room.isValidRoom("$50")); // '$' is not allowed
        assertFalse(Room.isValidRoom("^&*")); // special symbols only are not allowed
        assertFalse(Room.isValidRoom("aloha-5()")); // '(' or ')' is not allowed
        assertFalse(Room.isValidRoom("A".repeat(101))); // above 100 characters is not allowed (near boundary values)

        // valid room
        assertTrue(Room.isValidRoom("05-73")); // only alphanumeric characters and hyphen
        assertTrue(Room.isValidRoom("RoomA")); // only alphanumeric characters
        assertTrue(Room.isValidRoom("1")); // 1 character is allowed (near boundary values)
        assertTrue(Room.isValidRoom("Room5")); // hyphens are optional
        assertTrue(Room.isValidRoom("Aloha-5")); // only alphanumeric characters and hyphen
        assertTrue(Room.isValidRoom("A".repeat(100))); // 100 characters is allowed (near boundary values)
        assertTrue(Room.isValidRoom("A".repeat(80))); // less than 100 characters is allowed

    }
}
