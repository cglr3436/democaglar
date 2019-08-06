package com.caglar;

import com.caglar.entities.Araba;
import com.caglar.entities.ArabaRepository;
import com.caglar.entities.KisiRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemocaglarApplicationTests {

    @Autowired
    ArabaRepository arabaRepository;



    @Test
    public void contextLoads() {
    }

    @Test
    public  void insertAraba()
    {
        Araba araba=new Araba();
        araba.setMarkaAd("Reno");


        arabaRepository.save(araba);

         araba=new Araba();
        araba.setMarkaAd("Reno");


        arabaRepository.save(araba);

        araba=new Araba();
        araba.setMarkaAd("mercedes");


        arabaRepository.save(araba);


        List<Araba> arabaList =arabaRepository.getAllByMarkaAdEquals("Reno");
        for (Araba arabax:arabaList
             )
        {
            System.out.println(arabax);
        }

        System.out.println(arabaRepository.count());


    }

}
