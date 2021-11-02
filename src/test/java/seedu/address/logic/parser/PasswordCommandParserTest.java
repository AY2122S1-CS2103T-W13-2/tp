package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_FLAGS;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_INVALID＿PASSWORD;
import static seedu.address.logic.commands.PasswordCommand.MESSAGE_WRONG_PASSWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PasswordCommand;
import seedu.address.testutil.PasswordUtil;

public class PasswordCommandParserTest {
    private PasswordCommandParser parser = new PasswordCommandParser();
    private String validPassword = "Password1234!";
    private String inValidPassword = "";

    @Test
    public void incorrect_formatPasswordReturns_false() {
        // empty password
        assertFalse(PasswordCommandParser.isValidPassword(""));

        // password too short
        assertFalse(PasswordCommandParser.isValidPassword("1232a!"));

        // contain illegal characters
        assertFalse(PasswordCommandParser.isValidPassword("`````sdfdf1212121"));

        // does not contain all three types of characters
        assertFalse(PasswordCommandParser.isValidPassword("gsdjfkhk123123"));
        assertFalse(PasswordCommandParser.isValidPassword("@#%$^@&*dfghj"));
        assertFalse(PasswordCommandParser.isValidPassword("121212121!!!!"));
        assertFalse(PasswordCommandParser.isValidPassword("2132354241412"));
        assertFalse(PasswordCommandParser.isValidPassword("sdfgjbsjkdfsdfkhsdf"));
        assertFalse(PasswordCommandParser.isValidPassword("!@#$%^&*(*&^%$#$%^&"));

        // contains /
        assertFalse(PasswordCommandParser.isValidPassword("password1234/"));

        // valid cases
        assertTrue(PasswordCommandParser.isValidPassword("p1!@#$%&*()_+=|<>?{}~-[]"));

        // valid plus additional characters
        assertTrue(PasswordCommandParser.isValidPassword("password1!@''`"));
    }

    @Test
    public void parse_repeatedFlag_failure() {
        String expectedMessage = MESSAGE_TOO_MANY_FLAGS
                + System.lineSeparator()
                + PasswordCommand.MESSAGE_USAGE;

        assertParseFailure(parser, PasswordUtil.getRepeatedFlag(validPassword, validPassword),
                expectedMessage);
    }

    @Test
    public void parse_wrongPassword_failure() {
        String expectedMessage = MESSAGE_WRONG_PASSWORD;

        assertParseFailure(parser, PasswordUtil.getPasswordCommand(inValidPassword, validPassword),
                expectedMessage);
    }

    @Test
    public void parse_invalidPassword_failure() {
        String expectedMessage = MESSAGE_INVALID＿PASSWORD
                + System.lineSeparator()
                + PasswordCommand.CORRECT_PASSWORD_FORMAT;

        assertParseFailure(parser, PasswordUtil.getPasswordCommand(validPassword, inValidPassword),
                expectedMessage);
    }
}
