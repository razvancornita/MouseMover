import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) throws AWTException, InterruptedException {
        moveUntilEndOfDay();
    }

    private static void moveUntilEndOfDay() throws InterruptedException, AWTException {
        //TODO: change first parameter of LocalTime.of() method to specify the hour you want to stop
        LocalTime finishTime = LocalTime.of(18, random.nextInt(60), random.nextInt(60));
        Robot robot = new Robot();
        while (finishTime.isAfter(LocalTime.now())) {
            printLogMessage(LocalTime.now().until(finishTime, ChronoUnit.MINUTES) + " minutes left..");
            moveMouse(robot);
        }
    }

    private static void printLogMessage(String message) {
        String dateTime = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss").format(LocalDateTime.now());
        System.out.println("[" + dateTime + "] - " + message);
    }

    private static void moveMouse(Robot robot) throws InterruptedException {
        if (!checkIfMouseIsMoving()) {
            robot.mouseMove(random.nextInt(1921), random.nextInt(1081));
            printLogMessage("Moving mouse..");

            try {
                Thread.sleep((long) 1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    private static boolean checkIfMouseIsMoving() {
        try {
            Point startLocation = MouseInfo.getPointerInfo().getLocation();
            Thread.sleep((long) 1000 * 60);
            Point endLocation = MouseInfo.getPointerInfo().getLocation();
            if (!startLocation.equals(endLocation)) {
                printLogMessage("Mouse already moving :)");
            }
            return !startLocation.equals(endLocation);
        } catch (Exception e) {
            return false;
        }
    }
}
