package app.todo.controller;

import app.todo.dto.base.BaseDto;
import app.todo.dto.base.ListDto;
import app.todo.model.request.TodoListRequest;
import app.todo.model.request.TodoRequest;
import app.todo.service.TodoListService;
import app.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todoList")
public class TodoListController {

    @Autowired
    TodoListService todoListService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    public ListDto index(@RequestParam String userId, @RequestParam String sortBy, @RequestParam String direction) {
        return this.todoListService.getAllTodoListsByUserId(userId,sortBy,direction);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = {"application/json"})
    public BaseDto show(@RequestParam String todoListId) {
        return this.todoListService.getDetailOfTodoList(todoListId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    public BaseDto createTodoList(@RequestBody TodoListRequest todoListRequest) {
        return this.todoListService.createTodoList(todoListRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json"})
    public BaseDto deleteTodoList(@RequestBody TodoListRequest todoListRequest) {
        return this.todoListService.updateTodoList(todoListRequest);
    }
    @RequestMapping(value = "/{todoListId}", method = RequestMethod.DELETE, produces = {"application/json"})
    public BaseDto deleteTodoList(@PathVariable String todoListId) {
        return this.todoListService.deleteTodoList(todoListId);
    }


}