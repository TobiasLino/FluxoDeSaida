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
import br.com.fatec.lista3.model.user.User;

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
                        + "\t2. Criar nova conta.\n"
                        + "\t3. Sair.\n\t4. About\n\t5. Ajuda\n");
                return ctrl.getIntOption("Qual sua opção? : ");
        }

        /* Menu Principal */
        public int mainMenu() {
                System.out.println("\nInsira uma das opções:\n"
                        + "\t1. Meu Flow\n"
                        + "\t2. Meu perfil.\n"
                        + "\t3. Sair.\n");
                return ctrl.getIntOption("Qual sua opção? : ");
        }

        /* Meu Flow */
        public int myFlow() {
                System.out.println("\nMeu Flow\n"
                        + "\t1. Ver Flow.\n"
                        + "\t2. Inserir."
                        + "\t3. Voltar.\n");
                return ctrl.getIntOption("Qual sua opção? : ");
        }

        /* Inserir */
        public int newFlow() {
                System.out.println("\nInserir\n"
                        + "\t1. Cadastrar nova entrada.\n"
                        + "\t2. Editar entrada.\n"
                        + "\t3. Remover entrada.\n"
                        + "\t4. Cadastrar nova despesa.\n"
                        + "\t5. Editar despesas.\n"
                        + "\t6. Remover despesas.\n"
                        + "\t7. Voltar.\n");
                return ctrl.getIntOption("Qual sua opção? : ");
        }

        /*
         * mainMenu::meuPerfil
         */
        public int meuPerfil(User my_user) {
                my_user.print();
                System.out.println("\nSelecione uma das opções\n"
                        + "\t1. Editar\n"
                        + "\t2. Remover Conta\n"
                        + "\t3. Voltar\n");
                return ctrl.getIntOption("Insira sua opção: ");
        }

        public int editProfile(User my_user) {
                System.out.println("\nSelecione uma opção para editar\n"
                        + "\t1. Nome");
                {
                        if (my_user.getPeople_type().equals("F"))
                                System.out.println("\t2. CPF");
                        else System.out.println("\t2. CNPJ");
                }
                System.out.println("\t3. Telefone\n"
                        + "\t4. Email\n"
                        + "\t5. Endereço\n"
                        + "\t6. Cancelar\n"
                        + "\t7. Salvar\n");
                return ctrl.getIntOption("Insira sua opção: ");
        }

        public int editLogin() {
                System.out.println("\nInsira os dados de login\n"
                        + "\t1. Inserir Username\n"
                        + "\t2. Inserir senha\n"
                        + "\t3. Cancelar\n"
                        + "\t4. Salvar\n");
                return ctrl.getIntOption("Insira sua opção: ");
        }

        public int editProfile_Address() {
                System.out.println("\nAddress\n"
                        + "\t1. CEP\n"
                        + "\t2. Rua\n"
                        + "\t3. Número\n"
                        + "\t4. Complemento\n"
                        + "\t5. Bairro\n"
                        + "\t6. Cidade\n"
                        + "\t7. Estado\n"
                        + "\t8. Cancelar\n"
                        + "\t9. Salvar\n");
                return ctrl.getIntOption("Insira sua opção: ");
        }
}
