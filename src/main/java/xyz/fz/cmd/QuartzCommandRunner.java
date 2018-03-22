package xyz.fz.cmd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.fz.job.HelloJob;
import xyz.fz.util.QuartzUtil;

@Component
public class QuartzCommandRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        QuartzUtil.start();
        QuartzUtil.addJob("hello", "hello", "0/1 * * * * ?", HelloJob.class);
        QuartzUtil.addJob("hello2", "hello", "0/2 * * * * ?", HelloJob.class);
        Thread.sleep(5000L);
        QuartzUtil.removeJob("hello", "hello");
        Thread.sleep(5000L);
        QuartzUtil.addJob("hello3", "hello", "0/3 * * * * ?", HelloJob.class);
    }
}
