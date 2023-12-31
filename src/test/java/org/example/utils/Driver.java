package org.example.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Driver {
    private static AppiumDriver<MobileElement> driver;
    private static URL url;

    private Driver() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        String platform = ConfigurationReader.getProperty("platform");
        if (Objects.isNull(driver)) {
            switch (platform) {
                case "android":
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
                    desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/calculator.apk");
                    try {
                        url = new URL("http://localhost:4723/wd/hub");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new AndroidDriver<>(url, desiredCapabilities);
                    break;
                case "android-remote":
                    DesiredCapabilities caps = new DesiredCapabilities();

                    // Set your access credentials
                    caps.setCapability("browserstack.user", "testuser_1PhU8f");
                    caps.setCapability("browserstack.key", "qxU7LUK78o8BK1ki799f");

                    // Set URL of the application under test
                    caps.setCapability("app", "bs://e0ce6dfd61f8f7d9fd9c4fb11c746b65fd1d79f1");

                    // Specify device and os_version for testing
                    caps.setCapability("device", "OnePlus 8");
                    caps.setCapability("os_version", "10.0");
                    caps.setCapability("realMobile", "true");

                    // Set other BrowserStack capabilities
                    caps.setCapability("project", "My test appium automation");
                    caps.setCapability("build", "Java Android");
                    caps.setCapability("name", "Regression");

                    // Initialise the remote Webdriver using BrowserStack remote URL
                    // and desired capabilities defined above
                    try {
                        driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"), caps);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "swagLab":
                    DesiredCapabilities desiredCapabilities2 = new DesiredCapabilities();
                    desiredCapabilities2.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    desiredCapabilities2.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    desiredCapabilities2.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
                    desiredCapabilities2.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
                    desiredCapabilities2.setCapability(MobileCapabilityType.APP, "/Users/oscar/IdeaProjects/EU10_AppiumAutomation/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    // for most of the applications, you need to tell Appium, app package (location, in mobile phone), app Activity for it
                    desiredCapabilities2.setCapability("appPackage","com.swaglabsmobileapp");
                    desiredCapabilities2.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
                    try {
                        url = new URL("http://localhost:4723/wd/hub");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new AndroidDriver<>(url, desiredCapabilities2);
                    break;
                case "swagLab-remote-iphone":
                    MutableCapabilities capsIphone = new MutableCapabilities();
                    capsIphone.setCapability("platformName", "iOS");
                    capsIphone.setCapability("appium:deviceName", "iPhone.*");
                    capsIphone.setCapability("appium:deviceOrientation", "portrait");
                    capsIphone.setCapability("appium:automationName", "XCUITest");
                    capsIphone.setCapability(MobileCapabilityType.APP,"https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");  // iphone application
                    try {
                        url = new URL("YOUR HUB URL FROM USER SETTINGS--COPY PASTE HERE");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new IOSDriver<>(url, capsIphone);
                    break;
                case "swagLab-remote-android":
                    MutableCapabilities capsAndroid = new MutableCapabilities();
                    capsAndroid.setCapability("platformName", "android");
                    capsAndroid.setCapability("appium:deviceName", "Samsung.*");
                    capsAndroid.setCapability("appium:deviceOrientation", "portrait");
                    capsAndroid.setCapability("appium:automationName", "UiAutomator2");
                    capsAndroid.setCapability(MobileCapabilityType.APP, "https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk"); // --> for android app
                    capsAndroid.setCapability("appium:appWaitActivity","com.swaglabsmobileapp.MainActivity");

                    try {
                        url = new URL("YOUR HUB URL FROM USER SETTINGS--COPY PASTE HERE");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new AndroidDriver<>(url, capsAndroid);
                    break;
                case "swagLab-remote-android-fromSystem":
                    MutableCapabilities capsAndroidSYS = new MutableCapabilities();
                    MutableCapabilities sauceOptions = new MutableCapabilities();
                    capsAndroidSYS.setCapability("platformName", "android");
                    capsAndroidSYS.setCapability("appium:deviceName", "Samsung.*");
                    capsAndroidSYS.setCapability("appium:deviceOrientation", "portrait");
                    capsAndroidSYS.setCapability("appium:automationName", "UiAutomator2");
                    sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
                    sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
                    capsAndroidSYS.setCapability("sauce:options", sauceOptions);
                    capsAndroidSYS.setCapability(MobileCapabilityType.APP, "https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk"); // --> for android app
                    capsAndroidSYS.setCapability("appium:appWaitActivity","com.swaglabsmobileapp.MainActivity");

                    try {
                        url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new AndroidDriver<>(url, capsAndroidSYS);
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (Objects.nonNull(driver)) {
            driver.closeApp();
            driver = null;
        }
    }
}
