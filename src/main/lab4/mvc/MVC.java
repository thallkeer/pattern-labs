package main.lab4.mvc;

public class MVC {
    public static void main(String[] args){
        Model graphic = new Model();
        View view = new View();
        Controller controller = new Controller(graphic,view);
    }
}
