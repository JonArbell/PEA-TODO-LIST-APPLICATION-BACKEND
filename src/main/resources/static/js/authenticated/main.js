import {home} from './homeRequests/home.js';
import * as todo from './crudRequests/todo.js';
import * as buttons from './uiInteraction/buttons.js';
import * as view from './uiInteraction/viewDetails.js';

window.view = view; // Make the scope global for view details and remove view Details

document.addEventListener('DOMContentLoaded', () => { 
    home(); // Load the home request
    createNewItemHandler(); // Call create new item handler
    createNewTodoHandler(); // Call create new todo handler
    addTodoHandler(); // Call add todo handler
});

const createNewTodoHandler = () =>{ // This function is for the user click the todo button
    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');
    const createNewtodoButton = document.querySelector('#pick-todo');
    createNewtodoButton.addEventListener('mouseup',buttons.createNewTodo);

    const discardCreateNewButton = document.querySelector('#add-edit-todo-discard-button');
    discardCreateNewButton.addEventListener('mouseup',()=>{
        buttons.discardCreateNewTodo(createNewItemModal);
    });
}

const createNewItemHandler = () =>{ // This function is for the user click the create new button
    const createNewButton = document.querySelector('#create-new-item-button');
    const xButton = document.querySelector('#create-todo-list-modal-pick > span > p');
    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');

    if (createNewButton && xButton) {
        createNewButton.addEventListener('mouseup',buttons.createNewItem);
        xButton.addEventListener('mouseup',()=> buttons.removeCreateNewItemModal(createNewItemModal));
    }
}

const addTodoHandler = () =>{ // This function is for adding a todo and reload home request
    document.querySelector('#create-edit-todo-modal-container > form').addEventListener('submit',(event)=>{
        todo.addTodo(event,home);
    });
}
