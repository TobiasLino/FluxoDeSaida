package br.com.fatec.lista3.controller;

import java.util.Scanner;

public class Controller {
        public String getOption(String message) {
                System.out.print(message);
                Scanner scan = new Scanner(System.in);
                return scan.nextLine();
        }
}
