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

        public boolean verifyLogin(DataBase db) throws SQLException, NoSuchAlgorithmException {
                Controller ctrl = new Controller();
                String username = ctrl.getOption("Insira o Username: ");
                String pass = ctrl.getOption("Insira a senha: ");
                User to_log = new User(username, pass);
                return verify(db, to_log);
        }

        boolean verify(DataBase db, User user) throws SQLException, NoSuchAlgorithmException {
                User to_comp = db.getUser(user.getUsername());
                if (to_comp != null) {
                        return db.decrypt(db.encrypt(user.getPassword())).equals(to_comp.getPassword());
                }
                return false;
        }

}
