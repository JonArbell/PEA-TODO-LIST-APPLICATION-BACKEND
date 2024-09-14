import { Button } from "../index.js";
import { Home } from "../index.js";

export const addList = async (event) =>{

    event.preventDefault();

    try{

        const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');

        const listName = document.querySelector('#add-list-modal-container > form > input').value;

        console.log('ListName : '+listName);

        const response = await fetch('http://localhost:8080/api/authenticated/list/add',{
            method : 'POST',
            headers : {
                'Content-Type':'application/json'
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

        Button.discardAddList(createNewItemModal);

        await Home.home();

    }catch(e){
        console.error(e);
    }

}

