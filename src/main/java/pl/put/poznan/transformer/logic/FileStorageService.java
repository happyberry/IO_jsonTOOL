package pl.put.poznan.transformer.logic;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileStorageService {

    public FileStorageService() {

    }

    public static String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (fileName.contains("..")) {
                throw new FileStorageException("Invalid path sequence for " + fileName);
            }


            byte[] bytes = new byte[file.getInputStream().available()];
            file.getInputStream().read(bytes);
            String content = new String(bytes, StandardCharsets.UTF_8);

            return content;
        } catch (IOException ex) {
            throw new FileStorageException("Can't store " + fileName, ex);
        }
    }

}
