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

import java.util.HashMap;
import java.util.Map;

public class Input {
        private User user;
        private Map<String, Double> investments;

        public Input(User user) {
                // Map de investimentos com o nome e valor (key e value, respectivamente.)
                this.investments = new HashMap<>();
                this.user = user;
        }

        public Double sumOfInvestments() {
                Double count = 0.0;
                for (Double n : investments.values())
                        count += n;
                return count;
        }

        public void print() {
                investments.forEach((key, value) -> System.out.println("\t" + key + ": " + value));
        }

        public void addInvestment(String investment, double value) {
                this.investments.put(investment, value);
        }

        public void removeInvestment(String investment) {
                this.investments.remove(investment);
        }

        public Map<String, Double> getInvestments() {
                return investments;
        }

        public void setInvestments(Map<String, Double> investments) {
                this.investments = investments;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }
}
