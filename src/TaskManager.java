import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    public int taskId;
    public HashMap<Integer,Task> taskMap;
    public HashMap<Integer,Epic> epicMap;
    public HashMap<Integer,SubTask> subTaskMap;

    public TaskManager() {
        taskMap = new HashMap<>();
        epicMap = new HashMap<>();
        subTaskMap = new HashMap<>();
        taskId = 0;
    }

    public void addTask(Task newTask) {
        newTask.taskId = increaseTaskId();
        taskMap.put(newTask.taskId,newTask);
    }
    public void addEpic(Epic newEpic) {
        newEpic.taskId = increaseTaskId();
        epicMap.put(newEpic.taskId,newEpic);
     }
    public void addSubTask(SubTask newSubTask, Epic currentEpic) {
        int subTaskId = increaseTaskId();
        newSubTask.taskId = subTaskId;
        currentEpic.subTaskMap.put(subTaskId, newSubTask);
        subTaskMap.put(subTaskId,newSubTask);
        checkEpicStatus(currentEpic);
     }

    public Task getTask(int taskID) {
        return taskMap.get(taskID);
    }
    public Epic getEpic(int epicID) {
        return epicMap.get(epicID);
    }
    public SubTask getSubTask(int subTaskID) {
        return subTaskMap.get(subTaskID);
    }

    public ArrayList getTasksList() {
        return new ArrayList<>(taskMap.values());
    }
    public ArrayList getEpicsList() {
        return new ArrayList<>(epicMap.values());
    }
    public ArrayList getSubTaskList() {
        return new ArrayList<>(subTaskMap.values());
    }

    public ArrayList getEpicSubTasksList(Epic currentEpic) {
        return new ArrayList<>(currentEpic.subTaskMap.values());
    }
    public void updateTask(Task newVersionTask){
        taskMap.put(newVersionTask.taskId,newVersionTask);
    }
    public void updateEpic(Epic newVersionEpic){
        epicMap.put(newVersionEpic.taskId,newVersionEpic);
    }
    public void updateSubTask(SubTask newVersionSubTask){
        subTaskMap.put(newVersionSubTask.taskId,newVersionSubTask);
        checkEpicStatus(newVersionSubTask.parentEpic);
    }

    public void removeTaskById(int taskId) {
        taskMap.remove(taskId);
    }
    public void removeEpicById(int epicId) {
        epicMap.remove(epicId);
    }
    public void removeSubTaskById(int subTaskId) {
        Epic currentEpic = subTaskMap.get(subTaskId).parentEpic;
        subTaskMap.remove(subTaskId);
        checkEpicStatus(currentEpic);
    }

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

    public void clearAllTask() {taskMap.clear();}
    public void clearAllEpic() {epicMap.clear();}
    public void clearAllSubTask() {subTaskMap.clear();}

    private int increaseTaskId() {return taskId++;}

}
