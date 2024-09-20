import * as Index from './index.js';

window.Button = Index.Button; // Create global scope for Buttons
window.ViewDetails = Index.ViewDetails; // Create global scope for view details and remove view Details
window.CrudTodoUi = Index.CrudTodoUi; // Create global scope for Crud todo ui
window.SortBy = Index.SortBy; // Create global scope for sort by

window.Todo = { // Create global scope for search todo real time
    searchTodoQueryRealtime : Index.Todo.searchTodoQueryRealtime
};

document.addEventListener('DOMContentLoaded', () => { 
    Index.PageRequests.sortByRequest(); // Load the home request function
    addEditTodoRequestHandler(); // Call add edit todo handler function
    showProfileModalHandler(); // Call the show profile modal handler function
    logoutHandler(); // Call the logout handler function
    addListRequestHandler(); // Call the add list function handler
    editListNameRequestHandler(); // Call the edit list name function handler
    deleteListRequestHandler(); // Call the delete list function handler
    searchTodoUiHandler(); // Call the edit search todo ui handler function handler
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

    if(window.location.pathname !== '/about-us' && window.location.pathname !== '/contact-us'){
        document.querySelector('#create-edit-todo-modal-container > form').addEventListener('submit',Index.Todo.addEditTodo);
    }
    
}

const addListRequestHandler = () =>{ // This function is for adding a list and reload home request
    document.querySelector('#add-list-modal-container > form').addEventListener('submit',Index.List.addList);
}

const logoutHandler = () =>{ // This function is for logout
    document.querySelector('#profile-modal > form').addEventListener('submit',async (event) => {
        Index.PageRequests.logout(event);
    });
}

const editListNameRequestHandler = () =>{ // This function is for edit a list name and reload home request

    document.querySelector('#edit-list-name-container').addEventListener('submit', async (event) =>{
        Index.List.renameList(event);
    });

}

const deleteListRequestHandler = async () =>{ // This function is for delete list request and reload home request

    document.querySelector('#delete-list-modal-container > form').addEventListener('submit',Index.List.deleteList);

}

const searchTodoUiHandler = () =>{ // This function is for checking if the click is not in search result modal

    const searchModalContainer = document.querySelector('#search-result-modal');

    document.addEventListener('click',(event)=>{

        if(!searchModalContainer.contains(event.target)){
            searchModalContainer.style.display = 'none';
        }

    });    

}