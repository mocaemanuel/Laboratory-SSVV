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

public class IntegrationTest {

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
    public void testAddCorrectAssignment() {
        assertEquals(0, service.saveTema("1", "Test Description", 6, 4));
    }

    @Test
    public void testAddCorrectStudent() {
        assertEquals(0, service.saveStudent("1", "test", 935));
    }

    @Test
    public void testAddCorrectGrade() {
        assertEquals(0, service.saveNota("1", "1", 9, 7, "Very good"));
    }

    @Test
    public void testAddAllGrade() {
        assertEquals(0, service.saveTema("1", "Test Description", 6, 4));
        assertEquals(0, service.saveStudent("1", "test", 935));
        assertEquals(0, service.saveNota("1", "1", 9, 7, "Very good"));
    }

    @Test
    public void testIitdStudent() {
        assertEquals(0, service.saveStudent("1", "test", 935));
    }

    @Test
    public void testIitdStudAssign() {
        assertEquals(0, service.saveStudent("1", "test", 935));
        assertEquals(0, service.saveTema("1", "Test Description", 6, 4));
    }
    @Test
    public void testIitdStudAssignGrade() {
        assertEquals(0, service.saveStudent("1", "test", 935));
        assertEquals(0, service.saveTema("1", "Test Description", 6, 4));
        assertEquals(0, service.saveNota("1", "1", 9, 7, "Very good"));
    }

}
