import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Key;

public class PasswordEncode {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPwd = bCryptPasswordEncoder.encode("ab1$78");
        System.out.println(encodedPwd);

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("Secret key is ::::");
        System.out.println(key.toString());
    }
}
