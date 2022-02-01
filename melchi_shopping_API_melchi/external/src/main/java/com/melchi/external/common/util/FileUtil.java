package com.melchi.external.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class FileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 파일에 입력한 데이터를 한줄로 작성한다.
	 * @param file
	 * @param writeString
	 * @param appendFlag : 붙여 쓰기
	 * @return
	 * @throws Exception
	 */
	public static boolean writeFileLine(File file, String writeString, boolean appendFlag) throws Exception{
		//null 객체는 처리하지 않는다.
		if(file == null){
			return false;
		}
		//파일이 없을 경우 생성한다.
		if(!file.exists()){
			//상위까지 디렉토리를 만든다
			file.getParentFile().mkdirs();
			if(!file.createNewFile()){
				//생성하지 못했을 경우
				LOGGER.debug("File create error : {}", file.getName());
				return false;
			}
		}
		
		if(file.canWrite()){
			if(appendFlag){
				FileWriter fw = null;
				BufferedWriter bw = null;
				try{
					//읽을 수 있을 경우만 
					fw = new FileWriter(file, appendFlag);
					bw = new BufferedWriter(fw);
					bw.append(writeString);
					bw.newLine();
					bw.flush();
					
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(bw != null){
						bw.close();
					}
					if(fw != null){
						fw.close();
					}
				}
			}else{
				FileCopyUtils.copy(writeString.getBytes("UTF-8"), file);
			}
		}else{
			return false;
		}
		
		return true;
	}
}
