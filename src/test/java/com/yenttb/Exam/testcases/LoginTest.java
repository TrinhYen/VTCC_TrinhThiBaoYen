package com.yenttb.Exam.testcases;

import com.yenttb.Exam.pages.LoginPage;
import com.yenttb.common.BaseTest;
import com.yenttb.drivers.DriverManager;
import com.yenttb.helpers.CaptureHelper;
import com.yenttb.helpers.ExcelHelper;
import com.yenttb.keywords.WebUI;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginSuccess() {
        WebUI.sleep(1);
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/LoginCRM.xlsx", "Login");

        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        File theDir = new File("./screenshots/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try {
            FileHandler.copy(source, new File("./screenshots/testHomePage3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Screenshot success !!");

        loginPage = new LoginPage();
        loginPage.loginCRM(excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1));
        loginPage.verifyLoginSuccess();
        loginPage.logout();
        CaptureHelper.captureScreenshot("LoginPageHRM");
        loginPage.verifyRedirectToLoginPage();

        excelHelper.setCellData("Passed", "STATUS", 1);
    }

    @Test(priority = 2)
    public void testLoginFailWithEmailInvalid() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/LoginCRM.xlsx", "Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 2));
        loginPage.verifyLoginFail("Invalid email or password 2");

        excelHelper.setCellData("Passed", "STATUS", 2);
    }

    @Test(priority = 3)
    public void testLoginFailWithEmailNull() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/LoginCRM.xlsx", "Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(excelHelper.getCellData("EMAIL", 3),
                excelHelper.getCellData("PASSWORD", 3));
        loginPage.verifyLoginFail("The Email Address field is required. 123");

        excelHelper.setCellData("Passed", "STATUS", 3);
    }

    @Test(priority = 4)
    public void testLoginFailWithPasswordInvalid() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/LoginCRM.xlsx", "Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(excelHelper.getCellData("EMAIL", 4),
                excelHelper.getCellData("PASSWORD", 4));
        loginPage.verifyLoginFail("Invalid email or password");

        excelHelper.setCellData("Passed", "STATUS", 4);
    }

    @Test(priority = 5)
    public void testLoginFailWithPasswordNull() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/LoginCRM.xlsx", "Login");

        loginPage = new LoginPage();
        loginPage.loginCRM(excelHelper.getCellData("EMAIL", 5),
                excelHelper.getCellData("PASSWORD", 5));
        loginPage.verifyLoginFail("The Password field is required.");

        excelHelper.setCellData("Passed", "STATUS", 5);
    }
}
