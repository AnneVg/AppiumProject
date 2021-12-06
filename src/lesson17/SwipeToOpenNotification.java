package lesson17;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwipeToOpenNotification {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            // Get mobile screen size
            Dimension windowsSize = androidDriver.manage().window().getSize();
            int screenHeight = windowsSize.getHeight();
            int screenWidth = windowsSize.getWidth();
            // caculate touch point
            int xStartPoint = (20 * screenWidth) / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0 ;
            int yEndPoint = (90 * screenHeight) / 100;
            // Convert to PointOption - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
            // Perform touch action
            TouchAction touchAction = new TouchAction(androidDriver);
            for (int i = 0; i < 5; i++) {
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
                Thread.sleep(2000);
                // Get the info inside the notification by getting a list
                List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column"));
                if(notificationElems.isEmpty())
                    throw  new RuntimeException("Notification List is empty");
                Map<String, String> notificationList = new HashMap<>();

                notificationElems.forEach(notification ->{
                    String notificationTitle = notification.findElement(By.id("android:id/title")).getText();
                    String notificationContent = notification.findElement(By.id("android:id/text")).getText();
                    System.out.println(notificationTitle);
                    System.out.println(notificationContent);
                    notificationList.put(notificationTitle,notificationContent);
                });
                touchAction
                        .press(endPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                        .moveTo(startPoint)
                        .release()
                        .perform();
                notificationList.keySet().forEach(key -> System.out.println(key + ": " + notificationList.get(key)));

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DriverFactory.stopAppiumServer();

        }
    }


}
