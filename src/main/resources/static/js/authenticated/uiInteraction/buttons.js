import {Todo} from '../index.js';

export const createNewItem = (event) => {
    const createNewButton = document.querySelector('#create-new-item-button');

    if(createNewButton.contains(event.target)){
        document.querySelector('#create-todo-list-modal-pick').style.display = 'flex';
        document.querySelector('#modal-view-background').style.display = 'block';
    }
}

export const removeCreateNewItemModal = (createNewItemModal) => {
    createNewItemModal.style.display = 'none';
    document.querySelector('#modal-view-background').style.display = 'none';
}

export const createNewTodo = (event) => {

    const createNewtodoButton = document.querySelector('#pick-todo');

    if(createNewtodoButton.contains(event.target)){

        document.querySelector('#create-edit-todo-modal-container').style.display = 'block';
        
    }

}


export const createNewList = (event) =>{

    const createNewListButton = document.querySelector('#pick-list');

    if(createNewListButton.contains(event.target)){
        document.querySelector('#create-edit-delete-list-container').style.display = 'block';
        document.querySelector('#add-list-modal-container').style.display = 'block';
    }

}

const discardAddEditDeleteList = () =>{
    document.querySelector('#create-edit-delete-list-container').style.display ='none';
}

export const discardAddList = (createNewItemModal) =>{

    discardAddEditDeleteList();
    document.querySelector('#add-list-modal-container').style.display ='none';
    removeCreateNewItemModal(createNewItemModal);
    
}

export const discardCreateEditTodo = (createNewItemModal) =>{

    document.querySelector('#create-edit-todo-modal-container').style.display = 'none';
    document.querySelector('#add-edit-title').textContent = 'Add To-do:';
    removeCreateNewItemModal(createNewItemModal);
    
}


