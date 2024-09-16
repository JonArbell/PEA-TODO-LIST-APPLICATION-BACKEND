import { Button } from "../index.js";
import { Home } from "../index.js";

export const addList = async (event) =>{

    event.preventDefault();

    const listName = document.querySelector('#add-list-modal-container > form > input').value;

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

    try{

        const response = await fetch('http://localhost:8080/api/authenticated/list/add',{
            method : 'POST',
            headers : {
                'Content-Type':'application/json',
                'X-XSRF-TOKEN' : csrfToken
            },
            body : JSON.stringify(listName),
            credentials : 'include'
        });

        if(!response.ok){

            const error = await response.json();
            throw error;

        }

        const message = await response.json();

        console.log(message.message);

        Button.discardAddList();

        await Home.home();

    }catch(e){
        console.error(e);
    }

}

export const renameList = async (event) =>{

    event.preventDefault();

    const listId = document.querySelector('#edit-list-name-container > form > select').value;
    const listName = document.querySelector('#edit-list-name-container > form >input').value;
    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;
    try{

        const response = await fetch('http://localhost:8080/api/authenticated/list/edit',{

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

        await Home.home();

    }catch(e){
        console.error(e);
    }

}