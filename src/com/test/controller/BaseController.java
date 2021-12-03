package com.test.controller;

//import com.test.EmailManager;
import com.test.view.ViewFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class BaseController {
    protected ArrayList<String> leadManager;
    protected ViewFactory viewFactory;
    private String fxmlName; // Indication to the fxml file

    public BaseController(/*EmailManager emailManager,*/ ViewFactory viewFactory, String fxmlName) {
       // this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName(){
        return fxmlName;
    }
}
/*
Even if this is an abstract class it can contain a controller which will be used by the classes
extending this class. Notice how we can implement code in our base controller and it will be the same for all
other controllers.
 */