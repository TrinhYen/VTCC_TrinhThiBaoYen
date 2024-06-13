package com.anhtester.Exam.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class CommonPage {

    public CommonPage() {
    }

    public By inputSearch = By.xpath("//input[@id='search_input']");
    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
    public By menuProject = By.xpath("//span[normalize-space()='Projects']");
    public By menuProduct = By.xpath("//span[text()='Products']");

    public By menuAddProduct = By.xpath("//span[normalize-space()='Add New Product']");

    public By dropdownProfile = By.xpath("//a[contains(@class,'dropdown-toggle profile')]");
    public By itemLogout = By.xpath("//a[contains(@class,'dropdown-toggle profile')]/following-sibling::ul//a[normalize-space()='Logout']");

    public CustomerPage openCustomerPage() {
        WebUI.clickElement(menuCustomer);
        WebUI.waitForPageLoaded();

        return new CustomerPage();
    }
    public AddNewProductPage openAddNewProductPage() {
        WebUI.clickElement(menuProduct);
        WebUI.clickElement(menuAddProduct);
        WebUI.waitForPageLoaded();

        return new AddNewProductPage();
    }
    public void openProjectPage() {
        WebUI.clickElement(menuProject);
        WebUI.waitForPageLoaded();
    }

    public LoginPage logout() {
        WebUI.clickElement(dropdownProfile);
        WebUI.sleep(1);
        WebUI.clickElement(itemLogout);
        WebUI.waitForPageLoaded();

        return new LoginPage();
    }

    public void searchCommon(String text){
        WebUI.setText(inputSearch, text);
    }

    //Khởi tạo từng class riêng để gọi dùng cho nhanh
    LoginPage loginPage;
    DashboardPage dashboardPage;
   CustomerPage customerPage;
    AddNewProductPage addNewProductPage;
    ProductListPage showAllProductPage;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

   public CustomerPage getCustomerPage() {
        if (customerPage == null) {
            customerPage = new CustomerPage();
        }
        return customerPage;
    }

    public AddNewProductPage getAddNewProductPage() {
        if (addNewProductPage == null) {
            addNewProductPage = new AddNewProductPage();
        }
        return addNewProductPage;
    }

    public ProductListPage getAllProductPage() {
        if (showAllProductPage == null) {
            showAllProductPage = new ProductListPage();
        }
        return showAllProductPage;
    }

}
