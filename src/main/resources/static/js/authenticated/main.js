import * as Index from './index.js';

window.Button = Index.Button // Make the scope global for view details and remove view Details
window.ViewDetails = Index.ViewDetails
window.CrudTodoUi = Index.CrudTodoUi

document.addEventListener('DOMContentLoaded', () => { 
    Index.PageRequests.sortByDateModified(); // Load the home request function
    addEditTodoRequestHandler(); // Call add edit todo handler function
    showProfileModalHandler(); // Call the show profile modal handler function
    logoutHandler(); // Call the logout handler function
    addListRequestHandler(); // Call the add list function handler
    editListNameRequestHandler(); // Call the edit list name function handler
    deleteListRequestHandler();
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

const addEditTodoRequestHandler = () =>{ // This function is for adding and editing a todo and reload home request

    document.querySelector('#create-edit-todo-modal-container > form').addEventListener('submit',Index.Todo.addEditTodo);

}

const addListRequestHandler = () =>{ // This function is for adding a list and reload home request
    document.querySelector('#add-list-modal-container > form').addEventListener('submit',Index.List.addList);
}

const logoutHandler = () =>{ // This function is for logout
    document.querySelector('#profile-modal > form').addEventListener('submit',async (event) => {
        Index.Home.logout(event);
    });
}


const editListNameRequestHandler = () =>{ // This function is for edit a list name and reload home request

    document.querySelector('#edit-list-name-container').addEventListener('submit', async (event) =>{
        Index.List.renameList(event);
    });

}

const deleteListRequestHandler = async () =>{

    document.querySelector('#delete-list-modal-container > form').addEventListener('submit',Index.List.deleteList);

}