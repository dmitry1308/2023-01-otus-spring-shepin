package ru.otus.shepin.spring.service.importDataService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.shepin.spring.entity.TestData;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestImportServiceFileTest extends TestImportServiceFile {
    public static final String QUESTIONS_CSV = "Questions.csv";
    private TestImportServiceFile importServiceFile;
    private static List<String> fullListLikeInFile;
    private static List<String> questionList;
    private static List<String> answerList;

    public TestImportServiceFileTest() {
        super(QUESTIONS_CSV);
    }

    @BeforeAll
    static void beforeAll() {
        String line1 = "What is the biggest planet in the solar system?, Jupiter";
        String line2 = "How million people live in Russia?,143";
        String line3 = "How many data types in Java?,8";

        fullListLikeInFile = Arrays.asList(line1, line2, line3);

        questionList = List.of("What is the biggest planet in the solar system?",
                "How million people live in Russia?",
                "How many data types in Java?");

        answerList = List.of("Jupiter", "143", "8");
    }

    @BeforeEach
    void setUp() {
        importServiceFile = new TestImportServiceFile(QUESTIONS_CSV);
    }

    @Test
    void when_call_method_importData_return_not_empty_list() throws IOException {
        List<TestData> testData = importServiceFile.importData();
        assertFalse(testData.isEmpty());
    }

    @Test
    void when_call_method_convertLinesToData_should_return_count_3() {
        List<TestData> testDataList = importServiceFile.convertLinesToData(fullListLikeInFile);
        assertEquals(3, testDataList.size());
    }

    @Test
    void when_call_method_convertLinesToData_should_return_right_questions_and_answer() {
        List<TestData> testDataList = importServiceFile.convertLinesToData(fullListLikeInFile);
        for (int i = 0; i < testDataList.size(); i++) {
            TestData testData = testDataList.get(i);

            String question = questionList.get(i);
            assertEquals(testData.getQuestion(), question);

            String answer = answerList.get(i);
            assertEquals(testData.getRightAnswer(), answer);
        }
    }

    @Test
    void read_count_lines_test() throws IOException {
        List<String> lines = importServiceFile.getLines();
        assertEquals(lines.size(), 5);
    }
}