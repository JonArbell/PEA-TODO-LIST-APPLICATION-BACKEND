import {TodoContainer} from '../index.js';
import {ListContainer} from '../index.js';
import {Profile} from '../index.js';

export const sortByRequest = async () => {

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;
    const path = window.location.pathname;
    const sort = sessionStorage.getItem('sortBy');

    let url = null;
    const prod = 'https://pea-todo-list-application.onrender.com';
    const dev = 'http://localhost:8080';

    if(sort !== null && sort !== 'd-m'){
        url = `${prod}/api/authenticated${path}/${sort}`;
    }else{
        url = `${prod}/api/authenticated${path}`;
    }

    try{

        const response = await fetch(url, {
            method: 'GET',  
            headers : {
                'X-CSRF-TOKEN': csrfToken 
            },
            credentials: 'include'  
        });

        if(!response.ok){
            const error = await response.json();
            throw error;
        }

        const data = await response.json();
        await TodoContainer.createTodoContainer(data.todoResponse); 
        await ListContainer.createListsContainer(data.listsResponse);
        await Profile.loadProfile(data.userResponse);

    }catch(e){
        console.error(e);
    }

}

export const logout = async (event) =>{

    event.preventDefault();

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;
    
    try{

        const prod = 'https://pea-todo-list-application.onrender.com';
        const dev = 'http://localhost:8080';

        const url = `${prod}/logout`;

        const response = await fetch(url,{
            method : 'POST',
            credentials : 'include',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            }
        });

        if(!response.ok){
            const error = await response.json();
            throw error;
        }

        window.location.href = '/';
    }catch(e){
        console.log(e);
    }
}

