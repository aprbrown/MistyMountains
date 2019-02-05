import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for the Club class
 */
public class ClubTest {
    private Club testClub;
    private Climber testClimber1;
    private Climber testClimber2;
    private Climber testClimber3;
    private Mountain testMountain1;
    private Mountain testMountain2;
    private Mountain testMountain3;
    private Mountain testMountain4;
    private Mountain testMountain5;

    /**
     * Generates a test club, test climbers and test mountains for use in the following tests
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testClub = new Club("Test Club");
        testClimber1 = new Climber("Edmund Hillary", LocalDate.parse("1919-07-20"), "Male");
        testClimber2 = new Climber("Junko Tabei", LocalDate.parse("1939-09-22"), "Female");
        testClimber3 = new Climber("George Mallory", LocalDate.parse("1886-06-18"), "Male");
        testMountain1 = new Mountain("Everest", 8848);
        testMountain2 = new Mountain("K2", 8611);
        testMountain3 = new Mountain("Kangchenjunga", 8586);
        testMountain4 = new Mountain("Lhotse", 8516);
        testMountain5 = new Mountain("Makalu", 8485);

        testClub.addClimberToClub(testClimber1);
        testClub.addClimberToClub(testClimber2);

        testClub.addMountainToClub(testMountain1);
        testClub.addMountainToClub(testMountain2);

        testClimber1.addMountainToClimber(testMountain1);
        testClimber1.addMountainToClimber(testMountain3);
        testClimber2.addMountainToClimber(testMountain2);
        testClimber2.addMountainToClimber(testMountain4);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Tests that the club name is returned correctly
     * @throws Exception
     */
    @Test
    public void getClubName() throws Exception {
        assertEquals("Test Club", testClub.getClubName());
    }

    /**
     * Tests that the test climbers are in the list returned by the method
     * @throws Exception
     */
    @Test
    public void getClimbersInClub() throws Exception {
        assert(testClub.getClimbersInClub().contains(testClimber1));
        assert(testClub.getClimbersInClub().contains(testClimber2));
    }

    /**
     * Tests that the test mountains are in the list returned by the method
     * @throws Exception
     */
    @Test
    public void getMountainsRecorded() throws Exception {
        assert(testClub.getMountainsRecorded().contains(testMountain1));
        assert(testClub.getMountainsRecorded().contains(testMountain2));
    }

    /**
     * Tests that the correct mountain is returned when the value is set to exclude other mountain
     * @throws Exception
     */
    @Test
    public void getMountainsClimbedAboveX() throws Exception {
        assert(testClub.getMountainsClimbedAboveX(8700).contains(testMountain1));
    }

    /**
     * Tests that the correct climber is returned when checking for highest average height
     * @throws Exception
     */
    @Test
    public void getClimberWithHighestAverageHeight() throws Exception {
        assertEquals(testClimber1, testClub.getClimberWithHighestAverageHeight());
    }

    /**
     * Tests that the correct climber is returned when checking for highest total height
     * @throws Exception
     */
    @Test
    public void getClimberWithHighestTotalHeight() throws Exception {
        assertEquals(testClimber1, testClub.getClimberWithHighestTotalHeight());
    }

    /**
     * Tests that the correct mountain is returned when checking for highest mountain climbed
     * @throws Exception
     */
    @Test
    public void getHighestMountainClimbed() throws Exception {
        assertEquals(testMountain1, testClub.getHighestMountainClimbed());
    }

    /**
     * Tests that the name is correctly changes when using the setClubName method
     * @throws Exception
     */
    @Test
    public void setClubName() throws Exception {
        testClub.setClubName("New Test Club");
        assertEquals("New Test Club", testClub.getClubName());
    }

    /**
     * Checks that a climber is correctly added to the club
     * @throws Exception
     */
    @Test
    public void addClimberToClub() throws Exception {
        testClub.addClimberToClub(testClimber3);
        assert(testClub.getClimbersInClub().contains(testClimber3));
    }

    /**
     * Tests that a mountain is correctly added to the club
     * @throws Exception
     */
    @Test
    public void addMountainToClub() throws Exception {
        testClub.addMountainToClub(testMountain5);
        assert(testClub.getMountainsRecorded().contains(testMountain5));
    }

}