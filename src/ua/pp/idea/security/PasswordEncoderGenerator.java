package ua.pp.idea.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Dark on 11.11.2016.
 */
public class PasswordEncoderGenerator {
String hashMd5;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String getHashMd5() {
        return hashMd5;
    }

    public void setHashMd5(String hashMd5) {
        this.hashMd5 = passwordEncoder.encode(hashMd5);
    }
}
