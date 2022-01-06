package com.test.controller;

import com.test.view.ViewFactory;

public abstract class BaseController {
 
    protected ViewFactory viewFactory;
    private final String fxmlName; // Indication to the fxml file

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName(){
        return fxmlName;
    }
}
/*
This is an abstract class to can contain a controller which will be used by the classes
extending it. Notice how we can implement code in our base controller which will be the same for all
other controllers.
 */