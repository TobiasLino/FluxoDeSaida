package br.com.fatec.lista3;

import br.com.fatec.lista3.controller.DataBase;
import br.com.fatec.lista3.model.user.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Test {
        public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
                DataBase dataBase = new DataBase();
                User trying = new User("tobias", "2102001");
                User n = dataBase.getUser(trying.getUsername());

                String decripted = dataBase.decrypt(dataBase.encrypt(trying.getPassword()));

                if (decripted.equals(n.getPassword()))
                        System.out.println("TRUE");
                else
                        System.out.println("FALSE");
        }
}
