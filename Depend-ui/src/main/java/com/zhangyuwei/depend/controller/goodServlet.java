package com.zhangyuwei.depend.controller;

import com.zhangyuwei.depend.entities.Good;
import com.zhangyuwei.depend.entities.UpLoad;
import com.zhangyuwei.depend.mapper.IGoodDao;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;


@Controller
@RequestMapping("/GoodServlet")
public class goodServlet {
    /*IGoodDao ia=new goodDao();*/
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IGoodDao dao=ctx.getBean(IGoodDao.class);
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("message","我是一个测试类");
        return "index";
    }
    /*查询所有商品*/
    @RequestMapping("/selectAllGood")
    @ResponseBody
    public List<Good> selectAllGood(){
        List<Good> entity=dao.selectAllGood();
        return entity;
    }
    /*添加商品*/
    @RequestMapping(value = "/insertGood",method = RequestMethod.POST)
    @ResponseBody
    public void insertGood(@RequestBody List<Good> goodList, HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        Good good=new Good();
        good.setGname(goodList.get(0).getGname());
        good.setGprice(goodList.get(0).getGprice());
        good.setGpicture(goodList.get(0).getGpicture());
        int row=dao.insertGood(good);
        if(row>0){
            response.getWriter().print("添加成功!");
        }else{
            response.getWriter().print("添加失败!");
        }
    }
    /*修改商品*/
    @RequestMapping(value = "/updateGood",method = RequestMethod.POST)
    @ResponseBody
    public void updateGood(@RequestBody List<Good> goodList,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        Good good=new Good();
        good.setGname(goodList.get(0).getGname());
        good.setGprice(goodList.get(0).getGprice());
        good.setGpicture(goodList.get(0).getGpicture());
        good.setGid(goodList.get(0).getGid());
        int row=dao.updateGood(good);
        if(row>0){
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    /*删除商品*/
    @RequestMapping(value = "/deleteGood",method = RequestMethod.POST)
    @ResponseBody
    public void deleteGood(@RequestBody Integer gid,HttpServletResponse response){
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        int row=dao.deleteGood(gid);
        if(row>0){
            try {
                response.getWriter().print("删除成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().print("删除失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*多项删除*/
    @RequestMapping("/deleteGoodMore")
    @ResponseBody
    public void deleteGoodMore(@RequestBody List<Integer> integerList,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        int row=dao.deleteGoodMore(integerList);
        if(row==integerList.size()){
            response.getWriter().print("删除成功！");
        }else{
            response.getWriter().print("删除失败！");
        }
    }
    /*查询商品数量*/
    @RequestMapping("/selectGoodCount")
    @ResponseBody
    public int selectGoodCount(){
        int count=dao.selectGoodCount();
        return count;
    }
    /*分页*/
    @RequestMapping("/selectGoodPage")
    @ResponseBody
    public List<Good> selectGoodPage(@RequestBody List<Integer> integerList){
        List<Good> entity=dao.selectGoodPage((integerList.get(0)-1)*(integerList.get(1)),integerList.get(1));
        return entity;
    }
    /*文件上传*/
    @RequestMapping(value = "fileSave",method = RequestMethod.POST)
    @ResponseBody
    public UpLoad fileSave(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //文件存放的位置
        String path=request.getServletContext().getRealPath("/image");
        File fi=new File(path);
        if(!fi.exists()){
            fi.mkdir();
        }
        File tempFile=new File(path, file.getOriginalFilename());
        file.transferTo(tempFile);
        System.out.println(tempFile.getName());
        UpLoad upLoad=new UpLoad();
        upLoad.setCode(0);
        upLoad.setMsg("上传成功！");
        upLoad.setData(tempFile.getName());
        //response.getWriter().print(upLoad);
        return upLoad;
    }

    /*导出数据到Excel表格*/
    @RequestMapping(value = "/joinxml",method = RequestMethod.GET)
    @ResponseBody
    public void joinXml(HttpServletResponse response) throws IOException {
        //数据的来源
        List<Good> entity=dao.selectAllGood();
        //设置标题
        String head = "商品信息详细展示";
        //设置表头行
        String[] headrow = {"编号", "名称", "价格","图片路径"};
        if (null != entity && entity.size() > 0) {
            String fileName = "商品信息.xls";//定义导出头
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));    //设置文件头编码格式
            response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头
            //创建工作簿HSSFWorkbook 对象
            HSSFWorkbook book = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = book.createSheet();
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);
            //由工作簿创建表HSSFSheet对象
            CellStyle cellStyle = book.createCellStyle();

            cellStyle.setDataFormat(book.createDataFormat().getFormat("yyyy-MM-dd"));

            //设置表头
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue(head);
            row = sheet.createRow(1);
            for (int i = 0; i < headrow.length; i++) {
                cell = row.createCell((short) i);
                cell.setCellValue(headrow[i]);
            }

            for (int i = 0; i < entity.size(); i++) {
                //实体类对象
                row = sheet.createRow((i + 2));
                BigDecimal money=entity.get(i).getGprice();
                row.createCell((short) 0).setCellValue(entity.get(i).getGid());
                row.createCell((short) 1).setCellValue(entity.get(i).getGname());
                row.createCell((short) 2).setCellValue(money+"");
                row.createCell((short) 3).setCellValue(entity.get(i).getGpicture());
            }
            //写出流  刷新缓冲流  关闭流对象
            book.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

    /**
     * 导入数据到Excel表格
     *excel表格的形式为xlsx
     * @throws IOException
     */
    @RequestMapping(value = "/outxml",method = RequestMethod.POST)
    @SuppressWarnings("resource")
    public String excels(MultipartFile files, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        //文件存放的位置
        String path=request.getSession().getServletContext().getRealPath("/files2");
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        //保存文件
        File tempFile=new File(path, files.getOriginalFilename());
        files.transferTo(tempFile);//把文件从内存存到磁盘中
        System.out.println(path+"\\"+files.getOriginalFilename());

        //Excel导入数据
        InputStream is = new FileInputStream(path+"\\"+files.getOriginalFilename());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Good emp = new Good();
        // 循环工作表Sheet
        int row=0;int len=0;
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    len++;
                    XSSFCell gname = xssfRow.getCell(0);
                    XSSFCell gprice = xssfRow.getCell(1);
                    XSSFCell gpicture = xssfRow.getCell(2);
                    /*String vaId=getValue(eid);
                    String[] arr=vaId.split(".");*/
                    //double vaId=Double.parseDouble(eid.toString());
                    //int vaintId=(int)vaId;
                    Double valMoney=Double.parseDouble(getValue(gprice));
                    BigDecimal bMoney=new BigDecimal(valMoney);
                    emp.setGname(getValue(gname));
                    emp.setGprice(bMoney);
                    emp.setGpicture(getValue(gpicture));
                    if(dao.insertGood(emp)>0){
                        row++;
                    }
                }
            }
        }
        if(row==len){
            try {
                response.getWriter().print("添加成功!");
            } catch (IOException es) {
                es.printStackTrace();
            }
        }else {
            try {
                response.getWriter().print("添加失败!");
            } catch (IOException es) {
                es.printStackTrace();
            }
        }
        //request.getRequestDispatcher("index.html").forward(request, response);
        return "redirect:http://localhost:8080/";
    }
    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
