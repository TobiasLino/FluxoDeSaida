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
package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.client.type.Fisical;
import br.com.fatec.lista3.model.user.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DataBase {
        private Connection connection;

        public DataBase() {
                try {
                        // Carrega o driver
                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        connection = createConnection();
                } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                }
        }

        private static Connection createConnection() throws SQLException, ClassNotFoundException {
                String url = "jdbc:mysql://localhost:3306/feira";
                String user = "feira";
                String password = "feira";

                return DriverManager.getConnection(url, user, password);
        }

        public void addClient(Fisical fisical) throws SQLException {
                String to = "INSERT INTO people VALUES("
                        + "0, '"
                        + fisical.getName() + "', '"
                        + fisical.getCpf() + "', '"
                        + fisical.getPhone().getDdd() + fisical.getPhone().getNumber() + "', '"
                        + fisical.getEmail() + "', '"
                        + fisical.getAddress().getStreet() + "', '"
                        + fisical.getAddress().getNumber() + "', '"
                        + fisical.getAddress().getComplement() + "', '"
                        + fisical.getAddress().getNeighborhood() + "', '"
                        + fisical.getAddress().getCity() + "', '"
                        + fisical.getAddress().getState() + "', '"
                        + fisical.getAddress().getZip() + "', '"
                        + "F" + "'"
                        + ");";
                PreparedStatement ps = connection.prepareStatement(to);
                ps.execute();
        }

        private void createDataBase() throws SQLException {
                String create = "CREATE DATABASE IF NOT EXISTS localDB;";
                PreparedStatement ps = connection.prepareStatement(create);
                ps.execute();
        }

        public void addUser(User user) throws SQLException, NoSuchAlgorithmException {
                String to = "INSERT INTO users VALUES (0,"
                        + "? , "
                        + "? );";
                PreparedStatement ps = connection.prepareStatement(to);
                ps.setString(1, user.getUsername());
                ps.setBytes(2, encrypt(user.getPassword()));
                ps.execute();
        }

        public User getUser(String username) throws SQLException {
                Statement stmt = connection.createStatement();
                ResultSet user_result = stmt.executeQuery(getUser_info(username));
                User n = new User("", "");
                assert user_result != null;
                if (user_result.next()) {
                        n.setUsername(user_result.getString("username"));
                        n.setPassword(decrypt(user_result.getBytes("password")));
                }
                return n;
        }
        public String getUser_info(String username) {
                return "SELECT *" +
                        " FROM users " +
                        "WHERE username='" + username + "' ;#";
        }

        public byte[] encrypt(String password)
                throws NoSuchAlgorithmException {
                MessageDigest alg = MessageDigest.getInstance("SHA-256");
                return alg.digest(password.getBytes(StandardCharsets.UTF_8));
        }

        public String decrypt(byte[] messageDigest) {
                StringBuilder hex = new StringBuilder();
                for (byte b : messageDigest) {
                        hex.append((String.format("%02x", 0xFF & b)));
                }
                return hex.toString();
        }
}
