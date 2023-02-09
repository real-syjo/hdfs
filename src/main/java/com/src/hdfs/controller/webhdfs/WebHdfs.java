package com.src.hdfs.controller.webhdfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class WebHdfs {
	
	@Autowired
	static SessionFactory factory;

	//파일 리스트 조회 
	public String  HdfsList() throws IOException {
		BufferedReader br = null;
		StringBuffer sb = null;
		String responseData = "";	
		String returnData = "";
		
		
		URL url = new URL("http://13.209.82.71:50070/webhdfs/v1/?op=LISTSTATUS");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setDoInput(true);
		
		br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));	
		sb = new StringBuffer();	       
		while ((responseData = br.readLine()) != null) {
			sb.append(responseData); 
		}
		return returnData = sb.toString();
	}

}
