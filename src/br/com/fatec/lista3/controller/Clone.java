package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.client.Address;
import br.com.fatec.lista3.model.client.Phone;
import br.com.fatec.lista3.model.user.User;

public class Clone {
        // Métodos auxiliares para clonagem
        public User User(User a) {
                User novo = new User();
                novo.setName(a.getName());
                novo.setEmail(a.getEmail());
                novo.setPhone(this.Phone(a.getPhone()));
                novo.setCpfCnpj(a.getCpfCnpj());
                novo.setPeople_type(a.getPeople_type());
                novo.setUsername(a.getUsername());
                novo.setPassword(a.getPassword());
                novo.setAddress(this.Address(a.getAddress()));
                return novo;
        }
        public Phone Phone(Phone a) {
                Phone novo = new Phone();
                novo.setDdd(a.getDdd());
                novo.setNumber(a.getNumber());
                return novo;
        }
        public Address Address(Address a) {
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
