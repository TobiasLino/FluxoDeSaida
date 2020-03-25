package br.com.fatec.lista3.app;

import br.com.fatec.lista3.controller.Controller;
import br.com.fatec.lista3.controller.DataBase;
import br.com.fatec.lista3.model.user.Login;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class App {
        public static boolean logged;
        public static Controller ctrl = new Controller();
        public static DataBase db = new DataBase();
        public static Login login = new Login(db);

        public static void start() {

        }


        public static void main(String[] args) {
                try {
                        logged = login.verify();
                        if (logged)
                                App.start();
                        else if (login.tryAgain())
                                App.start();
                        else
                                login.notLogged();
                } catch (NoSuchAlgorithmException | SQLException e) {
                        e.printStackTrace();
                }
        }
}
