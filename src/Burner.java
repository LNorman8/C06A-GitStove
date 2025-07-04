/*
 * Authors: Steven Nguyen and Luke Norman
 * Date: 07/04/25
 * Collaborators: None
 * Sources: geeksforgeeks.org/java/enhancements-for-switch-statement-in-java-13
 */

public class Burner {
    public enum Temperature { // Set output values equivalent to figure
        ICECOLD("cooool"),
        WARMINGUP("warm"),
        GETTINGHOT("CAREFUL"),
        FIREFIREFIRE("VERY HOT! DON'T TOUCH");

        private final String printVal;

        Temperature(String printVal) {
            this.printVal = printVal;
        }

        @Override
        public String toString() {
            return printVal;
        }
    }

    private Temperature myTemp;
    private Setting mySetting;
    private int clock;
    private final static int TIME_LENGTH = 2;

    public Burner() {
        myTemp = Temperature.ICECOLD;
        mySetting = Setting.OFF;
        clock = 0;
    }

    public Temperature getMyTemp() {
        return myTemp;
    }

    /**
     * Use a switch statement to set state transitions
     */
    public void plusButton() {
        clock = TIME_LENGTH;
        mySetting = switch (mySetting) {
            case OFF -> Setting.LOWHEAT;
            case LOWHEAT -> Setting.MEDIUMHEAT;
            case MEDIUMHEAT, HIGHHEAT -> Setting.HIGHHEAT; // Map HIGHHEAT to itself
        };
    }

    public void minusButton() {
        clock = TIME_LENGTH;
        mySetting = switch (mySetting) {
            case HIGHHEAT -> Setting.MEDIUMHEAT;
            case MEDIUMHEAT -> Setting.LOWHEAT;
            case LOWHEAT, OFF -> Setting.OFF;
        };
    }

    /**
     * Update and check time.
     * Call the correct temp move function based on the current temperature and setting (semi-fragile)
     * Reset the timer if it is not at set temp
     */
    public void updateTemperature() {
        if (clock > 0) {
            clock--;
        }
        if (clock == 0) {
            if (myTemp.ordinal() < mySetting.ordinal()) {
                upTemp();
            } else if (myTemp.ordinal() > mySetting.ordinal()) {
                downTemp();
            }
            if (myTemp.ordinal() != mySetting.ordinal()) {
                clock = 2;
            }
        }
    }

    /**
     * Extracted methods to set state transitions for temp.
     * Does not check timing
     */
    private void upTemp() {
        myTemp = switch (myTemp) {
            case ICECOLD -> Temperature.WARMINGUP;
            case WARMINGUP -> Temperature.GETTINGHOT;
            case GETTINGHOT -> Temperature.FIREFIREFIRE;
            case FIREFIREFIRE -> null; // unreachable
        };
    }

    private void downTemp() {
        myTemp = switch (myTemp) {
            case FIREFIREFIRE -> Temperature.GETTINGHOT;
            case GETTINGHOT -> Temperature.WARMINGUP;
            case WARMINGUP -> Temperature.ICECOLD;
            case ICECOLD -> null;
        };
    }

    public void displayTemperature() {
        System.out.println(mySetting.toString() + "....." + myTemp);
    }
}