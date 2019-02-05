import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Test Suite for the Climber class.
 */
public class ClimberTest {


    private Climber testClimber;
    private Mountain testMountain1;
    private Mountain testMountain2;
    private Mountain testMountain3;
    private Mountain testMountain4;
    private Mountain testMountain5;

    /**
     * Generates a test climber and some test mountains to be used in the coming tests
     */
    @Before
    public void setUp() throws Exception {
        testClimber = new Climber("Edmund Hillary", LocalDate.parse("1919-07-20"), "Male");
        testMountain1 = new Mountain("Everest", 8848);
        testMountain2 = new Mountain("K2", 8611);
        testMountain3 = new Mountain("Kangchenjunga", 8586);
        testMountain4 = new Mountain("Lhotse", 8516);
        testMountain5 = new Mountain("Makalu", 8485);

        testClimber.addMountainToClimber(testMountain1);
        testClimber.addMountainToClimber(testMountain2);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Tests ability to retrieve the climber's name
     * @throws Exception
     */
    @Test
    public void getClimberName() throws Exception {
        assertEquals("Edmund Hillary", testClimber.getClimberName());
    }

    /**
     * Tests ability to retrieve the climber's date of birth
     * @throws Exception
     */
    @Test
    public void getClimberDateOfBirth() throws Exception {
        assertEquals(LocalDate.parse("1919-07-20"), testClimber.getClimberDateOfBirth());
    }

    /**
     * Tests ability to retrieve the climber's gender
     * @throws Exception
     */
    @Test
    public void getClimberGender() throws Exception {
        assertEquals("Male", testClimber.getClimberGender());
    }

    /**
     * Tests that the climber's mountains climbed list contains certain mountains
     * @throws Exception
     */
    @Test
    public void getMountainsClimbed() throws Exception {
        assert(testClimber.getMountainsClimbed().contains(testMountain1));
        assert(testClimber.getMountainsClimbed().contains(testMountain2));
    }

    /**
     * Test that the highest mountain in the list is returned
     * @throws Exception
     */
    @Test
    public void getHighestMountainClimbed() throws Exception {
        assertEquals(testMountain1, testClimber.getHighestMountainClimbed());
    }

    /**
     * Tests that the number of mountains climbed is returned correctly
     * @throws Exception
     */
    @Test
    public void getNumberOfMountainsClimbed() throws Exception {
        assertEquals(2, testClimber.getNumberOfMountainsClimbed());
    }

    /**
     * Tests that the total height climbed is returned correctly
     * @throws Exception
     */
    @Test
    public void getTotalHeightClimbed() throws Exception {
        assertEquals(17459, testClimber.getTotalHeightClimbed());
    }

    /**
     * Tests that the average height of mountains climbed is returned correctly
     * @throws Exception
     */
    @Test
    public void getAverageHeightOfMountains() throws Exception {
        assertEquals(8729.5, testClimber.getAverageHeightOfMountains(), 1);
    }

    /**
     * Tests that the correct mountain is returned when a height is entered which would exclude another mountain
     * @throws Exception
     */
    @Test
    public void getMountainsHigherThanX() throws Exception {
        assert(testClimber.getMountainsHigherThanX(8700).contains(testMountain1));
    }

    /**
     * Tests that the setClimberName method correctly changes the name of the climber
     * @throws Exception
     */
    @Test
    public void setClimberName() throws Exception {
        testClimber.setClimberName("Junko Tabei");
        assertEquals("Junko Tabei", testClimber.getClimberName());
    }

    /**
     * Tests that the setClimberDateOfBirth method correctly changes the date of birth of the climber
     * @throws Exception
     */
    @Test
    public void setClimberDateOfBirth() throws Exception {
        testClimber.setClimberDateOfBirth(LocalDate.parse("1939-09-22"));
        assertEquals(LocalDate.parse("1939-09-22"), testClimber.getClimberDateOfBirth());
    }

    /**
     * Tests that the setClimberGender method correctly changes the gender of the climber
     * @throws Exception
     */
    @Test
    public void setClimberGender() throws Exception {
        testClimber.setClimberGender("Female");
        assertEquals("Female", testClimber.getClimberGender());
    }

    /**
     * Tests that the addMountainToClimber method correctly adds a mountain to the climber
     * @throws Exception
     */
    @Test
    public void addMountainToClimber() throws Exception {
        testClimber.addMountainToClimber(testMountain3);
        assert(testClimber.getMountainsClimbed().contains(testMountain3));
    }

}
