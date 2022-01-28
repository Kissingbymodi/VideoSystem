package com.hu.video;

//import org.junit.jupiter.api.Test;
import com.hu.video.service.IVideoService;
import com.hu.video.service.impl.VideoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeginVideoApplicationTests {

    @Test
    public void contextLoads() {
        IVideoService videoService = new VideoServiceImpl();
        System.out.println(videoService.getVideoByVideoId(1L));
    }

}
