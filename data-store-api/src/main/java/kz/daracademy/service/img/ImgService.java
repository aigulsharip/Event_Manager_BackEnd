package kz.daracademy.service.img;


import kz.daracademy.model.img.ImgResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImgService {

    ImgResponse saveImg(MultipartFile file, String eventId, boolean mainPhoto) throws Exception;

//    ImgResponse updateImg(String eventId);

    List<ImgResponse> getAllImgs();

    ImgResponse getImgByEventIdAndMain(String eventId, boolean mainPhoto);

    List<ImgResponse> getImgsByEventId(String eventId);

    void deleteImgByEventId(String eventId);
}
