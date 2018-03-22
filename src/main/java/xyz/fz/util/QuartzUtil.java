package xyz.fz.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzUtil {

    private static final String QUARTZ_JOB_SUFFIX = "-quartz";
    private static final String QUARTZ_GROUP_SUFFIX = "-group";
    private static final String QUARTZ_TRIGGER_SUFFIX = "-trigger";

    private static Scheduler scheduler;

    static {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void start() throws SchedulerException {
        scheduler.start();
    }

    public static void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }

    public static void addJob(String jobName, String jobGroup, String cronExpress, Class<? extends Job> jobClass) throws SchedulerException {

        String quartzJobName = jobName + QUARTZ_JOB_SUFFIX;
        String quartzGroup = jobGroup + QUARTZ_GROUP_SUFFIX;
        String quartzTriggerName = jobName + QUARTZ_TRIGGER_SUFFIX;

        JobDetail job = newJob(jobClass)
                .withIdentity(quartzJobName, quartzGroup)
                .build();

        Trigger trigger = newTrigger()
                .withIdentity(quartzTriggerName, quartzGroup)
                .withSchedule(cronSchedule(cronExpress))
                .forJob(job)
                .build();

        scheduler.scheduleJob(job, trigger);
    }

    public static void removeJob(String jobName, String jobGroup) throws SchedulerException {

        String quartzJobName = jobName + QUARTZ_JOB_SUFFIX;
        String quartzGroup = jobGroup + QUARTZ_GROUP_SUFFIX;
        String quartzTriggerName = jobName + QUARTZ_TRIGGER_SUFFIX;

        TriggerKey triggerKey = new TriggerKey(quartzTriggerName, quartzGroup);
        JobKey jobKey = new JobKey(quartzJobName, quartzGroup);

        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
        scheduler.deleteJob(jobKey);
    }
}
