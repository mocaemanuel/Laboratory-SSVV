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

public class AssignmentTest {


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
        assertEquals(0, service.saveTema("1", "Test Description", 5, 4));
    }

    @Test
    public void testAddIncorrectAssignment() {
        assertEquals(1, service.saveTema("1", "Test Description", 4, 5));
    }

    @Test
    public void testAddIncorrectDeadlineAssignment() {
        assertEquals(1, service.saveTema("1", "Test Description", 0, 5));
        assertEquals(1, service.saveTema("1", "Test Description", 19, 5));
    }

    @Test
    public void testAddIncorrectStartlineAssignment() {
        assertEquals(1, service.saveTema("1", "Test Description", 5, 0));
        assertEquals(1, service.saveTema("1", "Test Description", 5, 19));
    }

    @Test
    public void testAddIncorrectStartWRTDeadAssig() {
        assertEquals(1, service.saveTema("1", "Test Description", 5, 7));
    }

    @Test
    public void testAddIncorrectDescAssignment() {
        assertEquals(1, service.saveTema("1", "", 5, 7));
    }

    @Test
    public void testAddIncorrectDescNullAssig() {
        assertEquals(1, service.saveTema("1", null, 5, 7));
    }

    @Test
    public void testAddCorrectDescAssig() {
        assertEquals(1, service.saveTema("1", "Test Description", 5, 7));
    }


    @Test
    public void testAddIncorrectIDAssig() {
        assertEquals(1, service.saveTema("", "Test Description", 5, 7));
    }

    @Test
    public void testAddIncorrectIDNullAssig() {
        assertEquals(1, service.saveTema(null, "Test Description", 5, 7));
    }

    @Test
    public void testAddCorrectIDAssignment() {
        assertEquals(1, service.saveTema("1", "Test Description", 5, 7));
    }
}
