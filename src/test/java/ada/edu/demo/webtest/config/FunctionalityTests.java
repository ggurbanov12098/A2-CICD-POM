package ada.edu.demo.webtest.config;

import ada.edu.demo.webtest.entity.Student;
import ada.edu.demo.webtest.repository.StudentRepository;
import ada.edu.demo.webtest.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
}

