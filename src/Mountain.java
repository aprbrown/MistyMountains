/**
 * Class for generating Mountain objects
 *
 * @author Andrew Brown
 */
public class Mountain {
    private String mountainName;
    private int heightInMetres;

    /**
     * Constructor for Mountain object
     *
     * @param mountainName String representing the name of the mountain
     * @param heightInMetres Integer representing the height of the mountain (in metres)
     */
    public Mountain(String mountainName, int heightInMetres) {
        this.mountainName = mountainName;
        this.heightInMetres = heightInMetres;
    }

    //Getters

    /**
     * Gets mountain's name
     *
     * @return String mountain name
     */
    public String getMountainName() {
        return mountainName;
    }

    /**
     * Gets mountain's height
     *
     * @return Integer mountain height
     */
    public int getHeightInMetres() {
        return heightInMetres;
    }

    //Setters

    /**
     * Sets mountain's name.
     * Replaces the name originally given, should an error have been made on original entry or change has been made
     *
     * @param mountainName String Name to change to
     */
    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    /**
     * Sets mountain's height.
     * Replaces the height originally given, should an error have been made on original entry or an official change be made
     *
     * @param heightInMetres Integer Height to change to
     */
    public void setHeightInMetres(int heightInMetres) {
        this.heightInMetres = heightInMetres;
    }
}