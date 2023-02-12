package Generador;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

public class Argon2Id {
    private static AtomicLong counter = new AtomicLong();
    private static Argon2BytesGenerator argon2Generator = new Argon2BytesGenerator();
    private static SecureRandom random = new SecureRandom();

    public static byte[] generateArgon2Id() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        Argon2Parameters params = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withSalt(salt)
                .withIterations(2)
                .withMemoryAsKB(512)
                .withParallelism(1)
                .build();

        byte[] hash = new byte[64];
        argon2Generator.init(params);
        argon2Generator.generateBytes(String.valueOf(counter.getAndIncrement()).getBytes(), hash, 0, hash.length);
        
        return hash;
    }

//    public static void main(String[] args) {
//        byte[] hash = generateArgon2Id();
//        System.out.println("Hash generado: " +  Base64.getEncoder().encodeToString(hash));
//    }
}