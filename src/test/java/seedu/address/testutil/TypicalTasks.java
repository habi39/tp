package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Planner;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withTitle("Alice Pauline")
            .withDescription("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withTitle("Benson Meier")
            .withDescription("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withTitle("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withDescription("wall street").build();
    public static final Task DANIEL = new TaskBuilder().withTitle("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withDescription("10th street").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withTitle("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withDescription("michegan ave").build();
    public static final Task FIONA = new TaskBuilder().withTitle("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withDescription("little tokyo").build();
    public static final Task GEORGE = new TaskBuilder().withTitle("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withDescription("4th street").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withTitle("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withDescription("little india").build();
    public static final Task IDA = new TaskBuilder().withTitle("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withDescription("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withTitle(VALID_TITLE_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withDescription(VALID_DESCRIPTION_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withTitle(VALID_TITLE_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withDescription(VALID_DESCRIPTION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code Planner} with all the typical tasks.
     */
    public static Planner getTypicalPlanner() {
        Planner ab = new Planner();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
