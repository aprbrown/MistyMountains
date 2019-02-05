import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class for generating a Climber object
 *
 * @author Andrew Brown
 */
public class Climber extends ArrayList {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private ArrayList<Mountain> mountainsClimbed;

    /**
     * Constructor method for creating a Climber.
     * Contains name, date of birth, gender and a list of mountains climbed by the particular climber
     *
     * @param name String representing climber's name
     * @param dateOfBirth LocalDate representing climber's date of birth
     * @param gender String representing climber's gender
     */
    public Climber(String name, LocalDate dateOfBirth, String gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        mountainsClimbed = new ArrayList<Mountain>();
    }

    //Getters

    /**
     * Get's climber's name
     *
     * @return String Climber's name
     */
    public String getClimberName() {
        return name;
    }

    /**
     * Get's climber's date of birth
     *
     * @return LocalDate Date of Birth
     */
    public LocalDate getClimberDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets climber's gender
     *
     * @return String gender
     */
    public String getClimberGender() {
        return gender;
    }

    /**
     * Gets list of mountain's climbed by the climber
     *
     * @return ArrayList containing mountains
     */
    public ArrayList<Mountain> getMountainsClimbed() {
        return mountainsClimbed;
    }

    /**
     * Gets the highest mountain climbed by the climber.
     * Compares each mountain in the mountainsClimbed list by their height, finding the highest
     *
     * @return Mountain object
     */
    public Mountain getHighestMountainClimbed() {
        int height = 0;
        Mountain mountain = null;
        for (Mountain m : mountainsClimbed) {
            if(m.getHeightInMetres() > height) {
                height = m.getHeightInMetres();
                mountain = m;
            }
        }
        return mountain;
    }

    /**
     * Gets the number of Mountains climbed by the climber
     *
     * @return Integer of number of mountains climbed
     */
    public int getNumberOfMountainsClimbed() {
        return mountainsClimbed.size();
    }

    /**
     * Gets the total height of mountains climbed by the climber.
     * Iterates over the list of mountains climbed and sums up the total heights
     *
     * @return Integer total height climbed
     */
    public int getTotalHeightClimbed() {
        int total = 0;
        for (Mountain m : mountainsClimbed) {
            total += m.getHeightInMetres();
        }
        return total;
    }

    /**
     * Calculates the average height of mountains climbed by the climber.
     * Sums the total height of mountains climbed and divides by the number of mountains climbed
     *
     * @return Double average height
     */
    public double getAverageHeightOfMountains() {
        double average =  getTotalHeightClimbed() / getNumberOfMountainsClimbed();
        return average;
    }

    /**
     * Gets a list of mountains climbed above a given height.
     *
     * @param height Integer, height to check against for mountains to be higher than
     * @return ArrayList of mountains which meet the criteria
     */
    public ArrayList<Mountain> getMountainsHigherThanX(int height) {
        ArrayList<Mountain> mountainHigh = new ArrayList<>();
        for (Mountain m : mountainsClimbed) {
            if (m.getHeightInMetres() > height) {
                mountainHigh.add(m);
            }
        }
        mountainHigh.sort(Comparator.comparingInt(Mountain::getHeightInMetres));
        return mountainHigh;
    }

    //Setters

    /**
     * Sets climber name in the event of an error being made on original entry or a climber changing their name later
     *
     * @param name String Name to change to
     */
    public void setClimberName(String name) {
        this.name = name;
    }

    /**
     * Sets climber date of birth should an error have been made on original entry
     *
     * @param dateOfBirth LocalDate to change date of birth to
     */
    public void setClimberDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets climber's gender should an error have been made on original entry or if climber changes gender later on
     *
     * @param gender String gender to change to
     */
    public void setClimberGender(String gender) {
        this.gender = gender;
    }

    /**
     * Adds a mountain to a climber's record of mountains
     *
     * @param mountain Mountain to add to record
     */
    public void addMountainToClimber(Mountain mountain) {
        mountainsClimbed.add(mountain);
    }
}