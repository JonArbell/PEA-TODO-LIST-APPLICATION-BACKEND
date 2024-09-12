import {home} from './homeRequests/home.js';
import * as todo from './crudRequests/todo.js';
import * as buttons from './uiInteraction/buttons.js';
import * as view from './uiInteraction/viewDetails.js';

document.addEventListener('DOMContentLoaded', () => { 
    home(); // Load the home request
    createNewItemHandler(); // Call create new item handler
    createNewTodoHandler(); // Call create new todo handler
    viewDetailsHandler(); // Call view details handler
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


const viewDetailsHandler = () =>{ // This function is for viewing details of specific todo
    document.querySelector('.todo-container > h2').addEventListener('mouseup',view.viewDetails);
    document.querySelector('#view-details > span > p').addEventListener('mouseup',view.removeDetails);
}

const addTodoHandler = () =>{ // This function is for adding a todo and reload home request
    document.querySelector('#create-edit-todo-modal-container > form').addEventListener('submit',(event)=>{
        todo.addTodo(event,home);
    });
}
