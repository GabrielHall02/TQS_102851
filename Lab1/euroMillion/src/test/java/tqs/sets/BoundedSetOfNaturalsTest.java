/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    //@Disabled("TODO revise test logic")
    @Test
    @DisplayName("add element to set")
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        assertThrows(IllegalArgumentException.class, () -> setB.add(11));
        assertTrue(setB.contains(10), "add: added element not found in set.");
        assertEquals(6, setB.size(), "add: elements count not as expected.");
    }

    //@Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    @DisplayName("new BoundedSetOfNaturals rejects wrong size ou negatives")
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


    // My tests

    // 1 Check if an array has duplicates for set that already exists
    @Test
    @DisplayName("new BoundedSetOfNaturals rejects duplicates")
    public void testDuplicates() {
        int[] elems = new int[]{10, 20, 30, 40, 50, 60, 60};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setB.add(elems));
    }

    // 2 Check if size is correct
    @Test
    @DisplayName("size is correct")
    public void testSize() {
        assertEquals(6, setB.size(), "size: elements count not as expected.");
    }

    // 3 Check if set accepts a duplicate number
    @Test
    @DisplayName("new BoundedSetOfNaturals accepts duplicates")
    public void testDuplicateNumber() {
        assertThrows(IllegalArgumentException.class, () -> setC.add(50));
    }

    // 4 Check if a set that has common elements with another set intersect each other
    @Test
    @DisplayName("new BoundedSetOfNaturals intersect each other")
    public void testIntersect() {
        assertTrue(setB.intersects(setC), "intersect: sets do not intersect each other.");
    }
}
