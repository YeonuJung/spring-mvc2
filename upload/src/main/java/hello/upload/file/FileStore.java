package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> files) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile file : files) {
            if(!file.isEmpty()){
                storeFileResult.add(storeFile(file));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        // 원래 파일명
        String originalFileName = file.getOriginalFilename();
        // 서버에 저장하는 파일명
        String storeFileName = createStoreFileName(originalFileName);

        file.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFileName, storeFileName);
    }

    private String createStoreFileName(String originalFileName) {
        String ext = extractFileExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractFileExt(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
    }
}