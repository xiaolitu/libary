package com.library.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class Upload {

	/**
	 * �ϴ��ļ�����
	 * @param req
	 * @param path  ��·��
	 * @param dir   Ŀ¼
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> upload(HttpServletRequest req, String path, String dir) throws Exception{
		//Ϊ�������ṩ������Ϣ 
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		//�����������ʵ�� 
		ServletFileUpload sfu = new ServletFileUpload(factory); 
		//��ʼ���� 
		sfu.setFileSizeMax(1024*400); 
		//��������������
		Map<String, String> values = new HashMap<>();
		StringBuffer buf = new StringBuffer();
		
		Map<String, List<FileItem>> items;
		items = sfu.parseParameterMap(req);
		for (Map.Entry<String, List<FileItem>> item: items.entrySet()) {
			List<FileItem> lists = item.getValue();
			for (int i = 0; i < lists.size(); i++) {
				//Ϊ���ʾ�����ļ����� ������input type="text"
				if (lists.get(i).isFormField()) {
					values.put(lists.get(i).getFieldName(), lists.get(i).getString());	
					//���ļ����ͣ����ļ�д�뵽������
				}else{
					File file = new File(path, dir);
					if (!file.exists()) {
						file.mkdirs();
					}
					InputStream in = null;
					FileOutputStream out = null;
					try {
						in = lists.get(i).getInputStream();
						String fileName = String.valueOf(UUID.randomUUID()).replace("-", "")+lists.get(i).getName().substring(lists.get(i).getName().lastIndexOf("."));
						//�����ļ���
						out = new FileOutputStream(new java.io.File(file, fileName));
						int index = -1;
						byte[] b = new byte[1024 * 3];
						while (( index = in.read(b)) != -1) {
							out.write(b, 0, index);
						}
						buf.append(dir+"/"+fileName).append(",");
					} catch (Exception e) {
						throw e;
					} finally {
						if (in != null) {
							in.close();
						}
						if (out != null) {
							out.close();
						}
					}
				}
			}
		}
		values.put("files", buf.toString());
		return values;
	}
}
