package br.com.fatec.lista3;

import br.com.fatec.lista3.model.client.Address;
import br.com.fatec.lista3.model.client.People;
import br.com.fatec.lista3.model.client.Phone;
import br.com.fatec.lista3.model.client.type.FisicalPeople;

public class Test {
        public static void main(String[] args) {
                People usr = new FisicalPeople();
                Address address = new Address();
                Phone phone = new Phone();

                usr.setName("Tobias");
                address.setStreet("A");
                address.setNumber("0");
                address.setComplement("A");
                address.setNeighborhood("A");
                address.setCity("A");
                address.setState("A");
                address.setZip("11222-256");
                usr.setAddress(address);
                phone.setDdd("12");
                phone.setNumber("996262526");
                usr.setPhone(phone);

                usr.addInvestment("Rendimento de aplicações", 1020.00);
                usr.addInvestment("Aluguel", 500.00);

                usr.print();
        }
}
