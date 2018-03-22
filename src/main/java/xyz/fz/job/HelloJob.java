package xyz.fz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import xyz.fz.service.HelloService;
import xyz.fz.util.SpringContextHelper;

@DisallowConcurrentExecution
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HelloService helloService = SpringContextHelper.getBean("helloServiceImpl", HelloService.class);
        System.out.println(jobExecutionContext.getJobDetail().getKey().getName());
        helloService.sayHello();
    }
}
