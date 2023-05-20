import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncode {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPwd = bCryptPasswordEncoder.encode("ab1$78");
        System.out.println(encodedPwd);
    }
}
