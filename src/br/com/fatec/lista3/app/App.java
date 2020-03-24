package br.com.fatec.lista3.app;

import br.com.fatec.lista3.controller.Controller;
import br.com.fatec.lista3.controller.DataBase;
import br.com.fatec.lista3.model.user.Login;
import br.com.fatec.lista3.model.user.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class App {
        public static boolean logged;
        public static Controller ctrl = new Controller();
        public static DataBase db = new DataBase();

        public static void main(String[] args) {
                try {
                        logged = new Login().verifyLogin(db);
                        if (logged)
                                System.out.println("Logado");
                        else
                                System.out.println("Erro");
                } catch (NoSuchAlgorithmException | SQLException e) {
                        e.printStackTrace();
                }
        }
}
