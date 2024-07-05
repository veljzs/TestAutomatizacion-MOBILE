package com.nttdata.screens;


import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class ProductoIndividualScreen extends PageObject {

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    private WebElement unidadestextoElement;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement botonAumentarUnidades;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private WebElement botonAddToCart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartTV\"]")
    private WebElement numeroIconoCarrito;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]")
    private WebElement iconoCarrito;

    private WebDriver getProxiedDriver() {
        return ((WebDriverFacade) getDriver()).getProxiedDriver();
    }

    public void scrollDown() {
        WebDriver driver = getProxiedDriver();
        int startX = driver.manage().window().getSize().width / 2;
        int startY = (int) (driver.manage().window().getSize().height * 0.8);
        int endY = (int) (driver.manage().window().getSize().height * 0.2);

        new TouchAction<>((io.appium.java_client.PerformsTouchActions) driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public void scrollHaciaBoton(int unidades) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        boolean buttonFound = false;
        Instant startTime = Instant.now();
        Duration timeout = Duration.ofSeconds(10);

        while (!buttonFound && Duration.between(startTime, Instant.now()).compareTo(timeout) < 0) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(botonAumentarUnidades));
                if (botonAumentarUnidades.isDisplayed() && botonAumentarUnidades.isEnabled()) {
                    getTextoUnidades();
                    ponerUnidadesProducto(unidades);
                    buttonFound = true;
                }
            } catch (Exception e) {
                scrollDown();
            }
        }
    }

    public String getTextoUnidades(){
        return unidadestextoElement.getText();
    }


    public void ponerUnidadesProducto(int unidades){
        int unidadesEnApp = Integer.parseInt(getTextoUnidades());
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        System.out.println("actual: "+unidadesEnApp);
        if(unidades > unidadesEnApp){
            wait.until(ExpectedConditions.elementToBeClickable(botonAumentarUnidades));
            botonAumentarUnidades.click();
        }
        int newUnidadesEnApp = Integer.parseInt(getTextoUnidades());
        System.out.println("new: "+newUnidadesEnApp);

        if(unidades == newUnidadesEnApp){
            wait.until(ExpectedConditions.elementToBeClickable(botonAumentarUnidades));
            botonAddToCart.click();
        }

    }

    public String getAgregadoAIconoCarrito(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(numeroIconoCarrito));
        return numeroIconoCarrito.getText();
    }

    public void seleccionarIconoCarrito(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(numeroIconoCarrito));
        iconoCarrito.click();
    }



}
