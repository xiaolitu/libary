package com.library.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class ProductServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Map<String, String> map = upload(req, "d:\\", "img");
			System.out.println(map);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件工具
	 * @param req
	 * @param path  根路径
	 * @param dir   目录
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> upload(HttpServletRequest req, String path, String dir) throws Exception{
		//为解析类提供配置信息 
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		//创建解析类的实例 
		ServletFileUpload sfu = new ServletFileUpload(factory); 
		//开始解析 
		sfu.setFileSizeMax(1024*400); 
		//存解析表单后的数据
		Map<String, String> values = new HashMap<>();
		StringBuffer buf = new StringBuffer();
		
		Map<String, List<FileItem>> items;
		items = sfu.parseParameterMap(req);
		for (Map.Entry<String, List<FileItem>> item: items.entrySet()) {
			List<FileItem> lists = item.getValue();
			for (int i = 0; i < lists.size(); i++) {
				//为真表示不是文件类型 可能是input type="text"
				if (lists.get(i).isFormField()) {
					values.put(lists.get(i).getFieldName(), lists.get(i).getString());	
					//是文件类型，把文件写入到服务器
				}else{
					File file = new File(path, dir);
					if (!file.exists()) {
						file.mkdirs();
					}
					InputStream in = null;
					FileOutputStream out = null;
					try {
						in = lists.get(i).getInputStream();
						//构建文件名
						out = new FileOutputStream(new java.io.File(file, String.valueOf(UUID.randomUUID()).replace("-", "")+lists.get(i).getName().substring(lists.get(i).getName().lastIndexOf("."))));
						int index = -1;
						byte[] b = new byte[1024 * 3];
						while (( index = in.read(b)) != -1) {
							out.write(b, 0, index);
						}
						buf.append(dir+"/"+String.valueOf(UUID.randomUUID()).replace("-", "")+lists.get(i).getName().substring(lists.get(i).getName().lastIndexOf("."))).append(",");
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
