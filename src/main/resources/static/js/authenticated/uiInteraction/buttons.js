export function createNewItem(event){

    const createNewButton = document.querySelector('#create-new-item-button');


    if(createNewButton.contains(event.target)){
        document.querySelector('#create-todo-list-modal-pick').style.display = 'flex';
        document.querySelector('#modal-view-background').style.display = 'block';
    }

}

export function removeCreateNewItemModal(createNewItemModal){
    createNewItemModal.style.display = 'none';
    document.querySelector('#modal-view-background').style.display = 'none';
}


export function createNewTodo(event){

    const createNewtodoButton = document.querySelector('#pick-todo');

    if(createNewtodoButton.contains(event.target)){

        document.querySelector('#create-edit-todo-modal-container').style.display = 'block';
        
    }

}

export function discardCreateNewTodo(createNewItemModal){

    document.querySelector('#create-edit-todo-modal-container').style.display = 'none';
    removeCreateNewItemModal(createNewItemModal);
    
}


