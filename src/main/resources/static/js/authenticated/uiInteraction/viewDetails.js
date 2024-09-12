import {findTodoById} from '../homeRequests/home.js';

export const viewDetails = async (id) =>{
    
    try{
        const data = await findTodoById(id);
        
        document.querySelector('#view-title').textContent = `Title : ${data.title}`;
        document.querySelector('#isCompleted').textContent = `${data.done ? 'Yes' : 'No'}`;
        document.querySelector('#view-description').textContent = `${data.shortDescription === '' ? 'No description' : data.shortDescription}`;
        document.querySelector('#view-todo-list').textContent = `${data.listName}`;
        document.querySelector('#view-target-date').textContent = `${data.formattedDate}`;

        document.querySelector('#view-details').style.display = 'flex';
        document.querySelector('#modal-view-background').style.display = 'block';
    }catch(e){
        console.log(e);
    }

}

export const removeDetails = () =>{
    document.querySelector('#view-details').style.display = 'none';
    document.querySelector('#modal-view-background').style.display = 'none';
}