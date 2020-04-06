package br.com.fatec.lista3.controller;


import br.com.fatec.lista3.model.client.Address;
import br.com.fatec.lista3.model.client.Phone;
import br.com.fatec.lista3.model.flow.Input;
import br.com.fatec.lista3.model.user.User;
import br.com.fatec.lista3.view.Menu;

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

        void newInput(User user) {
                Input in = new Input();
                editInput(in);
        }

        void editInput(Input input) {
                Input tmp = new Input();
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
        public void myProfile(User my_user) {
                boolean exit = false;
                while (!exit)
                        switch (new Menu().meuPerfil(my_user)) {
                                case 1 :
                                case 2 :
                                case 3 : exit = true; break;
                                default:
                                        System.err.println("\nInsira uma opção válida.");
                        }
        }
        // Edita o perfil
        void editProfile(User my_user) {
                my_user = profileInfos(my_user);
        }

        User profileInfos(User my_user) {
                User tmp = clone(my_user);
                while (true) {
                        my_user.print();
                        switch (new Menu().editProfile(tmp)) {
                                case 1:
                                        tmp.setUsername(getOption("Insira o nome: "));
                                        break;
                                case 2:
                                        tmp.setCpfCnpj(getOption("Insira o valor: "));
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
                                case 6:
                                        return my_user;
                                case 7:
                                        if (confirmOption()) {
                                                return tmp;
                                        }
                        }
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
