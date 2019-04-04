package app.todo.controller;
import app.todo.dto.base.BaseDto;
import app.todo.model.request.TodoRequest;
import app.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    public BaseDto index(@RequestParam String todoListId, @RequestParam String sortBy, @RequestParam String direction,@RequestParam String name, @RequestParam String todoStatus) {
        return this.todoService.getAllTodosByTodoListId(todoListId,sortBy,direction,name,todoStatus);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = {"application/json"})
    public BaseDto show(@RequestParam String todoId) {
        return this.todoService.getDetailOfTodo(todoId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    public BaseDto createTodo(@RequestBody TodoRequest todoRequest) {
        return this.todoService.createTodo(todoRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json"})
    public BaseDto updateTodo(@RequestBody TodoRequest todoRequest) throws Exception {
        return this.todoService.updateTodo(todoRequest);
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE, produces = {"application/json"})
    public BaseDto deleteTodo(@PathVariable String todoId) {
        return this.todoService.deleteTodo(todoId);
    }

    @RequestMapping(value = "/todoItems", method = RequestMethod.GET, produces = {"application/json"})
    public BaseDto getAllTodoItemsDropDownSource(@RequestParam String todoId, @RequestParam String todoListId) {
        return this.todoService.getAllTodoItemsDropDownSource(todoId,todoListId);
    }

}
