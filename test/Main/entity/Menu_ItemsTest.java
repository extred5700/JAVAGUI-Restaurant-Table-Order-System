package Main.entity;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Menu_ItemsTest {

    Menu_Items menu_items = new Menu_Items();

    @Test
    @Order(1)
    @DisplayName("Menu_Items_1")
    void viewMenuAllTest() {
        assertNotNull(menu_items.viewMenu("All"));
    }

    @Test
    @Order(2)
    @DisplayName("Menu_Items_2")
    void viewMenuPastaTest() {
        assertNotNull(menu_items.viewMenu("Pasta"));
    }

    @Test
    @Order(3)
    @DisplayName("Menu_Items_3")
    void viewMenuPizzaTest() {
        assertNotNull(menu_items.viewMenu("Pizza"));
    }

    @Test
    @Order(4)
    @DisplayName("Menu_Items_4")
    void viewMenuBakedTest() {
        assertNotNull(menu_items.viewMenu("Baked Rice"));
    }

    @Test
    @Order(5)
    @DisplayName("Menu_Items_5")
    void viewMenuDrinksTest() {
        assertNotNull(menu_items.viewMenu("Drinks"));
    }
}