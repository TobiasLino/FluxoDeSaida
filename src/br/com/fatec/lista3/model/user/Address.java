package br.com.fatec.lista3.model.user;

public class Address {
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
        private String zip;

        public Address() {
                street = "";
                number = "";
                complement = "";
                neighborhood = "";
                city = "";
                state = "";
                zip = "";
        }
        // Impressão dos dados
        public void print() {
                System.out.println("\tEndereço\n"
                        + "\t\tRua:         " + street + "\n"
                        + "\t\tNúmero:      " + number + "\n"
                        + "\t\tComplemento: " + complement + "\n"
                        + "\t\tBairro:      " + neighborhood + "\n"
                        + "\t\tCidade:      " + city + "\n"
                        + "\t\tEstado:      " + state + "\n"
                        + "\t\tCEP:         " + zip
                );
        }

        public String getStreet() {
                return street;
        }

        public void setStreet(String street) {
                this.street = street;
        }

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                if (number.matches("[0-9]*"))
                        this.number = number;
        }

        public String getComplement() {
                return complement;
        }

        public void setComplement(String complement) {
                this.complement = complement;
        }

        public String getNeighborhood() {
                return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
                this.neighborhood = neighborhood;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state.substring(0,1).toUpperCase();
        }

        public void setZip(String zip) {
                if (zip.substring(0,4).matches("[0-9]*"))
                        if (zip.substring(6, 9).matches("[0-9]*"))
                                this.zip = zip;
        }

        public String getZip() {
                return zip;
        }
}
