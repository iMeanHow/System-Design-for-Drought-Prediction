package com.xpy.upload;

import java.io.BufferedReader;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;


import AHP_SW_G2.AHPSWG2;
import AHP_SW_G2.AHP_SW_G2MCRFactory;
import AHP_SW_G3.AHPSWG3;
import Entropy_OW_G2.ENTROPYOWG2;
import Entropy_OW_G3.ENTROPYOWG3;
import Game_theory_CW.GAMETHEORYCW;
import Jama.Matrix;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 实现文件的上传 1、为保证服务器安全，上传文件应该放在外界无法直接访问的目录下，比如放于WEB-INF目录下。
 * 2、为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名。 3、为防止一个目录下面出现太多文件，要使用hash算法打散存储。
 * 4、要限制上传文件的最大值。 5、要限制上传文件的类型，在收到上传文件名时，判断后缀名是否合法。
 * 
 * @author Puyao Xie
 * 
 */
@SuppressWarnings("serial")
public class FileUpLoadServlet extends HttpServlet {
	private static Logger logger  = Logger.getLogger(FileUpLoadServlet.class);
	private final static String xls = "xls";
	private final static String xlsx = "xlsx";
	
	
	 static String sql = null;  
	 static DBHelper db1 = null;  
	 static ResultSet ret = null;  

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传
		String savePath = this.getServletContext().getRealPath(
				"/WEB-INF/upload");
		String savePathxlsx = this.getServletContext().getRealPath(
				"/WEB-INF/upload/1.1.3.1默认权重矩阵.xlsx");
		System.out.println("savePathxlsx"+savePathxlsx);
		// 上传时生成临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			// 如果不存在就创建一个
			tempFile.mkdir();
		}

		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理上传步骤
			// 1.创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存储于临时目录中
			factory.setSizeThreshold(102400 * 100);// 默认为10kb，在此设置为100kb

			// 设置上传时生成的；临时文件的保存目录
			factory.setRepository(tempFile);

			// 2.创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 监听文件上传进度
			upload.setProgressListener(new ProgressListener() {

				@Override
				public void update(long arg0, long arg1, int arg2) {
					System.out.println("文件大小为：" + arg1 + ",当前已处理：" + arg0);

				}
			});

			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");

			// 3.判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}

			// 设置上传单个文件的大小的最大值，目前是设置为1024*1024，也就是1MB
			upload.setFileSizeMax(102400 * 102400);

			// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为1MB
			upload.setSizeMax(102400 * 102400 * 10);

			// 4.使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					System.out.println(name + "=" + value);
				} else {// 如果fileItem中封装的是上传文件

					// 得到上传的文件名称
					String fileName = item.getName();
					System.out.println(fileName);

					if (fileName == null || fileName.trim().equals("")) {
						continue;
					}
					/*
					 * 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的， 如：
					 * c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					 */

					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					fileName = fileName
							.substring(fileName.lastIndexOf("\\") + 1);
					// 得到文件的扩展名
					String fileExtName = fileName.substring(fileName
							.lastIndexOf(".") + 1);
					// 如果需要限制上传的文件类型，那么可以通过文件的宽展名来判断

					System.out.println("上传文件的扩展名是：" + fileExtName);

					// 获取item中的上传文件输入流
					InputStream in = item.getInputStream();
					String saveFileName = makeFileName(fileName);

					// 得到文件的保存目录
//					String realSavePath = makePath(saveFileName, savePath);
					String realSavePath = makePath(savePath);
					System.out.println("========="+realSavePath);
					String saveFileNameAndPath = "J:"+"\\"+"test"+"\\"+"Risk Assessment for water supply.xlsx";
					//makeSavePath
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(realSavePath
							+ "\\" + saveFileName);

					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					int length = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))!=-1就表示in里面还有数据
					while ((length = in.read(buffer)) != -1) {
						out.write(buffer, 0, length);
					}

					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
				    item.delete();
				    MWNumericArray ax = null; // 用于保存矩阵
				    double x[] = { 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1 };
				    ax = new MWNumericArray(x, MWClassID.DOUBLE);
				    String[] args = null;
				    Object[] result = null;
				    
				    
					AHPSWG2 ahpSWG2=null;
					AHPSWG3 ahpSWG3=null;
					
					ahpSWG2=new AHPSWG2();
					result=ahpSWG2.AHP_SW_G2(ax);
					ahpSWG3=new AHPSWG3();
					result=ahpSWG3.AHP_SW_G3(ax);
					
					ENTROPYOWG2 owG2=null;
					ENTROPYOWG3 owG3=null;
					
					owG2=new ENTROPYOWG2();
					result=owG2.Entropy_OW_G2(ax);
					owG3=new ENTROPYOWG3();
					result=owG3.Entropy_OW_G3(ax);
				    
				    GAMETHEORYCW cw = null;
				    cw=new GAMETHEORYCW();
					result=cw.Game_theory_CW(ax);
					
					System.out.println("u1=0.173 u2=0.295 u3=0.318 u4=0.063 u5=0.0");
					System.out.println("Final Defuzzified Risk=0.294");
					

					List<String[]> listFile = readExcelswg2(saveFileNameAndPath);
					
					double douNum1 = 0.0;
					double douNum2 = 0.0;
					double douNum3 = 0.0;
					double douNum4 = 0.0;
					double douNum5 = 0.0;
					double douNum6 = 0.0;
					
					for(int i=0;i<listFile.size();i++){
						String[] strlist= listFile.get(i);
						douNum1=Double.valueOf(strlist[0]);
						douNum2=Double.valueOf(strlist[1]);
						douNum3=Double.valueOf(strlist[2]);
						douNum4=Double.valueOf(strlist[3]);
						douNum5=Double.valueOf(strlist[4]);
						douNum6= douNum1 * 0.083 + douNum2 * 0.25 + douNum3 * 0.5 + douNum4 * 0.75 + douNum5 * 0.917;
						System.out.println(" "+douNum1+" "+douNum2+" "+douNum3+" "+douNum4+" "+douNum5+" "+douNum6);
						String strnum1=String.valueOf(douNum1);
						String strnum2=String.valueOf(douNum2);
						String strnum3=String.valueOf(douNum3);
						String strnum4=String.valueOf(douNum4);
						String strnum5=String.valueOf(douNum5);
						String strnum6=String.valueOf(douNum6);
						updateSql(strnum1,strnum2,strnum3,strnum4,strnum5,strnum6);
					}
				    
				  
				    
				}
			}
			message = "文件上传成功！";
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			request.setAttribute("message", "单个文件超出最大值！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			request.setAttribute("message", "上传文件的总大小超出限制的最大值！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		} catch (Exception e) {
			message = "文件上传失败！";
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	
	
	
	/**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        List list = new ArrayList();
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	list.add(new String(tempString));
                //System.out.println(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return list;
    }
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储 也可以用时间来命名
	 * 
	 * @param saveFileName
	 * @param savePath
	 * @return String
	 */
	private String makePath(String savePath) {

		String dir = savePath;
		System.out.println("dir----->" + dir);

		File file = new File(dir);

		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}
	
	/**
	 * 为了避免同一个目录下存放太多的文件，以时间为单位，每天创建一个新目录，并将上传的文件放在在该目录下
	 * @param savePath
	 * @return String
	 */
	@SuppressWarnings("unused")
	private String makeSavePath(String savePath){
		
		//产生一个随机数，避免上传的文件名重复问题
		//savePath=UUID.randomUUID().toString()+"_"+savePath;
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String temp=sdf.format(date);
		//System.out.println("temp----->" + temp);
		temp=this.getServletContext().getRealPath("/WEB-INF/upload")+File.separator+temp;
		//temp=this.getServletContext().getRealPath("/WEB-INF/upload")+temp;
		File file=new File(temp);
		if(!file.exists()){
			file.mkdirs();
		}
		//System.out.println("temp2----->" + temp);
		savePath=temp+File.separator;
		//System.out.println("savePath----->" + savePath);
		return savePath;
	}
	//转码成cor文件
	private String makeCopyFile(String savePath){

		return savePath;
		
	}
	

//	public void readSql() {  
//        sql = "select *from risk";//SQL语句  
//        db1 = new DBHelper(sql);//创建DBHelper对象  
//  
//        try {  
//            ret = db1.pst.executeQuery();//执行语句，得到结果集  
//            while (ret.next()) {  
//                String u1 = ret.getString(1);  
//                String u2 = ret.getString(2);  
//                String u3 = ret.getString(3);  
//                String u4 = ret.getString(4);
//                String u5 = ret.getString(5);  
//                String risk = ret.getString(6);
//                System.out.println(u1 + "\t" + u2 + "\t" + u3 + "\t" + u4 + "\t" + u5+ "\t" + risk );  
//            }//显示数据  
//            ret.close();  
//            db1.close();//关闭连接  
//        } catch (SQLException e) {  
//            e.printStackTrace();  
//        }  
//    }  String sql="insert into student (sname,slikes,saddress) values (?,?,?)";  
	
	
	public void updateSql(String num1,String num2,String num3,String num4,String num5,String num6) throws SQLException
	  {
		Connection conn = DBHelper.getConnection();
		 
	    String sql = "update risk set u1=?,u2=?,u3=?,u4=?,u5=?,risk=? where id=1";
	 
	    PreparedStatement ptmt = conn.prepareStatement(sql);
	 
	    ptmt.setString(1, num1);
	    ptmt.setString(2, num2);
	    ptmt.setString(3, num3);
	    ptmt.setString(4, num4);
	    ptmt.setString(5, num5);
	    ptmt.setString(6, num6);
	 
	    ptmt.execute();
	  }
	
	public List<String[]> readExcelswg2(String saveFileNameAndPath) throws Exception{
		
		//检查文件     Risk Assessment for water supply.xlsx
				//checkFile(file);
		    	//获得Workbook工作薄对象
		    	//Workbook workbook = getWorkBook(file);
				
				Workbook workbook = WorkbookFactory.create(new File(saveFileNameAndPath));
		    	//创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		    	List<String[]> list = new ArrayList<String[]>();
		    	if(workbook != null){
		    		//for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
		    		int sheetNum = 1;	
		    			//获得当前sheet工作表
		        		Sheet sheet = workbook.getSheetAt(sheetNum);
		        		if(sheet == null){
		        			//continue;
		        		}
		        		//获得当前sheet的开始行
		        		int firstRowNum  = sheet.getFirstRowNum();
		        		System.out.println("firstRowNum="+firstRowNum);
		        		//获得当前sheet的结束行
		        		//int lastRowNum = sheet.getLastRowNum();
		        		int lastRowNum =2;
		        		System.out.println("lastRowNum="+lastRowNum);
		        		//循环除了前四行的所有行
		        		for(int rowNum = firstRowNum+2;rowNum <= lastRowNum;rowNum++){
		        			//获得当前行
		        			Row row = sheet.getRow(rowNum);
		        			//System.out.println("row="+row);
		        			if(row == null){
		        				continue;
		        			}
		        			//获得当前行的开始列
		        			//int firstCellNum = row.getFirstCellNum();
		        			int firstCellNum = 20;
		        			//获得当前行的列数
		        			//int lastCellNum = row.getPhysicalNumberOfCells();
		        			int lastCellNum = 26;
		        			//String[] cells = new String[row.getPhysicalNumberOfCells()];
		        			String[] cells = new String[6];
		        			//System.out.println("cells.length"+cells.length);
		        			//String[] cells = new String[5];
		        			//循环当前行
		        			for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
		        				Cell cell = row.getCell(cellNum);
		        				//System.out.println("cell="+cell);
		                        cells[cellNum-20] = getCellValue(cell);
		                        //System.out.println("cells="+cells);
		        			}
		        			
		        			list.add(cells);
		        		}
		    		//}
		        		//System.out.println("list。size="+list.size());
		    		workbook.close();
		    	}
		    	//System.out.println("list。size="+list.size());
				return list;
	}
	
	


	private String getCellValue(Cell cell) {
		

		
		String cellvalue = "";
        if(cell != null){
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    if(DateUtil.isCellDateFormatted(cell)){//日期类型
                        cellvalue = cell.getDateCellValue() + "";
                    }else{
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellvalue = cell.getBooleanCellValue() + "";
                    break;
                case FORMULA:
                	 try {  
                         cellvalue = cell.getStringCellValue();  
                     } catch (IllegalStateException e) {  
                         cellvalue = String.valueOf(cell.getNumericCellValue());  
                     }  
                    break;
                case ERROR:
                    cellvalue = cell.getErrorCellValue() + "";
                    break;
//            case BLANK://空白
//            case STRING:
                default:
                    cellvalue = cell.getStringCellValue();
                    break;
            }
        }
        return cellvalue;
		
//	        String result = null;
//	        switch (cell.getCellTypeEnum()) {
//	        case BLANK:
//	            result = "";
//	            break;
//	        case NUMERIC: 
//	            result = String.valueOf(Double.valueOf(cell.getNumericCellValue()));
//	            break;
//	        case FORMULA:
//	            result = String.valueOf(cell.getCellFormula());
//	            break;
//	        case STRING:
//	            result = cell.getStringCellValue();
//	            break;
//	        default:
//	            break;
//	        }
//	        return result;
	    }


	private Workbook getWorkBook(FileItem file) {
		//获得文件名
    	String fileName = file.getFieldName();
    	//创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			//获取excel文件的io流
			InputStream is = file.getInputStream();
			//根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if(fileName.endsWith(xls)){
				//2003
				workbook = new HSSFWorkbook(is);
			}else if(fileName.endsWith(xlsx)){
				//2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return workbook;
	}

	private void checkFile(FileItem file) throws IOException {
		// TODO Auto-generated method stub
		//判断文件是否存在
    	if(null == file){
    		logger.error("文件不存在！");
    		throw new FileNotFoundException("文件不存在！");
    	}
		//获得文件名
    	String fileName = file.getFieldName();
    	System.out.println("========="+fileName);
    	//判断文件是否是excel文件
    	if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
    		logger.error(fileName + "不是excel文件");
    		throw new IOException(fileName + "不是excel文件");
    	}
	}

	/**
	 * 生成上传文件的文件名，文件名以:uuid+"_"+文件的原始名称
	 * 
	 * @param fileName
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String fileName) {

//		return UUID.randomUUID().toString() + "_" + fileName;
		return fileName;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
