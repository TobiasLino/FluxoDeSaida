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

public class Address {
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
        private String zip;

        public Address() {
                street = "";
                number = "";
                complement = "";
                neighborhood = "";
                city = "";
                state = "";
                zip = "";
        }
        // Impressão dos dados
        public void print() {
                System.out.println("\tEndereço\n"
                        + "\t\tRua:         " + street + "\n"
                        + "\t\tNúmero:      " + number + "\n"
                        + "\t\tComplemento: " + complement + "\n"
                        + "\t\tBairro:      " + neighborhood + "\n"
                        + "\t\tCidade:      " + city + "\n"
                        + "\t\tEstado:      " + state + "\n"
                        + "\t\tCEP:         " + zip
                );
        }

        public String getStreet() {
                return street;
        }

        public void setStreet(String street) {
                this.street = street;
        }

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                if (number.matches("[0-9]*"))
                        this.number = number;
        }

        public String getComplement() {
                return complement;
        }

        public void setComplement(String complement) {
                this.complement = complement;
        }

        public String getNeighborhood() {
                return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
                this.neighborhood = neighborhood;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state.substring(0,1).toUpperCase();
        }

        public void setZip(String zip) {
                this.zip = zip;
        }

        public String getZip() {
                return zip;
        }
}
