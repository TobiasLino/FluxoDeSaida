package br.com.fatec.lista3;

import br.com.fatec.lista3.controller.DataBase;
import br.com.fatec.lista3.model.user.Login;
import br.com.fatec.lista3.model.user.User;

public class Test {
        public static void main(String[] args) {
                DataBase dataBase = new DataBase();
                /*
                User t = new User();
                t.setUsername("tobias");
                t.setPassword("24102001");
                dataBase.addUser(t);
                 */

                Login lg = new Login(dataBase);
                if (lg.verify()) {
                        System.out.println("Concluido");
                } else {
                        System.out.println("ERRO");
                }
        }
}
