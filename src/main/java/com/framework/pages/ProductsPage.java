package com.framework.pages;

import com.framework.base.BasePage;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {
    private final By productDescription = By.className("inventory_item_desc");
    public ProductsPage() {
        super();
        verifyPageLoaded(productDescription, "Products Inventory Page");
    }

    public String getTitleText() {
        return getElementText(productDescription);
    }
}