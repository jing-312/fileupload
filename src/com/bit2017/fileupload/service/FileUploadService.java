package com.bit2017.fileupload.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	private static final String URL = "gallery/";
	private static final String SAVE_PATH = "/upload";
	
	public String restore( MultipartFile file ) {
		String url =  "";
		
		try {
			if( file.isEmpty() == true ) {
				return url;
			}
			
			String originalFileName = file.getOriginalFilename();
			Long fileSize = file.getSize();
			String extName = originalFileName.substring(
					originalFileName.lastIndexOf( ".") + 1,
					originalFileName.length() );
			String saveFileName = generateSaveFileName( extName );
			url = URL + saveFileName;
			
			System.out.println( "#########" + originalFileName );
			System.out.println( "#########" + fileSize );
			System.out.println( "#########" + extName );
			System.out.println( "#########" + saveFileName );
		
			writeFile( file, saveFileName );
		} catch( IOException e ) {
			new RuntimeException( "upload file:" + e );
		}
		
		return url;
	}
	
	private void writeFile( MultipartFile file, String saveFileName ) 
		throws IOException {
		
		byte[] data = file.getBytes();
		FileOutputStream fos = new FileOutputStream( 
				SAVE_PATH + "/" + saveFileName );
		fos.write( data );
		fos.close();
	}
	
	private String generateSaveFileName( String extName ) {
		String fileName = "";
	
		Calendar calendar = Calendar.getInstance(); 
		fileName += calendar.get( Calendar.YEAR );
		fileName += calendar.get( Calendar.MONTH );
		fileName += calendar.get( Calendar.DATE );
		fileName += calendar.get( Calendar.HOUR );
		fileName += calendar.get( Calendar.MINUTE );
		fileName += calendar.get( Calendar.SECOND );
		fileName += calendar.get( Calendar.MILLISECOND );
		fileName += ("." + extName );
		
		return fileName;
	}
}
