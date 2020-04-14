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
import org.lavieri.modelutil.cep.WebServiceCep;

import java.util.Scanner;

public class Controller {
        private Scanner scan = new Scanner(System.in);
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
        public void myFlow(User user) {
                boolean exit = false;
                while (!exit) {
                        switch (new Menu().myFlow()) {
                                case 1:
                                case 2:
                                case 3: exit = true; break;
                                default:
                                        System.err.println("\nInsira uma opção válida.\n");
                        }
                }
        }

        /*
         * Define o tipo de entradas que serão aceitas pelo sistema.
         * É verificado a partir do people_type do User e cria o
         * objeto correspondente.
         */
        public void newInput(Status st) {
                switch (st.getUser().getPeople_type()) {
                        case "L": st.setInput(new Legal(st.getUser())); break;
                        case "F": st.setInput(new Fisical(st.getUser())); break;
                }
        }

        private void editInput(Input input) {
                Input tmp = new Input(input.getUser());
                boolean exit = false;
                while (!exit) {
                        switch (new Menu().newFlow()) {
                                case 1:
                                        break;
                                case 2:
                                        break;
                                case 3:
                                        break;
                                case 4: exit = true; break;
                        }
                }
        }

        void seeMyFlow() {
                
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
                User tmp = clone(my_user);
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
                Address tmp = clone(my_user.getAddress());
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

        // Métodos auxiliares para clonagem
        User clone(User a) {
                User novo = new User();
                novo.setName(a.getName());
                novo.setEmail(a.getEmail());
                novo.setPhone(clone(a.getPhone()));
                novo.setCpfCnpj(a.getCpfCnpj());
                novo.setPeople_type(a.getPeople_type());
                novo.setUsername(a.getUsername());
                novo.setPassword(a.getPassword());
                novo.setAddress(clone(a.getAddress()));
                return novo;
        }
        Phone clone(Phone a) {
                Phone novo = new Phone();
                novo.setDdd(a.getDdd());
                novo.setNumber(a.getNumber());
                return novo;
        }
        Address clone(Address a) {
                Address novo = new Address();
                novo.setStreet(a.getStreet());
                novo.setNumber(a.getNumber());
                novo.setComplement(a.getComplement());
                novo.setNeighborhood(a.getNeighborhood());
                novo.setCity(a.getCity());
                novo.setState(a.getState());
                novo.setZip(a.getZip());
                return novo;
        }
}
