package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.client.Address;
import org.lavieri.modelutil.cep.WebServiceCep;

public class ZIPChecker {
        private WebServiceCep webServiceCep;

        public void check(Address my_address) {
                String cep = search(my_address);
                // se a busca corresponder, insere os dados no objeto
                assert !cep.equals("");
                if (webServiceCep.wasSuccessful()) {
                        insertInAddress(cep, webServiceCep, my_address);
                }
        }

        private String search(Address my_address) {
                String cep = new Insertion().getOptionNotEmpty("Insira o CEP: ");
                if (cep == null) return "";
                // Faz a busca para o cep
                webServiceCep = WebServiceCep.searchCep(cep);
                return cep;
        }

        private void insertInAddress(String cep, WebServiceCep webServiceCep, Address my_address) {
                my_address.setZip(cep);
                my_address.setStreet(webServiceCep.getLogradouroFull());
                my_address.setNeighborhood(webServiceCep.getBairro());
                my_address.setCity(webServiceCep.getCidade());
                my_address.setState(webServiceCep.getUf());
        }
}
