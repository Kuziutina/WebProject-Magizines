package Objects;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String cookie_login;
    private String confirmation;

    private List<Magazine> magazines;
    private List<User> friends;
    private List<Letter> letters;

    private String email;

    public User() {
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setId(int id) {
            User.this.id = id;

            return this;
        }

        public Builder setLogin(String login){
            User.this.login = login;

            return this;
        }

        public Builder setPassword(String password){
            User.this.password = password;

            return this;
        }

        public Builder setName(String name){
            User.this.name = name;

            return this;
        }

        public Builder setCookie_login(String cookie_login){
            User.this.cookie_login = cookie_login;

            return this;
        }

        public Builder setConfirmation(String confirmation){
            User.this.confirmation = confirmation;

            return this;
        }


        public User build() {
            return User.this;
        }
    }

    public User(int id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }


    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getCookie_login() {
        return cookie_login;
    }

    public void setCookie_login(String cookie_login) {
        this.cookie_login = cookie_login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
