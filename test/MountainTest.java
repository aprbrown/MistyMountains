import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the Mountain class
 */
public class MountainTest {
    private Mountain testMountain;

    public MountainTest(){

    }

    /**
     * Creates a mountain to be used in the following tests
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testMountain = new Mountain("Everest", 8848);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Tests that the mountain name is returned correctly
     * @throws Exception
     */
    @Test
    public void getMountainName() throws Exception {
        assertEquals("Everest", testMountain.getMountainName());
    }

    /**
     * Tests that the mountain height is returned correctly
     * @throws Exception
     */
    @Test
    public void getHeightInMetres() throws Exception {
        assertEquals(8848, testMountain.getHeightInMetres());
    }

    /**
     * Tests that setMountainName correctly changes the name of the mountain
     * @throws Exception
     */
    @Test
    public void setMountainName() throws Exception {
        testMountain.setMountainName("Mount Kilimanjaro");
        assertEquals("Mount Kilimanjaro", testMountain.getMountainName());
    }

    /**
     * Tests that setHeightInMeters correctly changes the height of the mountain
     * @throws Exception
     */
    @Test
    public void setHeightInMetres() throws Exception {
        testMountain.setHeightInMetres(5895);
        assertEquals(5895, testMountain.getHeightInMetres());
    }

}