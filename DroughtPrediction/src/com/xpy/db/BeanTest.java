package com.xpy.db;
import com.xpy.upload.FileUpLoadServlet;
import java.sql.Connection; 

 


import com.xpy.matlab.CallMatlab;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/addressSerlvet")
public class BeanTest  extends HttpServlet{  
    /**
	 * 
	 */
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
	private static final long serialVersionUID = 1L;
	private CallMatlab mw;
	/** 
     * 取得一个数据库连接 
     *  
     * @return 
     * @throws SQLException 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     * @throws ClassNotFoundException 
     */  

    public Connection getConnection() throws SQLException,  
            InstantiationException, IllegalAccessException,  
            ClassNotFoundException {  
        Connection conn = null;  
        // 加载数据库驱动类  
        /** 
         * 这是SqlServer的情形； 
         * Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver" 
         * ).newInstance(); 
         */  
        Class.forName("com.mysql.jdbc.Driver");  
        // 数据库连接URL  
        /** 
         * 这是SqlServer的情形； 
         *  
         * String url = 
         * "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=Northwind"; 
         */  
        String url = "jdbc:mysql://localhost:3306/projectrain?characterEncoding=utf8";  
        // 数据库用户名  
        String user = "root";  
        // 数据库密码  
        String password = "reflux123";  
        // 根据数据库参数取得一个数据库连接  
        conn = DriverManager.getConnection(url, user, password);  
        return conn;  
    }  

    /** 
     * 根据传入的SQL语句返回一个结果集 
     *  
     * @param sql 
     * @return 
     * @throws Exception 
     */  
    public ResultSet select(String sql) throws Exception {  
        Connection conn = null;  
        Statement stmt = null;  
        ResultSet rs = null;  
        System.out.println("Select Excute="+sql);
        try {  
            conn = getConnection();  
            stmt = conn.createStatement();  
            rs = stmt.executeQuery(sql);  
            return rs;  
        } catch (SQLException sqle) {  
            throw new SQLException("select data exception: "  
                    + sqle.getMessage());  
        } catch (Exception e) {  
            throw new Exception("System e exception: " + e.getMessage());  
        }  

    }  

    /** 
     * 根据传入的SQL语句向数据库增加一条记录 
     *  
     * @param sql 
     * @throws Exception 
     */  
    
