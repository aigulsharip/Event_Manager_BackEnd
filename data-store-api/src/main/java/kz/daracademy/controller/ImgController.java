package kz.daracademy.controller;

import kz.daracademy.model.img.ImgResponse;
import kz.daracademy.service.img.ImgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/img")
public class ImgController {
    @Autowired
    private ImgServiceImpl imgService;

    @PostMapping("/upload/{eventId}")
    public ImgResponse uploadFile(@RequestParam("file") MultipartFile img, @PathVariable String eventId, @RequestParam boolean main) throws Exception {
        return imgService.saveImg(img, eventId, main);
    }
}
