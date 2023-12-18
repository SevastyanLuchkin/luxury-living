package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.luxury.living.model.Image;
import ru.luxury.living.repository.ImageRepository;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Long saveImage(MultipartFile file) {
        try {
            Image image = new Image();
            image.setTitle(file.getName());
            image.setPicture(file.getBytes());
            return imageRepository.save(image).getId();
        } catch (IOException e) {
            log.error("error when save image", e);
            throw new RuntimeException("error when save image");
        }
    }

    public byte[] getImage(Long id) {
        return imageRepository.findById(id)
                .orElseThrow().getPicture();
    }
}
