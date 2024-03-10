package ada.edu.demo.webtest;

import java.util.Date;
import ada.edu.demo.webtest.entity.Course;
import ada.edu.demo.webtest.entity.Student;
import ada.edu.demo.webtest.repository.CourseRepository;
import ada.edu.demo.webtest.repository.StudentRepository;
import ada.edu.demo.webtest.service.StudentService;
// import lombok.var;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
// import org.mockito.InjectMocks;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
// import java.util.Arrays;
// import java.util.Date;
import java.util.List;
import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.assertNotNull;


class UnitTests {

	@Test
	@DisplayName("The number of courses shall correspond to the added courses")
	void testTotalCourses() {
		List<Course> courseList = new ArrayList<>();

		Integer courseCnt = (int)(Math.random() * 20);

		for (int i = 0; i< courseCnt; i++) {
			Course c = new Course();
			courseList.add(c);
		}

		Student s = new Student();
		s.setCourses(courseList);

		assert (courseCnt == s.getCourses().size());
	}

	@Test
	@DisplayName("The total credits shall correspond to the sum of the added credits")
	void testCreditCalculation() {
		List<Course> courseList = new ArrayList<>();

		Integer courseCnt = (int)(Math.random() * 20);
		Integer testCreds = 0;

		for (int i = 0; i< courseCnt; i++) {
			Integer rndCred = (int)(Math.random() * 5);
			Course c = new Course();
			c.setCredits(rndCred);
			courseList.add(c);
			testCreds += rndCred;
		}

		Student s = new Student();
		s.setCourses(courseList);

		Integer totalCredits = s.getTotalCredits();
		assert (totalCredits == testCreds);
	}

	// 

	@Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(1, "Web & Mobile 1", 6, null));
        courses.add(new Course(2, "Web & Mobile 2", 6, null));
        courses.add(new Course(3, "System Analysis & Design", 6, null));

        when(courseRepository.findAll()).thenReturn(courses);

        Iterable<Course> result = courseService.getAllCourses();

        assertEquals(courses.size(), ((List<Course>) result).size());
    }

    @Test
    public void testSaveCourse() {
        Course course = new Course(1, "Web & Mobile 1", 6, null);

        when(courseRepository.save(course)).thenReturn(course);

        Course savedCourse = courseService.saveCourse(course);

        assertNotNull(savedCourse);
        assertEquals(course, savedCourse);
    }

}
