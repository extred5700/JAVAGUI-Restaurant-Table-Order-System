package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Restaurant_OwnerTest {

    Restaurant_Owner restaurant_owner = new Restaurant_Owner();

    @Test
    @Order(1)
    @DisplayName("Restaurant_Owner_1")
    void dailySpendingTest() {
        assertNotNull(restaurant_owner.dailySpending());
    }

    @Test
    @Order(2)
    @DisplayName("Restaurant_Owner_2")
    void weeklySpendingTest() {
        assertNotNull(restaurant_owner.weeklySpending());
    }

    @Test
    @Order(3)
    @DisplayName("Restaurant_Owner_3")
    void monthlySpendingTest() {
        assertNotNull(restaurant_owner.monthlySpending());
    }

    @Test
    @Order(4)
    @DisplayName("Restaurant_Owner_4")
    void dailyFrequencyTest() {
        assertNotNull(restaurant_owner.dailyFrequency());
    }

    @Test
    @Order(5)
    @DisplayName("Restaurant_Owner_5")
    void weeklyFrequencyTest() {
        assertNotNull(restaurant_owner.weeklyFrequency());
    }

    @Test
    @Order(6)
    @DisplayName("Restaurant_Owner_6")
    void monthlyFrequencyTest() {
        assertNotNull(restaurant_owner.monthlyFrequency());
    }

    @Test
    @Order(7)
    @DisplayName("Restaurant_Owner_7")
    void dailyPreferenceTest() {
        assertNotNull(restaurant_owner.dailyPreference());
    }

    @Test
    @Order(8)
    @DisplayName("Restaurant_Owner_8")
    void weeklyPreferenceTest() {
        assertNotNull(restaurant_owner.weeklyPreference());
    }

    @Test
    @Order(9)
    @DisplayName("Restaurant_Owner_9")
    void monthlyPreferenceTest() {
        assertNotNull(restaurant_owner.monthlyPreference());
    }

    @Test
    @Order(10)
    @DisplayName("Restaurant_Owner_10")
    void getReportTest() {
        assertNotNull(restaurant_owner.getReport("Average Spend"));
        assertNotNull(restaurant_owner.getReport("Frequency of Visits"));
        assertNotNull(restaurant_owner.getReport("Food Preference"));
    }
}