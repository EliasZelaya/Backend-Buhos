package com.example.eeza.backendbuhoseat.utils.handlers;

import com.example.eeza.backendbuhoseat.exception.DirectoryCreateException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.eeza.backendbuhoseat.utils.Constants.PATH_FILE;

public class SaveImages {
    public static String saveImagesHandler(MultipartFile image) {
        Path path = Paths.get(PATH_FILE);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path); // Crea la carpeta si no existe
            }
            //Se genera un nombre unico para el archivo
            String name = System.currentTimeMillis() + "-" + image.getOriginalFilename();
            Path imagePath = path.resolve(name); //Mete el archivo en la carpeta
            image.transferTo(imagePath);

            return name;
        } catch (IOException e) {
            throw new DirectoryCreateException(e.getMessage());
        }
    }
}
