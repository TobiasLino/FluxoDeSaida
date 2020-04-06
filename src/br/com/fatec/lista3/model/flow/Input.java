package br.com.fatec.lista3.model.flow;
import java.util.HashMap;
import java.util.Map;

public class Input {
        private String peopleType;
        private Map<String, Double> investments;
        private double salary;
        private double salesRevenue;

        public Input() {
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

        public double getSalary() {
                return salary;
        }

        public void setSalary(double salary) {
                this.salary = salary;
        }

        public double getSalesRevenue() {
                return salesRevenue;
        }

        public void setSalesRevenue(double salesRevenue) {
                this.salesRevenue = salesRevenue;
        }

        public String getPeopleType() {
                return peopleType;
        }

        public void setPeopleType(String peopleType) {
                this.peopleType = peopleType;
        }
}
