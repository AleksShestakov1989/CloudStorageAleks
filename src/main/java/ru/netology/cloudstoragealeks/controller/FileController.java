package ru.netology.cloudstoragealeks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloudstoragealeks.dto.response.FileNameEditResponse;
import ru.netology.cloudstoragealeks.entity.CloudFile;
import ru.netology.cloudstoragealeks.service.FileService;
import ru.netology.cloudstoragealeks.dto.request.FileNameEditRequest;
import ru.netology.cloudstoragealeks.dto.response.FileWebResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/file")
    public ResponseEntity<Void> uploadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename, MultipartFile file) {
        fileService.uploadFile(authToken, filename, file);
        log.info("File {} uploaded successfully", filename.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        fileService.deleteFile(authToken, filename);
        log.info("File {} deleted successfully", filename.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile(@RequestHeader("auth-token") String authToken, @RequestParam("filename") String filename) {
        CloudFile file = fileService.downloadFile(authToken, filename);
        log.info("File {} downloaded successfully", filename.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFilename())
                .body(file.getFileData());
    }

    @PutMapping("/file")
    public ResponseEntity<?> editFile(@RequestHeader("auth-token") String authToken,
                                      @RequestParam("filename") String filename,
                                      @RequestBody FileNameEditRequest editRequest) {
        fileService.editFile(authToken, filename, editRequest.filename());
        log.info("File edited successfully");
        return new ResponseEntity<>(new FileNameEditResponse(editRequest.filename()),HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileWebResponse>> getAllFiles(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) {
        List<FileWebResponse> files = fileService.getAllFiles(authToken, limit);
        log.info(String.format("Files %s received successfully", files));
        return ResponseEntity.ok(files);
    }
}
