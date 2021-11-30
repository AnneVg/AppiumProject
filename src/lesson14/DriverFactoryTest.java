package lesson14;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactoryTest {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            MobileElement emailInputElm = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            MobileElement passwordInputElm = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnElm = androidDriver.findElementByAccessibilityId("button-LOGIN");

            // Cách tìm khác khi k có dc xPaht - truong hop tra ve 1 list

           /* List<MobileElement> credsFormElm = androidDriver.findElementsByXPath("//android.widget.EditText");
            final int EMAIL_INPUT_INDEX = 0;
            final int PASSWORD_INPUT_INDEX =1;
            credsFormElm.get(EMAIL_INPUT_INDEX).sendKeys("test@hotmail.com");
            credsFormElm.get(PASSWORD_INPUT_INDEX).sendKeys("Password1@");*/
            emailInputElm.sendKeys("test@hotmail.com");
            passwordInputElm.sendKeys("Password1@");
            loginBtnElm.click();
            MobileElement loginFeatureDescElem = androidDriver.findElementByXPath("//*[contains(@text, 'When the device')]");
            MobileElement loginFeatureDescElemUISel =
                    androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"When the device\").className(\"android.widget.TextView\")");

            System.out.println(loginFeatureDescElem.getText());
            System.out.println(loginFeatureDescElemUISel.getText());
            WebDriverWait wait = new WebDriverWait(androidDriver,45);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));
            MobileElement loginResultDialogElem = androidDriver.findElementById("android:id/alertTitle");
            System.out.println(loginResultDialogElem.getText());
        } catch (Exception ignored) {

        } finally {
            DriverFactory.stopAppiumServer();
        }
        //Relative xPath
    }
}
