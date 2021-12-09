package lesson17;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class TaskingScreenShot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();
            // wait until we are on Login Screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");
            wait.until(ExpectedConditions.visibilityOf(loginBtnElem));
            // Talking whole screen
            File base64ScreenShotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginForm.png");
            FileUtils.copyFile(base64ScreenShotData, new File(fileLocation));

            // Taking element screenshot FAB - Floating Action Button
            File base64LoginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String LoginBtnImgFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginBtnElem.png");
            FileUtils.copyFile(base64LoginBtnData, new File(LoginBtnImgFileLocation));
            // Taking area screenshot
            List<MobileElement> viewGroupElems = androidDriver.findElementsByXPath("//android.view.ViewGroup/android.view.ViewGroup[2]");
            if(!viewGroupElems.isEmpty()){
                File base64NavData = viewGroupElems.get(viewGroupElems.size() - 1).getScreenshotAs(OutputType.FILE);
                String navImgFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("navElem.png");
                FileUtils.copyFile(base64NavData, new File(navImgFileLocation));
            }

            // Taking area screenshot

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //    DriverFactory.stopAppiumServer();

        }
    }
    }

