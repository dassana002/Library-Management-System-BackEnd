package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities")
public class ClassProjectLibraryManagementSoftwareApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ClassProjectLibraryManagementSoftwareApplication.class, args);


    }

}
