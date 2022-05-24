package kz.daracademy.model.img;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgResponse {
    private String imgId;
    private String eventId;
    private String fileName;
    private String fileType;
    private byte[] data;
    private boolean mainPhoto;
}
