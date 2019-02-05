import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which contains main method and allows user interaction with the application
 *
 * @author Andrew Brown
 * @version November 2017
 */

public class ClubStats {
    private BufferedReader inputText = new BufferedReader(new InputStreamReader(System.in));
    private Scanner input;
    private Club mistyMountains = new Club("Misty Mountains");

    public static void main(String[] args) throws IOException {
        new ClubStats();
    }

    /**
     * Calls the main menu method
     *
     * @throws IOException
     */
    public ClubStats() throws IOException {
        showMainMenu();
    }

    //Menu Options

    /**
     * Shows the main menu.
     * Greets the user with the name of the club and the date of user's machine.
     * Presents a list of options which can be navigated through keyboard input.
     *
     * @throws IOException
     */
    public void showMainMenu() throws IOException {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = date.format(formatter);

        System.out.println("------------------------------------------------------------");
        System.out.println("          Welcome to " + mistyMountains.getClubName() + " Climbing Club          ");
        System.out.println("               Today's Date: " + formattedDate);
        System.out.println("------------------------------------------------------------");
        System.out.println("                        MAIN MENU                           ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" [1] Mountain Menu                                          ");
        System.out.println(" [2] Climber Menu                                           ");
        System.out.println(" [3] Club Menu                                              ");
        System.out.println("                                                            ");
        System.out.println(" [X] Exit                                                   ");
        System.out.println("------------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Selection: ");

        String menuOption;
        input = new Scanner(System.in);
        menuOption = input.next().toUpperCase();
        switch (menuOption) {
            case "1":
                showMountainMenu();
                break;

            case "2":
                showClimberMenu();
                break;

            case "3":
                showClubMenu();
                break;

            case "X":
                quitProgram();
                break;

            default:
                System.out.println("Invalid Option");
                showMainMenu();
                break;
        }
    }

    /**
     * Shows mountain menu.
     * <p>
     * Provides user with options to add and manipulate mountains in the club's records.
     * There are also options to return to main menu and another sub menu to add preset mountains.
     * <p>
     * Case 3 to edit height, the user is asked which mountain to edit, it then checks if that mountain exists, if so
     * the method to edit the mountain height runs. If the mountain isn't found the user is given a message to say so.
     *
     * @throws IOException
     */
    public void showMountainMenu() throws IOException {
        System.out.println("");
        System.out.println("------------------------------------------------------------");
        System.out.println("                      MOUNTAIN MENU                         ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" [1] Add mountain to club record                            ");
        System.out.println(" [2] Edit a mountain's name                                 ");
        System.out.println(" [3] Edit a mountain's height                               ");
        System.out.println(" [4] List all recorded mountains                            ");
        System.out.println("                                                            ");
        System.out.println(" [A] Add preset lists of mountains to club record           ");
        System.out.println("                                                            ");
        System.out.println(" [X] Exit            [M] Main Menu                          ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Selection: ");

        String menuOption;
        menuOption = input.next().toUpperCase();
        switch (menuOption) {
            case "1":
                addNewMountainToClubRecord();
                showMountainMenu();
                break;

            case "2":
                editMountainName();
                showMountainMenu();
                break;

            case "3":
                System.out.print("Which mountain would you like to edit? ");
                String mountainHeightSelection = inputText.readLine();
                Mountain mountainToEdit = null;
                for (Mountain m : mistyMountains.getMountainsRecorded()) {
                    if (m.getMountainName().toLowerCase().equalsIgnoreCase(mountainHeightSelection)) {
                        mountainToEdit = m;
                        editMountainHeight(mountainToEdit);
                    } else {
                        System.out.println("Cannot find that mountain");
                    }
                }
                showMountainMenu();
                break;

            case "4":
                listMountainsRecorded();
                showMountainMenu();
                break;

            case "A":
                showMountainPresetsMenu();
                break;

            case "M":
                showMainMenu();
                break;

            case "X":
                quitProgram();
                break;

            default:
                System.out.println("Invalid Option");
                showMountainMenu();
                break;
        }
    }

    /**
     * Shows a menu of preset lists of mountains.
     * The menu can be expanded as more preset lists are made.
     *
     * @throws IOException
     */
    public void showMountainPresetsMenu() throws IOException {
        System.out.println("");
        System.out.println("------------------------------------------------------------");
        System.out.println("                   MOUNTAIN PRESETS MENU                    ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" [1] Lake District                                          ");
        System.out.println(" [2] Peak District                                          ");
        System.out.println("                                                            ");
        System.out.println(" [X] Exit           [M] Main Menu        [T] Mountain Menu  ");
        System.out.println("------------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Selection: ");

        String menuOption;
        menuOption = input.next().toUpperCase();
        switch (menuOption) {
            case "1":
                addLakeDistrictMountains();
                showMountainMenu();
                break;

            case "2":
                addPeakDistrictMountains();
                showMountainMenu();
                break;

            case "M":
                showMainMenu();
                break;

            case "T":
                showMountainMenu();
                break;

            case "X":
                quitProgram();
                break;

            default:
                System.out.println("Invalid Option");
                showMountainMenu();
                break;
        }
    }

    /**
     * Shows climber menu.
     * Presents options to add a climber, edit a climber's name, date of birth or gender, add or remove a mountain from
     * a climber's record and list all climbers in the club.
     * Also an option to access a separate menu to check stats of a particular climber.
     * <p>
     * Case 3 asks which climber the user would like to edit, checks if that climber exists and if so passes the climber
     * into the editDateOfBirth Method
     * <p>
     * Case S asks which climber the uer would like to check stats for and checks if they exist. If they do the
     * found climber is passed into the showClimberStats method.
     *
     * @throws IOException
     */
    public void showClimberMenu() throws IOException {
        System.out.println("");
        System.out.println("------------------------------------------------------------");
        System.out.println("                       CLIMBER MENU                         ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" [1] Add a climber to the club                              ");
        System.out.println(" [2] Edit a climber's name                                  ");
        System.out.println(" [3] Edit a climber's date of birth                         ");
        System.out.println(" [4] Edit a climber's gender                                ");
        System.out.println(" [5] Add a mountain to a climber's record                   ");
        System.out.println(" [6] Remove a mountain from a climber's record              ");
        System.out.println(" [7] List all climbers in the club                          ");
        System.out.println("                                                            ");
        System.out.println(" [S] Climber stats menu                                     ");
        System.out.println("                                                            ");
        System.out.println(" [X] Exit            [M] Main Menu                          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Selection: ");

        String menuOption;
        input = new Scanner(System.in);
        menuOption = input.next().toUpperCase();

        switch (menuOption) {
            case "1":
                addNewClimberToClub();
                showClimberMenu();
                break;

            case "2":
                editClimberName();
                showClimberMenu();
                break;

            case "3":
                Climber climberDateOfBirth = null;
                System.out.print("Which climber would you like to edit? ");
                String dobChoice = inputText.readLine();
                for (Climber c : mistyMountains.getClimbersInClub()) {
                    if (c.getClimberName().toLowerCase().equalsIgnoreCase(dobChoice)) {
                        climberDateOfBirth = c;
                    }
                }
                if (climberDateOfBirth == null) {
                    System.out.println("Cannot find that climber");
                    showClimberMenu();
                }
                editDateOfBirth(climberDateOfBirth);
                showClimberMenu();
                break;

            case "4":
                editClimberGender();
                showClimberMenu();
                break;

            case "5":
                addMountainToClimber();
                showClimberMenu();

            case "6":
                removeMountainFromClimber();
                showClimberMenu();

            case "7":
                listClimbers();
                showClimberMenu();
                break;

            case "S":
                Climber climber = null;
                System.out.print("Which climber would you like to check stats for? ");
                String choice = inputText.readLine();
                for (Climber c : mistyMountains.getClimbersInClub()) {
                    if (c.getClimberName().toLowerCase().equalsIgnoreCase(choice)) {
                        climber = c;
                    }
                }
                if (climber == null) {
                    System.out.println("Cannot find that climber");
                    showClimberMenu();
                }
                showClimberStatsMenu(climber);
                break;

            case "M":
                showMainMenu();
                break;

            case "X":
                quitProgram();
                break;

            default:
                System.out.println("Invalid Option");
                showClimberMenu();
                break;
        }
    }

    /**
     * Shows the climber stats menu.
     * The menu shows the climber's name for which the stats are being checked for.
     * Also provides option to check a different climber from this menu which calls the showClimberStats menu with a
     * given climber
     *
     * @param climber Climber to check stats for
     * @throws IOException
     */
    public void showClimberStatsMenu(Climber climber) throws IOException {
        System.out.println("");
        System.out.println("------------------------------------------------------------");
        System.out.println("                  " + climber.getClimberName().toUpperCase() + ": CLIMBER STATS");
        System.out.println("------------------------------------------------------------");
        System.out.println(" [1] Highest mountain climbed                               ");
        System.out.println(" [2] Average height climbed                                 ");
        System.out.println(" [3] Total height climbed                                   ");
        System.out.println(" [4] Mountains climbed above a given level                  ");
        System.out.println(" [5] Show full details of climber                           ");
        System.out.println("                                                            ");
        System.out.println(" [S] Check stats for a different climber                    ");
        System.out.println("                                                            ");
        System.out.println(" [X] Exit            [M] Main Menu      [C] Climber Menu    ");
        System.out.println("------------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Selection: ");

        String option;
        input = new Scanner(System.in);
        option = input.next().toUpperCase();

        switch (option) {
            case "1":
                highestMountainClimbed(climber);
                showClimberStatsMenu(climber);
                break;

            case "2":
                climbersAverageHeightClimbed(climber);
                showClimberStatsMenu(climber);
                break;

            case "3":
                climbersTotalHeightClimbed(climber);
                showClimberStatsMenu(climber);
                break;

            case "4":
                mountainsAboveX(climber);
                showClimberStatsMenu(climber);
                break;

            case "5":
                showClimberDetails(climber);
                showClimberStatsMenu(climber);
                break;

            case "S":
                Climber nextClimber = null;
                System.out.print("Which climber would you like to check stats for? ");
                String choice = inputText.readLine();
                for (Climber c : mistyMountains.getClimbersInClub()) {
                    if (c.getClimberName().toLowerCase().equalsIgnoreCase(choice)) {
                        nextClimber = c;
                    }
                }

                if (nextClimber == null) {
                    System.out.println("Cannot find that climber");
                    showClimberStatsMenu(climber);
                }
                showClimberStatsMenu(nextClimber);
                break;

            case "C":
                showClimberMenu();
                break;

            case "M":
                showMainMenu();
                break;

            case "X":
                quitProgram();
                break;

            default:
                System.out.println("Invalid Option");
                showClimberStatsMenu(climber);
                break;
        }
    }

    /**
     * Shows the club menu
     *
     * @throws IOException
     */
    public void showClubMenu() throws IOException {
        System.out.println("");
        System.out.println("------------------------------------------------------------");
        System.out.println("                         CLUB MENU                          ");
        System.out.println("------------------------------------------------------------");
        System.out.println(" [1] Show climber with the highest average height climbed   ");
        System.out.println(" [2] Show climber with the highest total height climbed     ");
        System.out.println(" [3] Show the highest mountain climbed by any climber       ");
        System.out.println(" [4] List all mountains climbed above a given level         ");
        System.out.println("                                                            ");
        System.out.println(" [X] Exit            [M] Main Menu                          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Selection: ");

        String option;
        input = new Scanner(System.in);
        option = input.next().toUpperCase();

        switch (option) {
            case "1":
                clubHighestAverageHeight();
                showClubMenu();
                break;

            case "2":
                clubHighestTotalHeight();
                showClubMenu();
                break;

            case "3":
                clubHighestMountainClimbed();
                showClubMenu();
                break;

            case "4":
                clubMountainsClimbedAboveGivenLevel();
                showClubMenu();
                break;

            case "M":
                showMainMenu();
                break;

            case "X":
                quitProgram();
                break;

            default:
                System.out.println("Invalid Option");
                showClimberMenu();
                break;
        }

    }

    /**
     * Ends the program.
     * Checks to make sure the user intended to do so. This method is available in all menus throughout the application.
     * A confirmed exit ends the program with status code 0 and wishes the user goodbye.
     *
     * @throws IOException
     */
    public void quitProgram() throws IOException {
        System.out.println("\nAre you sure you would like to quit? (Y)es/(N)o");
        String selection = input.next();
        if (selection.equalsIgnoreCase("Y")) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        } else {
            showMainMenu();
        }

    }

    //Mountain Menu Options

    /**
     * Iterates through existing mountains within club record and add the name to an array. Then asks the user to enter
     * the name of a mountain they wish to add. If the name provided is in the list of mountains previously checked,
     * the user is informed that that mountain  is already in the record and asks the user if they would like to add a
     * different one.
     * <p>
     * If the mountain doesn't exist then the user is asked for the height of the mountain. If the height provided is
     * below 0 then the user is instructed to enter a positive whole number. Both the height provided and name taken
     * earlier are passed into the addMountainToClub method.
     */
    public void addNewMountainToClubRecord() {
        ArrayList<String> existingMountainNames = new ArrayList<>();
        for (Mountain m : mistyMountains.getMountainsRecorded()) {
            existingMountainNames.add(m.getMountainName().toLowerCase());
        }

        try {
            System.out.print("Enter mountain name: ");
            String mountainName = inputText.readLine();

            if (existingMountainNames.contains(mountainName.toLowerCase())) {
                System.out.println("There is already a mountain with that name");
                System.out.print("Would you like to continue? (Y)es/(N)o ");
                String continueOption = inputText.readLine();
                if (continueOption.equalsIgnoreCase("Y")) {
                    System.out.print("Enter mountain name: ");
                    mountainName = inputText.readLine();
                } else {
                    showMountainMenu();
                }
            }

            System.out.print("Enter mountain height in meters: ");
            int mountainHeight = input.nextInt();
            if (mountainHeight < 0) {
                System.out.println("Please use a positive whole number");
            } else {
                mistyMountains.addMountainToClub(new Mountain(mountainName, mountainHeight));
                System.out.println("Mountain added to club record");
            }
        } catch (Exception e) {
            System.out.println("Invalid: please use positive whole numbers for height");
            input.next();
        }
    }

    /**
     * Asks the user which mountain they would like to edit and checks through the list of recorded mountains in the
     * club. Method then asks the user what they would like to change the name too and passes the resulting String into
     * the setMountainName method
     *
     * @throws IOException
     */
    public void editMountainName() throws IOException {
        System.out.print("Which mountain would you like to edit? ");
        String selection = inputText.readLine();

        for (Mountain m : mistyMountains.getMountainsRecorded()) {
            if (m.getMountainName().toLowerCase().equalsIgnoreCase(selection)) {
                System.out.print("What would you like to change the name to? ");
                String newName = inputText.readLine();
                m.setMountainName(newName);
                System.out.println("Name changed successfully");
            } else {
                System.out.println("Cannot find that mountain");
            }
        }
    }

    /**
     * Asks the user what they would like the height to be changed to, checks if the number is a positive number and
     * if not, the method is called on again. If the entered number is valid, the setHeightInMetres method is called
     * passing the height given as a parameter
     *
     * @param mountain Mountain to edit
     * @throws IOException
     */
    public void editMountainHeight(Mountain mountain) throws IOException {
        System.out.print("What would you like to change the height to? ");
        try {
            int newHeight = input.nextInt();
            if (newHeight < 0) {
                System.out.println("Invalid: please use positive whole numbers\n");
                editMountainHeight(mountain);
            } else {
                mountain.setHeightInMetres(newHeight);
                System.out.println("Height changed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Invalid: please use positive whole numbers\n");
        }
    }

    /**
     * Iterates through the list of mountains recorded by the club and prints the name and height of each onto a new
     * line. If there are no mountains in the list, the user is informed
     */
    public void listMountainsRecorded() {
        for (Mountain m : mistyMountains.getMountainsRecorded()) {
            String mName = m.getMountainName();
            int mHeight = m.getHeightInMetres();
            System.out.print("Name: " + mName + " - ");
            System.out.println("Height: " + mHeight + "m");
        }

        if(mistyMountains.getMountainsRecorded().size() == 0) {
            System.out.println("There are no mountains, add some.");
        }
    }

    //Climber Menu Options

    /**
     * Asks the user for a name, a date of birth and gender and passes each variable into the addClimberToClub method.
     * When entering the date of birth, the user is asked to enter in the format dd-mm-yyyy, and also checks that the
     * date is not in the future. The method will keep asking for the date of birth until a valid date is entered.
     *
     * @throws IOException
     */
    public void addNewClimberToClub() throws IOException {
        System.out.print("Enter Name: ");
        String climberName = inputText.readLine();

        System.out.print("Enter Date of Birth in format (dd-mm-yyyy): ");
        LocalDate climberDateOfBirth = null;
        while (climberDateOfBirth == null) {
            try {
                String input = inputText.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                if (LocalDate.parse(input, formatter).isAfter(LocalDate.now())) {
                    System.out.print("That date is in the future, try again: ");
                } else {
                    climberDateOfBirth = LocalDate.parse(input, formatter);
                }
            } catch (Exception e) {
                System.out.print("Cannot recognise date of birth, try again: ");
            }
        }

        System.out.print("Enter Gender: ");
        String climberGender = inputText.readLine();

        mistyMountains.addClimberToClub(new Climber(climberName, climberDateOfBirth, climberGender));

    }

    /**
     * Asks the user which climber they would like to edit and checks against the list of recorded climbers. If the
     * climber is found the user is then asked what they would like to change the name to.
     *
     * @throws IOException
     */
    public void editClimberName() throws IOException {
        System.out.print("Which climber would you like to edit? ");
        String selection = inputText.readLine();

        for (Climber c : mistyMountains.getClimbersInClub()) {
            if (c.getClimberName().toLowerCase().equalsIgnoreCase(selection)) {
                System.out.print("What would you like to change the name to? ");
                String newName = inputText.readLine();
                c.setClimberName(newName);
                System.out.println("Name changed successfully.");
            }
        }
    }

    /**
     * Asks the user what they would like to change the date of birth to for the climber passed into method. The
     * provided date is validated to make sure it is not in the future and keeps asking until a valid date of birth is
     * entered
     *
     * @param climber Climber to edit
     * @throws IOException
     */
    public void editDateOfBirth(Climber climber) throws IOException {
        LocalDate newDateOfBirth = null;
        System.out.print("What would you like to change the date of birth to? (dd-mm-yyyy): ");
        while (newDateOfBirth == null) {
            try {
                String input = inputText.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                newDateOfBirth = LocalDate.parse(input, formatter);
                if (newDateOfBirth.isAfter(LocalDate.now())) {
                    System.out.println("That date is in the future");
                    break;
                }
                climber.setClimberDateOfBirth(newDateOfBirth);
                System.out.println("Date of Birth changed successfully.");
            } catch (Exception e) {
                System.out.print("Cannot recognise date of birth, try again: ");
            }
        }
    }

    /**
     * Asks which climber to edit, and checks against list of climbers, if climber is found the user is then asked what
     * they would like to change the gender to
     *
     * @throws IOException
     */
    public void editClimberGender() throws IOException {
        System.out.print("Which climber would you like to edit? ");
        String selection = inputText.readLine();

        for (Climber c : mistyMountains.getClimbersInClub()) {
            if (c.getClimberName().toLowerCase().equalsIgnoreCase(selection)) {
                System.out.print("What would you like to change the gender to? ");
                String newGender = inputText.readLine();
                c.setClimberGender(newGender);
                System.out.println("Gender changed successfully.");
            }
        }
    }

    /**
     * Asks the user which climber they would like to add a mountain to their record. If the climber is found the user
     * is then asked which mountain they would like to add. The method then checks against the list of recorded
     * mountains in the club's record and if it is found the mountain is then added to the climber's record.
     * <p>
     * If the mountain cannot be found the user is asked if they would like to add a mountain to the club record and to
     * have it immediately added to the climber's record. If they reply yes, the addNewMountainToClubRecord method is
     * called and then the mountain at index 0 of the mountain record is added to the climber.
     * <p>
     * If the climber requested isn't found then the user is asked if they would like to add a climber to the club's
     * record.
     * <p>
     * The same mountain can be added to a climber's record more than once.
     *
     * @throws IOException
     */
    public void addMountainToClimber() throws IOException {
        System.out.print("Which climber would you like to add a mountain to? ");
        String climberToAdd = inputText.readLine();
        Climber climber = null;
        Mountain mountain = null;

        for (Climber c : mistyMountains.getClimbersInClub()) {
            if (c.getClimberName().equalsIgnoreCase(climberToAdd)) {
                climber = c;
                System.out.print("Which mountain would you like to add? ");
                String mountainToAdd = inputText.readLine();

                for (Mountain m : mistyMountains.getMountainsRecorded()) {
                    if (m.getMountainName().equalsIgnoreCase(mountainToAdd)) {
                        mountain = m;
                        c.addMountainToClimber(m);
                        System.out.println("Mountain added");
                    }
                }

                if (mountain == null) {
                    System.out.println("Cannot find that mountain");
                    System.out.print("Would you like to add one to the club's database and add it to "
                            + climber.getClimberName() + "'s record? (Y)es/(N)o ");
                    String response = inputText.readLine();
                    if (response.equalsIgnoreCase("Y")) {
                        addNewMountainToClubRecord();
                        climber.addMountainToClimber(mistyMountains.getMountainsRecorded().get(0));
                        System.out.println(mistyMountains.getMountainsRecorded().get(0).getMountainName() +
                                " has been added to " + climber.getClimberName() + "'s record");
                    }
                }
            }
        }

        if (climber == null) {
            System.out.println("Cannot find that climber");
            System.out.print("Would you like to add a climber to the club's database? (Y)es/(N)o ");
            String response = inputText.readLine();
            if (response.equalsIgnoreCase("Y")) {
                addNewClimberToClub();
            }
        }
    }

    /**
     * Asks the user which climber the user would like to remove a mountain from and checks the list of climbers and
     * adds the climber to a variable.
     * <p>
     * The user is then asked what mountain they would like to remove and checks against that climber's record of
     * mountains climbed. If the mountain is present the mountain is added to a variable.
     * <p>
     * If the climber variable is null, the user is informed that the climber cannot be found. If the mountain variable
     * is null, the user is informed that the climber hasn't climbed that mountain. If both climber and mountain are not
     * null then the remove method is called on the mountains climbed arraylist for the climber with the mountain as the
     * given parameter.
     *
     * @throws IOException
     */
    public void removeMountainFromClimber() throws IOException{
        System.out.print("Which climber would you like to remove a mountain from? ");
        String climberToEdit = inputText.readLine();
        Climber climber = null;
        Mountain mountain = null;

        for (Climber c : mistyMountains.getClimbersInClub()) {
            if (c.getClimberName().equalsIgnoreCase(climberToEdit)) {
                climber = c;
                System.out.print("Which mountain would you like to remove? ");
                String mountainToRemove = inputText.readLine();

                for (Mountain m : c.getMountainsClimbed()) {
                    if (m.getMountainName().equalsIgnoreCase(mountainToRemove)) {
                        mountain = m;

                    }
                }

                if (mountain == null) {
                    System.out.println(c.getClimberName() + " hasn't climbed that mountain");
                }
            }
        }

        if (climber == null) {
            System.out.println("Cannot find that climber");
        }

        if (climber != null && mountain != null) {
            climber.getMountainsClimbed().remove(mountain);
            System.out.println("Mountain removed");
        }
    }

    /**
     * Iterated through the list of climbers stored in the club and calls the showClimberDetails method on each climber.
     * If there are no climbers in the list the user is informed.
     */
    public void listClimbers() {
        for (Climber c : mistyMountains.getClimbersInClub()) {
            showClimberDetails(c);
        }

        if (mistyMountains.getClimbersInClub().size() == 0) {
            System.out.println("There are no climbers, add some.");
        }
    }

    //Climber Stats Menu Options

    /**
     * Finds the climber passed through the method and prints details on the highest mountain climbed
     *
     * @param climber Climber to find highest mountain
     * @throws IOException
     */
    public void highestMountainClimbed(Climber climber) throws IOException {
        Mountain mountainToFind = null;

        for (Climber c : mistyMountains.getClimbersInClub()) {

            if(c == climber) {
                mountainToFind = c.getHighestMountainClimbed();
                System.out.println("\nHighest Mountain " + c.getClimberName() + " has climbed");
                System.out.print("\nName: " + mountainToFind.getMountainName() + " - ");
                System.out.println("Height: " + mountainToFind.getHeightInMetres() + "m");
            }
        }
    }

    /**
     * Finds the climber passed into the method and prints results of getAverageHeightOfMountains
     *
     * @param climber Climber to find average height for
     * @throws IOException
     */
    public void climbersAverageHeightClimbed(Climber climber) throws IOException {
        for (Climber c : mistyMountains.getClimbersInClub()) {

            if(c == climber) {
                System.out.print("\nAverage height of mountains " + c.getClimberName() + " has climbed is: ");
                System.out.println(c.getAverageHeightOfMountains() + "m\n");
            }
        }
    }

    /**
     * Finds the climber passed into the method and prints the results of getTotalHeightClimber
     *
     * @param climber Climber to find total height climbed
     */
    public void climbersTotalHeightClimbed(Climber climber) {
        for (Climber c : mistyMountains.getClimbersInClub()) {

            if(c == climber) {
                System.out.print("\nTotal height of mountains " + c.getClimberName() + " has climbed is: ");
                System.out.println(c.getTotalHeightClimbed() + "m\n");
            }
        }
    }

    /**
     * Asks the user what height to find mountains higher than, then prints the list in ascending order.
     *
     * @param climber Climber to find mountains for
     * @throws IOException
     */
    public void mountainsAboveX(Climber climber) throws IOException {
        System.out.print("From which height do you want to see mountains higher than? ");
        try {
            int mountainHeight = input.nextInt();

            for (Climber c : mistyMountains.getClimbersInClub()) {
                if (c == climber) {
                    System.out.println("\nMountains that " + climber.getClimberName() + " has climbed above "
                            + mountainHeight + " meters are:");
                    for (Mountain m : climber.getMountainsHigherThanX(mountainHeight)) {
                        System.out.print("\tName: " + m.getMountainName() + " - ");
                        System.out.println("Height: " + m.getHeightInMetres() + "m");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Invalid: Please enter a whole number");
        }
    }

    /**
     * Prints details of a given climber. Prints the name, age (Calculated from date of birth compared to the date on
     * the user's system), gender and lists the mountains climbed
     *
     * @param climber Climber to return details for
     */
    public void showClimberDetails(Climber climber) {
        String cName = climber.getClimberName();
        LocalDate cDOB = climber.getClimberDateOfBirth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        long age = ChronoUnit.YEARS.between(cDOB, LocalDate.now());
        String cGender = climber.getClimberGender();
        ArrayList<Mountain> cMountains = climber.getMountainsClimbed();

        System.out.println("");
        System.out.println("Name: " + cName);
        System.out.println("Date of Birth: " + cDOB.format(formatter));
        System.out.println("Age: " + age);
        System.out.println("Gender: " + cGender);
        System.out.println("Mountains Climbed: ");
        for (Mountain m : cMountains) {
            System.out.print("\tName: " + m.getMountainName() + " - ");
            System.out.println("Height: " + m.getHeightInMetres() + "m");
        }
    }

    //Club Menu Options

    /**
     * Checks list of climbers in the club and if the size is 0, informs the user there are no climbers. Otherwise the
     * method getClimberWithHighestAverageHeight is called and the name and average height are returned to the user.
     */
    public void clubHighestAverageHeight() {
        if (mistyMountains.getClimbersInClub().size() == 0) {
            System.out.println("There are no climbers to get stats for");
        } else {
            System.out.println("The climber with the highest average height climbed is: "
                    + mistyMountains.getClimberWithHighestAverageHeight().getClimberName());
            System.out.println("With an average height of: "
                    + mistyMountains.getClimberWithHighestAverageHeight().getAverageHeightOfMountains() + "m");
        }
    }

    /**
     * Checks list of climbers in the club and if the size is 0, informs the user there are no climbers. Otherwise
     * getClimberWithHighestTotalHeight is ran and the name of climber and total height climbed are returned to the
     * user.
     */
    public void clubHighestTotalHeight() {
        if (mistyMountains.getClimbersInClub().size() == 0) {
            System.out.println("There are no climbers to get stats for");
        } else {
            System.out.println("The climber with the highest total height climbed is: "
                    + mistyMountains.getClimberWithHighestTotalHeight().getClimberName());
            System.out.println("With a total height of: "
                    + mistyMountains.getClimberWithHighestTotalHeight().getTotalHeightClimbed() + "m");
        }
    }

    /**
     * Checks list of climbers in the club and if the size is 0, informs the user there are no climbers. Otherwise, the
     * getHighestMountainClimbed method is called and result added to a variable. Next each climber is checked to see
     * if they have climbed the found mountain. If so they are added to an ArrayList. The method then prints the name
     * of the mountain climbed and a list of the climbers who have climbed it.
     */
    public void clubHighestMountainClimbed() {
        if (mistyMountains.getClimbersInClub().size() == 0) {
            System.out.println("There are no climbers to get stats for");
        } else {
            Mountain mountain = mistyMountains.getHighestMountainClimbed();
            ArrayList<Climber> climbers = new ArrayList<>();
            for (Climber c : mistyMountains.getClimbersInClub()) {
                if (c.getMountainsClimbed().contains(mountain)) {
                    climbers.add(c);
                }
            }

            System.out.println("The highest mountain climbed by any climber is " + mountain.getMountainName());
            System.out.println("It has been climbed by: ");
            for (Climber c : climbers) {
                System.out.println("\tName: " + c.getClimberName());
            }
        }
    }

    /**
     * Checks list of climbers in the club and if the size is 0, informs the user there are no climbers. Otherwise, asks
     * user for a height to check, then calls getMountainsClimbedAboveX method and prints a list of mountains meeting
     * the criteria. Also, checks for the size of the resulting ArrayList from that method and if its size is 0, informs
     * the user that no mountains have been climbed above the given level
     */
    public void clubMountainsClimbedAboveGivenLevel() {
        if (mistyMountains.getClimbersInClub().size() == 0) {
            System.out.println("There are no climbers to get stats for");
        } else {
            System.out.print("Enter a height to see all mountains climbed above that level: ");
            try {
                int selectedHeight = input.nextInt();

                for (Mountain m : mistyMountains.getMountainsClimbedAboveX(selectedHeight)) {
                    System.out.println("Name: " + m.getMountainName() + " - Height: " + m.getHeightInMetres());
                }

                if (mistyMountains.getMountainsClimbedAboveX(selectedHeight).size() == 0) {
                    System.out.println("There are no mountains climbed above that level");
                }
            } catch (Exception e) {
                System.out.println("Invalid: Please use a whole number");
            }
        }
    }

    //Mountain Presets Options

    /**
     * Contains a pre-made list of mountains for the Lake District National Park in England, UK. The method generates an
     * ArrayList of mountain names already in the clubs record. The method then checks each mountain name in the pre-made
     * list and if it doesn't exist in the club's record it is added, otherwise it is skipped.
     */
    public void addLakeDistrictMountains() {
        ArrayList<Mountain> lakeDistrictMountains = new ArrayList<>();
        lakeDistrictMountains.add(new Mountain("Helvellyn", 950));
        lakeDistrictMountains.add(new Mountain("Scafell Pike", 978));
        lakeDistrictMountains.add(new Mountain("Skiddaw", 931));
        lakeDistrictMountains.add(new Mountain("Old Man of Coniston", 803));
        lakeDistrictMountains.add(new Mountain("Great Gable", 899));
        lakeDistrictMountains.add(new Mountain("Blencathra", 868));
        lakeDistrictMountains.add(new Mountain("Cat Bells", 451));
        lakeDistrictMountains.add(new Mountain("Loughrigg Fell", 335));
        lakeDistrictMountains.add(new Mountain("Bowfell", 902));
        lakeDistrictMountains.add(new Mountain("Fairfield", 873));
        lakeDistrictMountains.add(new Mountain("Mellbreak", 512));
        lakeDistrictMountains.add(new Mountain("Place Fell", 657));
        lakeDistrictMountains.add(new Mountain("Grisedale Pike", 791));
        lakeDistrictMountains.add(new Mountain("Grasmoor", 852));
        lakeDistrictMountains.add(new Mountain("High Stile", 807));
        lakeDistrictMountains.add(new Mountain("Great End", 910));
        lakeDistrictMountains.add(new Mountain("Catstye Cam", 890));
        lakeDistrictMountains.add(new Mountain("Crinkle Crags", 859));
        lakeDistrictMountains.add(new Mountain("Castle Crag", 290));
        lakeDistrictMountains.add(new Mountain("Robinson", 737));
        lakeDistrictMountains.add(new Mountain("Sheffield Pike", 675));
        lakeDistrictMountains.add(new Mountain("Kirk Fell", 802));
        lakeDistrictMountains.add(new Mountain("High Raise", 762));
        lakeDistrictMountains.add(new Mountain("Esk Pike", 885));
        lakeDistrictMountains.add(new Mountain("Wetherlam", 763));
        lakeDistrictMountains.add(new Mountain("Eagle Crag", 521));
        lakeDistrictMountains.add(new Mountain("Dollywagon Pike", 858));
        lakeDistrictMountains.add(new Mountain("Glaramara", 783));
        lakeDistrictMountains.add(new Mountain("Red Screes", 776));
        lakeDistrictMountains.add(new Mountain("Helm Crag", 405));
        lakeDistrictMountains.add(new Mountain("Great Rigg", 766));
        lakeDistrictMountains.add(new Mountain("Harrison Stickle", 736));
        lakeDistrictMountains.add(new Mountain("Walla Crag", 379));
        lakeDistrictMountains.add(new Mountain("Causey Pike", 637));
        lakeDistrictMountains.add(new Mountain("Nethermost Pike", 891));
        lakeDistrictMountains.add(new Mountain("Whinlatter", 525));
        lakeDistrictMountains.add(new Mountain("Clough Head", 726));
        lakeDistrictMountains.add(new Mountain("Dale Head", 753));
        lakeDistrictMountains.add(new Mountain("Fleetwith Pike", 648));
        lakeDistrictMountains.add(new Mountain("Harter Fell", 649));
        lakeDistrictMountains.add(new Mountain("Pavey Ark", 700));
        lakeDistrictMountains.add(new Mountain("Crag Hill", 839));
        lakeDistrictMountains.add(new Mountain("Dow Crag", 778));
        lakeDistrictMountains.add(new Mountain("Red Pike", 755));
        lakeDistrictMountains.add(new Mountain("High Spy", 653));
        lakeDistrictMountains.add(new Mountain("Hart Crag", 803));
        lakeDistrictMountains.add(new Mountain("Great Dodd", 857));
        lakeDistrictMountains.add(new Mountain("Dove Crag", 792));
        lakeDistrictMountains.add(new Mountain("St Sunday Crag", 841));
        lakeDistrictMountains.add(new Mountain("High Seat", 608));
        lakeDistrictMountains.add(new Mountain("Sca Fell", 964));

        ArrayList<String> existingMountainNames = new ArrayList<>();
        for (Mountain m : mistyMountains.getMountainsRecorded()) {
            existingMountainNames.add(m.getMountainName());
        }

        for (Mountain m1 : lakeDistrictMountains) {
            if (!existingMountainNames.contains(m1.getMountainName())) {
                mistyMountains.addMountainToClub(m1);
            }
        }

        System.out.println("Mountains Added");
    }

    /**
     * Contains a pre-made list of mountains for the Peak District National Park in England, UK. The method generates an
     * ArrayList of mountain names already in the clubs record. The method then checks each mountain name in the pre-made
     * list and if it doesn't exist in the club's record it is added, otherwise it is skipped.
     */
    public void addPeakDistrictMountains() {
        ArrayList<Mountain> peakDistrictMountains = new ArrayList<>();
        peakDistrictMountains.add(new Mountain("Bleaklow", 633));
        peakDistrictMountains.add(new Mountain("Derwent Edge", 538));
        peakDistrictMountains.add(new Mountain("Win Hill", 463));
        peakDistrictMountains.add(new Mountain("Black Hill", 582));
        peakDistrictMountains.add(new Mountain("Rushup Edge", 550));
        peakDistrictMountains.add(new Mountain("Lose Hill", 476));
        peakDistrictMountains.add(new Mountain("Axe Edge Moor", 551));
        peakDistrictMountains.add(new Mountain("Shining Tor", 559));
        peakDistrictMountains.add(new Mountain("Shutlingsloe", 506));
        peakDistrictMountains.add(new Mountain("Higger Tor", 434));
        peakDistrictMountains.add(new Mountain("Margery Hill", 546));
        peakDistrictMountains.add(new Mountain("Eldon Hill", 470));
        peakDistrictMountains.add(new Mountain("Grindslow Knoll", 601));
        peakDistrictMountains.add(new Mountain("Black Chew Head", 542));
        peakDistrictMountains.add(new Mountain("Crook Hill", 382));
        peakDistrictMountains.add(new Mountain("Great End", 910));
        peakDistrictMountains.add(new Mountain("Black Edge", 507));
        peakDistrictMountains.add(new Mountain("Thorpe Cloud", 287));
        peakDistrictMountains.add(new Mountain("Gun", 373));
        peakDistrictMountains.add(new Mountain("The Cloud", 343));
        peakDistrictMountains.add(new Mountain("Alport Height", 314));
        peakDistrictMountains.add(new Mountain("Cheeks Hill", 520));
        peakDistrictMountains.add(new Mountain("Howden Edge", 545));
        peakDistrictMountains.add(new Mountain("Lantern Pike", 373));
        peakDistrictMountains.add(new Mountain("Wild Bank", 399));
        peakDistrictMountains.add(new Mountain("Harridge Pike", 395));
        peakDistrictMountains.add(new Mountain("High Stones", 548));
        peakDistrictMountains.add(new Mountain("Grasmoor", 852));
        peakDistrictMountains.add(new Mountain("Holme Moss", 524));
        peakDistrictMountains.add(new Mountain("Croker Hill", 402));

        ArrayList<String> existingMountainNames = new ArrayList<>();
        for (Mountain m : mistyMountains.getMountainsRecorded()) {
            existingMountainNames.add(m.getMountainName());
        }

        for (Mountain m1 : peakDistrictMountains) {
            if (!existingMountainNames.contains(m1.getMountainName())) {
                mistyMountains.addMountainToClub(m1);
            }
        }

        System.out.println("Mountains Added");
    }
}