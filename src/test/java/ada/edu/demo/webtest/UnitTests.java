package ada.edu.demo.webtest;

import ada.edu.demo.webtest.entity.Course;
import ada.edu.demo.webtest.entity.Student;
import ada.edu.demo.webtest.exception.GlobalExceptionHandler;
import ada.edu.demo.webtest.exception.StudentException;
import ada.edu.demo.webtest.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

	@Mock
    private CourseRepository courseRepository;

    @Test
    public void testSaveCourse() {
        Course course = new Course();
        course.setCourseId(1);
        course.setCourseName("Web & Mobile 1");
        course.setCredits(6);
        when(courseRepository.save(course)).thenReturn(course);
        Course savedCourse = courseRepository.save(course);
        assertNotNull(savedCourse);
        assertEquals(course, savedCourse);
    }


    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testHandle400Errors() {
		Exception ex = new Exception("Bad request");

		ModelAndView mv = globalExceptionHandler.handle400Errors(ex);

		assertNotNull(mv);
		assertEquals("/errorpages/error_general", mv.getViewName());
		assertEquals("Bad request", mv.getModel().get("exception"));
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mv.getStatus().value()); // Check for expected status value
	}

	@Test
	public void testHandle500Errors() {
		ModelAndView mv = globalExceptionHandler.handle500Errors(new StudentException(500, "Internal server error"));
		assertNotNull(mv);
		assertEquals("/errorpages/error_student", mv.getViewName());
		assertEquals("Entity error (500) : Internal server error", mv.getModel().get("exception"));
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mv.getStatus().value());
	}
}
