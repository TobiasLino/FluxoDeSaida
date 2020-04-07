package br.com.fatec.lista3;

import br.com.fatec.lista3.controller.DataBase;
import br.com.fatec.lista3.model.user.User;

public class Test {
        public static void main(String[] args) {
                User test = new User();

                test.setUsername("Tobalino");
                test.setName("Tobias da Silva Lino");
                test.getAddress().setState("SP");
                test.getPhone().setNumber("996262526");

                DataBase db = new DataBase();
                db.addUser(test);

                User n = db.getUser(test.getUsername());

                n.print();
        }
}
