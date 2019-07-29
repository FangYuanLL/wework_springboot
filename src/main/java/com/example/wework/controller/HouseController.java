package com.example.wework.controller;


import com.example.wework.model.*;
import com.example.wework.service.*;
import com.example.wework.utils.CreateUUID;
import com.example.wework.utils.JWT;
import com.example.wework.utils.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private collectionHouseService collectionHouseService;

    @Autowired
    private BusinessService businessService;


    @Autowired
    private userService userService;

    @Autowired
    private MailService mailService;
/*
 * 合租型办公室必须填写多次upload.html页面，每个办公室一条记录，图片与也不一样
 * 这个还没在页面实现
 *
 */
    //对应业主委托页面的ajax请求
    //业主委托功能
    @ResponseBody
    @RequestMapping(value="/HouseInformation")
    private void HouseInformation(@RequestParam("file") MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) throws IOException {

        house_Information house = new house_Information();
        Map map = new HashMap();
        //插入记录到house_Information表中
        String officename = request.getParameter("officename");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        String address = request.getParameter("address");
        String officetype = request.getParameter("officetype");
        System.out.println("officetype:"+officetype);
        String  person_number = request.getParameter("person_number");
        String price = request.getParameter("price");
        String officenumber;
        if(officetype.equals("整租") == true){
            System.out.println("1");
            officenumber = "1";
        }else{
            officenumber = person_number;
            System.out.println("2");
        }

        System.out.println("officenumber:"+officenumber);

        String prepayment = request.getParameter("prepayment");
        String introduce = request.getParameter("introduce");
        //System.out.println(city);

        //获取登录用户记录在cookie的数据
        /*String cookieValue=null;
        Cookie[]cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("userCustomer"));
                cookieValue = cookie.getValue();
            }
        }
        user_Customer user = new user_Customer();
        user = JWT.unsign(cookieValue,user_Customer.class);*/


        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        /*--------------获取user数据的cookie-end-----------*/
        //存储house数据并插入记录
        house.setLandlordid(user.getId());
        house.setEmpty(officename);
        house.setLandlordid(user.getId());
        house.setCity(city);
        house.setArea(area);
        house.setAddress(address);
        house.setOfficetype(officetype);
        house.setOfficenumber(Integer.valueOf(officenumber));
        house.setRemainnumber(Integer.valueOf(officenumber));
        house.setPersonNumber(Integer.parseInt(person_number));
        house.setPrice(Integer.parseInt(price));
        house.setPrepayment(Integer.parseInt(prepayment));
        house.setIntroduce(introduce);
        house.setStatus("0");//状态0，未审核通过
        house.setRentornot("1");//状态1，未删除，状态0已删除

        /*生成唯一的uuid，在id未生成之前，给图片存储使用*/
        CreateUUID UUID = new CreateUUID();
        String uuid = UUID.getUUID();
        house.setUuid(uuid);
        System.out.println("UUID:"+uuid);
        int flag = houseService.insert(house);//插入house信息，生成id
        if (flag>0){
            System.out.println("house_information插入成功！");
        }else {
            System.out.println("house_information插入失败！");
        }

        //获取house的id信息，以便于image信息的存储
        house_Information house_information ;
        house_information = houseService.selectHouseID(uuid);
        System.out.println("city:"+house_information.getCity());

        //接收到的File文件放入指定路径的磁盘下
        if(files!=null){
            for (MultipartFile imagefile:files){
                String filename = imagefile.getOriginalFilename();
                String path = "D:\\java_IDEA\\wework\\src\\main\\webapp\\images\\houseImages";//huose图片上传存储的文件夹
                File tempFile = new File(path,String.valueOf(filename));
                if (!tempFile.getParentFile().exists()){
                    tempFile.getParentFile().exists();
                }
                imagefile.transferTo(tempFile);

                System.out.println("filename:"+filename);
                Image image  = new Image();
                image.setHouseid(house_information.getId());
                image.setImgname(filename);
                //存储image信息
                int imageflag = imageService.insert(image);
                if (imageflag>0){
                    System.out.println("D:\\java_IDEA\\wework\\src\\main\\webapp\\images\\houseImages"+filename+"上传成功\n");
                }else{
                    System.out.println("D:\\java_IDEA\\wework\\src\\main\\webapp\\images\\houseImages"+filename+"上传失败\n");
                }

            }
        }else{
            System.out.println("files文件出错!\n");
        }
        /*--------------files存储end--------*/


        if (flag>0){
            response.sendRedirect("http://localhost:8080/index.html");
        }else {
            response.sendRedirect("http://localhost:8080/upload.html");
        }

    }


    //根据前台传来的数据，从数据库选取记录,定位的信息，index页面的数据
    @ResponseBody
    @RequestMapping(value = "/getHouseInformation")
    public Object getHouseInformation(HttpServletRequest request){
        Map map = new HashMap();
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        String officetype = request.getParameter("officetype");
        System.out.println("city:"+city+"area:"+area+"officetype:"+officetype);

        String status = "1";//1代表认证成功，0代表还未认证
        List<house_Information> list= houseService.selectHouseInformation(city,area,officetype,status);
        List<String> listImage = new ArrayList<String>();//存储图片名称
        if(list!=null){
            for(house_Information house_information:list){
                int houseid = house_information.getId();
                //存储查找到的这个房子的照片，不只一张
                List<Image> list1 = imageService.selectByHouseId(houseid);
                Image image = null;
                image = list1.get(0);
                listImage.add(image.getImgname());
            }

            map.put("list",list);
            map.put("listImage",listImage);
            map.put("status",1);
        }else {
            map.put("status",-1);
        }

        //System.out.println("123");
        return map;
    }

    //获取前台点击事件‘预定’传输的对应的houseID，进入房屋详情的页面
    //存储在session中
    @ResponseBody
    @RequestMapping(value = "/houseID")
    public Object GetHouseID(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map map = new HashMap();
        String id = request.getParameter("id");
        String LoginStatus = LoginCheck.WhetherLogin(request);
        if (LoginStatus != null){
            if (id == null){
                map.put("status",-1);
                System.out.println("-1");
            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("houseID",id);
                map.put("status",1);
            }
        }else {
            map.put("status",-3);
        }

        return map;
    }

    //details.hmtl页面发来请求，id使用session中获取的，从GetHouseID方法中存储的
    //获取该id的对应的huose的详细信息
    //这个是从section中获取id
    @ResponseBody
    @RequestMapping(value = "/getHouseInformationById")
    public Object GetHouseInformationById(HttpServletRequest request){
        HttpSession session = request.getSession();
        String  id = (String) session.getAttribute("houseID");
        house_Information house = houseService.selectByPrimaryKey(Integer.valueOf(id));

        List list = null;
        list = imageService.selectByHouseId(house.getId());

        Map map = new HashMap();

        if (house !=null && list !=null){
            map.put("status",1);
            map.put("houseInformation",house);
            map.put("ImageList",list);
        }else {
            map.put("status",-1);
        }
        return map;
    }

    //用与payoff页面，从ajax中获取id
    @ResponseBody
    @RequestMapping(value = "/getHouseByAjaxId")
    public Object GetHouseById(HttpServletRequest request){
        Map map = new HashMap();
        int houseid = Integer.valueOf(request.getParameter("houseid"));
        String OrderTime = request.getParameter("nowTime");

        house_Information house = houseService.selectByPrimaryKey(houseid);
        if (house == null){
            map.put("status",-1);
        }else {
            map.put("status",1);
        }
        /*-------------从cookie中取登录用户的信息----------------------------*/
        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        /*---------------------------------------------------------------*/

        System.out.println(user.getId());
        Business busi = businessService.selectOrderByMore(user.getId(),OrderTime,houseid);

        if (user == null){
            map.put("status",-1);

        }else {
            map.put("houseInformation",house);
            map.put("business",busi);
            map.put("userCustomer",user);
        }

        return map;
    }

    //收藏功能
    @ResponseBody
    @RequestMapping(value="/collectionHouse")
    public Object Collection(HttpServletRequest request){
        String houseid = request.getParameter("houseid");
        Map map = new HashMap();

        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        //System.out.println("user:"+user.getId());

        collectionHouse collection = new collectionHouse();
        collection.setHouseid(Integer.valueOf(houseid));
        collection.setUserCustomerid(user.getId());

        int flag = collectionHouseService.insert(collection);
        if (flag>0){
            map.put("status",1);
        }else {
            map.put("status",-1);
        }
        return map;
    }

    //显示收藏的房源的信息
    @ResponseBody
    @RequestMapping(value="/displayCollection")
    public Object displayCollection(HttpServletRequest request){
        Map map = new HashMap();

        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        //System.out.println("user:"+user.getId());

        List<collectionHouse> CollectionList=collectionHouseService.displayCollection(user.getId());

        List<house_Information> HouseList = new ArrayList<house_Information>();
        for(collectionHouse co:CollectionList){
            house_Information newHouse = null;
            newHouse = houseService.selectByPrimaryKey(co.getHouseid());
            HouseList.add(newHouse);
        }

        if (HouseList != null){
            map.put("CollectionList",CollectionList);
            map.put("houselist",HouseList);
            map.put("status",1);
        }else {
            map.put("status",-1);
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value="/DeleteCollection")
    public Object DeleteCollection(HttpServletRequest request){
        String collectionid = request.getParameter("collectionid");
        Map map = new HashMap();

        if (collectionid!=null){
            int deleteid = Integer.valueOf(collectionid);
            int flag = collectionHouseService.deleteByPrimaryKey(deleteid);
            map.put("status",1);
        }else{
            map.put("status",-1);
        }

        return map;
    }

    //预定功能
    @ResponseBody
    @RequestMapping(value = "/Order")
    public Object PreOrder(HttpServletRequest request){
        Map map = new HashMap();
        int number = Integer.valueOf(request.getParameter("number"));
        int houseid = Integer.valueOf(request.getParameter("id"));
        String nowTime = request.getParameter("nowTime");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        house_Information house = houseService.selectByPrimaryKey(houseid);
        System.out.println("houseid:"+houseid);



        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        System.out.println("user:"+user.getId());

        //user_Customer user = new user_Customer();
        //user = JWT.unsign(cookieValue,user_Customer.class);
        /*end*/
        //System.out.println("登录用户:"+user.getName());
        //System.out.println("登录用户:"+user.getName());

        if (house.getRemainnumber() >= number){//数量足够才去下订单
            Business business = new Business();
            business.setLandlordId(house.getLandlordid());//业主id
            business.setCustomerId(user.getId());//预定用户的id
            business.setDate(nowTime);
            business.setStartTime(startTime);
            business.setEndTime(endTime);
            business.setHouseId(houseid);
            business.setNumber(number);
            business.setType(house.getOfficetype());
            business.setPrepayment(house.getPrepayment()+"");
            business.setOrderstatus(0);//0
            business.setEmpty("未完成");

            int flag = businessService.insert(business);


            //先不能改变remainnumber，如果最后订单没有交付，这个订单不能算成功
            int remainnumber = house.getRemainnumber();
            //int remainnumber = house.getRemainnumber()-number;
            house.setRemainnumber(remainnumber);
            System.out.println("111");

            int uploadflag = houseService.updateByPrimaryKeySelective(house);

            System.out.println("2222");
            if (flag>0 && uploadflag>0){
                map.put("status",1);
            }else {
                map.put("status",-1);//mysql insert，update代码操作有误
               //map.put("ErrorMsg","插入有误");
            }
        }else {
            map.put("status",0);//办公室数量不够的状态码
        }
        return map;
    }

    //房屋管理模块
    @ResponseBody
    @RequestMapping(value = "/getHouseInformationByLandlordID")
    public Object getHouseInformationByLandlordID(HttpServletRequest request){
        Map map = new HashMap();
        /*获取登录用户记录在cookie的数据*/
        /*String cookieValue=null;
        Cookie[]cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if  (cookie.getName().equals("userCustomer"));
                cookieValue = cookie.getValue();
            }
        }
        user_Customer user = new user_Customer();
        user = JWT.unsign(cookieValue,user_Customer.class);*/

        user_Customer user = null;
        user = LoginCheck.proveMe(request);
        /*------end------*/
        List <house_Information> list = null;
        list = houseService.getHouseInformationByLandlordID(user.getId());//获取登录用户的全部房源
        //list = houseService.getHouseInformationByLandlordID(1);
        if (list!=null){
            map.put("status",1);
            map.put("houselist",list);
        }else {
            map.put("status",-1);
        }

        return map;
    }

    //修改房屋信息
    @ResponseBody
    @RequestMapping(value="/SaveInformation")
    public Object SaveInformation(HttpServletRequest request){
        Map map = new HashMap();
        int id = Integer.valueOf(request.getParameter("id"));
        String price = request.getParameter("price");
        String prepayment = request.getParameter("prepayment");
        System.out.println("prepayment:"+prepayment);
        house_Information house = null;
        house = houseService.selectByPrimaryKey(id);
        if (price !=null){
            int newprice = Integer.valueOf(price);

            if (newprice < house.getPrice()){
                house.setPrice(newprice);
                /*发送邮件start*/
                List<collectionHouse> collectList =  collectionHouseService.selectByHouseID(id);
                for (collectionHouse collect:collectList){
                   user_Customer user = userService.SelectById(collect.getUserCustomerid());
                   mailService.SendMail(user.getIdcard(),house);
                 }
               /*发送邮件end*/
            }
        }
        if (prepayment.equals("") == false){
            int newprepayment = Integer.valueOf(prepayment);
            house.setPrepayment(newprepayment);
        }

        int flag = houseService.updateByPrimaryKeySelective(house);
        if (flag>0){
            map.put("status",1);
        }else {
            map.put("status",-1);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value="/delete")
    public Object DeleteHouse(HttpServletRequest request){
        Map map = new HashMap();

        int houseID = Integer.valueOf(request.getParameter("houseID"));

        int flag = houseService.updateRentornot(houseID);//删除操作，更新状态位

        //businessService
        if (flag > 0){
            map.put("status",1);
        }else{
            map.put("status",-1);
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value="/updatehousestatus")
    public Object updateHouseStatus(HttpServletRequest request) {
        Map map = new HashMap();

        int houseid = Integer.valueOf(request.getParameter("id"));

        int flag = houseService.updateHouseStatus(houseid);

        if (flag>0){
            map.put("status",1);
        }else{
            map.put("status",-1);
        }

        return map;
    }

}
