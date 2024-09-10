import {discardCreateNewTodo} from '../uiInteraction/buttons.js';
import {home} from '../homeRequests/home.js';

export function addTodo(event){

    event.preventDefault();

    const createNewItemModal = document.querySelector('#create-todo-list-modal-pick');

    const title = document.querySelector('#add-edit-todo-title').value;
    const shortDescription = document.querySelector('#add-edit-todo-short-description').value;
    const list = document.querySelector('#add-edit-todo-list').value;
    const targetDate = document.querySelector('#add-edit-todo-target-date').value;

    discardCreateNewTodo(createNewItemModal);
    fetch('http://localhost:8080/todo/add',{
        method : 'POST',
        headers : {
            'Content-Type':'application/json'
        },
        body : JSON.stringify({
            title : title,
            shortDescription : shortDescription,
            list : list,
            date : targetDate
        }),
        credentials : 'include'
    })
    .then(response => {

        if(!response.ok){
            throw new Error(response);
        }

        return response.json();
    })
    .then(data => {
        console.log('Data : ',data);
        
    })
    .catch(error => {
        console.error('Error : ',error);
    });

}