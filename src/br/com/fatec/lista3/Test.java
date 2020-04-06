package br.com.fatec.lista3;

import br.com.fatec.lista3.controller.Controller;
import br.com.fatec.lista3.model.user.User;

public class Test {
        public static void main(String[] args) {
                Controller n = new Controller();

                User test = n.createNewProfile();

                test.print();
        }
}
