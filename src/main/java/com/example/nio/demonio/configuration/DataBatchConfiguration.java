package com.example.nio.demonio.configuration;

import com.example.nio.demonio.entity.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;


@Configuration
@EnableBatchProcessing
public class DataBatchConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataBatchConfiguration.class);

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private EntityManagerFactory entityManagerFactory;

    @Resource
    private JobListener jobListener;


    @Bean
    public Job dataHandleJob(){
        return jobBuilderFactory.get("dataHandleJob")
                .incrementer(new RunIdIncrementer())
                .start(handleDataStep())
                .listener(jobListener)
                .build();
    }

    @Bean
    public Step handleDataStep(){
        return stepBuilderFactory.get("getData").<Access,Access>chunk(100)
                .faultTolerant().retryLimit(3).retry(Exception.class).skipLimit(100).skip(Exception.class)
                .reader(getDataReader())
                .processor(getDataProcessor())
                .writer(getDataWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Access> getDataReader(){
        JpaPagingItemReader<Access> reader =new JpaPagingItemReader<>();
        String sqlQuery = "select * from access";
        try{
            JpaNativeQueryProvider<Access> queryProvider = new JpaNativeQueryProvider<>();
            queryProvider.setSqlQuery(sqlQuery);
            queryProvider.setEntityClass(Access.class);
            queryProvider.afterPropertiesSet();

            reader.setEntityManagerFactory(entityManagerFactory);
            reader.setPageSize(3);
            reader.setQueryProvider(queryProvider);
            reader.afterPropertiesSet();
            reader.setSaveState(true);
        }catch(Exception e){
            e.printStackTrace();
        }
        return reader;
    }

    @Bean
    public ItemProcessor<Access,Access> getDataProcessor(){
        return new ItemProcessor<Access, Access>() {
            @Nullable
            @Override
            public Access process(Access access) throws Exception {
                log.info("processor data:" + access.toString());
                return access;
            }
        };
    }

    @Bean
    public ItemWriter<Access> getDataWriter(){
        return list -> {
            for (Access access: list){
                log.info("write data:" + access);
            }
        };
    }

}
