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
package br.com.fatec.lista3.view;

import br.com.fatec.lista3.controller.Controller;

public class Menu {
        Controller ctrl = new Controller();

        public void copyrightMessage() {
                // Nota de copyright da GPL
                System.out.println("\nCarteiraVirtual  Copyright (C) 2020  Tobias da Silva Lino\n" +
                        "This program comes with ABSOLUTELY NO WARRANTY.\n" +
                        "This is free software, and you are welcome to redistribute it\n" +
                        "under certain conditions.\n");
        }

        public void aboutMessage() {
                System.out.println(
                        "\n\t\t\tCarteira Virtual\n\n" +
                        "\tAutores:\n" +
                        "\t\t   Tobias da Silva Lino\n" +
                        "\t\tJoão Pedro Esteves da Silva\n" +
                        "\t\t Larissa Aparecida Olimpio\n" +
                        "\t\t  Elieser de França Costa\n" +
                        "\t\t Kleber Apolinário Júnior\n");
        }

        public void helpMessage() {
                System.out.println("\nPara selecionar a opção:\n"
                        + "\tDigite o número correspondente"
                        + "\t e então tecle enter.\n");
        }

        public int login() {
                System.out.println("\n\tBem Vindo.\n\n"
                        + "Insira uma das opções:\n"
                        + "\t1. Login.\n"
                        + "\t2. Criar nova conta.\n\n"
                        + "\t3. Sair.\t4. About\t5. Ajuda\n");
                return ctrl.getIntOption("Qual sua opção? : ");
        }

        /* Menu Principal */
        public int mainMenu() {
                System.out.println("\nInsira uma das opções:\n"
                        + "\t1. Cadastrar Entrada.\n"
                        + "\t2. Sair.\n");
                return ctrl.getIntOption("Qual sua opção? : ");
        }

        /* Menu de Cadastro de Entrada */
        public int registerEntry() {
                System.out.println("\nInsira uma das opções: "
                        + "\t1. Inserir cliente.\n"
                        + "\t2. Inserir Data\n"
                        + "\t3. Inserir cliente.\n");
                return ctrl.getIntOption("Qual sua opção? : ");
        }
}
