package lab.ssvv;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private Validator<Student> studentValidator;
    private Validator<Tema> temaValidator;
    private Validator<Nota> notaValidator;

    private StudentXMLRepository fileRepository1;
    private TemaXMLRepository fileRepository2 ;
    private NotaXMLRepository fileRepository3;

    private Service service;

    @Before
    public void load(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();

        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddStudentWithCorrectUpperBoundGroup() {
        assertEquals(0, service.saveStudent("1", "test", 935));
    }

    @Test
    public void testAddStudentWithIncorrectUpperBound() {
        assertEquals(1, service.saveStudent("1", "bun", 940));
    }

    @Test
    public void testAddStudentWithCorrectLowerBoundGroup() {
        assertEquals(0, service.saveStudent("1", "bun", 120));
    }

    @Test
    public void testAddStudentWithIncorrectLowerBound() {
        assertEquals(1, service.saveStudent("1", "bun", 105));
    }

    @Test
    public void testAddStudentWithCorrectName() {
        assertEquals(0, service.saveStudent("1", "nume bun", 120));
    }

    @Test
    public void testAddStudentWithIncorrectName() {
        assertEquals(1, service.saveStudent("1", "", 105));
        assertEquals(1, service.saveStudent("1", null, 105));
    }

    @Test
    public void testAddStudentWithCorrectID() {
        assertEquals(0, service.saveStudent("1", "nume", 120));
    }

    @Test
    public void testAddStudentWithIncorrectID() {
        assertEquals(1, service.saveStudent("", "nume", 105));
        assertEquals(1, service.saveStudent(null, "nume", 105));
    }
}
