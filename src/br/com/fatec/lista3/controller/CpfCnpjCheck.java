package br.com.fatec.lista3.controller;

import java.util.InputMismatchException;

public class CpfCnpjCheck {
        public boolean isCPF(String CPF) {
                final int size = 11;
                removeCaracteresEspeciais(CPF);
                // considera-se erro CPF's formados por uma sequencia de numeros iguais
                if (cpfHaveEqualNumbers(CPF)) return false;
                // dígitos a serem calculados
                char dig10, dig11;
                // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
                try {
                        // Calculo dos dois dígitos verificadores
                        dig10 = cpfFirstDigitCheck(CPF);
                        dig11 = cpfSecondDigitCheck(CPF);
                        // Verifica se os digitos calculados conferem com os digitos informados.
                        return numbersMatchWithDigits(dig10, dig11, CPF, size);
                } catch (InputMismatchException erro) {
                        return (false);
                }
        }

        public boolean isCNPJ(String CNPJ) {
                final int size = 14;
                removeCaracteresEspeciais(CNPJ);
                // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
                if (cnpjHaveEqualNumbers(CNPJ)) return false;
                // digitos a serem calculados
                char dig13, dig14;
                // "try" - protege o código para eventuais erros de conversao de tipo (int)
                try {
                        // Calculo dos dois dígitos verificadores
                        dig13 = cnpjFirstDigitCheck(CNPJ);
                        dig14 = cnpjSecondDigitCheck(CNPJ);
                        // Verifica se os dígitos calculados conferem com os dígitos informados.
                        return numbersMatchWithDigits(dig13, dig14, CNPJ, size);
                } catch (InputMismatchException erro) {
                        return (false);
                }
        }

        void removeCaracteresEspeciais(String in) {
                if (in.contains(".")) in = in.replace(".", "");
                if (in.contains("-")) in = in.replace("-", "");
                if (in.contains("/")) in = in.replace("/", "");
        }

        boolean cpfHaveEqualNumbers(String CPF) {
                return CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
                        || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
                        || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
                        || CPF.equals("99999999999") || (CPF.length() != 11);
        }

        boolean cnpjHaveEqualNumbers(String CNPJ) {
                return CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                        || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                        || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                        || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                        || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                        || (CNPJ.length() != 14);
        }

        char cpfFirstDigitCheck(String CPF) {
                int sm, i, r, num, peso;
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                        // converte o i-esimo caractere do CPF em um numero:
                        // por exemplo, transforma o caractere '0' no inteiro 0
                        // (48 eh a posicao de '0' na tabela ASCII)
                        num = (int) (CPF.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                        return '0';
                else
                        return (char) (r + 48); // converte no respectivo caractere numerico
        }

        char cpfSecondDigitCheck(String CPF) {
                int sm, i, r, num, peso;
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                        num = (int) (CPF.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                        return '0';
                else
                        return (char) (r + 48);

        }

        char cnpjFirstDigitCheck(String CNPJ) {
                int sm, i, r, num, peso;
                // Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 2;
                for (i = 11; i >= 0; i--) {
                        // converte o i-ésimo caractere do CNPJ em um número:
                        // por exemplo, transforma o caractere '0' no inteiro 0
                        // (48 eh a posição de '0' na tabela ASCII)
                        num = (int) (CNPJ.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso + 1;
                        if (peso == 10)
                                peso = 2;
                }

                r = sm % 11;
                if ((r == 0) || (r == 1))
                        return '0';
                else
                        return  (char) ((11 - r) + 48);
        }

        char cnpjSecondDigitCheck(String CNPJ) {
                int sm, i, r, num, peso;
                // Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 2;
                for (i = 12; i >= 0; i--) {
                        num = (int) (CNPJ.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso + 1;
                        if (peso == 10)
                                peso = 2;
                }

                r = sm % 11;
                if ((r == 0) || (r == 1))
                        return '0';
                else
                        return (char) ((11 - r) + 48);
        }

        boolean numbersMatchWithDigits(char dig1, char dig2, String in, int size) {
                if (size == 11) return (dig1 == in.charAt(9)) && (dig2 == in.charAt(10));
                if (size == 14) return (dig1 == in.charAt(12)) && (dig2 == in.charAt(13));
                return false;
        }

        String isValid(String CPFCNPJ) {
                if (isCPF(CPFCNPJ))
                        return "F";
                if (isCNPJ(CPFCNPJ))
                        return "L";
                else return null;
        }
}
