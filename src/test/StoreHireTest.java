package test;

import com.emmanuel.Roles;
import com.emmanuel.StoreHire;
import com.emmanuel.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoreHireTest {

    static User manager;
    static User applicant;
    static StoreHire hr;

    @BeforeEach
    void setUp() {
        hr = new StoreHire();
        manager = new User("Chidi", Roles.MANAGER);
        applicant = new User("maria", Roles.APPLICANT);
    }

    @Test
    void hire() {
        String actualMessage = hr.hire(manager,applicant);
        String expectedMessage = "Welcome " + applicant.getName() + ", our new " + applicant.getRole();
        assertEquals(expectedMessage, actualMessage);
    }
}
