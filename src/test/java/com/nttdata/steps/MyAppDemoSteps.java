package com.nttdata.steps;

import com.nttdata.screens.CarritoScreen;
import com.nttdata.screens.ProductsScreen;
import com.nttdata.screens.ProductoIndividualScreen;
import org.junit.Assert;

public class MyAppDemoSteps {
    ProductsScreen productScreen;
    ProductoIndividualScreen productoIndividualScreen;
    CarritoScreen carritoScreen;

    public void validarCargaPrimerProducto () {
        Assert.assertEquals("Sauce Labs Backpack",productScreen.getProductElement());

    }

    public void agregarUnidadesDelProducto (int unidad, String texto) {
        productScreen.seleccionarProducto(texto);
        productoIndividualScreen.scrollHaciaBoton(unidad);
    }

    public void validacionIconoCarrito (){
        System.out.println("TextoUnidad: "+productoIndividualScreen.getTextoUnidades());
        System.out.println("Textoicono: "+productoIndividualScreen.getAgregadoAIconoCarrito());
        Assert.assertEquals(productoIndividualScreen.getAgregadoAIconoCarrito(),productoIndividualScreen.getTextoUnidades());
        productoIndividualScreen.seleccionarIconoCarrito();
    }
    public void validacionActualizacionCarrito (){
        Assert.assertEquals(carritoScreen.getUnidadesEnCarrito(),carritoScreen.getNumeroIconoCarrito());
    }
}
