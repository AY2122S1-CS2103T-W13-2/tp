package seedu.address.logic.parser;

import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Remark;

public class RemarkCommandParser implements Parser<RemarkCommand> {

    @Override
    public RemarkCommand parse(String userInput) throws ParseException {
        Objects.requireNonNull(userInput);
        ArgumentMultimap map = ArgumentTokenizer.tokenize(userInput, CliSyntax.PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(map.getPreamble());
        } catch (IllegalValueException e) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), e);
        }

        String remarkString = map.getValue(CliSyntax.PREFIX_REMARK).orElse("");
        Remark remark = new Remark(remarkString);
        return new RemarkCommand(index, remark);
    }

}
