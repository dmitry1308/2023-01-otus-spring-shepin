package ru.otus.shepin.spring.service.reportService;

import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.TestResult;

@Service
public class ReportTestResultService implements ReportService<TestResult> {
    @Override
    public String formReport(TestResult data) {

        String resultData = String.format(
                "\nFail Answer:%s\nRight answer: %s,\n\nHistory questions and answers%s",
                data.getFailAnswer(), data.getRightAnswer(),data.getTestDataList().toString());

        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("-------------------------------------------------------------------------------------\n");
        builder.append("Test Result:");
        builder.append("\n");
        builder.append(resultData);
        return builder.toString();
    }
}
