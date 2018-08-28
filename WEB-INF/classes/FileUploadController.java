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
		// ��̬��תҳ��
		return formName;
	}
	
	//�ϴ��ļ����Զ��󶨵�MultipartFile��
	 @RequestMapping(value="/upload",method=RequestMethod.POST)
	 public String upload(HttpServletRequest request,
			@RequestParam("file") MultipartFile file) throws Exception {
		
		String s = request.getParameter("uid");
		String ss = request.getParameter("uid1");
	    System.out.println("����:"+s+" "+ss);
	    //����ļ���Ϊ�գ�д���ϴ�·��
		if(!file.isEmpty()) {
			//�ϴ��ļ�·��
			String path = request.getServletContext().getRealPath("/loaded/");
			System.out.println("·��:"+path);
			//�ϴ��ļ���
			String filename = file.getOriginalFilename();
			System.out.println("�ļ�����:"+filename);
		    File filepath = new File(path,filename);
			//�ж�·���Ƿ���ڣ���������ھʹ���һ��
	        if (!filepath.getParentFile().exists()) { 
	        	filepath.getParentFile().mkdirs();
	        }
	        //���ϴ��ļ����浽һ��Ŀ���ļ�����
			file.transferTo(new File(path + File.separator + filename));
			
			System.out.println("����");
			return "success";
		}
		return null;
	 }
	 
	 @RequestMapping(value="/download")
	 public ResponseEntity<byte[]> download(HttpServletRequest request,
			 @RequestParam("filename") String filename,
			 Model model)throws Exception {
		//�����ļ�·��
		String path = request.getServletContext().getRealPath("/images/");
		File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();  
        //������ʾ���ļ������������������������  
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //֪ͨ�������attachment�����ط�ʽ����ͼƬ
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        //application/octet-stream �� �����������ݣ�������ļ����أ���
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                headers, HttpStatus.CREATED);  
	 }

}