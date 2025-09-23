import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    protected static HistoryManager historyManager = Managers.getDefaultHistory();
    protected Task task1,task2;
    protected Epic epic1,epic2;
    protected SubTask subTask1,subTask2,subTask3;
    @BeforeEach
    protected void createTasks() {
        task1 = new Task("Задача1","ОписаниеЗадача1");
        task2 = new Task("Задача2","ОписаниеЗадача2");

        epic1 = new Epic("Эпик1","ОписаниеЭпика1");
        epic2 = new Epic("Эпик2","ОписаниеЭпика2");

        subTask1 = new SubTask("Подзадача1","ОписаниеПодзадачи1",epic1);
        subTask2 = new SubTask("Подзадача2","ОписаниеПодзадачи2",epic2);
        subTask3 = new SubTask("Подзадача3","ОписаниеПодзадачи3",epic2);
    }
    @Test
    void add() {
        historyManager.add(task1);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая");
        assertNotEquals(0, history.size(), "Нет эелементов в списке истории");
        assertEquals(0, task1.taskId, "История сохранена неверно");

    }

    @Test
    void getHistory() {
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);

        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);

        historyManager.add(task1);
        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "История формируется на более 10 элементов");
    }


}