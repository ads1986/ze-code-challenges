package ze.delivery.partner.util;

import java.nio.file.Files;
import java.nio.file.Paths;

public class StringUtil {
    public static String readFileAsString(String path, String fileName)throws Exception {
        String file = String.format(path, fileName);
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
