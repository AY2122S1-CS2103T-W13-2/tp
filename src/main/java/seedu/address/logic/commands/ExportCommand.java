package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.address.commons.util.CsvWriter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Exports contacts to a csv file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports the contacts in the current displayed list to the exports folder in project directory."
            + System.lineSeparator()
            + "Parameters: FILENAME (Must be a valid filename and be in the csv format)"
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " myContacts.csv";
    public static final String MESSAGE_SUCCESS = "Contacts exported successfully";

    private final String[] fieldHeaders = new String[]{"name", "phone", "email", "address", "tags"};
    private final Map<String, List<String>> data = new HashMap<>();

    private final List<String> names = new ArrayList<>();
    private final List<String> phones = new ArrayList<>();
    private final List<String> emails = new ArrayList<>();
    private final List<String> addresses = new ArrayList<>();
    private final List<String> tags = new ArrayList<>();

    private final String filePath;
    private final CsvWriter csvWriter;

    /**
     * Creates an ExportCommand to add the specified list of {@code Person}.
     */
    public ExportCommand(String filePath, CsvWriter csvWriter) {
        requireNonNull(filePath);
        this.filePath = filePath;
        this.csvWriter = csvWriter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> personList = model.getFilteredPersonList();
        convertFieldsToString(personList);
        populateDataToExport();

        try {
            csvWriter.write(filePath, fieldHeaders, data);
        } catch (IOException e) {
            throw new CommandException("File cannot be written to");
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void convertFieldsToString(List<Person> personList) {
        for (Person p: personList) {
            names.add(p.getName().toString());
            phones.add(p.getPhone().toString());
            emails.add(p.getEmail().toString());
            addresses.add("\"" + p.getAddress().toString() + "\"");

            StringBuilder individualPersonsTagsStrForm = new StringBuilder();
            Set<Tag> individualPersonsTags = p.getTags();
            for (Tag t: individualPersonsTags) {
                String tagStrForm = t.toString();
                individualPersonsTagsStrForm.append(";");
                individualPersonsTagsStrForm.append(tagStrForm, 1, tagStrForm.length() - 1);
            }

            if (individualPersonsTags.size() == 0) {
                tags.add("");
            } else {
                tags.add(individualPersonsTagsStrForm.substring(1));
            }
        }
    }

    private void populateDataToExport() {
        data.put(fieldHeaders[0], names);
        data.put(fieldHeaders[1], phones);
        data.put(fieldHeaders[2], emails);
        data.put(fieldHeaders[3], addresses);
        data.put(fieldHeaders[4], tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && filePath.equals(((ExportCommand) other).filePath));
    }

}
