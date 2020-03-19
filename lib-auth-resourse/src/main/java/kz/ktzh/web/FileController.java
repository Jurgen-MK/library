package kz.ktzh.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kz.ktzh.utils.MediaTypeUtils;

@RestController
@RequestMapping("/files")
public class FileController {
	private static final String DIRECTORY = "C:\\ECB_LIB";
	private static final String DEFAULT_FILE_NAME = "1.docx";

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/getfile")
	public ResponseEntity<ByteArrayResource> getFile(@RequestParam(defaultValue = DEFAULT_FILE_NAME) String filepath, @RequestParam String fileName)
			throws IOException {
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("fileName: " + fileName);
		System.out.println("mediaType: " + mediaType);
		Path path = Paths.get(DIRECTORY + "/" + DEFAULT_FILE_NAME);
		byte[] data = Files.readAllBytes(path);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
				.contentType(mediaType).contentLength(data.length).body(resource);
	}

}
