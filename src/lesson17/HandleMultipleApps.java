package lesson17;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleMultipleApps {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            MobileElement emailInputElm = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement passwordInputElm = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnElm = androidDriver.findElementByAccessibilityId("button-LOGIN");
            emailInputElm.sendKeys("test@hotmail.com");
            passwordInputElm.sendKeys("Password1@");
            loginBtnElm.click();
            // put webdriver io demo app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));
            Thread.sleep(1000);
            // Open setting application
            androidDriver.activateApp("com.android.settings");
            Thread.sleep(2000);
            // Do sth here
            androidDriver.findElementByXPath("//*[@text='Network & internet']").click();

            //If ON -> OFF and els OFF -> ON
             MobileElement wifiToggleBtn = androidDriver.findElement(By.id("com.android.settings:id/switchWidget"));
            MobileElement wifiStatus = androidDriver.findElementByXPath("//*[@text='AndroidWifi']");
            boolean isWifiOn = wifiStatus.getText().equals("AndroidWifi");
            if(isWifiOn) {
                //Change to OFF
                wifiToggleBtn.click();
                // Change to ON
                wifiToggleBtn.click();
            }else
                wifiToggleBtn.click();

            // re-launch WebDriverIO app
            androidDriver.activateApp("com.wdiodemoapp");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //    DriverFactory.stopAppiumServer();

        }
    }

}
