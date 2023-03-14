package com.ss.Jamong.board.controller;

import com.google.common.collect.ImmutableMap;
import com.ss.Jamong.board.entity.UploadResult;
import com.ss.Jamong.board.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
public class FileController {
    @Autowired
    private StorageService storageService;

    /**
     * Froala Editor 이미지 업로드
     */
    @PostMapping("/image")
    @ResponseBody
    public Map<String, String> imageUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        try {
            UploadResult result = storageService.upload(file);

            log.debug("upload result : {}", result);

            // {"link" : "/image/201905/e98ff4f7-93a3-4aeb-813b-12f20a03db96.jpg"}
            return ImmutableMap.of(
                    "link",
                    String.format("%s/image/%s", request.getContextPath(), result.getPath()));

        } catch (Exception ex) {
            ex.printStackTrace();

            return ImmutableMap.of("error", ex.getMessage());
        }
    }
}
