# MyChatBot

Welcome to **MyChatBot**!  
This guide will help you use all the important features of this task management chatbot.

---

## Setup Instructions
1. Download the .jar file [here](https://github.com/yhthia/ip/releases/tag/A-Release) and save it to a folder.
2. Open your local terminal and navigate to the folder which you saved the .jar file in and run the following command:
```
java -jar mychatbot.jar
```

## Features

### Notes about command format

- Words in **UPPER_CASE** are parameters to be supplied by you.  
  Example:  
  `deadline DESCRIPTION /by DATETIME`  
  Here, DESCRIPTION and DATETIME are parameters.

- **Extraneous parameters** for commands that do not take in parameters are ignored.

---

## Task Types

You can add three types of tasks:

- **Todo**: General task without any date/time.
- **Deadline**: Task with a specific due date and time.
- **Event**: Task that will happen at a specific date and time.

---

## Commands

### 1. Add a Todo task

**Format:**  
`todo DESCRIPTION`

**Example:**  
`todo Buy groceries`

---

### 2. Add a Deadline task

**Format:**  
`deadline DESCRIPTION /by DATETIME`

**DATETIME format:** `d/M/yyyy HHmm`  
(e.g. `20/9/2025 1600` for 20 Sep 2025, 4:00pm)

**Example:**  
`deadline Submit report /by 20/9/2025 1600`

---

### 3. Add an Event task

**Format:**  
`event DESCRIPTION /from DATE_TIME /to DATE_TIME`

**DATETIME format:** `d/M/yyyy HHmm`

**Example:**  
`event Team meeting /from 18/9/2025 1400 /to 18/9/2025 1600`

---

### 4. List all tasks

**Format:**  
`list`

Shows all tasks.

---

### 5. Sort tasks chronologically

**Format:**  
`sort`

Shows all tasks sorted by date (events and deadlines first, with todos at the bottom).

---

### 6. Find tasks by keyword

**Format:**  
`find KEYWORD [MORE_KEYWORDS]...`

**Example:**  
`find report meeting`

Shows all tasks that match any keyword. This is case-insensitive.

---

### 7. Mark a task as done

**Format:**  
`mark TASK_INDEX`

Marks the specified task (by list number) as completed.

**Example:**  
`mark 2`

---

### 8. Mark a task as undone

**Format:**  
`unmark TASK_INDEX`

Marks the specified task (by list number) as incomplete.

**Example:**  
`unmark 2`

---

## Example Usage

```
todo Buy groceries
deadline Submit report /by 20/9/2025 1600
event Team meeting /from 18/9/2025 1400 /to 18/9/2025 1600
list
sort
find report
mark 2 
unmark 2
```

---

## Tips

- Always use the specified datetime format for deadlines and events.
- You can use multiple keywords with `find` to search more effectively.
- All commands are case-insensitive.

---

## Saving Data

- Data is saved automatically. No manual saving needed.

---

Enjoy using **MyChatBot** to manage your tasks!
