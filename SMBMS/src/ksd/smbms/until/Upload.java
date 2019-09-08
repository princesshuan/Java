package ksd.smbms.until;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class Upload {

	public static String fileUpload(MultipartFile pic,HttpServletRequest request) throws Exception{
	String proLicence = null;
	String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
	if (!pic.isEmpty()) {
		String FileName = pic.getOriginalFilename();
		String prefix = FilenameUtils.getExtension(FileName);
		if (prefix.equalsIgnoreCase("jpg")
				|| prefix.equalsIgnoreCase("png")
				|| prefix.equalsIgnoreCase("jpeg")
				|| prefix.equalsIgnoreCase("pneg")) {
			File targetFile = new File(path, FileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// ±£´æ
			try {
				pic.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			proLicence = "statics" + File.separator + "uploadfiles"
					+ File.separator + FileName;

		}
	}
	return proLicence;

}
}
