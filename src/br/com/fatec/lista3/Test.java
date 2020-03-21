package br.com.fatec.lista3;

import br.com.fatec.lista3.model.user.Address;
import br.com.fatec.lista3.model.user.Phone;
import br.com.fatec.lista3.model.user.User;

public class Test {
        public static void main(String[] args) {
                User usr = new User();
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

                usr.print();
        }
}
