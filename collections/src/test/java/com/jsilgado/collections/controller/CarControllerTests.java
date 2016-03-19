package com.jsilgado.collections.controller;

import static org.junit.Assert.*;
import org.junit.Test;


public class CarControllerTests {

    @Test
    public void testHandleRequestView() throws Exception{
    	CarController controller = new CarController();

        assertNotNull(controller);
    }

}