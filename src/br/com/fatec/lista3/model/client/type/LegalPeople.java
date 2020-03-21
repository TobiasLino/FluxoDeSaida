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
package br.com.fatec.lista3.model.client.type;

import br.com.fatec.lista3.model.client.People;

public class LegalPeople extends People {
        private double salesRevenue;

        public LegalPeople() {
                super();
                salesRevenue = 0.0;
        }

        @Override
        public void print() {
                System.out.println("Dados do cliente:\n"
                        + "\tID:   " + id + "\n"
                        + "\tNome: " + name);
                phone.print();
                System.out.println("\tEmail:\n\t\t"
                        + email);
                address.print();
                System.out.println("Receita de Vendas: " + salesRevenue);
                System.out.println("Investimentos:");
                investments.forEach((key, value) ->
                        System.out.printf("\t%s: R$ %.2f\n", key, value)
                );
        }

        public double getSalesRevenue() {
                return salesRevenue;
        }

        public void setSalesRevenue(double salesRevenue) {
                this.salesRevenue = salesRevenue;
        }
}
