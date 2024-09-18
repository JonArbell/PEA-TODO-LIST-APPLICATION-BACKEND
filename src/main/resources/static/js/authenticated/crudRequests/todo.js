import {Button} from '../index.js';
import {PageRequests} from '../index.js';

export const addEditTodo = async (event) => {
    event.preventDefault();

    const typeOfRequest = document.querySelector('#add-edit-title');

    const prod = 'https://pea-todo-list-application.onrender.com';
    const dev = 'http://localhost:8080';

    let url = null;
    let methodType = null;
    if(typeOfRequest.textContent === 'Add To-do:'){
        url = `${prod}/api/authenticated/todo/add`;
        methodType = 'POST';
    }else if(typeOfRequest.textContent === 'Edit To-do:'){
        url = `${prod}/api/authenticated/todo/edit`;
        methodType = 'PUT';
    }

    const todoId = document.querySelector('#todo-id').value;
    const title = document.querySelector('#add-edit-todo-title').value;
    const shortDescription = document.querySelector('#add-edit-todo-short-description').value;
    const listId = document.querySelector('#add-edit-todo-list').value;
    const targetDate = document.querySelector('#add-edit-todo-target-date').value;

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

    try{

        const response = await fetch(url,{
            method : methodType,
            headers : {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN' : csrfToken
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
            throw error;
        }

        await PageRequests.sortByDateModified();
        Button.discardCreateEditTodo();
    }catch(e){

        if(methodType === 'POST'){
            console.log(e.addTodoError);
        }else if(methodType === 'PUT'){
            console.error(e.editTodoError);
        }else{
            console.error('What error is that ? '+e);
        }

    }
}

export const findTodoById = async (id) =>{

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

    try{

        const prod = `https://pea-todo-list-application.onrender.com`;
        const dev = `http://localhost:8080`;

        const url = `${prod}/api/authenticated/find/todo/${id}`;

        const response = await fetch(url,{
            method : 'GET',
            credentials : 'include',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            }
        });

        if(!response.ok){
            const error = await response.json();
            throw error.findTodoError;
        }

        const data = await response.json();
        return data;
    }catch(e){
        throw e;
    }

}

export const deleteTodo = async (id) =>{

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

    try{

        const prod = 'https://pea-todo-list-application.onrender.com';
        const dev = 'http://localhost:8080';

        const url = `${prod}/api/authenticated/todo/delete/${id}`;

        const response = await fetch(url,{
            method : 'DELETE',
            credentials : 'include',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            }
        });

        if(!response.ok){
            const error = await response.json();
            throw error.deleteTodoError;
        }

        await PageRequests.sortByDateModified();

    }catch(e){
        console.log('Error Delete Todo : '+e);
    }

}

export const todoMarkAsDone = async (id) =>{

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

    try{

        const prod = 'https://pea-todo-list-application.onrender.com';
        const dev = 'http://localhost:8080';

        const url = `${prod}/api/authenticated/todo/${id}/mark-as-done`;

        const response = await fetch(url,{
            method : 'PATCH',
            credentials : 'include',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            }
        });

        if(!response.ok){
            const error = await response.json();
            throw error.todoDoneError;
        }

        await PageRequests.sortByDateModified();

    }catch(e){
        console.error(e);
    }

}