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
package br.com.fatec.lista3.controller;


import br.com.fatec.lista3.model.client.Address;
import br.com.fatec.lista3.model.client.Phone;
import br.com.fatec.lista3.model.flow.Fisical;
import br.com.fatec.lista3.model.flow.Input;
import br.com.fatec.lista3.model.flow.Legal;
import br.com.fatec.lista3.model.user.User;
import br.com.fatec.lista3.view.Menu;
import com.google.api.client.util.Data;
import com.google.api.client.util.DateTime;
import org.dom4j.Document;
import org.lavieri.modelutil.cep.WebServiceCep;

import java.util.Map;
import java.util.Scanner;

public class Controller {
        private Scanner scan = new Scanner(System.in);
        private Clone cloner = new Clone();
        // getOption retorna a opção do usuário mediante uma mensagem.
        /* getOption sem verificação de string vazia */
        public String getOption(String message) {
                System.out.print(message);
                return scan.nextLine();
        }
        /* getOption com verificação de string vazia */
        public String getOptionNotEmpty(String message) {
                System.out.print(message);
                String opt = scan.nextLine();
                if (!opt.equals(""))
                        return opt;
                return null;
        }

        /* getOption com retorno em int */
        public int getIntOption(String message) {
                System.out.print(message);
                String to_int = scan.nextLine();
                int ret = 0;
                if (!to_int.isEmpty() && canBeInt(to_int))
                        ret = Integer.parseInt(to_int);
                return ret;
        }
        // getOption com retorno em Double
        public double getDoubleOption(String message) {
                System.out.print(message);
                String to_double = scan.nextLine();
                double ret = 0;
                if (!to_double.isEmpty() && canBeInt(to_double))
                        ret = Integer.parseInt(to_double);
                return ret;
        }
        /* Verifica se uma string contém apenas números */
        public boolean canBeInt(String str) {
                return str.matches("[0-9]*");
        }
        /* Retorna true se o cliente confirmar a operação */
        public boolean confirmOption() {
                String opt = getOption("Confirmar ? [S/n] : ");
                return opt.equals("") || opt.equals("S") || opt.equals("s");
        }


        /* ******************************************************** *
         * Operações dos menus                                      *
         * ******************************************************** */
        public void myFlow(DataBase db, Status status) {
                boolean exit = false;
                while (!exit) {
                        switch (new Menu().myFlow()) {
                                case 1: seeMyFlow(db, status); break;
                                case 2: insertToFlow(db, status);
                                case 3: exit = true; break;
                                default:
                                        System.err.println("\nInsira uma opção válida.\n");
                        }
                }
        }
        void seeMyFlow(DataBase db, Status status) {
                db.search("input", "name", status.getUser().getUsername());
        }
        private void insertToFlow(DataBase db, Status status) {
                status.input = db.findInput(status.getUser());
                boolean exit = false;
                while (!exit) {
                        switch (new Menu().newFlow()) {
                                case 1: newInput(status);
                                        break;
                                case 2:
                                        break;
                                case 3:
                                        break;
                                case 7: exit = true; break;
                        }
                }
        }
        /*
         * Define o tipo de entradas que serão aceitas pelo sistema.
         * É verificado a partir do people_type do User e cria o
         * objeto correspondente.
         */
        private void newInput(Status st) {
                switch (st.getUser().getPeople_type()) {
                        case "L":
                                st.input = new Legal(st.getUser());
                                newInput((Legal) st.input);
                                break;
                        case "F":
                                st.input = new Fisical(st.getUser());
                                newInput((Fisical) st.input);
                                break;
                }
        }
        private void newInput(Fisical input) {
                Fisical tmp = cloner.clone(input);
                boolean exit = false;
                while (!exit) {
                        tmp.print();
                        switch (new Menu().editFisical()) {
                                case 1: insertSalary(tmp); break;
                                case 2: insertInvestment(tmp); break;
                                case 3: exit = true; break;
                                case 4:
                                        if (confirmOption()) {
                                                input = cloner.clone(tmp);
                                                exit = true; break;
                                        }
                                default:
                                        System.out.println("\nInsira um valor válido.");
                                        break;
                        }
                }
        }
        private void insertSalary(Fisical a) {
                double buffer = getDoubleOption("Insira o salário: ");
                a.setSalary(buffer);
        }
        private void insertInvestment(Input a) {
                int exit = 0;
                while (exit == 0)
                        exit = insertInvestment(a.getInvestments());
        }
        private int insertInvestment(Map<String, Double> investments) {
                String buffer_1 = getOptionNotEmpty("Insira o investimento [ENTER para sair] : ");
                double buffer_2 = getDoubleOption("Insira o valor [ENTER para sair] : ");
                if (buffer_1 != null) {
                        investments.put(buffer_1, buffer_2);
                        return 0;
                } else return 1;
        }

