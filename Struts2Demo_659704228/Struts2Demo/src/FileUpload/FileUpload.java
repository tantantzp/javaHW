package FileUpload;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUpload extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private File file;
	private String fileContentType;
	private String fileFileName;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String execute() throws Exception {

		//store files on server(Your Tomcat Dir)

		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/files");

		File toFile = new File(new File(uploadPath), this.getFileFileName());

		if (!toFile.getParentFile().exists())
			toFile.getParentFile().mkdirs();
		FileUtils.copyFile(file, toFile);
		
		System.out.println("Your Files here:"+uploadPath);
		System.out.println(this.fileFileName + " " + this.fileContentType);
		

		return "success";
	}
}
