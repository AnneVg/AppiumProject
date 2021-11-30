package lesson15;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwipeVertically {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            // Click on Forms label
            MobileElement formLableElm = androidDriver.findElementByAccessibilityId("Forms");
            formLableElm.click();
            //Thread.sleep(2000);
            // Check to see whether we are on Forms screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));
            // Get mobile screen size
            Dimension windowsSize = androidDriver.manage().window().getSize();
            int screenHeight = windowsSize.getHeight();
            int screenWidth = windowsSize.getWidth();
            // caculate touch point
            int xStartPoint = (50 * screenWidth) / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = (90 * screenHeight) / 100;
            int yEndPoint = (10 * screenHeight) / 100;
            // Convert to PointOption - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
            // Perform touch action
            TouchAction touchAction = new TouchAction(androidDriver);
            // Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
            // Swipe down
            touchAction
                    .press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(startPoint)
                    .release()
                    .perform();
            // Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(endPoint)
                    .release()
                    .perform();


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DriverFactory.stopAppiumServer();

        }

    }

}
