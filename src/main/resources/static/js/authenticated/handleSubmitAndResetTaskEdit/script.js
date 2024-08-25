//Functions for submit and reset task edit,add
function handleSubmit(){

    const typeOfSubmit = document.querySelectorAll('.add-edit-title');
    const formTask = document.querySelector('#add-edit-task-container form');
    const formList = document.querySelector('#add-edit-list-container form');

    typeOfSubmit.forEach(type =>{
        if(type.textContent === 'Add To-do:'){
            formTask.action = '/pea/todo/add';
        }else if(type.textContent === 'Edit To-do:'){
            formTask.action = '/pea/todo/edit';
            
        }else if(type.textContent === 'Add List:'){
            formList.action = '/pea/list/add';
        }else if(type.textContent === 'Edit List:'){
            formList.action = '/pea/list/edit';
        }
    });

    

}

function handleEditTodoItem(id,title,sD,tD,listsId){

    console.log('ID : '+id);
    console.log('Title : '+title);
    console.log('Short description : '+sD);
    console.log('Target Date : '+tD);
    console.log('List Id : '+listsId);

    document.querySelectorAll('.add-edit-title')[0].textContent = 'Edit To-do:';
    document.querySelector('#date-and-time-progress-display-container').style.display = 'none';
    document.querySelector('#add-edit-task-container').style.display = 'flex';
    document.querySelector(`#add-edit-task-submit`).value ="Save Changes";
    document.querySelector(`#add-edit-task-reset`).value ="Discard Changes";

    resetAllInformation();

    const todoId = document.querySelector('#todo-id');
    todoId.value = `${id}`;

    const todoTitle = document.querySelector('#add-task-title');
    todoTitle.value = `${title}`;

    const shortDescription = document.querySelector('#title-description-container textarea');
    shortDescription.value = sD === null ? '' : `${sD}`;
    
    const todoDate = document.querySelector('#select-date-container input');
    todoDate.value = `${tD}`;

    const list = document.querySelector('#select-list-container select');

    if(listsId === null){
        list.value = "0";
    }else{
        list.value = listsId;
    }

}

function resetAllInformation(){
    document.querySelector('#add-edit-task-container form')
    .reset();

}

function handleDiscard(){
    closeCreateNewEditItemContainer();
}

function removeDim(){
    document.querySelector('#dim-panis').style.display = 'none';
}

function closeCreateNewEditItemContainer(){
    document.querySelector('#add-edit-task-container').style.display = 'none';

    removeDim();

    document.querySelector('#date-and-time-progress-display-container').style.display = 'flex';
    document.querySelector('#add-edit-list-container').style.display = 'none';
}


