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

import br.com.fatec.lista3.controller.Controller;
import br.com.fatec.lista3.controller.DataBase;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login {
        private DataBase db;

        public Login(DataBase db) {
                this.db = db;
        }

        public boolean verify() throws SQLException, NoSuchAlgorithmException {
                Controller ctrl = new Controller();
                String username = ctrl.getOption("Insira o Username: ");
                String pass = ctrl.getOption("Insira a senha: ");
                User to_log = new User(username, pass);
                return verify_pass(to_log);
        }

        boolean verify_pass(User user) throws SQLException, NoSuchAlgorithmException {
                User to_comp = db.getUser(user.getUsername());
                if (verify_name(to_comp)) {
                        String decrypted = db.decrypt(db.encrypt(user.getPassword()));
                        String db_user = to_comp.getPassword();
                        return decrypted.equals(db_user);
                }
                return false;
        }

        boolean verify_name(User user) {
                return !user.getUsername().equals("");
        }


        public boolean tryAgain() throws SQLException, NoSuchAlgorithmException {
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
