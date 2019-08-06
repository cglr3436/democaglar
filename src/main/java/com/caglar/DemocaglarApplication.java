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

    @RequestMapping(value="/test")
    @ResponseBody
    public String methodtest(){
        StringBuffer test=new StringBuffer();


               test.append(" <form action=\"kayit\"${user}\"${user_surname}\"${user_tel}\"${user_tel}"
                        +"method=\"POST\">\r\n")
                .append("				Enter your name: \r\n")
                .append("				<input type=\"text\" name=\"user\" />\r\n")
                .append("				Enter your surname: \r\n")
                .append("				<input type=\"text\" name=\"user_surname\" />\r\n")
                .append("				Enter your tel: \r\n")
                .append("				<input type=\"text\" name=\"user_tel\" />\r\n")
                .append("				<input type=\"submit\" value=\"KAYIT\" />\r\n")
                .append("			</form>\r\n")
                .append("   </form> ")
                .append("   <form action = \"listes\" method = \"GET\">\r\n")
                .append("       <input type=\"submit\" value=\"LISTE\" />\r\n")
                .append("   </form> ");
        return test.toString();
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
            append("<p><input type=\"checkbox\" name=\"id\" value=")
                    .append(kisiList.get(i).getId().toString())
                    .append(">")
                    .append(" " + kisiList.get(i).getAd() + "\n" + "\n")
                    .append("	<input type=\"submit\" value=\"SIL\" /></p>\r\n")
                    .append("			</form>\r\n");
        }
        test.append("   <form action = \"test\" method = \"GET\">\r\n")
                .append("       <input type=\"submit\" value=\"ANASAYFA\" />\r\n")
                .append("   </form> ");
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
            test.append("<p><input type=\"checkbox\" name=\"id\" value=")
                    .append(kisiList.get(i).getId().toString())
                    .append("/>")
                    .append("" + kisiList.get(i).getAd() + "\n" + "</p>\n");
        }

        test.append("   <form action = \"test\" method = \"GET\">\r\n")
                .append("       <input type=\"submit\" value=\"ANASAYFA\" />\r\n")
                .append("   </form> ");
        return test.toString();
    }




    @RequestMapping(value = "/kayit")
    @ResponseBody
    public String kayits(@RequestParam(value = "user")  String adi,
                         @RequestParam(value = "user_surname") String soyadi,
                         @RequestParam(value = "user_tel") String tel){



        Kisi yenikisi = new Kisi();
        yenikisi.setAd(adi);
        yenikisi.setSoyAdi(soyadi);
        yenikisi.setTel(tel);

        kisiRepository.save(yenikisi);
        List<Kisi> kisiList = kisiRepository.findAll();
        StringBuffer test=new StringBuffer();

        int a = kisiList.size();
        for (int i = 0; i < a; i++) {
            test.append("<p>" +kisiList.get(i).getId()+" "+ kisiList.get(i).getAd() +
                    " "+ kisiList.get(i).getSoyAdi() + " "+ kisiList.get(i).getTel() + "\n" + "</p>\n");
        }
        test.append("   <form action = \"listes\" method = \"POST\">\r\n")
                .append("       <input type=\"submit\" value=\"LISTELE\" />\r\n")
                .append("   </form> ");
        test.append("   <form action = \"test\" method = \"GET\">\r\n")
                .append("       <input type=\"submit\" value=\"ANASAYFA\" />\r\n")
                .append("   </form> ");
        return test.toString();
    }



    public static void main(String[] args) {
        SpringApplication.run(DemocaglarApplication.class, args);
    }

}
