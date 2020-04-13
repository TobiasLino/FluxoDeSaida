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

import br.com.fatec.lista3.model.user.User;

public class Fisical extends Input {
        private double salary;
        private Expenses expenses;
        // -1 caso esteja negativo, 0 se gastar todo o salário e 1 se sobrar dinheiro
        private int balance;
        // total subtraindo o salário + investimentos das despesas.
        private Double total;

        public Fisical(User user) {
                super(user);
                expenses = new Expenses();
        }

        private void setTotal() {
                Double ganhos = salary + sumOfInvestments();
                Double despesas = expenses.sumOfExpenses();
                total = ganhos - despesas;
        }

        public Double getTotal() {
                setTotal();
                return total;
        }

        private void situation(Double t) {
                if (t == 0) balance = 0;
                else if (t < 0) balance = -1;
                else balance = 1;
        }

        public int getBalance() {
                situation(total);
                return balance;
        }

        public void print() {
                super.print();
                expenses.print();
        }

        public double getSalary() {
                return salary;
        }

        public void setSalary(double salary) {
                this.salary = salary;
        }

        public Expenses getExpenses() {
                return expenses;
        }

        public void setExpenses(Expenses expenses) {
                this.expenses = expenses;
        }
}
