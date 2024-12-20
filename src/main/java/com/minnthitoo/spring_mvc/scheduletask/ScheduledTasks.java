package com.minnthitoo.spring_mvc.scheduletask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
//@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        log.info("The time is now {}", dateFormat.format(new Date()));
        try{
//            Thread.sleep(8000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 6000)
    public void scheduleFixedDelayTask(){
        log.info("Fixed delay task - {}", System.currentTimeMillis() / 1000);
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void asyncScheduleTask(){
        log.info("Async The time is now {}", dateFormat.format(new Date()));
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();;
        }
    }

    // run in 10th minute of every hour
    @Scheduled(cron = "0 10 * * * ?")
    public void cronScheduleTask(){
        log.info("Async The time is now {}", dateFormat.format(new Date()));
    }
}
