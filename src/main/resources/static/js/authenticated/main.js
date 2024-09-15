import * as Index from './index.js';

window.Button = Index.Button // Make the scope global for view details and remove view Details
window.ViewDetails = Index.ViewDetails
window.CrudTodoUi = Index.CrudTodoUi

document.addEventListener('DOMContentLoaded', () => { 
    Index.Home.home(); // Load the home request function
    createNewItemHandler(); // Call create new item handler function
    createEditTodoUiHandler(); // Call create new todo handler function
    addEditTodoRequestHandler(); // Call add edit todo handler function
    showProfileModalHandler(); // Call the show profile modal handler function
    logoutHandler(); // Call the logout handler function
    createEditDeleteListUiHandler();
    addListRequestHandler();
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

const createEditTodoUiHandler = () =>{ // This function is for the user click the todo button and edit a todo
    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');
    const createEditTodoButton = document.querySelector('#pick-todo');
    createEditTodoButton.addEventListener('mouseup',Index.Button.createNewTodo);

    const discardCreateEditTodoButton = document.querySelector('#add-edit-todo-discard-button');
    discardCreateEditTodoButton.addEventListener('mouseup',()=>{
        Index.Button.discardCreateEditTodo(createNewItemModal);
    });
}

const createEditDeleteListUiHandler = () =>{
    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');
    const createEditListButton = document.querySelector('#pick-list');
    createEditListButton.addEventListener('mouseup',Index.Button.createNewList);

    const discardAddListButton = document.querySelector('#add-list-modal-container > form > span > p');
    discardAddListButton.addEventListener('mouseup',()=>{
        Index.Button.discardAddList(createNewItemModal);
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

const addEditTodoRequestHandler = () =>{ // This function is for adding and editing a todo and reload home request

    document.querySelector('#create-edit-todo-modal-container > form').addEventListener('submit',Index.Todo.addEditTodo);

}

const addListRequestHandler = () =>{
    document.querySelector('#add-list-modal-container > form').addEventListener('submit',Index.List.addList);
}

const logoutHandler = () =>{
    document.querySelector('#profile-modal > form').addEventListener('submit',async (event) => {
        Index.Home.logout(event);
    });
}