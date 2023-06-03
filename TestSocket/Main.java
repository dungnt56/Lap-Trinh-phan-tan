package TestSocket;

import java.time.Instant;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println(instant.toEpochMilli());
    }
}
