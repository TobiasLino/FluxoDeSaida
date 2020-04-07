package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.client.Phone;
import br.com.fatec.lista3.model.flow.Input;
import br.com.fatec.lista3.model.user.User;
import br.com.fatec.lista3.view.Menu;

public class Controller{
        private Insertion insert = new Insertion();
        private Clone clone = new Clone();
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
                String value = insert.getOptionNotEmpty("Digite o nome: ");
                if (value != null)
                        db.eraseUser(value);
        }
        User profileInfos(User my_user) {
                User tmp = clone.User(my_user);
                while (true) {
                        tmp.print();
                        switch (new Menu().editProfile(tmp)) {
                                case 1:
                                        tmp.setName(insert.getOption("Insira o nome: "));
                                        break;
                                case 2:
                                        CpfCnpjCheck checker = new CpfCnpjCheck();
                                        String str = insert.getOption("Insira o valor: ");
                                        String res = checker.isValid(str);
                                        tmp.setPeople_type(res);
                                        tmp.setCpfCnpj(str);
                                        break;
                                case 3:
                                        Phone n = new Phone();
                                        n.setDdd(insert.getOption("Insira o ddd: "));
                                        n.setNumber(insert.getOption("Insira o número: "));
                                        tmp.setPhone(n);
                                        break;
                                case 4:
                                        tmp.setEmail(insert.getOption("Insira o email: "));
                                        break;
                                case 5:
                                        defineAddressForUser(tmp);
                                        break;
                                case 6:
                                        return my_user;
                                case 7:
                                        if (insert.confirmOption()) {
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
                new AddressOperations().edit(my_user);
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
                                case 1: username = insert.getOption("insira o username: "); break;
                                case 2: password = insert.getOption("Insira a senha: "); break;
                                case 3:
                                        exit = true;
                                        break;
                                case 4:
                                        if (insert.confirmOption() && (!username.equals(""))) {
                                                user.setUsername(username);
                                                user.setPassword(password);
                                                exit = true;
                                                break;
                                        }
                        }
                }
        }
}
