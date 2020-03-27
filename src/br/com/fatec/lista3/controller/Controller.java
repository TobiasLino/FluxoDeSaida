package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.flow.Input;

import java.util.Scanner;

public class Controller {
        private Scanner scan = new Scanner(System.in);

        /* getOption sem verificação de string vazia */
        public String getOption(String message) {
                System.out.print(message);
                return scan.nextLine();
        }

        /* getOption com retorno em int */
        public int getIntOption(String message) {
                System.out.print(message);
                String to_int = scan.nextLine();
                int ret = 0;
                if (!to_int.isEmpty() && canBeInt(to_int))
                        ret = Integer.parseInt(to_int);
                return ret;
        }

        /* Verifica se uma string contém apenas números */
        public boolean canBeInt(String str) {
                return str.matches("[0-9]*");
        }
}
