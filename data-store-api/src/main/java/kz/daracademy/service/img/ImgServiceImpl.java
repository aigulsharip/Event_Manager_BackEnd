package kz.daracademy.service.img;

import kz.daracademy.model.img.ImgEntity;
import kz.daracademy.model.img.ImgRequest;
import kz.daracademy.model.img.ImgResponse;
import kz.daracademy.model.user.UserResponse;
import kz.daracademy.repository.ImgRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImgServiceImpl implements ImgService{

    @Autowired
    private ImgRepository imgRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    @Override
    public ImgResponse saveImg(MultipartFile file, String eventId, boolean mainPhoto) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }

            ImgEntity imgEntity
                    = new ImgEntity();
            String id = UUID.randomUUID().toString();

            imgEntity.setImgId(id);
            imgEntity.setEventId(eventId);
            imgEntity.setFileName(fileName);
            imgEntity.setFileType(file.getContentType());
            imgEntity.setData(file.getBytes());
            imgEntity.setMainPhoto(mainPhoto);
            imgRepository.save(imgEntity);
            return modelMapper.map(imgRepository.save(imgEntity), ImgResponse.class);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

//    @Override
//    public ImgResponse updateImg(String eventId) {
//        imgRepository.deleteImgEntityByEventId(eventId);
//        saveImg(eventId)
//        return null;
//    }

    @Override
    public List<ImgResponse> getAllImgs() {
        List<ImgEntity> imgEntities =  imgRepository.getImgEntitiesBy();
        if (imgEntities.size() == 0) {
            return null;
        }
        return imgEntities.stream().map(el -> modelMapper.map(el, ImgResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ImgResponse getImgByEventIdAndMain(String eventId, boolean mainPhoto) {
        List<ImgEntity> imgEntities = imgRepository.getImgEntitiesByEventId(eventId);
        System.out.println(imgEntities);
        if (imgEntities.size() == 0) {
            return null;
        }
        List<ImgEntity> imgEntities2 = new ArrayList<>();
        imgEntities.forEach(i -> {
            if (i.isMainPhoto()) {
                imgEntities2.add(i);
            }
        });
        ImgResponse imgResponse =
                modelMapper.map(imgEntities2.get(0), ImgResponse.class);
        return imgResponse;
    }

    @Override
    public List<ImgResponse> getImgsByEventId(String eventId) {
        List<ImgEntity> imgEntities = imgRepository.getImgEntitiesByEventId(eventId);
        if (imgEntities.size() == 0) {
            return null;
        }
        return imgEntities.stream().map(el -> modelMapper.map(el, ImgResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteImgByEventId(String eventId) {
        imgRepository.deleteImgEntityByEventId(eventId);
    }
}
