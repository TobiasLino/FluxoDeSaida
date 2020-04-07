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
package br.com.fatec.lista3.model.client;

import java.util.InputMismatchException;

public abstract class People {
        protected String name;
        protected String people_type;     // 'F' for Fisical and 'L' for Legal.
        protected String cpfCnpj;
        protected Phone phone;
        protected String email;
        protected Address address;

        public People() {
                name = "";
                cpfCnpj = "";
                phone = new Phone();
                email = "";
                address = new Address();
                people_type = "F";
        }

        public void print() {
                System.out.println("\n\n\tNome: "
                        + name + "\n"
                        + "Phone: " + phone.getNumber() + "\n"
                        + name + "\n");
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

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getCpfCnpj() {
                return cpfCnpj;
        }

        public void setCpfCnpj(String CPFCNPJ) {
                this.cpfCnpj = CPFCNPJ;
        }

        public String getPeople_type() {
                return people_type;
        }

        public void setPeople_type(String people_type) {
                this.people_type = people_type;
        }


        public String imprimeCNPJ() {
                // máscara do CNPJ: 99.999.999.9999-99
                return (cpfCnpj.substring(0, 2)
                        + "." + cpfCnpj.substring(2, 5)
                        + "." + cpfCnpj.substring(5, 8)
                        + "." + cpfCnpj.substring(8, 12)
                        + "-" + cpfCnpj.substring(12, 14));
        }
}
