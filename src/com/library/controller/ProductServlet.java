package com.library.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.SQLException;
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

import com.library.bean.Product;
import com.library.service.i.ProductServiceI;
import com.library.service.impl.ProductServiceImpl;
import com.library.util.Contant;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ProductServlet extends HttpServlet{

	private ProductServiceI productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String mothed = req.getParameter("mothed");
			if ("addProduct".equals(mothed)) {
				addProduct(req, resp);
			}else if ("productList".equals(mothed)) {
				productList(req, resp);
			}else if ("delProduct".equals(mothed)) {
				delProduct(req, resp);
			}else if ("updateProductJump".equals(mothed)) {
				updateProductJump(req, resp);
			}else if ("updateProduct".equals(mothed)) {
				updateProduct(req, resp);
			}else if ("productDisplay".equals(mothed)) {
				productDisplay(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void productDisplay(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		
		int page = Integer.valueOf(req.getParameter("page"));
		int rows = Integer.valueOf(req.getParameter("rows"));
		if (page < 1) {
			page = 1;
		}
		if (rows < 1) {
			rows = 3;
		}
		Map<String, Object> map = productService.productList(page, rows);
		map.put("page", page);
		req.setAttribute("map", map);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}

	private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = upload(req, Contant.PATH, Contant.DIR);
		com.library.bean.Product product = new Product();
		product.setId(Integer.parseInt(map.get("id")));
		product.setBookName(map.get("book_name"));
		product.setAuthor(map.get("author"));
		product.setCount(Integer.parseInt(map.get("count")));
		product.setCover(map.get("files"));
		product.setPrice(Float.parseFloat(map.get("price")));
		if(productService.updateProduct(product)){
			Map<String, Object> mapTemp = productService.productList(1, 3);
			mapTemp.put("page", 1);
			req.setAttribute("map", mapTemp);
			req.getRequestDispatcher("view/product/productList.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "操作失败");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}

	/**
	 * 跳转到修改界面
	 * @param req
	 * @param resp
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateProductJump(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		Product product = productService.updateProductJump(req.getParameter("id"));
		req.setAttribute("product", product);
		req.getRequestDispatcher("view/product/updateProduct.jsp").forward(req, resp);
	}

	private void delProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		String id = req.getParameter("id");
		if (productService.delManager(id)) {
			Map<String, Object> map = productService.productList(1, 3);
			map.put("page", 1);
			req.setAttribute("map", map);
			req.getRequestDispatcher("view/product/productList.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "操作失败");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
		}
	}

	private void productList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
		int page = Integer.valueOf(req.getParameter("page"));
		int rows = Integer.valueOf(req.getParameter("rows"));
		if (page < 1) {
			page = 1;
		}
		if (rows < 1) {
			rows = 3;
		}
		Map<String, Object> map = productService.productList(page, rows);
		map.put("page", page);
		req.setAttribute("map", map);
		req.getRequestDispatcher("view/product/productList.jsp").forward(req, resp);
	}

	private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Map<String, String> map = upload(req, Contant.PATH, Contant.DIR);
		if (map.get("book_name") == null || map.get("book_name").trim().length() == 0) {
			req.setAttribute("msg", "参数有误");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
		if (map.get("author") == null || map.get("author").trim().length() == 0) {
			req.setAttribute("msg", "参数有误");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
		if (map.get("count") == null || map.get("count").trim().length() == 0) {
			req.setAttribute("msg", "参数有误");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
		if (map.get("price") == null || map.get("price").trim().length() == 0) {
			req.setAttribute("msg", "参数有误");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
			return;
		}
		com.library.bean.Product product = new Product();
		product.setBookName(map.get("book_name"));
		product.setAuthor(map.get("author"));
		product.setCount(Integer.parseInt(map.get("count")));
		product.setCover(map.get("files").replace(",", ""));
		product.setPrice(Float.parseFloat(map.get("price")));
		if(productService.addProduct(product)){
			Map<String, Object> mapTemp = productService.productList(1, 3);
			mapTemp.put("page", 1);
			req.setAttribute("map", mapTemp);
			req.getRequestDispatcher("view/product/productList.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "添加失败");
			req.getRequestDispatcher("view/error.jsp").forward(req, resp);
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
					if (lists.get(i).getName() != null && lists.get(i).getName().trim().length() > 0) {


						File file = new File(path, dir);
						if (!file.exists()) {
							file.mkdirs();
						}
						InputStream in = null;
						FileOutputStream out = null;
						try {
							in = lists.get(i).getInputStream();
							//构建文件名
							String fileName = String.valueOf(UUID.randomUUID()).replace("-", "")+lists.get(i).getName().substring(lists.get(i).getName().lastIndexOf("."));
							out = new FileOutputStream(new java.io.File(file, fileName));
							int index = -1;
							byte[] b = new byte[1024 * 3];
							while (( index = in.read(b)) != -1) {
								out.write(b, 0, index);
							}
							buf.append(dir+"/"+ fileName).append(",");
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
		}
		values.put("files", buf.toString());
		return values;
	}

}
