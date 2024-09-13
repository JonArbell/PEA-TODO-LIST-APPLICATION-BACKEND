import * as Index from './index.js';

window.Button = Index.Button // Make the scope global for view details and remove view Details
window.ViewDetails = Index.ViewDetails
window.CrudTodoUi = Index.CrudTodoUi

document.addEventListener('DOMContentLoaded', () => { 
    Index.Home.home(); // Load the home request function
    createNewItemHandler(); // Call create new item handler function
    createNewTodoHandler(); // Call create new todo handler function
    addTodoHandler(); // Call add todo handler function
    showProfileModalHandler(); // Call the show profile modal handler function
    logoutHandler(); // Call the logout handler function
});


const showProfileModalHandler = () =>{ // This function is for showing and hide a profile modal

    let isClicked = false;

    document.addEventListener('mouseup',(event)=>{

        const profile = document.querySelector('#image-account-icon');
        const modalContainer = document.querySelector('#profile-modal');

        if(isClicked && !modalContainer.contains(event.target)){
            Index.Profile.hideProfileModal();
            isClicked = false;
            return;
        }

        if(profile.contains(event.target)){
            Index.Profile.showProfileModal();
            isClicked = true;
        }

    });

}

const createNewTodoHandler = () =>{ // This function is for the user click the todo button
    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');
    const createNewtodoButton = document.querySelector('#pick-todo');
    createNewtodoButton.addEventListener('mouseup',Index.Button.createNewTodo);

    const discardCreateNewButton = document.querySelector('#add-edit-todo-discard-button');
    discardCreateNewButton.addEventListener('mouseup',()=>{
        Index.Button.discardCreateNewTodo(createNewItemModal);
    });
}

const createNewItemHandler = () =>{ // This function is for the user click the create new button
    const createNewButton = document.querySelector('#create-new-item-button');
    const xButton = document.querySelector('#create-todo-list-modal-pick > span > p');
    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');

    if (createNewButton && xButton) {
        createNewButton.addEventListener('mouseup',Index.Button.createNewItem);
        xButton.addEventListener('mouseup',()=> Index.Button.removeCreateNewItemModal(createNewItemModal));
    }
}

const addTodoHandler = () =>{ // This function is for adding a todo and reload home request
    document.querySelector('#create-edit-todo-modal-container > form').addEventListener('submit',Index.Todo.addTodo);
}

const logoutHandler = () =>{
    document.querySelector('#profile-modal > form').addEventListener('submit',Index.Home.logout);
}