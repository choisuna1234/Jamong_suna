package com.ss.Jamong.board.service;

import com.ss.Jamong.board.entity.UploadResult;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
@Slf4j
@Service
public class StorageService {
    @Value("${upload.path}")
    String uploadPath;  // application.yml 에 정의( 업로드 파일이 들어갈 path (ex)/tmp/upload )

    /**
     *
     * @param file 업로드할 파일
     * @return UploadResult {path=201905/e98ff4f7-93a3-4aeb-813b-12f20a03db96.JPG,
     *         name=샘플파일.jpg, size=126495}
     * @throws IOException
     */
    public UploadResult upload(MultipartFile file, HttpServletRequest req) throws IOException {
        String uploadPath = req.getSession().getServletContext().getRealPath("/").concat("resources");
        log.info(uploadPath);
        // 년월 별로 업로드 디렉토리 관리
        String path = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 서버에 저장될 유니크한 파일명
        String id = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (StringUtils.isNotEmpty(extension)) {
            id = id + "." + extension;
        }

        File save = new File(String.format("%s%s%s%s%s", uploadPath, File.separator, path, File.separator, id));

        FileUtils.forceMkdirParent(save);
        file.transferTo(save);

        return UploadResult.builder()
                .path(String.format("%s%s%s", path, File.separator, id))
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .build();
    }

    /**
     * 파일 bytes 가져오기
     */
    public byte[] bytes(String path, String id) throws IOException {
        return Files.readAllBytes(
                new File(String.format(
                        "%s%s%s%s%s",
                        uploadPath, File.separator,
                        path, File.separator, id)).toPath()
        );
    }

    /**
     * out 으로 파일 bytes 보내기
     */
    public void to(String path, String id, OutputStream out) throws IOException {
        IOUtils.copy(new ByteArrayInputStream(bytes(path, id)), out);
    }
}
