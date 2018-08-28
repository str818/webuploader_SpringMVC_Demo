package cn.edu.jseti.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 * @author Cody
 * @version V1.0 
 */
@Controller
public class FileUploadController {
	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName) {
		// 动态跳转页面
		return formName;
	}
	
	//上传文件会自动绑定到MultipartFile中
	 @RequestMapping(value="/upload",method=RequestMethod.POST)
	 public String upload(HttpServletRequest request,
			@RequestParam("file") MultipartFile file) throws Exception {
		
		String s = request.getParameter("uid");
		String ss = request.getParameter("uid1");
	    System.out.println("进入:"+s+" "+ss);
	    //如果文件不为空，写入上传路径
		if(!file.isEmpty()) {
			//上传文件路径
			String path = request.getServletContext().getRealPath("/loaded/");
			System.out.println("路径:"+path);
			//上传文件名
			String filename = file.getOriginalFilename();
			System.out.println("文件名称:"+filename);
		    File filepath = new File(path,filename);
			//判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	        	filepath.getParentFile().mkdirs();
	        }
	        //将上传文件保存到一个目标文件当中
			file.transferTo(new File(path + File.separator + filename));
			
			System.out.println("结束");
			return "success";
		}
		return null;
	 }
	 
	 @RequestMapping(value="/download")
	 public ResponseEntity<byte[]> download(HttpServletRequest request,
			 @RequestParam("filename") String filename,
			 Model model)throws Exception {
		//下载文件路径
		String path = request.getServletContext().getRealPath("/images/");
		File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();  
        //下载显示的文件名，解决中文名称乱码问题  
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                headers, HttpStatus.CREATED);  
	 }

}