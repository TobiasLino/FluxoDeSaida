package br.com.fatec.lista3;

import br.com.fatec.lista3.controller.DataBase;

public class Test {
        public static void main(String[] args) {
                DataBase db = new DataBase();
                db.search("inputs", "name", "Toba");
        }
}
