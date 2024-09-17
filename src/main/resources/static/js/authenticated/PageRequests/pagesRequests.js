import {TodoContainer} from '../index.js';
import {ListContainer} from '../index.js';
import {Profile} from '../index.js';

export const sortByDateModified = async () => {

    const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;
    const path = window.location.pathname;

    let url = `http://localhost:8080/api/authenticated${path}`;

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
            throw new Error(error.message);
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

        const response = await fetch('http://localhost:8080/logout',{
            method : 'POST',
            credentials : 'include',
            headers : {
                'X-XSRF-TOKEN' : csrfToken
            }
        });

        if(!response.ok){
            const error = await response.json();
            throw new Error(JSON.stringify(error,null,2));
        }

        window.location.href = '/';
    }catch(e){
        console.log(e);
    }
}