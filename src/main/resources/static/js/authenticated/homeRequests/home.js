import {createTodoContainer} from '../uiInteraction/todoContainer.js';
import {createListsContainer} from '../uiInteraction/listsContainer.js';
import {profile} from '../uiInteraction/profile.js';

export const home = async () => {

    try{
        const response = await fetch('http://localhost:8080/home', {
            method: 'GET',  
            credentials: 'include'  
        });

        if(!response.ok){
            const error = await response.json();
            throw new Error(error.message);
        }

        const data = await response.json();
        await createTodoContainer(data.todoResponse); 
        await createListsContainer(data.listsResponse);
        await profile(data.userResponse);

    }catch(e){
        console.error(e);
        // window.location.href = '../../../index.html';
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
