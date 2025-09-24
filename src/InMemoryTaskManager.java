import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    public int taskId = 0;
    public Map<Integer,Task> taskMap = new HashMap<>();
    public Map<Integer,Epic> epicMap = new HashMap<>();
    public Map<Integer,SubTask> subTaskMap = new HashMap<>();
    final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public void addTask(Task newTask) {
        newTask.taskId = increaseTaskId();
        taskMap.put(newTask.taskId,newTask);
    }
    @Override
    public void addEpic(Epic newEpic) {
        newEpic.taskId = increaseTaskId();
        epicMap.put(newEpic.taskId,newEpic);
     }
    @Override
    public void addSubTask(SubTask newSubTask) {
        int subTaskId = increaseTaskId();
        newSubTask.taskId = subTaskId;
        Epic currentEpic = newSubTask.parentEpic;
        newSubTask.parentEpic.subTaskMap.put(subTaskId, newSubTask);
        subTaskMap.put(subTaskId,newSubTask);
        checkEpicStatus(currentEpic);
     }

    @Override
    public Task getTask(int taskId) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }
    @Override
    public Epic getEpic(int epicId) {
        Epic epic = epicMap.get(epicId);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }
    @Override
    public SubTask getSubTask(int subTaskId) {
        SubTask subTask = subTaskMap.get(subTaskId);
        if (subTask != null) {
            historyManager.add(subTask);
        }
        return subTask;
    }

    @Override
    public Integer getId(Task currentTask) {
        return currentTask.taskId;
    }

    @Override
    public List getTasksList() {
        return new ArrayList<>(taskMap.values());
    }
    @Override
    public List getEpicsList() {
        return new ArrayList<>(epicMap.values());
    }
    @Override
    public List getSubTaskList() {
        return new ArrayList<>(subTaskMap.values());
    }

    @Override
    public List getEpicSubTasksList(Epic currentEpic) {
        return new ArrayList<>(currentEpic.subTaskMap.values());
    }
    @Override
    public void updateTask(Task newVersionTask){
        taskMap.put(newVersionTask.taskId,newVersionTask);
    }
    @Override
    public void updateEpic(Epic newVersionEpic){
        epicMap.put(newVersionEpic.taskId,newVersionEpic);
    }
    @Override
    public void updateSubTask(SubTask newVersionSubTask){
        subTaskMap.put(newVersionSubTask.taskId,newVersionSubTask);
        checkEpicStatus(newVersionSubTask.parentEpic);
    }

    @Override
    public void removeTaskById(int taskId) {
        taskMap.remove(taskId);
    }
    @Override
    public void removeEpicById(int epicId) {
        List<SubTask> epicSubTaskList = getEpicSubTasksList(getEpic(epicId));
        for (SubTask currentSubTask: epicSubTaskList) {
            removeSubTaskById(currentSubTask.taskId);
        }
        epicMap.remove(epicId);
    }
    @Override
    public void removeSubTaskById(int subTaskId) {
        Epic currentEpic = subTaskMap.get(subTaskId).parentEpic;
        subTaskMap.remove(subTaskId);
        checkEpicStatus(currentEpic);
    }

    @Override
    public void checkEpicStatus(Epic parentEpic) {
        int quantityNew = 0;
        int quantityDone = 0;
        HashMap<Integer,SubTask> subTasks = parentEpic.subTaskMap;
        for (SubTask subTask: subTasks.values()) {
            if (subTask.taskStatus.equals(TaskStatus.NEW)) {
                quantityNew++;
            } else if (subTask.taskStatus.equals(TaskStatus.DONE)) {
                quantityDone++;
            }
        }
         if (subTasks.size() == quantityNew || subTasks.isEmpty()) {
             parentEpic.setStatus(TaskStatus.NEW);
        } else if (subTasks.size() == quantityDone) {
             parentEpic.setStatus(TaskStatus.DONE);
        } else {
             parentEpic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    @Override
    public void clearAllTask() {taskMap.clear();}
    @Override
    public void clearAllEpic() {
        subTaskMap.clear();
        epicMap.clear();}
    @Override
    public void clearAllSubTask() {subTaskMap.clear();}

    private int increaseTaskId() {return taskId++;}

    @Override
    public HistoryManager getHistoryManager() {
        return historyManager;
    }
}
