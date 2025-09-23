import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final TaskManager taskManager = Managers.getDefault();

        System.out.println("Создаем задачи");
        taskManager.addTask(new Task("Повторение", "Повторить материал для выполнения задания"));
        taskManager.addTask(new Task("Изучение ТЗ", "Вникнуть в техзадание"));


        System.out.println("Создаем эпик");
        Epic currentEpic = new Epic("Реализация проекта", "Подумать над реализацией");
        taskManager.addEpic(currentEpic);

        System.out.println("Создаем подзадачи " + currentEpic.taskName);
        SubTask currentSubTask = new SubTask("Классы", "Обдумываем классы", currentEpic);
        taskManager.addSubTask(currentSubTask);
        currentSubTask = new SubTask("Наполняем", "Реализуем классы", currentEpic);
        taskManager.addSubTask(currentSubTask);


        System.out.println("Создаем эпик");
        currentEpic = new Epic("Работа над Спринтом 5", "Решение задач С5");
        taskManager.addEpic(currentEpic);

        System.out.println("Создаем подзадачи " + currentEpic.taskName);
        currentSubTask = new SubTask("Урок 2_5", "Абстракция и полиморфизм", currentEpic);
        taskManager.addSubTask(currentSubTask);
        currentSubTask = new SubTask("Урок 3_5", "Дженерики", currentEpic);
        taskManager.addSubTask(currentSubTask);
        currentSubTask = new SubTask("Урок 4_5", "Unit-тесты", currentEpic);
        taskManager.addSubTask(currentSubTask);

        System.out.println("Список задач:");
        System.out.println(taskManager.getTasksList());
        System.out.println("Список эпиков:");
        System.out.println(taskManager.getEpicsList());
        System.out.println("Список подзадач:");
        System.out.println(taskManager.getSubTaskList());

        System.out.println("Задача по ИД:");
        int taskID = scanner.nextInt();
        Task currentTask = taskManager.getTask(taskID);
        System.out.println(currentTask);

        currentTask.taskDiscription += "!";
        taskManager.updateTask(currentTask);
        System.out.println("Задача после обновления:");
        System.out.println(currentTask);

        // проверка статуса эпика
        for (Object obj: taskManager.getEpicsList()) {
            Epic epic = (Epic) obj;
            System.out.println("Эпик:" + epic.taskName + " статус " + epic.taskStatus);
        }
        // меняем статус подзадачи
        currentSubTask = taskManager.getSubTask(4);
        currentSubTask.taskStatus = TaskStatus.DONE;
        taskManager.updateSubTask(currentSubTask);
        System.out.println("После изменения статуса эпика...");
        for (Object obj: taskManager.getEpicsList()) {
            Epic epic = (Epic) obj;
            System.out.println("Эпик:" + epic.taskName + " статус " + epic.taskStatus);
        }

        System.out.println("Список подзадач эпика:");
        System.out.println(taskManager.getEpicSubTasksList(currentEpic));

        // 09/2025
        System.out.println("История просмотра:");
        taskManager.getSubTask(3);
        taskManager.getEpic(2);
        taskManager.getEpic(2);
        taskManager.getTask(0);
        taskManager.getTask(2);
        taskManager.getEpic(2);
        taskManager.getEpic(2);
        taskManager.getSubTask(3);
        taskManager.getEpic(2);
        taskManager.getEpic(2);
        taskManager.getEpic(2);
        taskManager.getEpic(2);
        taskManager.getEpic(2);

        System.out.println("Проверка удаления...");
        System.out.println("Список задач:");
        taskManager.clearAllTask();
        System.out.println(taskManager.getTasksList());
        System.out.println("Список эпиков:");
        taskManager.clearAllEpic();
        System.out.println(taskManager.getEpicsList());
        System.out.println("Список подзадач:");
        taskManager.clearAllSubTask();
        System.out.println(taskManager.getSubTaskList());

        System.out.println("История задач:");
        for (Task task:taskManager.getHistoryManager().getHistory()) {
            System.out.println(task);
        }

    }
}
