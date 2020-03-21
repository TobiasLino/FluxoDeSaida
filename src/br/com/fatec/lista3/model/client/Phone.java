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

public class Phone {
        private String ddd;
        private String number;

        public Phone() {
                ddd = "";
                number = "";
        }

        public void print() {
                System.out.println("\tTelefone:\n"
                        + "\t\t(" + ddd + ") " + number);
        }

        public String getDdd() {
                return ddd;
        }

        public void setDdd(String ddd) {
                this.ddd = ddd;
        }

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                this.number = number;
        }
}
