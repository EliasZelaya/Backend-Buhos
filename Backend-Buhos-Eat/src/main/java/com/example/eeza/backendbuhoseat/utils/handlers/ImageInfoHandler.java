package com.example.eeza.backendbuhoseat.utils.handlers;

import com.example.eeza.backendbuhoseat.domain.dto.response.ImageInfo;
import com.example.eeza.backendbuhoseat.exception.DirectoryCreateException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.eeza.backendbuhoseat.utils.Constants.CONTENT_TYPE_DEFAULT;
import static com.example.eeza.backendbuhoseat.utils.Constants.PATH_FILE;

public class ImageInfoHandler {
    public static ImageInfo getImageInfo(String imageUrl) {
        Path path = Paths.get(PATH_FILE+"/"+imageUrl);
        if (Files.exists(path)) {
            String contentType = null;
            try {
                contentType = Files.probeContentType(path);
            } catch (IOException e) {
                throw new DirectoryCreateException(e.getMessage());
            }
            // Si no se pudo determinar el tipo de contenido, usar un valor predeterminado
            if (contentType == null) {
                contentType = CONTENT_TYPE_DEFAULT;
            }
            String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(PATH_FILE+"/")
                    .path(imageUrl)
                    .toUriString();

            return ImageInfo.builder()
                    .contentType(contentType)
                    .imageUrl(uri)
                    .build();
        }

        return null;
    }

    public static String replaceImage(Path oldImage, MultipartFile newImage) {
        try {
            Files.deleteIfExists(oldImage);
            String newImageFile = System.currentTimeMillis() + "-" + newImage.getOriginalFilename();
            Path newImagePath = Paths.get(PATH_FILE+"/"+newImageFile);
            newImage.transferTo(newImagePath);

            return newImageFile;
        } catch (IOException e) {
            throw new DirectoryCreateException(e.getMessage());
        }
    }
}
