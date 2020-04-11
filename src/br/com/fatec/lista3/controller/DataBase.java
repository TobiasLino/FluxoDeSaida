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

import br.com.fatec.lista3.model.client.Address;
import br.com.fatec.lista3.model.user.User;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class DataBase {
        private Firestore db;
        /*
         * Construtor:
         *      -> 1º busca uma chave de acesso que ta armazenada na pasta raíz do projeto,
         *              cujo nome é .firebase ('.' por que é pra ser uma pasta oculta)
         *      -> 2º chama um método connect().
         */
        public DataBase() {
                try {
                        // Chave de acesso
                        FileInputStream serviceAccount = new FileInputStream(".firebase/" +
                                "/virtual-wallet-fatec-poo3-2020-firebase-adminsdk-esfrj-5798826318.json");
                        // Realiza a conexão
                        connect(serviceAccount);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        /*
         * Inicia o firebase a partir do objeto FirebaseOptions, onde eu defino
         *  as credenciais(arquivo .firebase), define a url do banco e construo
         *  depois inicio com FirebaseApp.initializeApp(options);
         * Isso faz o firebase ser carregado no projeto.
         */
        void connect(FileInputStream serviceAccount) throws IOException {
                GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(credentials)
                        .build();
                FirebaseApp.initializeApp(options);
                db = FirestoreClient.getFirestore();
        }
        /*
         * Obtém as referências para as chaves selecionadas
         */
        public CollectionReference getReference(String key) {
                 return db.collection(key);
        }
        public DocumentReference getReference(String key, String document) {
                return getReference(key).document(document);
        }
        /*
         * Imprime o tempo de consulta
         */
        public void getUpdateTime(ApiFuture<WriteResult> result) {
                try {
                        System.out.println("Update timer: " + result.get().getUpdateTime());
                } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                }
        }
        /*
         * Adiciona novo usuário no firebase.
         * users é uma referência para o campo users no banco.
         *
         * O map in possui chave -> username
         *                 valor -> usuário
         * assim, o cadastro ficará registrado por nome do usuário
         */
        public void addUser(User user) {
                DocumentReference ref = getReference("users", user.getUsername());

                Map<String, Object> in = new HashMap<>();
                insertUser(in, user);

                ApiFuture<WriteResult> result = ref.set(in);
                getUpdateTime(result);
        }
        /*
         * Insere os dados do Usuário no map
         */
        private void insertUser(Map<String, Object> map, User user) {
                insertMinimalInfos(map, user);
                insertCpfOrCnpj(map, user);
                insertAddress(map, user);
                insertPhone(map, user);
        }
        private void insertMinimalInfos(Map<String, Object> map, User user) {
                map.put("name", user.getName());
                map.put("password", user.getPassword());
                map.put("username", user.getUsername());
                map.put("people_type", user.getPeople_type());
                map.put("admin", user.isAdmin());
                map.put("email", user.getEmail());
        }
        private void insertCpfOrCnpj(Map<String, Object> map, User user) {
                switch (user.getPeople_type()) {
                        case "F": map.put("cpf", user.getCpfCnpj()); break;
                        case "L": map.put("cnpj", user.getCpfCnpj()); break;
                }
        }
        private void insertAddress(Map<String, Object> map, User user) {
                Map<String, String> address = new HashMap<>();
                address.put("zip", user.getAddress().getZip());
                address.put("street", user.getAddress().getStreet());
                address.put("number", user.getAddress().getNumber());
                address.put("complement", user.getAddress().getComplement());
                address.put("neighborhood", user.getAddress().getNeighborhood());
                address.put("city", user.getAddress().getCity());
                address.put("state", user.getAddress().getState());
                map.put("address", address);
        }
        private void insertPhone(Map<String, Object> map, User user) {
                Map<String, String> p = new HashMap<>();
                p.put("ddd", user.getPhone().getDdd());
                p.put("number", user.getPhone().getNumber());
                map.put("phone", p);
        }
        /*
         * Atualiza o usuário no firebase.
         * a entrada é o nick do antigo usuário, o usuário atualizado e
         * o que será atualizado, senha ou nick.
         */
        public void updateUser(String oldUsername, User user, String what) {

        }

        /*
         * Realiza uma consulta no firebase em busca de um usuário com username fornecido
         */
        public User getUser(String username) {
                CollectionReference users = getReference("users");
                Query query = users.whereEqualTo("username", username);
                ApiFuture<QuerySnapshot> querySnapshot = query.get();
                return getUserFromDocument(querySnapshot);
        }
        private User getUserFromDocument(ApiFuture<QuerySnapshot> querySnapshot) {
                try {
                        for (DocumentSnapshot document : querySnapshot.get().getDocuments())
                                return document.toObject(User.class);
                } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                }
                return null;
        }

        /*
         * Faz a verificação dos dados de login
         */
        public User checkUser(String username, String pass) {
                User to_comp = getUser(username);
                pass = to_comp.encodePassword(pass);
                if (pass.equals(to_comp.getPassword())) {
                        return to_comp;
                }
                return null;
        }

        /*
         * Apaga usuário do FireBase
         * A consulta pode ser realizada name
         * Compreendendo a regra de negócio 09.
         */
        public void eraseUser(String name) {
                CollectionReference ref = db.collection("users");
                ApiFuture<WriteResult> writeResult = ref.document(name).delete();
                getUpdateTime(writeResult);
        }

        /*
         * Realiza pesquisa no banco de dados
         */
        public void search(String local, String key, String value) {
                CollectionReference ref = getReference(local);
                Query query = ref.whereEqualTo(key, value);
                ApiFuture<QuerySnapshot> querySnapshot = query.get();
                printResult(querySnapshot);
        }

        private void printResult(ApiFuture<QuerySnapshot> querySnapshot) {
                try {
                        for (DocumentSnapshot d : querySnapshot.get().getDocuments()) {
                                printUser(d);
                        }
                } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                }
        }

        private void printUser(DocumentSnapshot d) {
                System.out.println("\nNome: " + d.get("name") + "\n"
                        + "Username: " + d.getId());
                {
                if (Objects.equals(d.get("people_type"), "L"))
                                System.out.println("\tCNPJ: " + d.get("cnpj"));
                        else System.out.println("\tCPF: " + d.get("cpf"));
                }
                System.out.println("\tPhone: "
                        + printPhone((Map<String, Objects>) d.get("phone")) + "\n"
                        + "\tEmail: " + d.get("email"));
                printAddress((Map<String, Object>) d.get("address"));
        }

        private void printAddress(Map<String, Object> address) {
                System.out.println("\tEndereço\n"
                        + "\t\tRua:         " + address.get("street") + "\n"
                        + "\t\tNúmero:      " + address.get("number") + "\n"
                        + "\t\tComplemento: " + address.get("complement") + "\n"
                        + "\t\tBairro:      " + address.get("neighborhood") + "\n"
                        + "\t\tCidade:      " + address.get("city") + "\n"
                        + "\t\tEstado:      " + address.get("state") + "\n"
                        + "\t\tCEP:         " + address.get("zip")
                );
        }

        private String printPhone(Map<String, Objects> phone) {
                return "(" + phone.get("ddd") + ") " + phone.get("number");
        }
}
