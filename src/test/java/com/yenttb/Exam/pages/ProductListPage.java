package com.yenttb.Exam.pages;

import com.yenttb.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductListPage {

    public ProductListPage() {
    }

    private By inputSearch = By.xpath("//input[@name='search']");
    private By itemFirstProductOnTheTable = By.xpath("//tbody/tr[1]/td[2]//div[2]/span");
    private By itemFirstProductDetail = By.xpath("//i[@class='las la-eye']");
    private By nameDetail = By.xpath("//div[@class='col-xl-7 col-lg-6']//h1");
//    private By nothingMess = By.xpath("//tbody/tr[1]/td[1]");

    public ProductListPage verifyAddProduct(String name) {
        new DashboardPage().getAllProductPage();
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
        Assert.assertEquals(WebUI.getElementText(itemFirstProductOnTheTable), name, "The Product value match.");
        return this;
    }

    public ProductListPage searchProductName(String productName) {
        new DashboardPage().getAllProductPage();
        WebUI.waitForPageLoaded();
        WebUI.setText(inputSearch, productName);
        WebUI.pressENTER();
        WebUI.waitForPageLoaded();
        WebUI.waitForJQueryLoad();
        WebUI.sleep(2);
        Assert.assertTrue(WebUI.isDisplayed(itemFirstProductOnTheTable), "Nothing found");
        Assert.assertEquals(WebUI.getElementText(itemFirstProductOnTheTable), productName, "The Product value match.");
        WebUI.clickElement(itemFirstProductDetail);
        WebUI.waitForPageLoaded();
        Assert.assertEquals(WebUI.getElementAttribute(nameDetail, "value"), productName, "The Company name not match.");
        return this;
    }
}
