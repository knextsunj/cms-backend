import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.regex.Pattern;

public class JeePasswordEncode {

    public static void main(String[] args) {
        Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

        //$2a$12$FGQQfuoeHiApqF.TJCyUP.Q2oDPmbP8Xisc3rmygzX87KUwKRHWYa

        String hash = "$2a$12$hvDe.5FUUWfwMmBKH9yCuOmKrAnytiafstJ5uYIR942amSLeS3WBe";

        String plainPwd = "123456";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, plainPwd.toCharArray());
        System.out.println("Hashed Password is ::: "+bcryptHashString);
//        for(char c:bcryptHashString.toCharArray()) {
//            System.out.println(c);
//        }

//        BCrypt.Result result = BCrypt.verifyer().verify(plainPwd.toCharArray(), hash);
//        System.out.println(result.verified);
    }
}
