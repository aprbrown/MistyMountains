import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to generate a Club object
 *
 * @author Andrew Brown
 */
public class Club {
    private String clubName;
    private ArrayList<Climber> climbersInClub;
    private ArrayList<Mountain> mountainsRecorded;

    /**
     * Constructor for a Club object, containing a name, list of climbers registered to the club and a list of mountains
     * that the club has recorded
     *
     * @param clubName String Name of the club to be generated
     */
    public Club(String clubName) {
        this.clubName = clubName;
        this.climbersInClub = new ArrayList<Climber>();
        this.mountainsRecorded = new ArrayList<Mountain>();
    }

    //Getters

    /**
     * Method to get the name of the club
     *
     * @return String Club's name
     */
    public String getClubName() {
        return clubName;
    }

    /**
     * Gets a list of Climbers in the club
     *
     * @return ArrayList of climbers
     */
    public ArrayList<Climber> getClimbersInClub() {
        return climbersInClub;
    }

    /**
     * Gets a list of mountains recorded by the club
     *
     * @return ArrayList of mountains
     */
    public ArrayList<Mountain> getMountainsRecorded() {
        return mountainsRecorded;
    }

    /**
     * Gets a list of mountains climbed (not only recorded) above a given height.
     * This method checks each climber's record and adds each eligible mountain to a set, eliminating duplicates.
     * The contents of the set are added to an ArrayList which is then sorted by height in ascending order.
     *
     * @param height Integer to check mountains height against
     * @return ArrayList of mountains above height chosen sorted in ascending order
     */
    public ArrayList<Mountain> getMountainsClimbedAboveX(int height) {
        Set<Mountain> mountainSet = new HashSet<>();
        for (Climber c : climbersInClub) {
            ArrayList<Mountain> mountainArrayList = c.getMountainsHigherThanX(height);
            for (Mountain m : mountainArrayList) {
                mountainSet.add(m);
            }
        }
        ArrayList<Mountain> mountains = new ArrayList<>();
        for (Mountain m : mountainSet) {
            mountains.add(m);
        }
        mountains.sort(Comparator.comparingInt(Mountain::getHeightInMetres));
        return mountains;
    }

    /**
     * Gets the climber who has climbed the highest average height
     *
     * @return Climber with highest average height climbed
     */
    public Climber getClimberWithHighestAverageHeight() {
        double averageHeight = 0;
        Climber climber = null;
        for (Climber c : climbersInClub) {
            if (c.getAverageHeightOfMountains() > averageHeight) {
                averageHeight = c.getAverageHeightOfMountains();
                climber = c;
            }
        } return climber;
    }

    /**
     * Get the climber who has the highest total height climbed
     *
     * @return Climber with highest total height
     */
    public Climber getClimberWithHighestTotalHeight() {
        int total = 0;
        Climber climber = null;
        for (Climber c : climbersInClub) {
            if (c.getTotalHeightClimbed() > total) {
                total = c.getTotalHeightClimbed();
                climber = c;
            }
        }
        return climber;
    }

    /**
     * Gets the highest mountain climbed by any climber (not just recorded by the club).
     * Checks through each climber's list of climbed mountains and keeps track of the highest
     *
     * @return Mountain highest mountain climbed by anyone
     */
    public Mountain getHighestMountainClimbed() {
        int mountainHeight = 0;
        Mountain mountain = null;

        for (Climber c : climbersInClub) {
            if (c.getHighestMountainClimbed().getHeightInMetres() > mountainHeight) {
                mountainHeight = c.getHighestMountainClimbed().getHeightInMetres();
                mountain = c.getHighestMountainClimbed();
            }
        }
        return mountain;
    }

    //Setters

    /**
     * Sets club name, unused in this application
     *
     * @param clubName String, new name for club
     */
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    /**
     * Adds a climber to the club's record
     *
     * @param climber Climber to add to club
     */
    public void addClimberToClub(Climber climber) {
        climbersInClub.add(climber);
    }

    /**
     * Add a mountain to the club's record. New mountains are added at index 0 to assist methods in ClubStats.java
     *
     * @param mountain Mountain to add to club
     */
    public void addMountainToClub(Mountain mountain) {
        mountainsRecorded.add(0, mountain);
    }
}
