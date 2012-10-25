package zhwb.powermock.service;

import java.io.File;

public class PersistenceManager {

    public boolean createDirectoryStructure(String directoryPath) {
            File directory = new File(directoryPath);

            if (directory.exists()) {
                    throw new IllegalArgumentException("\"" + directoryPath + "\" already exists.");
            }

            return directory.mkdirs();
    }
}
