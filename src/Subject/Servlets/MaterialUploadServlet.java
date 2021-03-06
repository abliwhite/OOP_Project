package Subject.Servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import Common.AppCode.CommonConstants;
import Subject.Models.DbModels.SubjectComponentMaterial;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig(location="/tmp", fileSizeThreshold=0, 
maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)

@WebServlet("/Upload")
public class MaterialUploadServlet extends SubjectServletParent {
	private static final long serialVersionUID = 1L;

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 500 * 1024;
	private int maxMemSize = 100 * 1024;
	private File file;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaterialUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Date date= new Date();
		if (!isMultipart) {
			response.setContentType("text/html");
		//	out.println("<script type=\"text/javascript\">");
			//out.println("alert('ERROR\nNo File Uploaded');");
		//	out.println("</script>");

			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		// set upload encoding
		upload.setHeaderEncoding("UTF-8");

		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			response.setContentType("text/html");
		//	out.println("<script type=\"text/javascript\">");
		//	out.println("alert('Upload Completed');");
		//	out.println("</script>");
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					filePath = filePath + "\\";
					boolean success = (new File(filePath)).mkdirs();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					fi.write(file);			
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		String dat=CommonConstants.getDatetime();
		String path= file.getPath();
		SubjectComponentMaterial material= new SubjectComponentMaterial(path, dat, "2");
		//manager.addSubjectComponentMaterial(material);
		response.sendRedirect("/ComponentMaterialsPageGeneratorServlet?id=2");
	}

	/*
	 * private void deleteFile(String fileLocation) { try{
	 * 
	 * File file = new File(fileLocation);
	 * 
	 * if(file.delete()){ System.out.println(file.getName() + " is deleted!");
	 * }else{ System.out.println("Delete operation is failed."); }
	 * 
	 * }catch(Exception e){ e.printStackTrace();
	 * 
	 * } }
	 */

}
