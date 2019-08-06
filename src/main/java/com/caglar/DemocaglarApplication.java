package com.caglar;

import com.caglar.entities.Kisi;
import com.caglar.entities.KisiRepository;
import com.caglar.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RestController
@SpringBootApplication
public class DemocaglarApplication {
    @Autowired
    KisiRepository kisiRepository;

    private static List<Person> persons = new ArrayList<Person>();

    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }


    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    public String sayHello(){
        return "HELLO SPRING BOOT";

    }



    //@GetMapping("/manual")
    @RequestMapping(value = "/manual",method = RequestMethod.GET)
    void manual(HttpServletResponse response) throws IOException {

        response.setHeader("Custom-Header", "foo");
        response.setStatus(200);
        response.getWriter().println("<h1>Inside handlerOne() Method Of MyController</h1>");

    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // set response headers
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML form
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Form input</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n")
                .append("			<form action=\"kayit\" method=\"POST\">\r\n")
                .append("				Enter your name: \r\n")
                .append("				<input type=\"text\" name=\"user\" />\r\n")
                .append("				Enter your surname: \r\n")
                .append("				<input type=\"text\" name=\"user_surname\" />\r\n")
                .append("				Enter your tel: \r\n")
                .append("				<input type=\"text\" name=\"user_tel\" />\r\n")
                .append("				<input type=\"submit\" value=\"Submit\" />\r\n")
                .append("			</form>\r\n")
                .append("   <form> ")
                .append("   <form action = \"sil\" method = \"POST\">\r\n")
                .append("       <input type=\"submit\" value=\"SİL\" />\r\n")
                .append("   <form> ")
                .append("   <form action = \"liste\" method = \"GET\">\r\n")
                .append("       <input type=\"submit\" value=\"Submit\" />\r\n")
                .append("   <form> ")
                .append("		</body>\r\n")
                .append("</html>\r\n");
    }
    @PutMapping("rparam")
    public void put(@RequestParam("test") String test) {
        System.out.println(test);
    }



    @RequestMapping(value="/listes")
    @ResponseBody
    public String method0(){

        List<Kisi> kisiList = kisiRepository.findAll();
        StringBuffer test=new StringBuffer();
        int a = kisiList.size();
        for (int i = 0; i < a; i++) {
            test.append("<form action=\"sil\"${id}"+""+"method=\"POST\">\r\n").
            append("<p><input type=\"text\" name=\"id\" value=")
                    .append(kisiList.get(i).getId().toString())
                    .append(">")
                    .append(" " + kisiList.get(i).getAd() + "\n" + "</p>\n")
                    .append("	<input type=\"submit\" value=\"Submit\" />\r\n")
                    .append("			</form>\r\n");
        }
        return test.toString();
    }


    @RequestMapping(value = "/sil")
    @ResponseBody
    public String sils(@RequestParam(value = "id",required = false) Long id){
       kisiRepository.deleteById(id);
         List<Kisi> kisiList = kisiRepository.findAll();

        StringBuffer test=new StringBuffer();

        int a = kisiList.size();
        for (int i = 0; i < a; i++) {
            test.append("<input type=\"text\" name=\"id\" value=")
                    .append(kisiList.get(i).getId().toString())
                    .append(">")
                    .append("<p>" + kisiList.get(i).getAd() + "\n" + "</p>\n");
        }
        return test.toString();
    }



    @RequestMapping(value ={"/kayit" },method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getParameterValues("value") ;
        PrintWriter writer = response.getWriter();

         String user = request.getParameter("user");
            String user_surname = request.getParameter("user_surname");
            String user_tel = request.getParameter("user_tel");
            Kisi yenikisi = new Kisi();
            yenikisi.setAd(user);
            yenikisi.setSoyAdi(user_surname);
            yenikisi.setTel(user_tel);

            kisiRepository.save(yenikisi);

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            // create HTML response

            writer.append("<!DOCTYPE html>\r\n")
                    .append("<html>\r\n")
                    .append("		<head>\r\n")
                    .append("			<title>KAYITLILAR</title>\r\n")
                    .append("		</head>\r\n")
                    .append("		<body>\r\n");


            List<Kisi> kisiList = kisiRepository.findAll();
            ArrayList asdsd = (ArrayList) kisiList;
            int a = kisiList.size();
            for (int i = 0; i < a; i++) {
                writer.append("<p>" +kisiList.get(i).getId()+" "+kisiList.get(i).getAd() + "\n" + "</p>");
            }


        writer.append("   <form action = \"test\" method = \"GET\">\r\n")
                .append("       <input type=\"submit\" value=\"test\" />\r\n")
                .append("   <form> ")
        .append("   <form action = \"listes\" method = \"GET\">\r\n")
                .append("     <input type=\"submit\" value=\"liste\" />\r\n")
                .append("   <form> ")
        .append("		</body>\r\n")
                .append("</html>\r\n");
    }


    @RequestMapping(value = "/sil/id",method = RequestMethod.POST)
    protected void doPostss(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Long IDSI= Long.valueOf(id).longValue();

        kisiRepository.deleteById(IDSI);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML response
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>silme yapma </title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n")
                .append("   <form action = \"test\" method = \"GET\">\r\n")
                .append("       <button>GERİ</button>\r\n")
                .append("   <form> ")
                .append("		</body>\r\n")
                .append("   <form action = \"liste\" method = \"POST\">\r\n")
                .append("       <button>liste</button>\r\n")
                .append("   <form> ")
                .append("</html>\r\n");
    }


    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        String message = "Hello Spring Boot + JSP";

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {

        model.addAttribute("persons", persons);

        return "personList";
    }

 /*
    @RequestMapping(value = "/welcome",method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML response
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Welcome message</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n");
        if (user != null && !user.trim().isEmpty()) {
            writer.append("	Welcome " + user + ".\r\n");
            writer.append("	You successfully completed this javatutorial.net example.\r\n");
        } else {
            writer.append("	You did not entered a name!\r\n");
        }
        writer.append("		</body>\r\n")
                .append("</html>\r\n");
    }
*/


    @ResponseBody
    public String method3(){
        return "method3";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemocaglarApplication.class, args);
    }

}
