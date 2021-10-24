package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CsvWriter;
import seedu.address.commons.util.CsvWriterTest;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.Model;
import seedu.address.testutil.ModelStubProvidingValidFilteredListAllFields;
import seedu.address.testutil.ModelStubProvidingValidFilteredListNamesOnly;

public class ExportCommandIntegrationTest {

    @Disabled
    @Test
    public void execute_exportCommand_success() throws Exception {
        ExportCommand command = new ExportCommand(CsvWriterTest.TESTED_FILE_PATH, new CsvWriter());
        Model model = new ModelStubProvidingValidFilteredListAllFields();
        command.execute(model);
        assertTrue(FileUtil.areFilesEqual(Paths.get(CsvWriterTest.TESTED_FILE_PATH),
                Paths.get(CsvWriterTest.EXPECTED_FILE_PATH_ALL_FIELDS)));
    }

    @Disabled
    @Test
    public void execute_exportCommandNameOnly_success() throws Exception {
        ExportCommand command = new ExportCommand(CsvWriterTest.TESTED_FILE_PATH, new CsvWriter());
        Model model = new ModelStubProvidingValidFilteredListNamesOnly();
        command.execute(model);
        assertTrue(FileUtil.areFilesEqual(Paths.get(CsvWriterTest.TESTED_FILE_PATH),
                Paths.get(CsvWriterTest.EXPECTED_FILE_PATH_NAMES_ONLY)));
    }
}
