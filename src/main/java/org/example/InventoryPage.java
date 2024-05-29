package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {    private WebDriver driver;

    private By backpack = By.id("add-to-cart-sauce-labs-backpack");
    private By boltTShirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By onesie = By.id("add-to-cart-sauce-labs-onesie");
    private By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartButton = By.className("shopping_cart_link");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        driver.findElement(backpack).click();
    }

    public void addBoltTShirtToCart() {
        driver.findElement(boltTShirt).click();
    }

    public void addOnesieToCart() {
        driver.findElement(onesie).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(bikeLight).click();
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }


}
