package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Email;
import seedu.address.model.task.Phone;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Deletes a specific field in a task identified using it's displayed index from the planner.
 */
public class DeleteFieldCommand extends Command {
    public static final String COMMAND_WORD = "delete-field";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified field in the task identified by the index number "
            + "used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "]"
            + "[" + PREFIX_DESCRIPTION + "]"
            + "[" + PREFIX_PHONE + "]"
            + "[" + PREFIX_TAG + "]... \n"
            + "Exactly one prefix is to be specified.\n"
            + "Example: " + COMMAND_WORD + " 1" + " tags/";

    public static final String MESSAGE_DELETE_FIELD_SUCCESS = "Deleted Field in Task: %1$s";

    private final Index targetIndex;

    private final Prefix targetField;

    /**
     * @param targetIndex of the task in the filtered task list to delete the field from
     * @param targetField the field user wants to delete
     */
    public DeleteFieldCommand(Index targetIndex, String targetField) {
        requireNonNull(targetIndex);
        requireNonNull(targetField);

        this.targetIndex = targetIndex;
        this.targetField = new Prefix(targetField);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDeleteFieldFrom = lastShownList.get(targetIndex.getZeroBased());
        Task taskWithFieldDeleted = deleteFieldFromTask(taskToDeleteFieldFrom, targetField);

        model.setTask(taskToDeleteFieldFrom, taskWithFieldDeleted);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS, taskWithFieldDeleted));
    }

    /**
     * Creates and returns a {@code Task} with the {@code field}
     * deleted from {@code taskToDeleteFieldFrom}.
     */
    private static Task deleteFieldFromTask(Task taskToDeleteFieldFrom, Prefix field) throws CommandException {
        assert taskToDeleteFieldFrom != null;

        Title title = taskToDeleteFieldFrom.getTitle();
        Phone oldPhone = taskToDeleteFieldFrom.getPhone();
        Email oldEmail = taskToDeleteFieldFrom.getEmail();
        Description oldDescription = taskToDeleteFieldFrom.getDescription();
        Set<Tag> oldTags = taskToDeleteFieldFrom.getTags();

        if (field.equals(PREFIX_TITLE)) {
            throw new CommandException("Cannot delete title field.");
        } else if (field.equals(PREFIX_PHONE)) { //not implemented
            return new Task(title, oldPhone, oldEmail, oldDescription, oldTags);
        } else if (field.equals(PREFIX_EMAIL)) { //not implemented
            return new Task(title, oldPhone, oldEmail, oldDescription, oldTags);
        } else if (field.equals(PREFIX_DESCRIPTION)) {
            Description updatedDescription = new Description("");
            return new Task(title, oldPhone, oldEmail, updatedDescription, oldTags);
        } else if (field.equals(PREFIX_TAG)) {
            Set<Tag> updatedTags = new HashSet<>();
            return new Task(title, oldPhone, oldEmail, oldDescription, updatedTags);
        } else {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteFieldCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteFieldCommand) other).targetIndex)) // state check
                && targetField.equals(((DeleteFieldCommand) other).targetField);
    }
}
