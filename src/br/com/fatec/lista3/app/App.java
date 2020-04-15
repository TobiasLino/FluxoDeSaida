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
package br.com.fatec.lista3.app;

import br.com.fatec.lista3.controller.Controller;
import br.com.fatec.lista3.controller.DataBase;
import br.com.fatec.lista3.controller.Status;
import br.com.fatec.lista3.model.user.Login;
import br.com.fatec.lista3.view.Menu;

public class App {
        public static Controller ctrl = new Controller();
        public static DataBase db = new DataBase();
        public static Login login = new Login(db);
        public static Menu menu = new Menu();
        public static Status status = new Status();

        /* Inicia o programa */
        public static void __start__() {
                boolean exit = false;
                while (!exit) {
                        switch (menu.mainMenu()) {
                                /* Meu flow */
                                case 1: ctrl.myFlow(db, status); break;
                                /* Meu Perfil */
                                case 2: ctrl.myProfile(status.getUser(), db); break;
                                /* Sair */
                                case 3:
                                        exit = true;
                                        break;
                        }
                }
        }

        public static void __init__() {
                while (!status.isLogged()) {
                        switch (menu.login()) {
                                /* Login */
                                case 1:
                                        status.setLogged(login.signIn(status.getUser()));
                                        if (status.isLogged())
                                                App.__start__();
                                        else if (login.tryAgain(status.getUser()))
                                                App.__start__();
                                        else
                                                login.notLogged();
                                        break;
                                /* Criar conta */
                                case 2:
                                        status.setUser(login.signUp());
                                        break;
                                /* Sair do programa */
                                case 3:
                                        System.exit(0);
                                        /* Sobre */
                                case 4:
                                        menu.aboutMessage();
                                        break;
                                /* Help */
                                case 5:
                                        menu.helpMessage();
                                        break;
                        }
                }
        }


        public static void main(String[] args) {
                menu.copyrightMessage();
                App.__init__();
        }
}
