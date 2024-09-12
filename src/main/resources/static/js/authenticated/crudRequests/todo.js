import {discardCreateNewTodo} from '../uiInteraction/buttons.js';

export const addTodo = async (event,home) => {
    event.preventDefault();

    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');

    const title = document.querySelector('#add-edit-todo-title').value;
    const shortDescription = document.querySelector('#add-edit-todo-short-description').value;
    const listName = document.querySelector('#add-edit-todo-list').value;
    const targetDate = document.querySelector('#add-edit-todo-target-date').value;

    console.log('List : '+listName);

    try{
        const url = 'http://localhost:8080/todo/add';
        const response = await fetch(url,{
            method : 'POST',
            headers : {
                'Content-Type':'application/json'
            },
            body : JSON.stringify({
                title : title,
                shortDescription : shortDescription,
                listName : listName,
                date : targetDate
            }),
            credentials : 'include'
        });

        if(!response.ok){
            const error = await response.json();
            throw new Error(error);
        }

        await home();
        discardCreateNewTodo(createNewItemModal);
    }catch(e){
        console.error(e);
    }
}