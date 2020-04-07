package br.com.fatec.lista3.controller;

import java.util.Scanner;

public class Insertion {
        private Scanner scan = new Scanner(System.in);
        // getOption retorna a opção do usuário mediante uma mensagem.
        /* getOption sem verificação de string vazia */
        public String getOption(String message) {
                System.out.print(message);
                return scan.nextLine();
        }
        /* getOption com verificação de string vazia */
        public String getOptionNotEmpty(String message) {
                System.out.print(message);
                String opt = scan.nextLine();
                if (!opt.equals(""))
                        return opt;
                return null;
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

        /* Retorna true se o cliente confirmar a operação */
        public boolean confirmOption() {
                String opt = getOption("Confirmar ? [S/n] : ");
                return opt.equals("") || opt.equals("S") || opt.equals("s");
        }
}
