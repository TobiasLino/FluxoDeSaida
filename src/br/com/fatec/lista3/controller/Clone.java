package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.client.Address;
import br.com.fatec.lista3.model.client.Phone;
<<<<<<< HEAD
import br.com.fatec.lista3.model.user.User;

public class Clone {
        // Métodos auxiliares para clonagem
        public User User(User a) {
                User novo = new User();
                novo.setName(a.getName());
                novo.setEmail(a.getEmail());
                novo.setPhone(this.Phone(a.getPhone()));
=======
import br.com.fatec.lista3.model.flow.Fisical;
import br.com.fatec.lista3.model.flow.Legal;
import br.com.fatec.lista3.model.user.User;

public class Clone {
        public User clone(User a) {
                User novo = new User();
                novo.setName(a.getName());
                novo.setEmail(a.getEmail());
                novo.setPhone(clone(a.getPhone()));
>>>>>>> code/database
                novo.setCpfCnpj(a.getCpfCnpj());
                novo.setPeople_type(a.getPeople_type());
                novo.setUsername(a.getUsername());
                novo.setPassword(a.getPassword());
<<<<<<< HEAD
                novo.setAddress(this.Address(a.getAddress()));
                return novo;
        }
        public Phone Phone(Phone a) {
=======
                novo.setAddress(clone(a.getAddress()));
                return novo;
        }
        public Phone clone(Phone a) {
>>>>>>> code/database
                Phone novo = new Phone();
                novo.setDdd(a.getDdd());
                novo.setNumber(a.getNumber());
                return novo;
        }
<<<<<<< HEAD
        public Address Address(Address a) {
=======
        public Address clone(Address a) {
>>>>>>> code/database
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
<<<<<<< HEAD
=======
        public Fisical clone(Fisical a) {
                Fisical novo = new Fisical(a.getUser());
                novo.setExpenses(a.getExpenses());
                novo.setSalary(a.getSalary());
                novo.setInvestments(a.getInvestments());
                return novo;
        }
        public Legal clone(Legal a) {
                Legal novo = new Legal(a.getUser());
                novo.setExpenses(a.getExpenses());
                novo.setSalesRevenue(a.getSalesRevenue());
                novo.setInvestments(a.getInvestments());
                return novo;
        }
>>>>>>> code/database
}
