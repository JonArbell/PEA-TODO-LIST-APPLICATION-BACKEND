import {home} from './homeRequests/home.js';
import * as todo from './crudRequests/todo.js';
import * as buttons from './uiInteraction/buttons.js';
import * as view from './uiInteraction/viewDetails.js';

document.addEventListener('DOMContentLoaded',home); // Load the home request

document.addEventListener('DOMContentLoaded', () => { // Functionalities of all buttons for creating, editing, deleting a list and todo
    const createNewButton = document.querySelector('#create-new-item-button');
    const xButton = document.querySelector('#create-todo-list-modal-pick > span > p');
    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');

    if (createNewButton && xButton) {
        createNewButton.addEventListener('mouseup',buttons.createNewItem);
        xButton.addEventListener('mouseup',()=> buttons.removeCreateNewItemModal(createNewItemModal));
    }

    const createNewtodoButton = document.querySelector('#pick-todo');
    createNewtodoButton.addEventListener('mouseup',buttons.createNewTodo);

    const discardCreateNewButton = document.querySelector('#add-edit-todo-discard-button');
    discardCreateNewButton.addEventListener('mouseup',()=>{
        buttons.discardCreateNewTodo(createNewItemModal);
    });

});

document.addEventListener('DOMContentLoaded',()=>{ //Functionalities of View Details
    document.querySelector('.todo-container > h2').addEventListener('mouseup',view.viewDetails);
    document.querySelector('#view-details > span > p').addEventListener('mouseup',view.removeDetails);
});

document.addEventListener('DOMContentLoaded',()=>{ //Functionalities of submiting an add todo
    document.querySelector('#create-edit-todo-modal-container > form').addEventListener('submit',(event)=>{
        todo.addTodo(event,home);
    });

});