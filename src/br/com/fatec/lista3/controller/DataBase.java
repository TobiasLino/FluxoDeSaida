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
import br.com.fatec.lista3.model.user.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

        public DataBase() {
                try {
                        // Chave de acesso
                        FileInputStream serviceAccount = new FileInputStream(".firebase/" +
                                "/virtual-wallet-fatec-poo3-2020-firebase-adminsdk-esfrj-5798826318.json");
                        connect(serviceAccount);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        /*
         * Método que carrega o banco de dados
         * A entrada é o caminho para o arquivo json com a serviceAcount.
         */
        void connect(FileInputStream serviceAccount) throws IOException {
                // Inicia o aplicativo com previlégios de admin.
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://virtual-wallet-fatec-poo3-2020.firebaseio.com")
                        .build();
                FirebaseApp.initializeApp(options);

                DatabaseReference ref = FirebaseDatabase.getInstance()
                        .getReference("resctricted_acess/secret_document");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                Object document = dataSnapshot.getValue();
                                System.out.println(document);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                });
        }
        /*
         * Adiciona novo usuário no firebase usando uma estrutura de map
         * O índice será o nome de usuário.
         */
        public void addUser(User user) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();
                DatabaseReference users = ref.child("users");

                Map<String, User> in = new HashMap<>();
                in.put(user.getUsername(), user);

                users.setValueAsync(in);
        }
        /*
         * Atualiza o usuário no firebase.
         * a entrada é o nick do antigo usuário, o usuário atualizado e
         * o que será atualizado, senha ou nick.
         */
        public void updateUser(String oldUsername, User user, String what) throws NoSuchAlgorithmException {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("server/savig-data/fireblog");
                DatabaseReference users = ref.child("users");
                DatabaseReference update = users.child(oldUsername);

                Map<String, Object> in = new HashMap<>();
                switch (what) {
                        case "USERNAME":
                                in.put("username", user.getUsername());
                        case "PASSWORD":
                                in.put("password", user.getPassword());
                }

                update.updateChildrenAsync(in);
        }

        /*
         * Realiza uma consulta no firebase em busca de um usuário com username fornecido
         */
        public User getUser(String username) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference users = database.getReference("users");
                User user = new User();
                users.orderByChild("users").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                insertIntoUser(user, dataSnapshot);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                });
                return user;
        }
        /* Insere os dados obtidos em um user */
        void insertIntoUser(User user, DataSnapshot snapshot) {
                user.setUsername((String) snapshot.child("username").getValue());
                user.setPassword((String) snapshot.child("password").getValue());
        }

}
