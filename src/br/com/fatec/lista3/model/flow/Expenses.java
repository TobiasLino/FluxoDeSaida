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
package br.com.fatec.lista3.model.flow;

import java.util.*;

public class Expenses {
        // Despesas do mês, onde key = tipo e value = valor;
        private Map<String, Double> expenses;

        public Expenses() {
               this.expenses = new HashMap<>();
        }

        public void print() {
                System.out.println("\nDespesas: ");
                expenses.forEach((key, value) ->
                        System.out.println("\t" + key + ": " + value));
                System.out.println("\tTotal: " + sumOfExpenses());
        }

        public Double sumOfExpenses() {
                Double count = 0.0;
                for (Double n : expenses.values())
                        count += n;
                return count;
        }

        public void addExpense(String type, Double value) {
                expenses.put(type, value);
        }

        public void removeExpense(String type) {
                expenses.remove(type);
        }

        public boolean contains(String type) {
                return expenses.containsKey(type);
        }

        public Double getValue(String type) {
                return expenses.get(type);
        }

        public Map<String, Double> getExpenses() {
                return expenses;
        }

        public void setExpenses(Map<String, Double> expenses) {
                this.expenses = expenses;
        }
}
