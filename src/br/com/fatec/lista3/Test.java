package br.com.fatec.lista3;

import br.com.fatec.lista3.model.flow.Expenses;

public class Test {
        public static void main(String[] args) {
                Expenses e = new Expenses();
                e.addExpense("luz", 274.5);
                e.addExpense("gás", 150.0);

                e.print();
        }
}
