import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    protected Managers manager;
    @BeforeEach
    protected void createManagers() {
        manager = new Managers();
    }

    @Test
    void getDefault() {
        assertEquals(manager.getDefault().getClass(),new InMemoryTaskManager().getClass(),"Не удалось создать InMemoryTaskManager");
    }

    @Test
    void getDefaultHistory() {
        assertEquals(manager.getDefaultHistory().getClass(),new InMemoryHistoryManager().getClass(),"Не удалось создать InMemoryHistoryManager");
    }
}