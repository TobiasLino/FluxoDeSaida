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
package br.com.fatec.lista3.model.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
        private String username;
        private String password;

        public User() {
                password = "";
                username = "";
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                try {
                        byte[] p = encrypt(password);
                        this.password = decrypt(p);
                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                }

        }

        /*
         * Retorna uma hash SHA-256 em formato byte[] de tamanho 32bytes.
         */
        private byte[] encrypt(String password)
                throws NoSuchAlgorithmException {
                MessageDigest alg = MessageDigest.getInstance("SHA-256");
                return alg.digest(password.getBytes(StandardCharsets.UTF_8));
        }

        /*
         * Retorna uma string criptografada a partir de uma mensagem de 32bytes.
         */
        public String decrypt(byte[] messageDigest) {
                StringBuilder hex = new StringBuilder();
                for (byte b : messageDigest) {
                        hex.append((String.format("%02x", 0xFF & b)));
                }
                return hex.toString();
        }
}
