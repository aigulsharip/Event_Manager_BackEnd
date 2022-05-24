package kz.daracademy.model.img;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgRequest {
    private String imgId;
    private String eventId;
    private MultipartFile file;
    private boolean mainPhoto;
}
