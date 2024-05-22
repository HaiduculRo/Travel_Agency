package App.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static final String AUDIT_FILE_PATH = "audit.csv";

    public static void writeAudit(String actionName) {
        try {
            if (!Files.exists(Paths.get(AUDIT_FILE_PATH))) {
                // Dacă nu există, creăm fișierul și scriem antetul
                FileWriter writer = new FileWriter(AUDIT_FILE_PATH);
                writer.write("action_name,timestamp\n");
                writer.close();
            }

            // Obținem timestamp-ul curent
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = timestamp.format(formatter);

            FileWriter writer = new FileWriter(AUDIT_FILE_PATH, true);
            writer.write(actionName + "," + formattedTimestamp + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to audit file: " + e.getMessage());
        }
    }
}
