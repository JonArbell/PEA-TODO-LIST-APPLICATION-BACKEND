import {Todo} from '../index.js';

export const deleteTodoUi = async (id) =>{
    await Todo.deleteTodo(id);
}

export const editTodoUi = async (id) =>{

    try{

        const data = await Todo.findTodoById(id);

        document.querySelector('#create-edit-todo-modal-container').style.display = 'block';
        document.querySelector('#add-edit-title').textContent = 'Edit To-do:';
        document.querySelector('#modal-view-background').style.display = 'block';
        document.querySelector('#add-edit-todo-discard-button').textContent = 'Discard Changes';
        document.querySelector('#add-edit-todo-save-button').textContent = 'Save Changes';

        document.querySelector('#todo-id').value = `${data.id}`;
        document.querySelector('#add-edit-todo-title').value = `${data.title}`;
        document.querySelector('#add-edit-todo-short-description').value = `${data.shortDescription}`;
        document.querySelector('#add-edit-todo-list').value = `${data.listId}`;
        document.querySelector('#add-edit-todo-target-date').value = `${data.date}`;

    }catch(e){
        console.error(e);
    }

}

export const todoMarkAsDoneUi = async (id) =>{

    await Todo.todoMarkAsDone(id);

}