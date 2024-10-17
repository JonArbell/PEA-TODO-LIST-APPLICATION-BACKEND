import {Menu} from '../index.js';
export const createNewItem = (event) => {
    const createNewButton = document.querySelector('#create-new-item-button');

    if(createNewButton.contains(event.target)){
        document.querySelector('#create-todo-list-modal-pick').style.display = 'flex';
        document.querySelector('#modal-view-background').style.display = 'block';
    }
}

export const removeCreateNewItemModal = () => {
    document.querySelector('#create-todo-list-modal-pick').style.display = 'none';
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
        document.querySelector('#delete-list-modal-container').style.display = 'none';
    }

}

const discardAddEditDeleteList = () =>{
    document.querySelector('#create-edit-delete-list-container').style.display ='none';
    document.querySelector('#add-list-modal-container').style.display = 'none';
    document.querySelector('#delete-list-modal-container').style.display = 'none';
}

export const discardAddList = () =>{

    discardAddEditDeleteList();
    document.querySelector('#add-list-modal-container > form').reset();
    document.querySelector('#add-list-modal-container').style.display ='none';
    removeCreateNewItemModal();
    
}

export const discardCreateEditTodo = () =>{

    document.querySelector('#create-edit-todo-modal-container').style.display = 'none';
    document.querySelector('#add-edit-title').textContent = 'Add To-do:';
    document.querySelector('#create-edit-todo-modal-container > form').reset();
    removeCreateNewItemModal();
    
}

export const manageList = () => {
    const check = document.querySelector('#check');
    if(window.innerWidth < 1024){
        check.checked = false;
        check.dispatchEvent(new Event('change'));
    }
    document.querySelector('#edit-delete-list-modal-pick').style.display = 'flex';

    document.querySelector('#modal-view-background').style.display = 'block';
}

export const removeManageListModal = () =>{
    document.querySelector('#edit-delete-list-modal-pick').style.display = 'none';
    document.querySelector('#modal-view-background').style.display = 'none';
}

export const editListName = () =>{
    document.querySelector('#edit-list-name-container').style.display = 'flex';
}

export const deleteList = () =>{
    document.querySelector('#create-edit-delete-list-container').style.display = 'block';
    document.querySelector('#delete-list-modal-container').style.display = 'block';
    document.querySelector('#delete-select-list-container').style.display = 'flex';
    document.querySelector('#add-list-modal-container').style.display = 'none';
}

export const confirmDeleteList = () =>{

    const select = document.querySelector('#delete-select-list-container > select');

    if(select.value != '0'){
        document.querySelector('#delete-select-list-container').style.display = 'none';
        document.querySelector('#delete-list-submit-confirmation').style.display = 'flex';
        return;
    }

    alert('No list can be deleted.');

}

export const discardDeleteList = () =>{
    document.querySelector('#delete-list-modal-container > form').reset();
    document.querySelector('#delete-list-submit-confirmation').style.display = 'none';
    document.querySelector('#create-edit-delete-list-container').style.display = 'none';
    removeManageListModal();
}

export const discardEditListName = () =>{
    document.querySelector('#edit-list-name-container > form').reset();
    document.querySelector('#edit-list-name-container').style.display = 'none';
    removeManageListModal();
}
