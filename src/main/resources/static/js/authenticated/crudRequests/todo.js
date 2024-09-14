import {Button} from '../index.js';
import {Home} from '../index.js';

export const addEditTodo = async (event) => {
    event.preventDefault();

    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');
    const typeOfRequest = document.querySelector('#add-edit-title');
    let url = null;
    if(typeOfRequest.textContent === 'Add To-do:'){
        url = 'http://localhost:8080/todo/add';
    }else if(typeOfRequest.textContent === 'Edit To-do:'){
        url = 'http://localhost:8080/todo/edit';
    }

    const todoId = document.querySelector('#todo-id').value;
    const title = document.querySelector('#add-edit-todo-title').value;
    const shortDescription = document.querySelector('#add-edit-todo-short-description').value;
    const listId = document.querySelector('#add-edit-todo-list').value;
    const targetDate = document.querySelector('#add-edit-todo-target-date').value;

    try{
        
        const response = await fetch(url,{
            method : 'POST',
            headers : {
                'Content-Type':'application/json'
            },
            body : JSON.stringify({
                id : todoId,
                title : title,
                shortDescription : shortDescription,
                listId : listId,
                date : targetDate
            }),
            credentials : 'include'
        });

        if(!response.ok){
            const error = await response.json();
            throw new Error(error);
        }

        await Home.home();
        Button.discardCreateEditTodo(createNewItemModal);
    }catch(e){
        console.error(e);
    }
}

export const findTodoById = async (id) =>{
    const url = `http://localhost:8080/find/todo/${id}`;

    try{
        const response = await fetch(url,{
            method : 'GET',
            credentials : 'include'
        });

        if(!response.ok){
            const error = await response.json();
            throw new Error(error.message);
        }

        const data = await response.json();
        return data;
    }catch(e){
        throw e;
    }

}

export const deleteTodo = async (id) =>{

    try{
        const url = `http://localhost:8080/todo/delete/${id}`;

        const response = await fetch(url,{
            method : 'POST',
            credentials : 'include'
        });

        if(!response.ok){
            const error = await response.json();
            throw new Error(error);
        }

        await Home.home();

    }catch(e){
        console.log('Error Delete Todo : '+e);
    }

}

