package org.max.demo;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateFileTest {

    static List<Employee> employeeList = new ArrayList<Employee>(10000);

    @BeforeAll
    static void init() {
        Faker faker = new Faker();
        for(int i=0; i<10000; i++) {
            employeeList.add(new Employee(faker.name().fullName(),
                    faker.address().fullAddress(),
                    faker.phoneNumber().phoneNumber()));
        }
    }

    @Test
    void generateFileCSVTest() throws IOException {

        File csvFile = new File("employees.csv");
        FileWriter fileWriter = new FileWriter(csvFile);

        for (Employee data : employeeList) {
            StringBuilder line = new StringBuilder();
            line.append(data.getName()+';');
            line.append(data.getAddress()+';');
            line.append(data.getPhone()+';');
            line.append(data.getStatusType().name()+';');

            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();
    }

    private static class Employee {
        private String name;
        private String address;
        private String phone;
        private StatusType statusType;

        public Employee(String name, String address, String phone) {
            this.name = name.replaceAll(";",",");
            this.address = address.replaceAll(";",",");
            this.phone = phone.replaceAll(";",",");
            this.statusType = StatusType.randomDirection();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public StatusType getStatusType() {
            return statusType;
        }

        public void setStatusType(StatusType statusType) {
            this.statusType = statusType;
        }
    }

    private enum StatusType {
        NEW(0),
        OLD(1),
        CLOSED(2);

        private int code;

        StatusType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        private static final Random PRNG = new Random();

        public static StatusType randomDirection()  {
            StatusType[] statusTypes = values();
            return statusTypes[PRNG.nextInt(statusTypes.length)];
        }
    }
}
