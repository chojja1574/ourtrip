package com.kh.ourtrip.common;

import java.text.SimpleDateFormat;

public class FileRename {
	public static String rename(String originFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String date = sdf.format(new java.util.Date(System.currentTimeMillis()));
		
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i<6 ; i++) {
			sb.append((int)(Math.random()*10));
		}
		
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		return date + "_" + sb.toString() + ext;
	}
}
