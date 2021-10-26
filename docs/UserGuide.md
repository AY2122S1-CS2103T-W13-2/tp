---
layout: page
title: User Guide
---

Spam is a desktop app for managing contacts **targeted at marketers who require fast manipulation and precise handling of contact data.** It enables marketers to more easily manage and make use of email and phone leads, and **minimise tedious and repetitive tasks** such as data entry, email blasts, and mail merge.

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a person: `add`](#adding-a-person-add)
  - [Batch importing contacts: `import`](#batch-importing-contacts-import)
  - [Listing all persons: `list`](#listing-all-persons-list)
  - [Editing a person: `edit`](#editing-a-person-edit)
  - [Locating persons by name: `find`](#locating-persons-find)
  - [Deleting a person: `delete`](#deleting-a-person-delete)
  - [Clearing all entries: `clear`](#clearing-all-entries-clear)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
  - [Saving the data](#saving-the-data)
  - [Data encryption](#data-encryption)
- [Controls](#controls)
  - [Navigating Previously Entered Commands](#navigating-previously-entered-commands)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Spam.jar` (Coming Soon).

1. Copy the file to the folder you want to use as the _home folder_ for your Spam.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all contacts.

   - `add -n "John Doe" -p "+659875432" -e "johnd@example.com"` : Adds a contact named `John Doe` to the Address Book.

   - `delete 3` : Deletes the 3rd contact shown in the current list.

   - `clear` : Deletes all contacts.

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

> **:information_source: Notes about the command format:**<br>
>
> All command inputs follows the following format:
>
> `<command> "<parameters>" <flag> "<flag parameters>" ...`
>
> > Examples:
> >
> > `clear`
> >
> > `find --name "John"`
> >
> > `edit "1" --phone "91234567" -e "johndoe@example.com"`
>
> > Note:
> >
> > All flags will have a long version and a short version that can be used. The long version will be prefixed with
> > `--` while the short versions will be prefixed with `-`. (E.g. `--phone` and `-p`)

</div>

### Viewing help: `help`

Displays a window containing this user guide.

![help window](images/helpWindow.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book.

Format: `add (-n/--name) "[NAME]" (-p/--phone) "[PHONE]" (-e/--email) "[EMAIL]" (-a/--address) "[ADDRESS]" (-t/--tag) "[TAG]"`

- At least the name field must be provided.

Examples:

- `add -n "John Doe" -p "6501234567" -e "johndoe@example.com" -a "NUS School of Computing" -t "undergraduate,computer science"` adds a contact with name of `John Doe`, phone number `6501234567`, email `johndoe@example.com`, address `NUS School of Computing`, tags `undergraduate` and `computer science`.
- `add -n "Jane Deer" -t "woman"` adds a contact with the name of `Jane Deer` and tag of `woman`.

### Batch importing contacts: `import`

Imports all contacts from a selected _csv_ file. Calling the command will open a file browser to help select the file.

Format: `import`

Example:

![import window](images/importWindow.png)

1. Select the `.csv` file.
1. Click open to import contacts.

Note:  
- `.csv` file should have corresponding **headers**:
  - name
  - phone(optional)
  - email(optional)
  - address(optional)
  - tags(optional)
- Addresses containing **commas (,)** should be wrapped in **"double quotes"**.
- Multiple tags should be seperated via **semicolons (;)**.
- Make sure to save the spreadsheet data as **`.csv`** and not **`.csv UTF-8`**.
- A template `importTemplate.csv` can be found in the default directory of the file browser.

Valid `.csv` file example:

| name | phone    | email                | address       | tag              |
| -----| -------- | -------------------- | ------------- | ---------------- |
| Adam | 81234567 | adam<span>@test.com  | "ABC, Street" |                  |
| Beth | 620400   | beth123<span>@eg.edu | 123 Drive     |friend            |
| Cody | 90005000 | Cody<span>@test.org  | Oak Lane      | mentor;colleague |

### Listing all persons: `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person: `edit`

Edits an existing person in the address book.

Format: `edit "[INDEX]" (-n/--name) "[NAME]" (-p/--phone) "[PHONE]" (-e/--email) "[EMAIL]" (-a/--address) "[ADDRESS]" (-t/--tag) "[TAG]"`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
- You can remove all the person’s tags by typing `-t` without specifying any tags after it.

Examples:

- `edit "1" -p "91234567" -e "johndoe@example.com"` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `edit "2" -n "Betsy Crower" -t ""` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons: `find`

Finds persons whose respective fields contain any of the respective keywords.

Format: `find (-n/--name) [NAME] (-p/--phone) [PHONE] (-e/--email) [EMAIL] (-a/--address) [ADDRESS] (-t/--tag) [TAG]`

- At least one of the optional fields must be provided.
- The search is case-insensitive. e.g. `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- The order of the optional fields does not matter. e.g. `-n Hans Bo -p 12345678` and `-p 12345678 -n Hans Bo` will return the same result
- Optional fields can be repeated. e.g. `-t friend -t colleagues` returns only persons with tags containing both `friends` and `colleagues`
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword in **EACH** option will be returned (i.e. `OR` search within individual fields).

  e.g. `Hans Bo` will return:

  - `Hans Gruber`
  - `Bo Yang`

- Persons matching **all** given options will be returned (i.e. `AND` search across multiple fields).

  e.g. `-n Hans Bo -p 12345678` will return:

  - `Name: Hans, Phone: 12345678`
  - `Name: Bo, Phone: 12345678`

  But not:

  - `Name: Hans Bo, Phone: 87654321`

Examples:

- `find -n John` returns `john` and `John Doe`
- `find -n alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person: `delete`

Deletes the person of choice from the address book.

Format: `delete "[INDEX]"`

- Deletes the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `delete 1` deletes the first person in the list.

### Clearing all entries: `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program: `exit`

Shuts down and exits the program.

Format: `exit`

### Saving the data

Spam data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<div markdown="block" class="alert alert-info">
:information_source: Spam data are saved as an encrypted file `[JAR file location]/data/addressbook.enc`. It is not intended to be user-modifiable. The contents of the encrypted file are intentionally obfuscated for security.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Manually modifying the encrypted file **will** corrupt the data. Spam will discard all data and start with an empty data file at the next run.
</div>
</div>

### Data encryption

Spam data is protected with the AES-256 encryption standard and is inaccessible to unauthorised users. 

---

## Controls

In order to improve your experience when using the app, there are a few features that were added.

### Navigating Previously Entered Commands

Use the ↑ and ↓ arrow keys to navigate between previously entered commands.

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Spam home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                       |
| ---------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**    | `add (-n/--name) "[NAME]" (-p/--phone) "[PHONE]" ...` <br> e.g., `add -n "James Ho" -p "22224444" -e "jamesho@example.com" -a "123, Clementi Rd, 1234665" -t "friend"` |
| **Clear**  | `clear`                                                                                                                                                                |
| **Delete** | `delete [INDEX]`<br> e.g., `delete 3`                                                                                                                    |
| **Edit**   | `edit "[INDEX]" (-n/--name) "[NAME]" (-p/--phone) "[PHONE]"…​`<br> e.g., `edit "2" -n "James Lee" -e "jameslee@example.com"`                                           |
| **Exit**   | `exit`                                                                                                                                                                 |
| **Find**   | `find (-n/--name) "[NAME]" (-p/--phone) "[PHONE]" (-e/--email) "[EMAIL]" (-a/--address) "[ADDRESS]" (-t/--tag) "[TAG]"`<br> e.g., `find -n "James Jake"`               |
| **Help**   | `help`                                         |
| **Import** | `import`                                                                                                                                                               |
| **List**   | `list`                                                                                                                                                                 |