    public void insert_to_excel(int []A) throws UnsupportedEncodingException{
    	for(int i=0;i<30;i++)
    		System.out.println(A[i]);
        List<Map> list=new ArrayList<Map>();     
        for(int i=0;i<15;i++)
        {
            Map<String, String> dataMap=new HashMap<String, String>();
        	dataMap.put("v1", String.valueOf(A[i*2]));
        	dataMap.put("v2", String.valueOf(A[i*2+1]));
        	list.add(dataMap);
        }
   
        
        writeExcel(list, 2, "D:/EclP/info_tmp.xlsx");
        mw=new CallMatlab();
        try {
			mw.excute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			Sheet_Auto_calculate("D:/EclP/info_tmp.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void insert(String sql) throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        try {  
            conn = getConnection();  
            ps = conn.prepareStatement(sql);  
            ps.executeUpdate();  
        } catch (SQLException sqle) {  
            throw new Exception("insert data exception: " + sqle.getMessage());  
        } finally {  
            try {  
                if (ps != null) {  
                    ps.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("ps close exception: " + e.getMessage());  
            }  
        }  
        try {  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (Exception e) {  
            throw new Exception("connection close exception: " + e.getMessage());  
        }  
    }  

    /** 
     * 根据传入的SQL语句更新数据库记录 
     *  
     * @param sql 
     * @throws Exception 
     */  
    
    
    public void update(String sql) throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        try {  
            conn = getConnection();  
            ps = conn.prepareStatement(sql);  
            ps.executeUpdate();  
        } catch (SQLException sqle) {  
            throw new Exception("update exception: " + sqle.getMessage());  
        } finally {  
            try {  
                if (ps != null) {  
                    ps.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("ps close exception: " + e.getMessage());  
            }  
        }  
        try {  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (Exception e) {  
            throw new Exception("connection close exception: " + e.getMessage());  
        }  
    }  
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method=request.getParameter("method");
		if("year".equals(method)){
			try {
				getYear(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("month".equals(method)){
			try {
				getMonth(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		if("month".equals(method)){
//			getMonth(request, response);
//		}
	}
    /** 
     * 根据传入的SQL语句删除一条数据库记录 
     *  
     * @param sql 
     * @throws Exception 
     */  
	
	public void getYear(HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		String station = request.getParameter("station");
		String sql="select distinct 年 from rain where 站点="+station;
		System.out.println(sql);
		ResultSet rs=select(sql);
		ArrayList<String> yearList =new ArrayList<String>();
		while(rs.next()){
			yearList.add(rs.getString("年"));
		}
	
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(yearList);
		jsonObj.put("yearList", jsonArray);
		String jsonDataStr = jsonObj.toString();
		
		response.getWriter().print(jsonDataStr);
	}

	public void getMonth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

			String station = request.getParameter("station");
			String year=request.getParameter("year");
			String sql="select distinct 月 from rain where 站点="+station+ " and 年="+year;
			System.out.println(sql);
			ResultSet rs=select(sql);
			ArrayList<String>monthList =new ArrayList<String>();
			while(rs.next()){
				monthList.add(rs.getString("月"));
			}
		
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(monthList);
			jsonObj.put("monthList", jsonArray);
			String jsonDataStr = jsonObj.toString();
			
			response.getWriter().print(jsonDataStr);
		}
	
	
	
    public void delete(String sql) throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        try {  
            conn = getConnection();  
            ps = conn.prepareStatement(sql);  
            ps.executeUpdate();  
        } catch (SQLException sqle) {  
            throw new Exception("delete data exception: " + sqle.getMessage());  
        } finally {  
            try {  
                if (ps != null) {  
                    ps.close();  
                }  
            } catch (Exception e) {  
                throw new Exception("ps close exception: " + e.getMessage());  
            }  
        }  
        try {  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (Exception e) {  
            throw new Exception("connection close exception: " + e.getMessage());  
        }  
    }
    
    
    @SuppressWarnings("deprecation")
	public void read_insert(String sql) throws Exception{
        FileUpLoadServlet tmp=new FileUpLoadServlet();
        List<String[]> s =new ArrayList<String[]>();
        s=tmp.readExcelswg2("D:/EclP/info_tmp.xlsx");
       
        for(int i = 0 ; i < 5 ; i++) {
        		sql=sql+","+s.get(0)[i];
        	}
        
        double t=Double.parseDouble(s.get(0)[0])*0.083+Double.parseDouble(s.get(0)[1])*0.25+Double.parseDouble(s.get(0)[2])*0.5+Double.parseDouble(s.get(0)[3])*0.75+Double.parseDouble(s.get(0)[4])*0.917;
        System.out.println("==============");
        System.out.println(t);
        
        
        sql=sql+","+t+")";
        insert(sql);
      }
    
    public static void Sheet_Auto_calculate(String path) throws IOException
    {
        File finalXlsxFile = new File(path);
        Workbook workBook = getWorkbok(finalXlsxFile);
        // sheet 对应一个工作页
        for(int i=0;i<10;i++){
        Sheet sheet = workBook.getSheetAt(i);
        sheet.setForceFormulaRecalculation(true);}
    }
    public static void writeExcel(List<Map> dataList, int cloumnCount,String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 2; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            System.out.println("size="+dataList.size());
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第3行开始，跳过属性列
                Row row = sheet.createRow(j + 2);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                String s1 = dataMap.get("v1").toString();
                String s2 = dataMap.get("v2").toString();
  
                for (int k = 0; k <= columnNumCount; k++) {
                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(Integer.parseInt(s1));
                Cell second = row.createCell(1);
                second.setCellValue(Integer.parseInt(s2));                

                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
			Sheet_Auto_calculate(finalXlsxPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */

    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
    
