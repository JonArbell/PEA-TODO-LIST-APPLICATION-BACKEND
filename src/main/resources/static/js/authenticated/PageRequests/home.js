import {TodoContainer} from '../index.js';
import {ListContainer} from '../index.js';
import {Profile} from '../index.js';

export const home = async () => {

    try{
        const response = await fetch('http://localhost:8080/api/authenticated/home', {
            method: 'GET',  
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
    try{

        const response = await fetch('http://localhost:8080/api/logout',{
            method : 'POST',
            credentials : 'include'
        });

        if(!response.ok){
            const error = await response.json();
            throw new Error(error);
        }

        console.log('Success logout'+ await response.json());
    }catch(e){
        console.log(e);
    }
}