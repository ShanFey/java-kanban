import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    protected static TaskManager taskManager = Managers.getDefault();
    protected Task task1,task2;
    protected Epic epic1,epic2;
    protected SubTask subTask1,subTask2,subTask3;
    @BeforeEach
    protected void createTasks() {
        task1 = new Task("Задача1","ОписаниеЗадача1");
        //taskManager.addTask(task1);
        task2 = new Task("Задача2","ОписаниеЗадача2");
        //taskManager.addTask(task2);

        epic1 = new Epic("Эпик1","ОписаниеЭпика1");
        //taskManager.addEpic(epic1);
        epic2 = new Epic("Эпик2","ОписаниеЭпика2");
        //taskManager.addEpic(epic2);

        subTask1 = new SubTask("Подзадача1","ОписаниеПодзадачи1",epic1);
        //taskManager.addSubTask(subTask1);
        subTask2 = new SubTask("Подзадача2","ОписаниеПодзадачи2",epic2);
        //taskManager.addSubTask(subTask2);
        subTask3 = new SubTask("Подзадача3","ОписаниеПодзадачи3",epic2);
        //taskManager.addSubTask(subTask3);
    }
    @Test
    void addNewTask() {
        taskManager.addTask(task1);

        Task currentTask = taskManager.getTask(task1.taskId);
        assertNotNull(currentTask, "Задача не найдена!");
        assertEquals(task1,currentTask,"Задачи не совпадают!");

        int currentId = task1.taskId;
        taskManager.addTask(task1);
        assertNotEquals(task1.taskId,currentId,"При внесении задачи ИД не перезаписывается");
    }
    @Test
    void addNewEpic() {
        taskManager.addEpic(epic1);

        Task currentEpic = taskManager.getEpic(epic1.taskId);
        assertNotNull(currentEpic, "Эпик не найден!");
        assertEquals(epic1,currentEpic,"Эпики не совпадают!");
    }
    @Test
    void addNewSubTask() {
        taskManager.addSubTask(subTask1);

        Task currentSubTask = taskManager.getSubTask(subTask1.taskId);
        assertNotNull(currentSubTask, "Подзадача не найдена!");
        assertEquals(subTask1,currentSubTask,"Подзадача не совпадают!");
    }

    @Test
    void getTask() {
        taskManager.addTask(task1);
        Task currentTask = taskManager.getTask(task1.taskId);
        assertNotNull(currentTask, "Получение задачи по ИД не выполняется!");
    }

    @Test
    void getEpic() {
        taskManager.addEpic(epic1);
        Epic currentEpic = taskManager.getEpic(epic1.taskId);
        assertNotNull(currentEpic, "Получение эпика по ИД не выполняется!");
    }

    @Test
    void getSubTask() {
        taskManager.addSubTask(subTask1);
        SubTask currentSubTask = taskManager.getSubTask(subTask1.taskId);
        assertNotNull(currentSubTask, "Получение подзадачи по ИД не выполняется!");
    }

    @Test
    void getTasksList() {
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        List taskList = taskManager.getTasksList();
        assertNotNull(taskList,"После добавления задачи - список пуст!");
        assertEquals(2,taskList.size(),"Некорректное значение списка задач!");
    }

    @Test
    void getEpicsList() {
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        List epicList = taskManager.getEpicsList();
        assertNotNull(epicList,"После добавления эпика список пуст!");
        assertEquals(2,epicList.size(),"Некорректное значение списка эпиков!");
    }

    @Test
    void getSubTaskList() {
        taskManager.addSubTask(subTask1);
        taskManager.addSubTask(subTask2);
        List subTaskList = taskManager.getSubTaskList();
        assertNotNull(subTaskList,"После добавления подзадачи - список пуст!");
        assertEquals(2,subTaskList.size(),"Некорректное значение списка подзадач!");
    }

    @Test
    void getEpicSubTasksList() {
        taskManager.addEpic(epic2);
        taskManager.addSubTask(subTask2);
        taskManager.addSubTask(subTask3);
        List subTaskListByEpic = taskManager.getEpicSubTasksList(epic2);
        assertNotNull(subTaskListByEpic,"После добавления подзадачи - список пуст!");
        assertEquals(2,subTaskListByEpic.size(),"Некорректное значение списка подзадач!");
    }

    @Test
    void updateTask() {
        taskManager.addTask(task1);
        Task currentTask = taskManager.getTask(task1.taskId);
        assertNotNull(currentTask, "Задача не ,была добавлена!");
        task1.taskStatus = TaskStatus.IN_PROGRESS;
        taskManager.updateTask(task1);
        currentTask = taskManager.getTask(task1.taskId);
        assertEquals(TaskStatus.IN_PROGRESS,currentTask.taskStatus,"Не удалось присвоить новый статус задаче!");
    }

    @Test
    void updateEpic() {
        taskManager.addEpic(epic1);
        Task currentEpic = taskManager.getEpic(epic1.taskId);
        assertNotNull(currentEpic, "Эпик не был добавлен!");
        taskManager.addSubTask(subTask1);
        subTask1.taskStatus = TaskStatus.IN_PROGRESS;
        taskManager.updateSubTask(subTask1);
        taskManager.updateEpic(epic1);
        currentEpic = taskManager.getEpic(epic1.taskId);
        assertEquals(TaskStatus.IN_PROGRESS,currentEpic.taskStatus,"Эпик не получил новый статус!");
    }

    @Test
    void updateSubTask() {
        taskManager.addSubTask(subTask1);
        Task currentSubTask = taskManager.getSubTask(subTask1.taskId);
        assertNotNull(currentSubTask, "Подзадача не была добавлена!");
        subTask1.taskStatus = TaskStatus.IN_PROGRESS;
        taskManager.updateSubTask(subTask1);
        currentSubTask = taskManager.getSubTask(subTask1.taskId);
        assertEquals(TaskStatus.IN_PROGRESS,currentSubTask.taskStatus,"Не удалось присвоить новый статус подзадаче!");
    }

    @Test
    void removeTaskById() {
        taskManager.addTask(task1);
        taskManager.removeTaskById(task1.taskId);
        Task currentTask = taskManager.getTask(task1.taskId);
        assertNull(currentTask, "Задача по ИД не удаляется!");
    }

    @Test
    void removeEpicById() {
        taskManager.addEpic(epic1);
        taskManager.addSubTask(subTask1);
        taskManager.removeEpicById(epic1.taskId);
        Task currentSubTask = taskManager.getSubTask(subTask1.taskId);
        Task currentEpic = taskManager.getEpic(epic1.taskId);
        assertNull(currentSubTask, "После удаления эпика остаются подзадачи!");
        assertNull(currentEpic, "Эпик не удаляется!");
    }

    @Test
    void removeSubTaskById() {
        taskManager.addSubTask(subTask1);
        taskManager.removeSubTaskById(subTask1.taskId);
        Task currentSubTask = taskManager.getSubTask(subTask1.taskId);
        assertNull(currentSubTask, "Подзадача по ИД не удаляется!");
    }

    @Test
    void checkEpicStatus() {
        taskManager.addEpic(epic1);
        taskManager.addSubTask(subTask1);
        subTask1.taskStatus = TaskStatus.IN_PROGRESS;
        taskManager.updateSubTask(subTask1);
        taskManager.updateEpic(epic1);
        taskManager.checkEpicStatus(epic1);
        assertEquals(TaskStatus.IN_PROGRESS,epic1.taskStatus,"Эпик не получил новый статус!");
    }

    @Test
    void clearAllTask() {
        taskManager.addTask(task1);
        taskManager.clearAllTask();
        Task currentTask = taskManager.getTask(task1.taskId);
        assertNull(currentTask,"Полное удаление задач не работает!");
    }

    @Test
    void clearAllEpic() {
        taskManager.addEpic(epic1);
        taskManager.clearAllEpic();
        Task currentEpic = taskManager.getEpic(epic1.taskId);
        assertNull(currentEpic,"Полное удаление эпиков не работает!");
    }

    @Test
    void clearAllSubTask() {
        taskManager.addSubTask(subTask1);
        taskManager.clearAllSubTask();
        Task currentSubTask = taskManager.getSubTask(subTask1.taskId);
        assertNull(currentSubTask,"Полное удаление подзадач не работает!");
    }
}