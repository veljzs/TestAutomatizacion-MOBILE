package com.nttdata.stepsdefinitions;

import com.nttdata.steps.MyAppDemoSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class MyDemoAppStepdefs {
    @Steps
    MyAppDemoSteps myDemoSteps;

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicaciónDeSauceLabs() {
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        myDemoSteps.validarCargaPrimerProducto();
    }


    @When("agrego {int} del siguiente producto {string}")
    public void agregoDelSiguienteProducto(int arg0, String arg1) {
        myDemoSteps.agregarUnidadesDelProducto(arg0, arg1);
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void validoElCarritoDeCompraActualiceCorrectamente() {
        myDemoSteps.validacionIconoCarrito();
        myDemoSteps.validacionActualizacionCarrito();
    }
}
