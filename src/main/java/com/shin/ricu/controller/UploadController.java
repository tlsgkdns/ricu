package com.shin.ricu.controller;

import com.shin.ricu.dto.upload.UploadFileDTO;
import com.shin.ricu.dto.upload.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UploadController {

    @Value("${org.ricu.upload.path}")
    private String uploadPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO)
    {
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() != null)
        {
            final List<UploadResultDTO> list = new ArrayList<>();
            MultipartFile multipartFile = uploadFileDTO.getFiles();
            String originalName = multipartFile.getOriginalFilename();
            log.info(originalName);
            String[] names = originalName.split("_");
            if(names.length > 1)
            {
                String newName = "";
                for(int i = 0; i < names.length; i++)
                {
                    newName += names[i];
                    if(i != names.length - 1) newName += "-";
                }
                originalName = newName;
            }
            String uuid = UUID.randomUUID().toString();
            Path savePath = Paths.get(uploadPath, uuid+ "_" + originalName);
            boolean image = false;
            try
            {
                multipartFile.transferTo(savePath);
                if(Files.probeContentType(savePath).startsWith("image"))
                {
                    image = true;
                    File thumbFile = new File(uploadPath, "s_" + uuid + "_"+ originalName);
                    Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            log.info("Upload FILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            list.add(UploadResultDTO.builder()
                    .uuid(uuid).fileName(originalName).isImage(image).build()
            );
            return list;
        }
        log.info("return null....");
        return null;
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName)
    {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        try
        {
            headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e)
        {
            return  ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @DeleteMapping("/remove/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName)
    {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        String resourceName = resource.getFilename();

        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try
        {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            removed = resource.getFile().delete();

            if(contentType.startsWith("image"))
            {
                File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                thumbnailFile.delete();
            }
        } catch (Exception e)
        {
            log.error(e.getMessage());
        }
        resultMap.put("result", removed);
        return resultMap;
    }
}
