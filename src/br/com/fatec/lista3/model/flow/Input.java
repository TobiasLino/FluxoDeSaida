package br.com.fatec.lista3.model.flow;

import br.com.fatec.lista3.model.client.People;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Input {
        private People people;
        private Map<String, Double> investments;
        private Date date;

        public Input() {
                date = new Date();                      // Data do dia corrente.
                // Map de investimentos com o nome e valor (key e value, respectivamente.)
                this.investments = new HashMap<>();
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

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public People getPeople() {
                return people;
        }

        public void setPeople(People people) {
                this.people = people;
        }
}
