package in.career.springboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.career.springboot.model.Employee;
import in.career.springboot.repository.EmployeeRepository;

@SpringBootApplication
public class SpringbootBackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootBackendApplication.class,
				args);

		EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);

		List<Employee> asList = Arrays.asList(new Employee("Rahul", "Raj", "rahul@gmail.com"),
				new Employee("Rahul", "Raj", "rahul@gmail.com"), new Employee("Rahul", "Raj", "rahul@gmail.com"),
				new Employee("Rahul", "Raj", "rahul@gmail.com"), new Employee("Rahul", "Raj", "rahul@gmail.com"),
				new Employee("Rahul", "Raj", "rahul@gmail.com"));

		employeeRepository.saveAll(asList);
	}

}
