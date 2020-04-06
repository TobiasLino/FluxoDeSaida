/*
        This file is part of FluxoDeSaida.

        FluxoDeSaida is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        FluxoDeSaida is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Foobar.  If not, see <https://www.gnu.org/licenses/>.

 */
package br.com.fatec.lista3.model.user;

import br.com.fatec.lista3.app.App;
import br.com.fatec.lista3.controller.Controller;
import br.com.fatec.lista3.controller.DataBase;
import br.com.fatec.lista3.view.Menu;

public class Login {
        private DataBase db;

        public Login(DataBase db) {
                this.db = db;
        }

        public void signIn(boolean logged) {
                logged = verify();
                if (logged)
                        App.start();
                else if (tryAgain())
                        App.start();
                else
                        notLogged();
        }

        public void signUp() {
                User newUser = new User();
                boolean exit = false;
                Menu m = new Menu();
                /*
                while (!exit) {
                        switch (m.NewAccount()) {
                                // Insere o nome
                                // Cancela
                                case 6: exit = true; break;
                                case 7:
                                        if (new Controller().confirmOption()) {

                                        }
                        }
                }
                 */
        }

        public boolean verify() {
                Controller ctrl = new Controller();
                String username = ctrl.getOption("Insira o Username: ");
                String pass = ctrl.getOption("Insira a senha: ");
                User to_log = new User();
                to_log.setUsername(username);
                to_log.setPassword(pass);
                return verify_pass(to_log);
        }

        boolean verify_pass(User user) {
                User toComp = db.getUser(user.getUsername());
                if (verify_name(toComp)) {
                        return toComp.getPassword().equals(user.getPassword());
                }
                return false;
        }

        boolean verify_name(User user) {
                return !user.getUsername().equals("");
        }


        public boolean tryAgain() {
                userOrPassError();
                boolean logged = false;
                int attempts = 0;
                while (attempts < 3) {
                        logged = verify();
                        if (logged)
                                break;
                        userOrPassError();
                        attempts++;
                }
                return logged;
        }


        public void notLogged() {
                System.err.println("Você errou o usuário três vezes."
                        + "\n Tente novamente outra vez.");
                System.exit(1);
        }

        void userOrPassError() {
                System.err.println("\nUsuário ou senha incorretos."
                        + "\n    Tente novamente");
        }

}
