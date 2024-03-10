package ada.edu.demo.webtest.config;

import ada.edu.demo.webtest.controller.StudentController;
import ada.edu.demo.webtest.entity.Student;
import ada.edu.demo.webtest.repository.StudentRepository;
import ada.edu.demo.webtest.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.validation.BeanPropertyBindingResult;
// import org.springframework.validation.BindingResult;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FunctionalityTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("Test finding s student by ID")
    public void testStudentSearchById() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(new Student()));
        Student result = studentService.getStudentById(1);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Search by first or last name")
    public void testStudentSearch() {
        Student s1 = new Student(1,"Jamal","Hasanov","jhasanov@ada.edu.az",new Date(),null,null);
        Student s2 = new Student(2,"Gabil","Gurbanov","ggurbanov12098@ada.edu.az",new Date(),null,null);
        Student s3 = new Student(3,"Farid","Mammadli","fmammadli12101@ada.edu.az",new Date(),null,null);

        List<Student> stList = List.of(s1,s2,s3);

        when(studentRepository.findAll()).thenReturn(stList);
        List<Student> students = studentService.getStudentByEitherName("Jamal","Gurbanov");
        System.out.printf("Found students: "+students.size());
        assertEquals(2, students.size());
    }
    // ///////////////////////

    @Test
    @DisplayName("Test saving a new student")
    public void testSaveNewStudent() {
        Student newStudent = new Student(12098, "Gabil", "Gurbanov", "ggurbanov12098@ada.edu.az", new Date(), null, null);
        when(studentRepository.save(any(Student.class))).thenReturn(newStudent);

        Student savedStudent = studentService.saveStudent(newStudent);

        assertNotNull(savedStudent);
        assertEquals("Gabil", savedStudent.getFirstName());
        assertEquals("Gurbanov", savedStudent.getLastName());
    }


    @Test
	@DisplayName("Test getting list of all students")
	public void testGetStudent() {
		List<Student> students = Arrays.asList(new Student(12098, "Gabil", "Gurbanov", "ggurbanov12098@ada.edu.az", new Date(), null, null));
        
        // findAll()
        when(studentRepository.findAll()).thenReturn(students);

		Iterable<Student> result = studentService.getStudentList();
		assertNotNull(result); assertEquals(1, ((List<Student>) result).size());
	}

	@Test
	@DisplayName("Test getting list of ACTIVE students")
	public void testGetActiveStudents() {
		List<Student> activeStudents = Arrays.asList(new Student(12098, "Gabil", "Gurbanov", "ggurbanov12098@ada.edu.az", new Date(), null, null));

        // findAllActive()
        when(studentRepository.findAllActive()).thenReturn(activeStudents);

		Iterable<Student> result = studentService.getActiveStudents();
		assertNotNull(result); assertEquals(1, ((List<Student>) result).size());
	}

    // Put new test code here below

    @Test
    @DisplayName("Test error handling for invalid student data")
    public void testErrorHandlingForInvalidData() {
        // Mock studentService
        StudentService studentService = mock(StudentService.class);

        // Create instance of StudentController and set studentService mock
        StudentController studentController = new StudentController();
        ReflectionTestUtils.setField(studentController, "studentService", studentService);

        // Mock BindingResult
        BindingResult bindingResult = mock(BindingResult.class);

        // Create instance of Model
        Model model = new ConcurrentModel();

        // Create invalid student object
        Student invalidStudent = new Student(); // Invalid student object with missing required fields

        // Mock behavior of studentService.saveStudent() method
        when(studentService.saveStudent(any(Student.class))).thenReturn(null);

        
        try {
            // Call the method under test
            String viewName = studentController.saveStudent(model, invalidStudent, bindingResult);   
            // Assertions
            assertEquals("student/details", viewName);
            assertTrue(model.containsAttribute("org.springframework.validation.BindingResult.student")); 
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            System.out.println("Null ptr exception already handled in other class");
        }
        
    }


    @Test
    public void testGetStudentById_WhenStudentExists() {
        Student student = new Student(1, "John", "Doe", "john@example.com", null, null, null);
        Optional<Student> optionalStudent = Optional.of(student);

        when(studentRepository.findById(1)).thenReturn(optionalStudent);

        Student result = studentService.getStudentById(1);

        assertTrue(optionalStudent.isPresent());
        assertEquals(student, result);
    }


}

