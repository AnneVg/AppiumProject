package lesson16;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HandleHybridContext {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try{
            AppiumDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();
            MobileElement webViewElemt = appiumDriver.findElementByAccessibilityId("Webview");
            webViewElemt.click();
            // show all context
            appiumDriver.getContextHandles().forEach(context -> {
                System.out.println(context);
            });
            //Switch to webview context
            appiumDriver.context("WEBVIEW_com.wdiodemoapp");
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();
            // find list webelement
            List<MobileElement> menuItems = appiumDriver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItemList = new ArrayList<>();
            for (MobileElement menuItem : menuItems) {
                String menuText = menuItem.getText();
                String menuHyperLink = menuItem.getAttribute("href");
                if(StringUtils.isEmpty(menuText))
                    menuItemList.add(new MenuItem("GitHub", menuHyperLink));
                else
                    menuItemList.add(new MenuItem(menuText,menuHyperLink));
            }
            menuItemList.forEach(System.out::println);
            //Switch to native context
            appiumDriver.context("NATIVE_APP");
            // Interact with native context
            MobileElement loginLabelElem = appiumDriver.findElementByAccessibilityId("Login");
            loginLabelElem.click();
            Thread.sleep(2000);
            // Back to Home screen
            Thread.sleep(2000);
        }catch(Exception ignored)
        {

        }finally {
            // DriverFactory.stopAppiumServer();
        }

    }
    public  static class MenuItem{
        private String text;
        private String hyperlink;

        public MenuItem(String text, String hyperlink) {
            this.text = text;
            this.hyperlink = hyperlink;
        }

        public String getText() {
            return text;
        }

        public String getHyperlink() {
            return hyperlink;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "text='" + text + '\'' +
                    ", hyperlink='" + hyperlink + '\'' +
                    '}';
        }
    }
}
