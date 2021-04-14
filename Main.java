import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();

        moveUntilEndOfDay(robot);
    }

    private static void moveUntilEndOfDay(Robot robot) throws InterruptedException {
        //TODO: change first parameter of LocalTime.of() method to specify the hour you want to stop
        LocalTime finishTime = LocalTime.of(18, random.nextInt(60), random.nextInt(60));
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
            double x = random.nextDouble() * 1921;
            double y = random.nextDouble() * 1081;
            printLogMessage("Moving mouse to x = " + x + " and y = " + y);
            robot.mouseMove((int) x, (int) y);

            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    private static boolean checkIfMouseIsMoving() {
        try {
            Point startLocation = MouseInfo.getPointerInfo().getLocation();
            Thread.sleep(1000 * 60);
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
