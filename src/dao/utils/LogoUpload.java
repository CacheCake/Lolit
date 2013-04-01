package dao.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class LogoUpload {

	public boolean update(HttpServletRequest request, ServletContext context,
			String filepath) throws Exception {

		// Ϊ�ļ����������������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 4); // ���û������Ĵ�С���˴�Ϊ4kb
		factory.setRepository(new File(filepath)); // �����ϴ��ļ���Ŀ�ĵ�

		// ����servlet�ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(4 * 1024 * 1024); // �����ϴ��ļ��Ĵ�С���˴�Ϊ4M

		String fileName = null;

		try {

			Random rd = new Random();
			int tId = rd.nextInt(9999999);
			System.out.println("tid:" + tId);
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request); // ȡ�����е��ϴ��ļ���Ϣ
			Iterator<FileItem> it = list.iterator();

			while (it.hasNext()) {

				FileItem item = it.next();
				if (item.isFormField() == true) { // ����һ����ͨ�ı�����
					String fieldName = item.getFieldName();
					fileName = item.getName(); // �ļ���
					// String contentType = item.getContentType(); // �ļ�����
					System.out.println(fieldName);
					// boolean isInMemory = item.isInMemory(); // �Ƿ��Ǳ������ڴ���
					// long sizeInBytes = item.getSize();

					if (!"".equals(fileName) && !(fileName == null)) {// ���fileNameΪnull����û���ϴ��ļ�
						System.out.println(item);
						// ȡ�ļ���
						fileName = fileName.substring(
								fileName.lastIndexOf("\\") + 1,
								fileName.length());
						File uploadedFile = new File(filepath, fileName);
						try {
							item.write(uploadedFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						return false;
					}

					return true;

				} else { // ȡ����ͨ�Ķ���[�������ı����������͵�ʹ��]}
					File file = new File(filepath);
					file.mkdir();

					System.out.println(item);

					String fileName1 = tId + ".jpg";
					System.out.println(fileName1);
					String fileName2 = fileName1.substring(fileName1
							.lastIndexOf("\\") + 1);
					System.out.println(fileName2);
					file = new File(filepath + fileName2);

					item.write(file);

					return true;
				}

			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// ��ȡ�ļ�����
		/*
		 * try { FileReader fr = new FileReader(request.getContextPath() +
		 * "/Lolit/images/" + fileName); BufferedReader reader = new
		 * BufferedReader(fr); String line = "";
		 * 
		 * // �������ݿ�
		 * 
		 * String strDriverName = "com.mysql.jdbc.Driver"; String strUrl =
		 * "jdbc:mysql://localhost:3306/costdb"; String strUser = "root"; String
		 * strPwd = "laopoaini";
		 * 
		 * try { Class.forName(strDriverName); } catch (ClassNotFoundException
		 * e) { e.printStackTrace(); }
		 * 
		 * Connection connection = null; Statement st = null; ResultSet rs =
		 * null;
		 * 
		 * try { connection = DriverManager.getConnection(strUrl, strUser,
		 * strPwd); st = connection.createStatement();
		 * 
		 * while ((line = reader.readLine()) != null) {
		 * System.out.println(line); System.out.println(cardType); int inta = st
		 * .executeUpdate(
		 * "insert into phonesource (phoneCardId,phoneCardType) values (" + line
		 * + ",'" + cardType + "')");
		 * 
		 * if (inta > 0) { System.out.println("up��ӳɹ�phone��"); } }
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		return false;
	}
}
