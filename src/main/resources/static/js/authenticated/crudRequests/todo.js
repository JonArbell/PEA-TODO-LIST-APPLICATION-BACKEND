import {Button} from '../index.js';
import {PageRequests} from '../index.js';
import { CrudTodoUi } from '../index.js';
import { PromptMessage } from '../index.js';

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

        if(response.status !== 201 || !response.ok){
            const error = await response.json();
            throw error;
        }

        const message = await response.json();

        await PromptMessage.successMessage(message.message);

        await PageRequests.sortByRequest();

        Button.discardCreateEditTodo();

    }catch(e){

        if(methodType === 'POST' && e){
            console.log('Add error : '+e.addTodoError);
            await PromptMessage.failedMessage(e.addTodoError);
        }else if(methodType === 'PUT' && e){
            console.error('Edit error : '+e.editTodoError);
            await PromptMessage.failedMessage(e.editTodoError);
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
            throw error;
        }

        const message = await response.json();

        await PromptMessage.successMessage(message.message);

        await PageRequests.sortByRequest();

    }catch(e){
        console.log('Error Delete Todo : '+e);
        await PromptMessage.failedMessage(e.deleteTodoError);
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
            throw error;
        }

        const message = await response.json();

        await PromptMessage.successMessage(message.message);

        await PageRequests.sortByRequest();

    }catch(e){
        console.error(e);
        await PromptMessage.failedMessage(e.todoDoneError);
    }

}

export const searchTodoQueryRealtime = async (search) =>{

    if(search === ''){
        return;
    }

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

    const prod = 'https://pea-todo-list-application.onrender.com';
    const dev = 'http://localhost:8080';
    const url = `${prod}/api/authenticated/search-todo?search=${encodeURIComponent(search)}`;

    try{
        
        const response = await fetch(url,{

            method : 'GET',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            },
            credentials : 'include'

        });

        if(!response.ok){
            const error = await response.json();
            throw error;
        }

        const data = await response.json();

        await CrudTodoUi.searchTodoUi(data);
        

    }catch(e){
        console.error(e);
    }

}
