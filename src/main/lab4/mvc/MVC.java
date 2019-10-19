package main.lab4.mvc;

import main.lab4.mvc.interfaces.IModel;
import main.lab4.mvc.interfaces.IView;

public class MVC {
    public static void main(String[] args){
        IModel graphic = new Model();
        IView view = new View();
        Controller controller = new Controller(graphic,view);
        controller.initController();
    }
}
