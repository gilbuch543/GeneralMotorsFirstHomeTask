import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TestUtils extends BaseFrontEnd {
    private static int SCREENSHOT_COUNTER = 0;
    public static void takeScreenShot(){
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // now copy the  screenshot to desired location using copyFile //method
            Random rand = new Random();

            // Obtain a number between [0 - 199].
            int randomNumber = rand.nextInt(200);
            FileUtils.copyFile(src, new File(String.format("D:/screenShot%s_%s.png",randomNumber,SCREENSHOT_COUNTER)));
            System.out.println("Taking a screenshot");
            SCREENSHOT_COUNTER++;
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}
