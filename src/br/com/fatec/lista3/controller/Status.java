package br.com.fatec.lista3.controller;

import br.com.fatec.lista3.model.user.User;

public class Status {
        private boolean logged;
        private User user;
        private boolean adminStatus;

        public Status() {
                logged = false;
                user = new User();
                adminStatus = false;
        }

        public boolean isLogged() {
                return logged;
        }

        public void setLogged(boolean logged) {
                this.logged = logged;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public boolean isAdminStatus() {
                return adminStatus;
        }

        public void setAdminStatus(boolean adminStatus) {
                this.adminStatus = adminStatus;
        }
}
