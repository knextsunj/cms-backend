import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.regex.Pattern;

public class JeePasswordEncode {

    public static void main(String[] args) {
        Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

        String plainPwd = "123456";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, plainPwd.toCharArray());
        System.out.println("Hashed Password is ::: "+bcryptHashString);
        for(char c:bcryptHashString.toCharArray()) {
            System.out.println(c);
        }
    }
}
