package ru.luxury.living.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.luxury.living.service.ImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("images")
@Tag(name = "Операции с изображениями")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public Long createImage(@RequestParam MultipartFile file) {
        return imageService.saveImage(file);
    }

    @GetMapping(value = "{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] image = imageService.getImage(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cache-Control", "public,max-age=360000");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(image);
    }
}

