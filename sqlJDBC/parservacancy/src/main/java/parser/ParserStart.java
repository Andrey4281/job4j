package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Main class of program
 * @author asemenov
 * @since 25.09.2019
 * @version 1
 */
public class ParserStart {
    public final static Logger LOG = LogManager.getLogger(ParserStart.class);


    public static void main(String[] args) {
        ParserConfig config = new ParserConfig(args[0]);
        config.init();

        SchedulerFactory schedFact = new StdSchedulerFactory();

        try {
            Scheduler sched = schedFact.getScheduler();


            JobDetail job = newJob(ParserQuartz.class)
                    .withIdentity("parser", "group1")
                    .usingJobData("jdbc.driver", config.getKey("jdbc.driver"))
                    .usingJobData("jdbc.url", config.getKey("jdbc.url"))
                    .usingJobData("jdbc.username", config.getKey("jdbc.username"))
                    .usingJobData("jdbc.password", config.getKey("jdbc.password"))
                    .usingJobData("site.url", "https://www.sql.ru/forum/job-offers/")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger", "group1")
                    .withSchedule(cronSchedule(config.getKey("cron.time")))
                    .forJob("parser", "group1")
                    .build();

            sched.start();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error("Error of schedule", e);
        }
    }
}
