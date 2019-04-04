package app.todo.service;

import app.todo.TodoStatus;
import app.todo.dao.DependentDao;
import app.todo.dao.TodoDao;
import app.todo.dao.TodoListDao;
import app.todo.dto.base.BaseDto;
import app.todo.dto.base.ListDto;
import app.todo.dto.base.ObjectDto;
import app.todo.model.Dependent;
import app.todo.model.Todo;
import app.todo.model.TodoList;
import app.todo.model.request.TodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired TodoDao todoDao;
    @Autowired
    TodoListDao todoListDao;
    @Autowired
    DependentDao dependentDao;


    public BaseDto getAllTodosByTodoListId(String todoListId,String sortBy, String direction,String name,String todoStatus) {
        BaseDto baseDto;
        List<Todo> todoList;

        try {
            if(!name.equals("null") && !todoStatus.equals("null"))
                todoList = this.todoDao.findByAndSort(UUID.fromString(todoListId),name, TodoStatus.valueOf(todoStatus), Sort.by(Sort.Direction.fromString(direction),sortBy));
            else if(name.equals("null") && !todoStatus.equals("null"))
                todoList = this.todoDao.findByTodoStatusAndSort(UUID.fromString(todoListId),TodoStatus.valueOf(todoStatus), Sort.by(Sort.Direction.fromString(direction),sortBy));
            else if(!name.equals("null"))
                todoList = this.todoDao.findByNameAndSort(UUID.fromString(todoListId),name,Sort.by(Sort.Direction.fromString(direction),sortBy));
            else
                todoList = this.todoDao.findAllByTodoListIdAndSort(UUID.fromString(todoListId),Sort.by(Sort.Direction.fromString(direction),sortBy));

            baseDto = new ListDto<>(todoList);
            baseDto.setReturnWithMessageResponseStats("All Todo Items Successfully Listed", true);

        } catch (Exception ex) {
            baseDto = new BaseDto();
            baseDto.setReturnWithMessageResponseStats(ex.getMessage(), false);
        }

        return baseDto;
    }
    public BaseDto getDetailOfTodo(String todoId) {
        BaseDto baseDto;

        try {

            Todo todo = this.todoDao.findById(UUID.fromString(todoId));



            baseDto = new ObjectDto<>(todo);
            baseDto.setReturnWithMessageResponseStats("Detail Of Todo Has Been Set", true);

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
            baseDto = new BaseDto(ex.getMessage(), false);
        }

        return baseDto;
    }

    public BaseDto createTodo(TodoRequest todoRequest) {
        BaseDto baseDto = new BaseDto();

        try {
            TodoList todoList = todoListDao.findById(UUID.fromString(todoRequest.getTodoListId()));
            TodoStatus todoStatus = TodoStatus.valueOf(todoRequest.getTodoStatus());
            Todo todo = new Todo();
            todo.setName(todoRequest.getName());
            todo.setDescription(todoRequest.getDescription());
            todo.setTodoStatus(todoStatus);
            todo.setEndDate(LocalDate.parse(todoRequest.getEndDate()));
            todo.setCreatedDate(new Date());
            todo.setTodoList(todoList);

            if(todoRequest.getDependentTodo() != null) {
                Todo dependent = todoDao.findById(UUID.fromString(todoRequest.getDependentTodo()));
                    if (dependent.getTodoStatus() != TodoStatus.COMPLETE && todo.getTodoStatus() == TodoStatus.COMPLETE) {
                        baseDto.setReturnWithMessageResponseStats("Dependent Todo Still Incompleted", false);
                        return baseDto;
                    }
                    else {
                        Dependent dependentTo = new Dependent();
                        dependentTo.setDependentTodo(dependent);
                        dependentDao.save(dependentTo);
                        todo.setDependentTodo(dependentTo);
                    }
            }

            this.todoDao.save(todo);

            baseDto.setReturnWithMessageResponseStats("Todo Successfully Created", true);
        } catch (Exception ex) {
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
        }

        return baseDto;
    }


    public BaseDto updateTodo(TodoRequest todoRequest) {
        BaseDto baseDto = new BaseDto();

        try {

            TodoList todoList = todoListDao.findById(UUID.fromString(todoRequest.getTodoListId()));
            TodoStatus todoStatus = TodoStatus.valueOf(todoRequest.getTodoStatus());
            Todo todo = this.todoDao.findById(UUID.fromString(todoRequest.getId()));
            todo.setName(todoRequest.getName());
            todo.setDescription(todoRequest.getDescription());
            todo.setTodoStatus(todoStatus);
            todo.setEndDate(LocalDate.parse(todoRequest.getEndDate()));
            todo.setCreatedDate(new Date());
            todo.setTodoList(todoList);

            if(todoRequest.getDependentTodo() != null) {
                Todo dependent = todoDao.findById(UUID.fromString(todoRequest.getDependentTodo()));
                    if (dependent.getTodoStatus() != TodoStatus.COMPLETE && todo.getTodoStatus() == TodoStatus.COMPLETE) {
                        baseDto.setReturnWithMessageResponseStats("Dependent Todo Still Incompleted", false);
                        return baseDto;
                    }
                    else {
                        Dependent dependentTo = new Dependent();
                        dependentTo.setDependentTodo(dependent);
                        dependentDao.save(dependentTo);
                        todo.setDependentTodo(dependentTo);
                    }
            }

            this.todoDao.save(todo);

            baseDto.setReturnWithMessageResponseStats("Todo Successfully Updated", true);

        } catch (Exception ex) {
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
        }

        return baseDto;
    }

    public BaseDto deleteTodo(String todoId) {
        BaseDto baseDto = new BaseDto();

        try {

            Todo todo = this.todoDao.findById(UUID.fromString(todoId));

            this.todoDao.delete(todo);

            baseDto.setReturnWithMessageResponseStats("Todo Successfully Deleted", true);

        } catch (Exception ex) {
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
        }

        return baseDto;
    }

    public BaseDto getAllTodoItemsDropDownSource(String todoId,String todoListId) {
        BaseDto baseDto = new BaseDto();
        List<Todo> todoList;

        try {

            if(!todoId.equals("null")){
                 todoList = this.todoDao.findAllByTodoListIdAndDependentTodoIsNullAndIdIsNotAndTodoStatusEquals(UUID.fromString(todoListId),  UUID.fromString(todoId),TodoStatus.INCOMPLETE);
            }
            else{
                todoList = this.todoDao.findAllByTodoListIdAndDependentTodoIsNullAndTodoStatusEquals(UUID.fromString(todoListId),TodoStatus.INCOMPLETE);
            }

            baseDto = new ListDto<>(todoList);
            baseDto.setReturnWithMessageResponseStats("Drop Down Source Items Have Been Gotten", true);

        } catch (Exception ex) {
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
        }

        return baseDto;
    }



}
