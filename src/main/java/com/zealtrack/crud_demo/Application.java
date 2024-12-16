package com.zealtrack.crud_demo;

import com.zealtrack.crud_demo.dao.AppDAO;
import com.zealtrack.crud_demo.entity.Course;
import com.zealtrack.crud_demo.entity.Instructor;
import com.zealtrack.crud_demo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        SpringApplication app = new SpringApplication(Application.class);
        app.setApplicationStartup(new FlightRecorderApplicationStartup());
        app.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            System.out.println("Hi, Mom");
//          createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);

//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);

            createInstrctorWithCourses(appDAO);
        };
    }

    private void createInstrctorWithCourses(AppDAO appDAO) {
        //create the instructor
        Instructor tempInstructor = new Instructor(
                "Suasn", "Public", "susan_pub@luv2code.com");

        // create the instructor detail entity
        InstructorDetail tempInstructorDetail = new InstructorDetail(
                "http://youtube.com/susan",
                "Video Games"
        );

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //create some courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add courses to the instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        // this will also save the courses because of CascadeType.PERSIST

        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("Courses: " + tempInstructor.getCourses());

        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 5;
        System.out.println("Deleting instrcutor detail id: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting instrcutor id: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
        System.out.println("Done!");
    }

    private void createInstructor(AppDAO appDAO) {

//		Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//
//		InstructorDetail tempInstructorDetail = new InstructorDetail(
//				"http://www.luv2code.com/youtube",
//				"Luv 2 code!!!"
//		);
//
//		tempInstructor.setInstructorDetail(tempInstructorDetail);


        Instructor tempInstructor = new Instructor(
                "Kaeli", "Siri", "kaeli2@luv2code.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail(
                "http://www.luv2code.com/youtube",
                "Guitar"
        );

        tempInstructor.setInstructorDetail(tempInstructorDetail);


        System.out.println("Saving instructor: " + tempInstructor);

        appDAO.save(tempInstructor);

        System.out.println("Done");
    }
}
