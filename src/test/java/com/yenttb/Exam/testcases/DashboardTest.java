package com.yenttb.Exam.testcases;

import com.yenttb.Exam.pages.DashboardPage;
import com.yenttb.Exam.pages.LoginPage;
import com.yenttb.common.BaseTest;
import com.yenttb.keywords.WebUI;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void testOpenPage(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM("admin@example.com", "123456");

        dashboardPage.openPage("Customers");
        WebUI.sleep(2);
        dashboardPage.openPage("Projects");
        WebUI.sleep(2);
        dashboardPage.openPage("Tasks");
        WebUI.sleep(2);
    }

    @Test
    public void testLogout(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.logout();
        loginPage.verifyRedirectToLoginPage();
    }

    @Test
    public void testTotalOfTasksNotFinished(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123456");
        dashboardPage = new DashboardPage();
        dashboardPage.verifyRedirectToDashboardPage();
        dashboardPage.verifyTotalOfTasksNotFinished("1 / 1");
    }
}
