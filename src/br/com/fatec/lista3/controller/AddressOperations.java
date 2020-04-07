package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.client.Address;
import br.com.fatec.lista3.model.user.User;
import br.com.fatec.lista3.view.Menu;

public class AddressOperations {
        private Address currentAddress;
        private Insertion insert = new Insertion();

        public void edit(User my_user) {
                currentAddress  = new Clone().Address(my_user.getAddress());
                switcher(my_user);
        }

        private void switcher(User my_user) {
                boolean exit = false;
                while (!exit) {
                        currentAddress.print();
                        switch (new Menu().editProfile_Address()) {
                                case 1: zip(); break;
                                case 2: street(); break;
                                case 3: number(); break;
                                case 4: complement(); break;
                                case 5: neighborhood(); break;
                                case 6: city(); break;
                                case 7: state(); break;
                                case 8: exit = true; break;
                                case 9:
                                        if (insert.confirmOption()) {
                                                my_user.setAddress(currentAddress);
                                                exit = true;
                                                break;
                                        }
                        }
                }
        }

        private void zip() {
                new ZIPChecker().check(currentAddress);
        }

        private void street() {
                currentAddress.setStreet(insert.getOption("Insira o endereço: "));
        }

        private void number() {
                currentAddress.setNumber(insert.getOption("Insira o número: "));
        }

        private void complement() {
                currentAddress.setComplement(insert.getOption("Insira o complemento: "));
        }

        private void neighborhood() {
                currentAddress.setNeighborhood(insert.getOption("Insira o bairro: "));
        }

        private void city() {
                currentAddress.setCity(insert.getOption("Insira a cidade: "));
        }

        private void state() {
                currentAddress.setState(insert.getOption("Insira a sigla da UF: "));
        }
}
