package app.todo.service;

import app.todo.dao.TodoListDao;
import app.todo.dao.UserDao;
import app.todo.dto.base.BaseDto;
import app.todo.dto.base.ListDto;
import app.todo.dto.base.ObjectDto;
import app.todo.model.TodoList;
import app.todo.model.User;
import app.todo.model.request.TodoListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoListService {

    @Autowired
    TodoListDao todoListDao;
    @Autowired
    UserDao userDao;


    public ListDto getAllTodoListsByUserId(String userId, String sortBy, String direction) {
        ListDto listDto;

        try {

            List<TodoList> todoList = this.todoListDao.findAllByUserId(UUID.fromString(userId),Sort.by(Sort.Direction.fromString(direction),sortBy));

            listDto = new ListDto<>(todoList);
            listDto.setReturnWithMessageResponseStats("All Todo List Items Successfully Listed", true);

        } catch (Exception ex) {
            listDto = new ListDto();
            listDto.setReturnWithMessageResponseStats(ex.getMessage(), false);
        }

        return listDto;
    }

    public BaseDto getDetailOfTodoList(String todoListId) {
        BaseDto baseDto;

        try {

            TodoList todo = this.todoListDao.findById(UUID.fromString(todoListId));


            baseDto = new ObjectDto<>(todo);
            baseDto.setReturnWithMessageResponseStats("Detail Of Todo List Has Been Set", true);

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
            baseDto = new BaseDto(ex.getMessage(), false);
        }

        return baseDto;
    }


    public BaseDto createTodoList(TodoListRequest todoListRequest) {
        BaseDto baseDto = new BaseDto();

        try {

            TodoList todoList = new TodoList();
            User user = this.userDao.findById(UUID.fromString(todoListRequest.getUserId()));
            todoList.setUser(user);
            todoList.setName(todoListRequest.getName());
            this.todoListDao.save(todoList);

            baseDto.setReturnWithMessageResponseStats("Todo List Successfully Created", true);

        } catch (Exception ex) {
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
        }

        return baseDto;
    }


    public BaseDto updateTodoList(TodoListRequest todoListRequest) {
        BaseDto baseDto = new BaseDto();

        try {

            TodoList todoList = this.todoListDao.findById(UUID.fromString(todoListRequest.getId()));

            todoList.setName(todoListRequest.getName());
            this.todoListDao.save(todoList);

            baseDto.setReturnWithMessageResponseStats("Todo List Successfully Updated", true);

        } catch (Exception ex) {
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
        }

        return baseDto;
    }

    public BaseDto deleteTodoList(String todoListId) {
        BaseDto baseDto = new BaseDto();

        try {

            TodoList todoList = this.todoListDao.findById(UUID.fromString(todoListId));

            this.todoListDao.delete(todoList);

            baseDto.setReturnWithMessageResponseStats("Todo List Successfully Deleted", true);

        } catch (Exception ex) {
            baseDto.setReturnWithMessageResponseStats(ex.getMessage() ,false);
        }

        return baseDto;
    }

}
