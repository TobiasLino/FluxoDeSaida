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


import java.util.HashMap;
import java.util.Map;

public abstract class People {
        protected static int id;
        protected int people_id;
        protected String name;
        protected String cpf;
        protected Phone phone;
        protected String email;
        protected Address address;
        protected Map<String, Double> investments;

        public People() {
                people_id = id++;
                name = "";
                phone = new Phone();
                email = "";
                address = new Address();
                this.investments = new HashMap<>();
        }

        public abstract void print();

        public void addInvestment(String investment, double value) {
                this.investments.put(investment, value);
        }

        public void removeInvestment(String investment) {
                this.investments.remove(investment);
        }

        public int getPeople_id() {
                return people_id;
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

        public Map<String, Double> getInvestments() {
                return investments;
        }

        public void setInvestments(Map<String, Double> investments) {
                this.investments = investments;
        }

        public String getCpf() {
                return cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }
}
