import { Button } from "../index.js";
import { PageRequests } from "../index.js";
import { PromptMessage } from '../index.js';

export const addList = async (event) =>{

    event.preventDefault();

    const listName = document.querySelector('#add-list-modal-container > form > input').value;

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

    try{

        const prod = 'https://pea-todo-list-application.onrender.com';
        const dev = 'http://localhost:8080';

        const url = `${prod}/api/authenticated/list/add`;

        const response = await fetch(url,{
            method : 'POST',
            headers : {
                'Content-Type':'application/json',
                'X-XSRF-TOKEN' : csrfToken
            },
            body : JSON.stringify({listName}),
            credentials : 'include'
        });

        console.log('Status : '+response.status);

        if(response.status !== 201){

            const error = await response.json();
            throw error;

        }

        const message = await response.json();

        await PromptMessage.successMessage(message.message);

        Button.discardAddList();

        await PageRequests.sortByRequest();

    }catch(e){
        await PromptMessage.failedMessage(e.addListError);
    }

}

export const renameList = async (event) =>{

    event.preventDefault();

    const listId = document.querySelector('#edit-list-name-container > form > select').value;
    const listName = document.querySelector('#edit-list-name-container > form >input').value;
    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;
    try{

        const prod = 'https://pea-todo-list-application.onrender.com';
        const dev = 'http://localhost:8080';
        const url = `${prod}/api/authenticated/list/edit`;

        const response = await fetch(url,{

            method : 'PATCH',
            headers : {
                'Content-Type':'application/json',
                'X-XSRF-TOKEN' : csrfToken
            },
            body : JSON.stringify({
                id : listId,
                listName : listName
            }),
            credentials : 'include'
        });

        if(!response.ok){
            const error = await response.json();
            throw error;
        }

        Button.discardEditListName();

        const message = await response.json();

        await PromptMessage.successMessage(message.message);

        await PageRequests.sortByRequest();

    }catch(e){
        await PromptMessage.failedMessage(e.editListNameError);
    }

}

export const deleteList = async (event) =>{

    event.preventDefault();

    const listId = document.querySelector('#delete-select-list-container > select').value;
    const deleteTasks = document.querySelector('#delete-select-list-container > section > input').checked;
    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;
    try{

        const prod = `https://pea-todo-list-application.onrender.com`;
        const dev = `http://localhost:8080`;
        const url = `${prod}/api/authenticated/list/delete/${listId}`;
        
        const response = await fetch(url,{
            method : 'DELETE',
            headers : {
                'Content-Type':'application/json',
                'X-XSRF-TOKEN' : csrfToken
            },
            body : JSON.stringify(deleteTasks),
            credentials : 'include'
        });

        if(!response.ok){
            const error = await response.json();
            throw error;
        }

        const message = await response.json();

        await PromptMessage.successMessage(message.message);

        Button.discardDeleteList();
        await PageRequests.sortByRequest();

    }catch(e){
        await PromptMessage.failedMessage(e.deleteListError);
    }

}