        private void newInput(Legal legal) {
                Legal tmp = cloner.clone(legal);
                boolean exit = false;
        }



        /*
         * Visualiza o perfil
         */
        public void myProfile(User my_user, DataBase db) {
                boolean exit = false;
                while (!exit)
                        switch (new Menu().meuPerfil(my_user)) {
                                // Editar
                                case 1 : editProfile(my_user);
                                // Remover
                                case 2 : eraseProfile(db);
                                // Sair
                                case 3 : exit = true; break;
                                default:
                                        System.err.println("\nInsira uma opção válida.");
                        }
        }
        // Cria um novo perfil
        public User createNewProfile(DataBase db) {
                User novo = profileInfos(new User());
                setUsernameAndPass(novo);
                db.addUser(novo);
                return novo;
        }
        // Edita o perfil
        public void editProfile(User my_user) {
                profileInfos(my_user);
        }
        // Remove o perfil
        public void eraseProfile(DataBase db) {
                String value = getOptionNotEmpty("Digite o nome: ");
                if (value != null)
                        db.eraseUser(value);
        }
        User profileInfos(User my_user) {
                User tmp = cloner.clone(my_user);
                while (true) {
                        tmp.print();
                        switch (new Menu().editProfile(tmp)) {
                                case 1:
                                        tmp.setName(getOption("Insira o nome: "));
                                        break;
                                case 2:
                                        CpfCnpjCheck checker = new CpfCnpjCheck();
                                        String str = getOption("Insira o valor: ");
                                        String res = checker.isValid(str);
                                        tmp.setPeople_type(res);
                                        tmp.setCpfCnpj(str);
                                        break;
                                case 3:
                                        Phone n = new Phone();
                                        n.setDdd(getOption("Insira o ddd: "));
                                        n.setNumber(getOption("Insira o número: "));
                                        tmp.setPhone(n);
                                        break;
                                case 4:
                                        tmp.setEmail(getOption("Insira o email: "));
                                        break;
                                case 5:
                                        defineAddressForUser(tmp);
                                        break;
                                case 6:
                                        return my_user;
                                case 7:
                                        if (confirmOption()) {
                                                return tmp;
                                        }
                                default:
                                System.out.println("\nInsira uma opção válida!");
                        }
                }
        }

        /*
         * Cadastro do endereço do usuário
         * Contém uma busca do CEP e preenchimento
         */
        void defineAddressForUser(User my_user) {
                Address tmp = cloner.clone(my_user.getAddress());
                boolean exit = false;
                while (!exit) {
                        tmp.print();
                        switch (new Menu().editProfile_Address(my_user)) {
                                case 1 : searchCEP(tmp); break;
                                case 2 : tmp.setStreet(getOption("Insira o endereço: ")); break;
                                case 3 : tmp.setNumber(getOption("Insira o número: ")); break;
                                case 4 : tmp.setComplement(getOption("Insira o complemento: ")); break;
                                case 5 : tmp.setNeighborhood(getOption("Insira o bairro: ")); break;
                                case 6 : tmp.setCity(getOption("Insira a cidade: ")); break;
                                case 7 : tmp.setState(getOption("Insira a UF: ")); break;
                                case 8 : exit = true; break;
                                case 9 : if (confirmOption()) {
                                        my_user.setAddress(tmp);
                                        exit = true;
                                        break;
                                }
                        }
                }
        }

        /*
         * Cadastro do username e senha
         */
        public void setUsernameAndPass(User user) {
                boolean exit = false;
                String username, password;
                username = password = "";
                while (!exit) {
                        switch (new Menu().editLogin()) {
                                case 1: username = getOption("insira o username: "); break;
                                case 2: password = getOption("Insira a senha: "); break;
                                case 3:
                                        exit = true;
                                        break;
                                case 4:
                                        if (confirmOption() && (!username.equals(""))) {
                                                user.setUsername(username);
                                                user.setPassword(password);
                                                exit = true;
                                                break;
                                        }
                        }
                }
        }

        void searchCEP(Address my_address) {
                String cep = getOptionNotEmpty("Insira o CEP: ");
                if (cep == null) return;
                // Faz a busca para o cep
                WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
                // se a busca corresponder, insere os dados no objeto
                if (webServiceCep.wasSuccessful()) {
                        my_address.setZip(cep);
                        my_address.setStreet(webServiceCep.getLogradouroFull());
                        my_address.setNeighborhood(webServiceCep.getBairro());
                        my_address.setCity(webServiceCep.getCidade());
                        my_address.setState(webServiceCep.getUf());
                }

        }

}
