package c06realm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Setter
@Getter
@ToString
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String salt;

    private Boolean locked = Boolean.FALSE;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
