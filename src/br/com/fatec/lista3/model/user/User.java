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
package br.com.fatec.lista3.model.user;

public class User {
        private static int id;
        private int user_id;
        private String name;
        private Phone phone;
        private String email;
        private Address address;

        public User() {
                user_id = id++;
                name = "";
                phone = new Phone();
                email = "";
                address = new Address();
        }

        public void print() {
                System.out.println("Dados do cliente:\n"
                        + "\tID:   " + id + "\n"
                        + "\tNome: " + name);
                phone.print();
                System.out.println("\tEmail:\n\t\t"
                        + email);
                address.print();
        }

        public int getUser_id() {
                return user_id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Phone getPhone() {
                return phone;
        }

        public void setPhone(Phone phone) {
                this.phone = phone;
        }

        public Address getAddress() {
                return address;
        }

        public void setAddress(Address address) {
                this.address = address;
        }
}
